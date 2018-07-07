package VCFprocesses;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe responsável por guardar e identificar as colunas de anotações deixadas no VCF pelo SNPeff (CampoANN)
 * @author eliseumedeiros
 */
public class SnpANN {
    private boolean executeSNP;
    private boolean containsANN;
    private String[] columns;
    private HashMap<String, ArrayList<String>> columnRows;
    private ArrayList<String> headerVCF;
    
    public boolean getExecuteSNP(){
        return this.executeSNP;
    }
    public boolean getContainsANN(){
       return this.containsANN;
    }
    public String[] getColumns(){
        return this.columns;
    }
    public HashMap<String, ArrayList<String>> getColumnRows(){
        return this.columnRows;
    }
    public void setExecuteSNP(boolean executeSNP){
        this.executeSNP = executeSNP;
    }
    public ArrayList<String> headerVCF(){
        return this.headerVCF;
    }
    public SnpANN(){
        columnRows = new HashMap<>();
    }
    public SnpANN(ArrayList headerVCF){
        this.executeSNP = false;
        containsANN = false;
        columnRows = new HashMap<>();
        this.headerVCF = headerVCF;
        separateColumnsANN();
    }
    
    public void separaValoresDaLinha(String linha){
        String[] valores = linha.split("\\|");
        ArrayList<String> stringCol;
        for(int i=0;i<columns.length; i++){
            if(!columnRows.containsKey(columns[i])){
                this.columnRows.put(columns[i], new ArrayList<String>());
            }
            if(i>=valores.length || (i<valores.length && valores.length > columns.length)){
                this.columnRows.get(columns[i]).add("");
            }else{
                this.columnRows.get(columns[i]).add(valores[i]);
            }
       }
    }
    public void  separaValoresCorpoVCF(ArrayList<String> camposVCF){
        for(String aux : camposVCF){
            separaValoresDaLinha(aux);
        }
    }
    
    /**
      * Separar as colunas dos valores anotados após rodar o SNPEff -> Preencher as novas colunas dos valores ANN que serão adicionadas a tabela do VCF
      */
     public void separateColumnsANN(){
         separateColumnsANN(this.headerVCF);
     }
     public void separateColumnsANN(ArrayList<String> comentarioBruto){
         String currentLine;
         String[] currentLineSep;
         for(int i=0; i< comentarioBruto.size(); i++){
             currentLine = comentarioBruto.get(i).replaceAll("#", "");
             currentLineSep = currentLine.split("=");
             if(currentLineSep[0].contains("INFO")){
                 if(currentLineSep[2].contains("ANN")){
                     containsANN = true;
                     String aux = currentLineSep[5].replace("\"Functional annotations: ", "");
                     aux = aux.replaceAll("['\"<> ]", "");
                     columns = aux.split("\\|"); //Novas Colunas do campo ANN, separadas para serem os índices das novas colunas
                 }
             }           
         }
     }
}
