package Model;

import Util.Classe;

public class Token {

    private Classe classe; // Estado do Token
    private int linha; // Linha onde o Token estava no código-fonte.
    private int coluna; // Coluna onde o Token estava no código-fonte
    private String lexema; // Conteudo identificado pelo Token

    public Token(Classe estado, int linha, int coluna, String lexema) {
        this.classe = estado;
        this.linha = linha;
        this.coluna = coluna;
        this.lexema = lexema;
    }

    public Classe getEstado() {
        return classe;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public String getLexema() {
        return lexema;
    }
}
