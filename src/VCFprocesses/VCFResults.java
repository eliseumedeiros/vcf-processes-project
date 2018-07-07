package VCFprocesses;

import Table.ModelTable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *Classe responsável por guardar os resultados do arquivo VCF.
 * @author eliseumedeiros
 */
public class VCFResults {
    private final static int NUM_COL = 2;
    private final static int NUM_LIN_TABGERAL= 5;
    private final static int NUM_LIN_TABQUAL = 3;
    //--------
    private final static int COL_CHROM = 0;
    private final static int COL_POS = 1;    
    private final static int COL_ID = 2;
    private final static int COL_REF = 3;
    private final static int COL_ALT = 4;
    private final static int COL_QUAL = 5;
    private final static int COL_FILTER = 6;
    
    private int totalVariacoes; 
    private ArrayList<String> filtrosPresentes;
    private int totalAmostras;
    //-------- GERAL
    private ModelTable tabelaGeralResults;
    //-------- QUAL
    private ModelTable tabelaQUALResults;
    private double mediaDeQual;
    private double minimoQUAL;
    private double maximoQUAL;
    //-------- CHROM
    private ModelTable tabelaCHROMResults;
    private ArrayList<String> cromoPresentes;
    private HashMap<String,Integer> cromoOcorrencias;
    
    public VCFResults(){
        totalVariacoes = 0;
        mediaDeQual = 0;
        totalAmostras = 0;
        maximoQUAL =0;
        minimoQUAL = -1;
        this.tabelaGeralResults = new ModelTable();
        this.tabelaCHROMResults = new ModelTable();
        //---- QUAL
        this.tabelaQUALResults = new ModelTable();
        this.cromoOcorrencias = new HashMap<>(); 
        this.cromoPresentes = new ArrayList<>();
        this.filtrosPresentes = new ArrayList<>();
        
    }
    
    public void setTotalVariacoes(int novoTotal){
        this.totalVariacoes = novoTotal;
    }
    public void setMediaDeQual(double novaMedia){
        this.mediaDeQual = novaMedia;
    }
    public void setTotalAmostras(int novoTotal){
        this.totalAmostras = novoTotal;
    }
    public ModelTable getTabelaGeralResults(){
        return this.tabelaGeralResults;
    }
    public ModelTable getTabelaCHROMResults(){
        return this.tabelaCHROMResults;
    }
    public ModelTable getTabelaQUALResults(){
        return this.tabelaQUALResults;
    }
    /**
     * atualiza todos os valores através do conteúdo da tabela mandada.
     * @param tabela 
     */
    public void atualizarResultados(ModelTable tabela){
        //------- Resetando valores
        totalVariacoes = 0;
        mediaDeQual = 0;
        minimoQUAL = -1;
        this.cromoPresentes.clear();
        this.filtrosPresentes.clear();
        //-----------------
        setTotalVariacoes(tabela.getRowCount()); //total de variações.
        //QUAL - Valor Mínimo Inicial
        int cont=0;
        while(minimoQUAL==-1 && cont<tabela.getRowCount()){
                String auxMin = (String)tabela.getValueAt(cont, COL_QUAL); //colocando valor mínimo inicial
                if(!auxMin.equals("."))
                    minimoQUAL = Double.parseDouble(auxMin);
            cont++;
        }
        
                
        double soma=0;
        double div=0;
        for(int lin=0; lin< tabela.getRowCount(); lin++){
            //QUALIDADE
            String auxQual = (String) tabela.getValueAt(lin, COL_QUAL);
            if(!auxQual.equals(".")) {
                double auxValor = Double.parseDouble(auxQual);
                soma+= auxValor;
                div++;
                if(auxValor>maximoQUAL){//Se é o valor máximo
                    maximoQUAL = auxValor;
                }
                if(auxValor<minimoQUAL){//Se é valor mínimo
                    minimoQUAL = auxValor;
                }
            } 
            
            //CHROM
            String auxCHROM = (String) tabela.getValueAt(lin,COL_CHROM);
            if(addTiposdeCromossomo(auxCHROM)){ //adiciona o elemento. Se já existe a ocorrência desse elemento na tabela, então
                this.cromoOcorrencias.put(auxCHROM, cromoOcorrencias.get(auxCHROM)+1); //adiciona o valor dela + 1
            }
            
            //Tipos de Filtro
            addTiposdeFiltro((String)tabela.getValueAt(lin, COL_FILTER));
        }
        setMediaDeQual(soma/div);
        preencherTabelaGeralResults();
        preencherTabelaCHROMResults();
        preencherTabelaQUALResults();
    }
     
    
    /**
     * guarda o nome do cromossomo, caso não tenha. Retorna verdadeiro, caso o elemento já esteja no array, para somar mais elementos
     * @param cromo
     */
    public boolean addTiposdeCromossomo(String cromo){
        if(!cromoPresentes.contains(cromo)){
            this.cromoOcorrencias.put(cromo, 1);//coloca o primeiro elemento na hash de elementos
            cromoPresentes.add(cromo);
            return false;
        }
        return true;
    }
    /**
     * guarda o nome do filtro, caso não tenha no Array.
     * @param filt
     */
    public void addTiposdeFiltro(String filt){
        if(!filtrosPresentes.contains(filt)){
            filtrosPresentes.add(filt);
        }
    }
    private void preencherTabelaGeralResults(){
        String[] colunas =new String[2];
        colunas[0] = "result";
        colunas[1] = "Value";
        Object[][] matriz = new Object[NUM_LIN_TABGERAL][NUM_COL];
        matriz[0][0] = "Total Variations";
        matriz[0][1] = totalVariacoes;
        
        matriz[1][0] = "Average Quality";
        matriz[1][1] = mediaDeQual;
        
        matriz[2][0] = "Chromosome Types";
        matriz[2][1] = cromoPresentes.toString();
        
        matriz[3][0] = "Filter Types";
        matriz[3][1] = filtrosPresentes.toString();
        
        matriz[4][0] = "Total Samples";
        matriz[4][1] = this.totalAmostras +"";
        
        tabelaGeralResults.setColunas(colunas);
        tabelaGeralResults.setMatrizTable(matriz);
        
    }
    private void preencherTabelaCHROMResults(){
        String[] colunas =new String[2];
        colunas[0] = "CHROM";
        colunas[1] = "Occurrence";
        Object[][] matriz = new Object[cromoPresentes.size()][NUM_COL];
        int lin=0;
        for(String cromo : cromoPresentes){
            matriz[lin][0] =  cromo; //nome do cromo
            matriz[lin][1] = cromoOcorrencias.get(cromo); //quantidade de vezes que ele aparece
            lin++;
        }
            
        tabelaCHROMResults.setColunas(colunas);
        tabelaCHROMResults.setMatrizTable(matriz);
   }
   public void preencherTabelaQUALResults(){
        String[] colunas =new String[2];
        colunas[0] = "QUAL";
        colunas[1] = "Value";
        Object[][] matriz = new Object[NUM_LIN_TABQUAL][NUM_COL];
        matriz[0][0] = "average quality";
        matriz[0][1] = mediaDeQual;
        
        matriz[1][0] = "minimum quality";
        matriz[1][1] = minimoQUAL;
        
        matriz[2][0] = "maximum quality";
        matriz[2][1] = maximoQUAL;
        
        tabelaQUALResults.setColunas(colunas);
        tabelaQUALResults.setMatrizTable(matriz);
   }
   
}
