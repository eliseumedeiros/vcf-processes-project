package VCFprocesses;

import Files.ReadFile;
import Files.WriteFile;
import Table.ModelTable;
import VCFprocesses.TriAnalyses.Format;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *  Separa as informações do arquivoBruto VCF que serão utilizadas na tabelaPrincipal.
 * @author eliseumedeiros
 * @version 23/05/2018
 */
public final class VCFArquive {
    private final static int COL_REF = 3;
    private final static int COL_ALT = 4;
    private final static int COL_INFO = 7;
    private final static int COL_FORMAT = 8;
    private final static int COL_SAMPLE = 9;
    
    
    private final ArrayList<String> arquivoBruto;
    //arquivo que guarda todas as informações da tabelaPrincipal
    private final ModelTable tabelaPrincipal;
    private final ReadFile arquivoLido; //abre o arquivoBruto e retorna-o num ArrayList
    private boolean[] showColum; //irá dizer quais colunas serão visualizadas no JPanel
    private String endereco; 
    private String rowColumnsVCF;
    
    //---------- array de format
    ArrayList<String> geralColumnsFormat; //um array que guarda o campo geral (GT, GQ..) das colunas format. 
    //-----------
    private int fimInfo_colunm; //variável que demarca o final do campo info, e o início do campo format. (Com as novas colunas integradas)
    
    //--------- Campo FORMAT
    Format format;
    //--------- Amostras
    ArrayList<String> samples;
    //--------- Guarda Cabeçalho e identifica suas particularidades
    VCFHeader vcfHeader;
    //--------- arquivo que manipula o SnpANN
    SnpANN snpANN;
    /**
     * Contrstutor da classe de leitura do arquivo VCF.
     * @param endereco
     * @throws java.io.IOException
     */
    public VCFArquive(String endereco, boolean executeSNP) throws IOException{
        fimInfo_colunm = 0;
        this.endereco = endereco;
        arquivoLido = new ReadFile(endereco);
        showColum = null;
        geralColumnsFormat = new ArrayList<>();
        //o documento VCF é carregado num ArrayList e guardado em "arquivoBruto"
        this.arquivoBruto = arquivoLido.getArquivo();
        this.tabelaPrincipal = new ModelTable();
        //FOMAT - para todos os campos format
        this.format = new Format();
        this.samples = new ArrayList<>();
        this.vcfHeader = new VCFHeader();
        separateArquiveColumn(); //separa as colunas e identifica as colunas existentes na anotação do SNPEff (se existir)
        this.snpANN = new SnpANN(vcfHeader.getFullComment());
        this.snpANN.setExecuteSNP(executeSNP);
        separaArquiveLin();
    }
    
    /**
     * método que retorna o local do vetor de colunas, as novas colunas do campo INFO. Para saver a transião do campo INFO para FORMAT.
     * @return 
     */
    public int getFimInfo_column(){
        return fimInfo_colunm;
    }
    /**
     * Separa as colunas do arquivo e coloca na ModelTable. 
     */
    public void separateArquiveColumn(){
        int indice=0; //índice do arquivoBruto
        String row = arquivoBruto.get(indice);
        char caracter;
        boolean encontrouCol = false;
            while (!encontrouCol) {// lê da primeira linha até a ultima
                   caracter = row.charAt(0);//pega o primeiro caractere da string
                   if(caracter == '#'){//compara se é o índice do documento
                       caracter = row.charAt(1);
                       if(caracter == '#'){//vê se esta linha é uma String de comentário
                            vcfHeader.addRow(row);
                            indice++;
                            row = arquivoBruto.get(indice); // chama a próxima linha
                       }
                       else{ //separando o cabeçalho (colunas
                           this.rowColumnsVCF = row;
                           row = row.substring(1); //elimina o '#' da linha 
                           encontrouCol = true;
                       }
                   }
            }
            ArrayList<String> linArray = new ArrayList<>();
            String[] lin = row.split("\t");
            linArray.addAll(Arrays.asList(lin));
           
            
            tabelaPrincipal.setColunas(lin);
    }

