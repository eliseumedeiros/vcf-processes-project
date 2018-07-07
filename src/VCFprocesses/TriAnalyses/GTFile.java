/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VCFprocesses.TriAnalyses;

/**
 *Classe responsável por diferenciar os conteúdos para as Trio-análises
 * @author eliseumedeiros
 */
public class GTFile {
    private String father, mother, child;
    private int numFather, numMother, numChild;
    private boolean considerRef; //considerar 0/0 como homozigoto para a pesquisa na tabela
    
    public GTFile(String father, String mother, String child, int numChild, int numFather, int numMother){
        this.father = father;
        this.mother = mother;
        this.child = child;
        this.numChild = numChild;
        this.numFather = numFather;
        this.numMother = numMother;
    }
    public GTFile(){
        this.child = "";
        this.father = "";
        this.mother = "";
        this.numChild = 0;
        this.numFather = 0;
        this.numMother = 0;
    }
    
    public String getFather(){
        return this.father;
    }
    public String getMother(){
        return this.mother;
    }
    public String getChild(){
        return this.child;
    }
    public int getNumFather(){
        return this.numFather;
    }
    public int getNumMother(){
        return this.numMother;
    }
    public int getNumChild(){
        return this.numChild;
    }
    public boolean getConsiderRef(){
        return this.considerRef;
    }
    public void setFather(String father){
        this.father = father;
    }
    public void setMother(String mother){
        this.mother = mother;
    }
    public void setChild(String child){
        this.child = child;
    }
     public void setNumFather(int numFather){
        this.numFather=numFather;
    }
    public void setNumMother(int numMother){
        this.numMother = numMother;
    }
    public void setNumChild(int numChild){
        this.numChild = numChild;
    }
    public void setConsiderRef(boolean considerRef){
        this.considerRef = considerRef;
    }
    /**
     * verifica se tem algum campo "pai, mãe ou filho" vazio. E retorna true se contiver algum vazio, e false caso contrário.
     * @return 
     */
    public boolean isAnyEmpy(){
        if(this.child.isEmpty() || this.father.isEmpty() || this.mother.isEmpty())
            return false;
        else
            return true;
                   
    }
}
