package Files;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Escreve um arquivo txt, com as informações de chamada
 *
 * @author eliseumedeiros
 */
public class WriteFile extends ExcelFile{
    private PrintWriter gravarArq;
    FileWriter arq;
   public WriteFile(){
       this.arq = null;
       this.gravarArq = null;
      
    }
   /**
    * Escreve o arquivo de forma tabular, separado por "\t", no formato ".txt"
    * @param guardaArq
    * @param nome 
    */
   public void writeFileTxt(ArrayList<String> guardaArq, String nome)
   {
        arq = null;
        try {
            arq = new FileWriter(nome+".csv");
        } catch (IOException ex) {
        }
        gravarArq = new PrintWriter(arq);
       
        gravarArq.println("sep=\t");
        for (String texto: guardaArq) {
          gravarArq.println(texto);
        }

        try {
            arq.close();

        } catch (IOException ex) {
            Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
   } 
   
    /**
    * Escreve o arquivo de forma tabular, separado por "\t", no formato ".txt"
    * @param guardaArq
    * @param nome 
    */
   public void writeFileVCF(ArrayList<String> guardaArq, String nome)
   {
        arq = null;
        try {
            arq = new FileWriter(nome.replace(".vcf", "")+"SEP.vcf");
        } catch (IOException ex) {
        }
        gravarArq = new PrintWriter(arq);
       
        for (String texto: guardaArq) {
          gravarArq.println(texto);
        }

        try {
            arq.close();

        } catch (IOException ex) {
            Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
   } 
}
