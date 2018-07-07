package VCFprocesses;

import java.util.ArrayList;

/**
 * Classe que guarda e identifica todas as informações descritas no cabeçalho do arquivo VCF
 * @author eliseumedeiros
 */
public class VCFHeader {
    private ArrayList<String> fullComment;
    public VCFHeader(ArrayList<String> comentario){
        this.fullComment = comentario;
    }
     public VCFHeader(){
        this.fullComment = new ArrayList<>();
    }
     public void addRow(String lin){
         this.fullComment.add(lin);
     }
     public ArrayList<String> getFullComment(){
         return fullComment;
     }
     
     
}
