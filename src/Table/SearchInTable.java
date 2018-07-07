package Table;

import VCFprocesses.TriAnalyses.GTFile;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 * Realizar buscas na tabela, mantendo apenas os elementos na tabela principal que possuem o índice pesquisado.
 * @author EliseuMedeiros
 */
public class SearchInTable {
    private final static int COL_CHROM = 0;
    private final static int COL_POS = 1;    
    private final static int COL_ID = 2;
    private final static int COL_REF = 3;
    private final static int COL_ALT = 4;
    private final static int COL_QUAL = 5;
    private final static int COL_FILTER = 6;

    private ModelTable seachTable; //tabela de elementos buscados
    private final DefaultListModel listaSoughtItems; //elementos buscados
    private final DefaultListModel listaAutoComplete; //lista de complemento de palavra (auto-complete)
    private final ArrayList<String> listElementosBuscados;
    public SearchInTable(){
        this.seachTable = new ModelTable();
        this.listaSoughtItems = new DefaultListModel();
        this.listaAutoComplete = new DefaultListModel();
        this.listElementosBuscados = new ArrayList<>();
    }
    public ModelTable getSeachTable(){
        return seachTable;
    }
    public void setSeachTable(ModelTable newTable){
        this.seachTable = newTable;
    }
    public DefaultListModel getListaModels(){
        return this.listaAutoComplete;
    }
    public void setListaModels(ArrayList<String> newLista){
        for(String newlis:newLista){
            this.listaAutoComplete.addElement(newlis);
        }
    }
    public DefaultListModel getListaSoughtItems(){
        return this.listaSoughtItems;
    }
    public void setListaSoughtItems(ArrayList<String> newLista){
        for(String newlis:newLista){
            this.listaSoughtItems.addElement(newlis);
        }
    }
    
    /**
     * remover eleemento da lista de elementos buscados, de acordo com a coluna (int) e descrição dadas.
     * @param coluna
     * @param descricao 
     */
    public void removeListaSoughtItems(int coluna, String descricao){
        String elementoLista = null;
        switch (coluna) {
            case COL_CHROM:
                elementoLista = "CHROM"+":   "+descricao;
                break;
            case COL_POS:
                elementoLista = "POS"+":   "+descricao;
                break;
            case COL_ID:
                elementoLista = "ID"+":   "+descricao;
                break;
            case COL_REF:
                elementoLista ="REF"+":   "+descricao;
                break;
            case COL_ALT:
                elementoLista ="ALT"+":   "+descricao;
                break;
            case COL_QUAL:
                elementoLista = "min QUAL"+":   "+descricao;
                break;
            case COL_FILTER:
                elementoLista = "FILTER"+":   "+descricao;
        }
        listElementosBuscados.remove(elementoLista);
        listaSoughtItems.removeElement(elementoLista);
    }
    /**
     * Reover elemento da lista de elementos pesquisados.
     * @param coluna
     * @param descricao 
     */
    public void removeListaSoughtItems(String coluna, String descricao){
        String elemento = coluna + ":   "+ descricao;

        listElementosBuscados.remove(elemento);
        listaSoughtItems.removeElement(elemento);
    }
  
    /** 
     * Esvazia a lista de models - Para adicionar as descrições no Jlist. 
     */
    public void clearListaModels(){
        listaAutoComplete.clear();
    }
    public void clearListElementosBuscados(){
        listElementosBuscados.clear();
        listaSoughtItems.clear();
    }
    /**
     * Adicionar o elemento procurado na lista de elementos buscados e na Jlist de buscas.
     * @param elemento
     * @param coluna 
     */
    public void addElementoBuscado(String elemento, int coluna){
        String elementoLista = null;
            switch (coluna) {
            case COL_CHROM:
                elementoLista = "CHROM"+":   "+elemento;
                break;
            case COL_POS:
                elementoLista = "POS"+":   "+elemento;
                break;
            case COL_ID:
                elementoLista = "ID"+":   "+elemento;
                break;
            case COL_REF:
                elementoLista ="REF"+":   "+elemento;
                break;
            case COL_ALT:
                elementoLista ="ALT"+":   "+elemento;
                break;
            case COL_QUAL:
                elementoLista = "min QUAL"+":   "+elemento;
                break;
            case COL_FILTER:
                elementoLista = "FILTER"+":   "+elemento;
        }
        this.listaSoughtItems.addElement(elementoLista);
        this.listElementosBuscados.add(elementoLista);
    }
     public void addElementoBuscado(GTFile gtfile){
        String elementoLista = "Father: " + gtfile.getFather() + " Mother: " + gtfile.getMother() + " Child: " + gtfile.getChild();
        this.listaSoughtItems.addElement(elementoLista);
        this.listElementosBuscados.add(elementoLista);
     }
    
