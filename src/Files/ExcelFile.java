package Files;

import Table.ModelTable;
import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Escrever os arquivos da tabela no formato "Excel".
 * @author eliseumedeiros
 */
public class ExcelFile {
    /**
     * Escrever o arquivo excel. Recebendo o caminhoArq (local/caminhoArq) e informações do arquivo.
     * @param caminhoArq
     * @param colunmnsForAdd
     * @param tabela
     * @throws IOException
     * @throws WriteException 
     */
    public void writeExcel(String caminhoArq, boolean[] colunmnsForAdd, ModelTable tabela)throws IOException, WriteException{
        WritableWorkbook workbook = Workbook.createWorkbook(new File(caminhoArq+".xls"));
        
        WritableSheet sheet = workbook.createSheet("VCF-Processes", 0);
        int contNotVisible; //conta quantas colunas foram setadas para não aparecer, para ser usado na hora de guardálas no arquivo excel
        //------------- Salvano tabela
        for(int lin=0; lin<tabela.getRowCount(); lin++){
            contNotVisible=0;
            for(int col=0; col<tabela.getColumnCount(); col++){
                if(colunmnsForAdd[col]){//se a coluna deve ser salva
                    if(lin==0){//adiciona a linha que possue o cabeçalho das colunas
                        Label label = new Label(col - contNotVisible, 0, tabela.getColumnName(col)); 
                        sheet.addCell(label);
                    }
                    //adicona as linhas da matriz, caso ela tenha sido marcada na caixa.
                    Label label = new Label(col - contNotVisible, lin+1, (String) tabela.getMatrizTable()[lin][col]); 
                    sheet.addCell(label); 
                }
                else{
                    contNotVisible++;
                }
                    
            }
        }
            workbook.write(); 
            workbook.close();
    }
}
