package Util;

public enum Classe {

    PRE(1),  // PALAVRA RESERVADA
    IDE(2),  // IDENTIFICADOR
    NRO(3),  // NUMERO
    ART(4),  // OPERADOR ARITIMETICO
    REL(5),  // OPERADOR RELACIONAL
    LOG(6),  // OPERADOR LÓGICO
    DEL(7),  // DELIMITADOR
    CAR(8),  // CADEIA DE CARACTERES
    TMF(9),  // TOKEN MAL FORMADO
    COM(10); // COMENTARIO

    private final int classeValor;

    Classe(int classe){
        classeValor = classe;
    }
    public int getClasse(){
        return classeValor;
    }
}
