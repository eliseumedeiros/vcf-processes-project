package Table;

import javax.swing.table.AbstractTableModel;

/**
 * É uma classe que tratará todos os dados referentes de tabela.
 * possue linhas(ArrayList) e colunas(String[])
 * @author eliseumedeiros
 * @version 23/10/2015
 */
public class ModelTable extends AbstractTableModel{
    private String[] colunas = null;
    private Object[][] matrizTable;
    

    public ModelTable(Object[][] matriz, String[] colunas){
        this.matrizTable = matriz;
        setColunas(colunas);
    }
    public ModelTable(){
        colunas=null;
        matrizTable=null;
    }

    /**
     * Retornar vetor de colunas
     * @return colunas
     */
    public String[] getColunas(){
        return colunas;
    }
    /**
     * Setar vetor de colunas
     * @param nomes 
     */
    public void setColunas(String[] nomes){
        colunas = new String[nomes.length];
        for(int i=0; i< nomes.length; i++){
            colunas[i] = nomes[i];
        }
    }
    /**
     * retorna a matriz de elementos da tabelaPrincipal.
     * @return matrizTable
     */
    public Object[][] getMatrizTable(){
        return matrizTable;
    }
    /**
     * seta a matriz de elementos da tabelaPrincipal.
     * @param newMatriz
     */
    public void setMatrizTable(Object[][] newMatriz){
        this.matrizTable = newMatriz;
    }
    /**
     * retornar número de colunas.
     * @return colunas.length
     */
    @Override
    public int getColumnCount(){
        return colunas.length;
    }
    /**
     * retornar número de linhas.
     * @return matrizTable.size()
     */
    @Override
    public int getRowCount(){
        if(this.matrizTable != null)
            return this.matrizTable.length;
        else
            return 0;
    }
    /**
     * retornar nome da coluna.
     * @param numCol
     * @return  colunas[numCol]
     */
    @Override
    public String getColumnName(int numCol){
        return colunas[numCol];
    }
    /**
     * Retornar elemento da tabela.
     * @param numLin
     * @param numCol
     * @return 
     */
    @Override
    public Object  getValueAt(int numLin, int numCol){
        return matrizTable[numLin][numCol];
    }
    @Override
     public boolean isCellEditable(int row, int column) {  
        return false;  
    }  
    /**
     * para remover determinada coluna da tabela
     * @param numCol 
     */
    public void romeveColumnNumber(int numCol){
        String[] aux = null;
        for(int i=0, j=0; i<colunas.length; i++){
            if(i!=numCol){
                aux[j] = colunas[i];
                j++;
            }
        }
        colunas = aux;
    }
}
