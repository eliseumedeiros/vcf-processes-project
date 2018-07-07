package Files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ler o arquivo VCF e separa os campos para utilização
 * @author eliseumedeiros
 * @version 18.09.2015
 */
public class ReadFile {
    BufferedReader lerArq;
    //Array que guarda todo o campo de amostras. (sem a parte que começa com "#")
    ArrayList<String> guardaArq; 
    String linha;
    public ReadFile(String name) throws IOException{
        //se o nome for nulo recebe o nome defalt.
        if(name==null){
            name = "exemplos/VCF.vcf";
        }
        readArquive(name);
        
    }
    
    public void readArquive(String name) throws FileNotFoundException, IOException{

            FileReader arq;
            arq = new FileReader(name);
            lerArq = new BufferedReader(arq);
            guardaArq = new ArrayList<>();
            /* A variável "linha" recebe o valor "null" quando o processo
               de repetição atingir o final do arquivo texto */
            linha = lerArq.readLine();
            //verifica se o arquivo começa com #(comentário) para determinar se o arquivo é VCF.
            if(linha.charAt(0) == '#') 
               while ( linha != null) {// lê da primeira linha até a ultima
                   guardaArq.add(linha);
                   linha = lerArq.readLine();
                }
            else{
                throw new IOException();
            }

    }
    
    /**
     * Retornar o ArrayList que contém todo o arquivo para a tabela.
     * @return 
     */
    public ArrayList<String> getArquivo(){
        return guardaArq;
    }
}