    /**
     * atualizar a tabela peincipal, de acordo com os parâmetros de pesquisa.
     * @param tableBase 
     */
    public void atualizarTabelaBuscas(ModelTable tableBase){
        Object[] auxArray = listaSoughtItems.toArray();
        this.seachTable.setMatrizTable(tableBase.getMatrizTable());
     //
        clearListElementosBuscados(); //limpando elementos buscados
        
        for(Object lis : auxArray){
            String listElement = (String)lis;
            String[] colAndDescricao= listElement.split(":");
            String coluna = colAndDescricao[0];
            String descricao = colAndDescricao[1];
            int intColuna = -1;
            switch (coluna) {
                case "CHROM":
                    intColuna = COL_CHROM;
                    break;
                case "POS":
                    intColuna = COL_POS;
                    break;
                case "ID":
                    intColuna = COL_ID;
                    break;
                case "REF":
                    intColuna = COL_REF;
                    break;
                case "ALT":
                    intColuna = COL_ALT;
                    break;
                case "min QUAL":
                    intColuna = COL_QUAL;
                    break;
                case "FILTER":
                    intColuna = COL_FILTER;
            }
            if(intColuna>=0)
                buscaElementoEGeraMatriz(descricao.substring(3), seachTable, intColuna );
        }
        
    }
    /**
     * realizar buscas na tabela e mostas os nomes para auto-complete.
     * @param prefixo
     * @param table
     * @param colForSeach
     * @return 
     */
    public ArrayList<String> getDescricoesComInicio(String prefixo, ModelTable table, int colForSeach){
        ArrayList<String> descricoes = new ArrayList<>();
        String prefixoMin = prefixo.toLowerCase(); //para não ter diferença entre minúsculas e maiúsculas
        if (!prefixo.isEmpty()){
            for(int lin=0; lin< table.getRowCount(); lin++){
                String linAtual = (String) table.getMatrizTable()[lin][colForSeach];
                if (linAtual.toLowerCase().startsWith(prefixoMin)){
                    if(!descricoes.contains((String)table.getMatrizTable()[lin][colForSeach]))
                        descricoes.add((String)table.getMatrizTable()[lin][colForSeach]);
                }
            }
        }
        return descricoes;
    }
    /**
     * Gera a Matriz que possue a descricao procurada.
     * @param descricao
     * @param tabelaPrincipal
     * @param colParaBusca 
     */
    public void buscaElementoEGeraMatriz(String descricao,ModelTable tabelaPrincipal, int colParaBusca)throws NumberFormatException{
        ModelTable newTabela = new ModelTable();
        ArrayList<String[]> auxNewTabela = new ArrayList<>();
        String[] auxForNewTable;//auxiliar que quarda a linha do elemento encontrado
        Object[][] newMatriz = null; //a nova matriz formada, que possue, ou começa, com o elemento procurado.
        for(int lin=0; lin< tabelaPrincipal.getRowCount(); lin++){
                String linAtual = (String) tabelaPrincipal.getMatrizTable()[lin][colParaBusca];
                //se a coluna de busca for igual a min QUAL
                if(colParaBusca == COL_QUAL && Double.parseDouble(descricao)<= Double.parseDouble(linAtual)){
                    auxForNewTable = new String[tabelaPrincipal.getColumnCount()];
                    //passa os termos da matriz,(object[][]) da tabela principal, para o vetor String[]
                    for(int col=0; col< tabelaPrincipal.getColumnCount(); col++){
                        auxForNewTable[col] = (String)tabelaPrincipal.getMatrizTable()[lin][col];
                    }
                    //adiciona o vetor que possue os elementos da linha da tabelaPrincipal que possue a descrição procurada
                    auxNewTabela.add(auxForNewTable);
                }
                else if (linAtual.toLowerCase().equals(descricao.toLowerCase()) || linAtual.toLowerCase().startsWith(descricao.toLowerCase())){
                    auxForNewTable = new String[tabelaPrincipal.getColumnCount()];
                    //passa os termos da matriz,(object[][]) da tabela principal, para o vetor String[]
                    for(int col=0; col< tabelaPrincipal.getColumnCount(); col++){
                        auxForNewTable[col] = (String)tabelaPrincipal.getMatrizTable()[lin][col];
                    }
                    //adiciona o vetor que possue os elementos da linha da tabelaPrincipal que possue a descrição procurada
                    auxNewTabela.add(auxForNewTable);
                }
        }
        if(!auxNewTabela.isEmpty()){
            newMatriz = new Object[auxNewTabela.size()][tabelaPrincipal.getColumnCount()];
            //transforma um ArrayList<String[]> numa matriz Object[][]
            for(int lin=0; lin<auxNewTabela.size();lin++){
                for(int col=0; col<tabelaPrincipal.getColumnCount();col++){
                     newMatriz[lin][col] = auxNewTabela.get(lin)[col];
                }
            }
        }
            this.seachTable.setMatrizTable(newMatriz); //colocando a nova matriz, na seachTable
            this.seachTable.setColunas(tabelaPrincipal.getColunas());

            addElementoBuscado(descricao, colParaBusca); //guardar o elemento que foi buscado.
                  
        
    }
    int HETEROZIGOTO = 1, HOMOZIGOTO=2, HOMOZIGOTOREF=0;//para diferenciar o homozigoto igual ao referência "0/0"
    public void buscaTrioAnalyses(GTFile gtfile, ModelTable tabelaPrincipal){
        ModelTable newTabela = new ModelTable();
        ArrayList<String[]> auxNewTabela = new ArrayList<>();
        String[] auxForNewTable;//auxiliar que quarda a linha do elemento encontrado
        Object[][] newMatriz = null; //a nova matriz formada, que possue, ou começa, com o elemento procurado.
        for(int lin=0; lin< tabelaPrincipal.getRowCount(); lin++){
                String linFather = (String) tabelaPrincipal.getMatrizTable()[lin][gtfile.getNumFather()]; //valor GT do pai
                String linMother = (String) tabelaPrincipal.getMatrizTable()[lin][gtfile.getNumMother()]; //valor GT da mãe
                String linChild = (String) tabelaPrincipal.getMatrizTable()[lin][gtfile.getNumChild()]; //valor GT do filho

                //se a coluna de busca for igual a min QUAL
               if (isHeterozygotus(linFather)==HETEROZIGOTO && isHeterozygotus(linMother)==HETEROZIGOTO){//Se o pai e mãe são heterozigotos e o filho homozigoto. Coloca na tabela
                   if(isHeterozygotus(linChild)==HOMOZIGOTO || isHeterozygotus(linChild)==HOMOZIGOTOREF && gtfile.getConsiderRef()) {
                        auxForNewTable = new String[tabelaPrincipal.getColumnCount()];
                         //passa os termos da matriz,(object[][]) da tabela principal, para o vetor String[]
                         for(int col=0; col< tabelaPrincipal.getColumnCount(); col++){
                             auxForNewTable[col] = (String)tabelaPrincipal.getMatrizTable()[lin][col];
                         }
                         //adiciona o vetor que possue os elementos da linha da tabelaPrincipal que possue a descrição procurada
                         auxNewTabela.add(auxForNewTable);
                   }
                }
        }
        if(!auxNewTabela.isEmpty()){
            newMatriz = new Object[auxNewTabela.size()][tabelaPrincipal.getColumnCount()];
            //transforma um ArrayList<String[]> numa matriz Object[][]
            for(int lin=0; lin<auxNewTabela.size();lin++){
                for(int col=0; col<tabelaPrincipal.getColumnCount();col++){
                     newMatriz[lin][col] = auxNewTabela.get(lin)[col];
                }
            }
        }

            this.seachTable.setMatrizTable(newMatriz); //colocando a nova matriz, na seachTable
            this.seachTable.setColunas(tabelaPrincipal.getColunas());

            addElementoBuscado(gtfile); //guardar o elemento que foi buscado.
    }
    /**
     * função auxiliar para verificar se o genotype é heterozigoto (retorna true) ou homozigoto (retorna false).
     * @param value
     * @return 
     */
    private int isHeterozygotus(String value){
        String[] values = value.split("|/");
        System.out.println(values[0]);
        if(values[0].equals(values[2])){ //SE É HOMOZIGOTO. Trando os casos onde o filho tem os alelos iguais ao referência o 0/0
            if(values[0].equals("0"))
                return HOMOZIGOTOREF;
            else
                return HOMOZIGOTO;
        }
        else //É HETEROZIGOTO
            return HETEROZIGOTO;
    }
}
