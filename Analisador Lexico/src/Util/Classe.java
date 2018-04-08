package Util;

public enum Classe {

    PALAVRA_RESERVADA(1),
    IDENTIFICADOR(2),
    NUMERO(3),
    OPERADOR_ARITMETICO(4),
    OPERADOR_RELACIONAL(5),
    OPERADOR_LOGICO(6),
    DELIMITADOR(7),
    CADEIA_DE_CARACTERES(8),
    TOKEN_MAL_FORMADO(9);

    private final int classeValor;

    Classe(int classe){
        classeValor = classe;
    }
    public int getClasse(){
        return classeValor;
    }
}