    /**
     * Separa as linhas do arquivo lido dividindo os campos da coluna ALT, e coloca no vetor de linhas.
     */
    public void separaArquiveLin(){
        ArrayList<String> linhas=new ArrayList(); //índice do arquivoBruto
        for(String lin : arquivoBruto){
            if(lin.charAt(0) != '#'){ //compara se é o índice do documento
                String[] colunas = lin.split("\t");
                String[] alteracao = colunas[COL_ALT].split(",");
                String referencia = colunas[COL_REF];
                if(alteracao.length>1){
                     for(String alt : alteracao){
                         String newLine = colunas[0];
                         for(int i=1; i< colunas.length; i++){
                             if(i==COL_ALT)
                                 newLine += "\t"+alt;
                             else
                                 newLine += "\t"+colunas[i];
                         }
                         linhas.add(newLine); //adicona a nova linha separada de alteração
                     }
                 }
                else
                    linhas.add(lin);
            }  
        }
        if(this.snpANN.getExecuteSNP()){
             //Salvar novo VCF sem repetilções de alterações
            ArrayList<String> auxNewVCF = new ArrayList<>();
            auxNewVCF.addAll(vcfHeader.getFullComment());
            auxNewVCF.add(rowColumnsVCF);
            auxNewVCF.addAll(linhas);
            WriteFile writeFile = new WriteFile();
            writeFile.writeFileVCF(auxNewVCF, endereco);
        }
        preencheMatrizTable(linhas);
    }
    
    /**
     * Preenche a matriz com suas respectivas linhas e colunas.
     * @param linhas 
     */
    private void preencheMatrizTable(ArrayList<String> linhas){
        
        String[] vectorAuxColumn; //recebe todas as colunas de cada linha.
        List<String[]> listCol = new ArrayList();
        //A matrizTable transforma o Vetor de linhas, numa tabelaPrincipal bidimensional.
        for(String lin : linhas){
            //vector auxiliar para receber todas as colunas de cada linha.
            vectorAuxColumn = lin.split("\t");
            listCol.add(vectorAuxColumn);
        }
        if(tabelaPrincipal.getColumnCount()>COL_FORMAT){
            for(int i=COL_SAMPLE; i<tabelaPrincipal.getColumnCount(); i++){
                samples.add(tabelaPrincipal.getColumnName(i));
            }
            readFormat(tabelaPrincipal.getColunas(), listCol);
            }
        else if(tabelaPrincipal.getColumnCount()> COL_INFO){
            tabelaPrincipal.setColunas(readInfo(tabelaPrincipal.getColunas(), listCol));
        }
        Object[][] auxTable= new Object[listCol.size()][listCol.get(0).length];
       for(int i=0; i<listCol.size();i++){
            System.arraycopy(listCol.get(i), 0, auxTable[i], 0, listCol.get(0).length);
       }
        tabelaPrincipal.setMatrizTable(auxTable);
        

    }
    /**
     * retorna a tabelaPrincipal com as informações lidas do arquivo. (classe ModelTable)
     * @return tabelaPrincipal
     */
    public ModelTable getTabela(){
        return tabelaPrincipal;
    }
    
