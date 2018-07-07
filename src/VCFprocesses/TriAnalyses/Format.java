/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VCFprocesses.TriAnalyses;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *Classe responsável por guardar toda a divisão realizada nos campos format
 * @author eliseumedeiros
 */
public class Format {
    private final HashMap<String, ArrayList<String>> hashNewColumns ; //Chave: GT ----->  Fechadura: GT_N001, GT_N002
    private final HashMap<Integer, HashMap<String, ArrayList<String>>> hashLinOfhashCol; //caompos format de cada linha da tabela
    public Format(){
        this.hashNewColumns = new HashMap<>();
        this.hashLinOfhashCol = new HashMap<>();
    }
    /**
     * retorna os índices existente no arquivo VCF do campo FORMAT
     * Chave: GT ----->  Fechadura: GT_N001, GT_N002
     * @return 
     */
    public HashMap<String, ArrayList<String>> getHashNewColums(){
        return this.hashNewColumns;
    }
    /**
     * Campo Format de cada linha do arquivo VCF
     * @return 
     */
    public HashMap<Integer, HashMap<String, ArrayList<String>>> getHashLinOfhashCol(){
        return this.hashLinOfhashCol;
    }
    /**
     * copiar todos os elementos do hashmap dos campos FORMAT
     * @param hashNewColumns 
     */
    public void setHashNewColums(HashMap<String, ArrayList<String>> hashNewColumns){
        this.hashNewColumns.putAll(hashNewColumns); 
    }
    /**
     * copiar todas as linhas dos elementos do campo FORMAT do arquivo VCF lido
     * @param hashLinOfhashCol 
     */
    public void setHashLinOfhashCol(HashMap<Integer, HashMap<String, ArrayList<String>>> hashLinOfhashCol){
        this.hashLinOfhashCol.putAll(hashLinOfhashCol);
    }
    
    public void addHashNewColums(String key, ArrayList<String> lock){
        this.hashNewColumns.put(key, lock);
    }
    
    public void addHashLinOfhashCol (int key, HashMap<String, ArrayList<String>> lock){
        this.hashLinOfhashCol.put(key, lock);
    }
    
}
