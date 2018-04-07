package Model;

import Util.Estado;

public class Token {

    private Estado estado; // Estado do Token
    private int linha; // Linha onde o Token estava no código-fonte.
    private int coluna; // Coluna onde o Token estava no código-fonte
    private String lexema; // Conteudo identificado pelo Token

    public Token(Estado estado, int linha, int coluna, String lexema) {
        this.estado = estado;
        this.linha = linha;
        this.coluna = coluna;
        this.lexema = lexema;
    }

    public Estado getEstado() {
        return estado;
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