    /**
     * retorna o vetor usado para visualização das colunas na tabelaPrincipal (jtabelaPrincipal).
     * @return 
     */
    public boolean[] getShowCol(){
        return showColum;
    }
    /**
     * retorna o array de campos gerais do  campo Format do arquivo vcf.
     * @return 
     */
    public ArrayList<String> getGeralColumFormat(){
        return geralColumnsFormat;
    }
    /**
     * retorna todos os campos FORMAT identificados no arquivo VCF.
     * Separados da forma hash(chve, fechadura) tipo: hash(GT, (GT_N001, GT_N002...)) e hash de cada linha do arquivo
     * @return 
     */
    public Format getFormat(){
        return this.format;
    }
    /**
     * retorna os normes das amostras presentes no arquivo VCF.
     * @return 
     */
    public ArrayList<String> getSamples(){
        return this.samples;
    }
    /**
     * Setar colunas que serão visíveis na tabelaPrincipal
     * @param visible 
     */
    public void setShowCol(boolean[] visible){
        showColum = visible;
    }
    /**
     * tornar todas as colunas da tabelaPrincipal em visíveis, no vetor showCol, para a jTablePrincipal
     */
    public void setAllShowColVisible(){
        //Coloca todas as colunas da tabelaPrincipal, visível no JPanel
            for(int i=0; i<showColum.length; i++){
                showColum[i]=true;
            }
    }
    /**
     * tornar todas as colunas da tabelaPrincipal em invisíveis, no vetor showCol, para a jTablePrincipal
     */
    public void setAllShowColInvisible(){
        for(int i=0; i<showColum.length; i++){
                showColum[i]=false;
           }
    }
    /**
     * setar a coluna respectiva para invisível, no vetor showCol.
     * @param numCol 
     */
    public void setColInvisible(int numCol){
        showColum[numCol] = false;
    }
    /**
     * setar a coluna respectiva para visível, no vetor showCol.
     * @param numCol 
     */
    public void setColVisile(int numCol){
        showColum[numCol] = true;
    }
    /**
     * Separar o campo format em colunas
     * @param colunas
     * @param columnsOfEachRow
    */
    private void readFormat(String[] colunas,  List<String[]> columnsOfEachRow){
        for(int lin=0; lin < columnsOfEachRow.size(); lin++){ //laço que percorre as linhas do arquivo VCF
            String[] camposFormat = columnsOfEachRow.get(lin)[8].split(":"); //campo Format da linha respectiva
            HashMap<String, ArrayList<String>> hashNewlines = new HashMap<>();
            for (int colF=0; colF < camposFormat.length; colF++) {//laço que percorre as colunas GT, GQ.. do campo Format
                for(int am=9; am < colunas.length; am++){ //laço que separa o cabeçalho das novas colunas. (Amostras. ex:NA0001...
                    String[] linhaAtual = columnsOfEachRow.get(lin)[am].split(":"); //separa os valores respectivos do format, de cada amostra
                    String novaColuna;
                    novaColuna = camposFormat[colF] + "_" + colunas[am];
                    if(!format.getHashNewColums().containsKey(camposFormat[colF])){// se não contem o campo format (GT, GQ...)
                        geralColumnsFormat.add(camposFormat[colF]);// adiciona o novo campo (GT, GQ) para ser usado nas caixas de marcação 
                        format.addHashNewColums(camposFormat[colF], new ArrayList<String>());
                    }
                    if(!hashNewlines.containsKey(novaColuna)){ //confere se na linha atual, existe a nova coluna construída
                        hashNewlines.put(novaColuna, new ArrayList<String>());
                    }
                    if(format.getHashNewColums().get(camposFormat[colF]).size()< colunas.length -9){//certifica-se de que na próxima linha possa ter um campo format a mais
                        format.getHashNewColums().get(camposFormat[colF]).add(novaColuna);          // só poderá ter no máximo, a quantidade de amostras por coluna: GT_NA001 - GT_NA00n
                    }
                    if(linhaAtual.length > colF){
                        hashNewlines.get(novaColuna).add(linhaAtual[colF]); //colocando valores da linha respectiva (ex: <GT, <"1|1", "0|1"...>> ) 
                    }
                    else {
                        hashNewlines.get(novaColuna).add(null);
                    }
                }
            }
            format.addHashLinOfhashCol(lin, hashNewlines);
        }
        //--------- LER CAMPO INFO ------------
        colunas = readInfo(colunas, columnsOfEachRow);
        int col_Inf = colunas.length;
        //----------- Adionar no array de colunas, as novas colunas -------------
        ArrayList<String> auxNewColumns = new ArrayList<>();
        Set<String> chaves = format.getHashNewColums().keySet();
        Iterator<String> it = chaves.iterator();
        String key;
        auxNewColumns.addAll(Arrays.asList(colunas));
        while (it.hasNext()){
             key = it.next();
             auxNewColumns.addAll(format.getHashNewColums().get(key));
        }
        colunas = new String[auxNewColumns.size()];
        tabelaPrincipal.setColunas(auxNewColumns.toArray(colunas));
        showColum = new boolean[colunas.length];//atualizar o tamanho do visualizador de colunas
        setAllShowColVisible();
        //----------------- Adicionar no array de linhas, as novas colunas das linhas
        List<String[]> auxColumnsOfEachRow = new ArrayList<>();
        ArrayList<String> auxNewLines = new ArrayList<>();
       
        
        for(int i=0; i< columnsOfEachRow.size(); i++){ //percorre as linhas
            auxNewLines.addAll(Arrays.asList(columnsOfEachRow.get(i))); //adicionar as colunas
            for(int col=col_Inf; col< colunas.length; col++){
                if(format.getHashLinOfhashCol().get(i).containsKey(colunas[col])){ //verifica se a linha contem o campo da coluna.
                     auxNewLines.addAll(format.getHashLinOfhashCol().get(i).get(colunas[col]));
                }
                else{//se esta linha não contem o campo, preenche com null
                    auxNewLines.add(null);
                }
            }
            
            auxColumnsOfEachRow.add(auxNewLines.toArray(new String[auxNewLines.size()]));
            auxNewLines.clear();
        }
        columnsOfEachRow.clear();
        columnsOfEachRow.addAll(auxColumnsOfEachRow);
        auxColumnsOfEachRow.clear();

}

    
    /** 
     * Separa o campo Info, atribuindo ao HashMap campoInfo, as informações 
 correspondentes do arquivoBruto VCF.
     * @param colunas
     * @param columnsOfEachRow 
     */
    private String[] readInfo(String[] colunas, List<String[]> columnsOfEachRow){
        ArrayList<String> newColumns = new ArrayList<>(); //novas colunas
        ArrayList <HashMap<String, ArrayList<String>>> linOfhashCol = new ArrayList<>(); //array de hash, que percorre cada linha, pegando os valores respectivos. Nem todas as linhas possuem o mesmo campo de INFO
        for(int lin=0; lin < columnsOfEachRow.size(); lin++){ //laço que percorre as linhas do arquivo VCF
            String[] camposInfo = columnsOfEachRow.get(lin)[COL_INFO].split(";"); //campo Info da linha respectiva
            HashMap<String, ArrayList<String>> hashNewlines = new HashMap<>();//linhas da nova coluna
            for (String camposInfo1 : camposInfo) {
                //laço que percorre as colunas NS, DP.. do campo Info
                String[] auxNome_Valor = camposInfo1.split("="); //separa o nome e o valor repectivo. ex. "NS=3" => "NS" e "3"
                String nome, valor;
                if(auxNome_Valor.length==2){ //compara se o campo possue o valor de chave e fechadura => ex. nome:"NS", valor: "02"
                    nome = auxNome_Valor[0];
                    valor = auxNome_Valor[1];
                }
                else{//caso o campo não tenha mais de um parâmetro, o seu valor será true, para indicar que na nova coluna, o local com true, possue aquela referência
                    nome = auxNome_Valor[0]; //coloca o nome da culuna.
                    valor ="true"; //refere-se ao caso de ter somente uma coluna. coloca-se o nome true para dizer que o valor dessa coluna é verdadeiro.
                }
                if(nome.equals("ANN")){
                    snpANN.separaValoresDaLinha(valor);
                }
                else{
                    if(!hashNewlines.containsKey(nome)){// se não contem o campo nome (NS, AA...) do campo Info respectivo
                        if(!newColumns.contains(nome)){
                            newColumns.add(nome); //adiciona uma nova coluna, no array de novas colunas
                        }
                        hashNewlines.put(nome, new ArrayList<String>());//cria um capo para colocar os valores respectivos
                    }
                    hashNewlines.get(nome).add(valor); //adiciona o valor com o nome respectivo
                }
            }
            linOfhashCol.add(hashNewlines);
        }
        //adicionar informações do campo ANN se existirem
        if(snpANN.getContainsANN()){
            newColumns.addAll(Arrays.asList(snpANN.getColumns()));
        }
        //----------- Adionar no array de colunas, as novas colunas -------------
        ArrayList<String> auxNewColumns = new ArrayList<>();

        for(int i=0; i<COL_INFO; i++){
            auxNewColumns.add(colunas[i]);
        }
        //LUGAR PARA VERIFICAR EXISTÊNCIA DE CAMPO ANN
        auxNewColumns.addAll(newColumns);//adicionando todas as novas colunas, no vetor de colunas principal
        newColumns.clear();
        colunas = new String[auxNewColumns.size()];
        colunas = auxNewColumns.toArray(colunas);
        //----------------- Adicionar no array de linhas, as novas colunas das linhas------------------------------- 
        List<String[]> auxColumnsOfEachRow = new ArrayList<>();
        ArrayList<String> auxNewLines = new ArrayList<>();
       
        for(int i=0; i< columnsOfEachRow.size(); i++){// laço que percorre todas as linhas
            for(int j=0; j< COL_INFO; j++){ //laço para adicionar as colunas até o campo INFO
                auxNewLines.add(columnsOfEachRow.get(i)[j]);
            }
            for(int col=COL_INFO; col< colunas.length; col++){
                if(linOfhashCol.get(i).containsKey(colunas[col])){ //verifica se a linha contem o campo da coluna.
                     auxNewLines.addAll(linOfhashCol.get(i).get(colunas[col]));
                } else if(snpANN.getColumnRows().containsKey(colunas[col])){
                    auxNewLines.add(snpANN.getColumnRows().get(colunas[col]).get(i));
                }
                else{//se esta linha não contem o campo, preenche com null
                    auxNewLines.add(null);
                }
            }
            
            auxColumnsOfEachRow.add(auxNewLines.toArray(new String[auxNewLines.size()]));
            auxNewLines.clear();
        }
       
        columnsOfEachRow.clear();
        columnsOfEachRow.addAll(auxColumnsOfEachRow);
        auxColumnsOfEachRow.clear();
        //colocar o final do campo Info na variável para saber a transição do campo Info para os campos Format
        fimInfo_colunm = colunas.length - 1;
        //criar o vetor de visualização de colunas de acordo com seu respectivo tamanho
           showColum = new boolean[colunas.length];
           setAllShowColVisible();
        return colunas;
    }
}
