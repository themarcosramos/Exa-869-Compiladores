package Model;

import Util.Classe;

public class Token {

    private Classe classe; // Classe do Token
    private int linha; // Linha onde o Token estava no código-fonte.
    private int coluna; // Coluna onde o Token estava no código-fonte

    public Token() {}

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }


}
