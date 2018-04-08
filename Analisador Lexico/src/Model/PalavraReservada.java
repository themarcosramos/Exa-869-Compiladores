package Model;

import java.util.ArrayList;

public class PalavraReservada {

    private String[] palavras_reservadas; // Array com as palavras reservadas para verificação

    public PalavraReservada(){
        // inicializando o array com as palavras reservadas
        this.palavras_reservadas = new String[]
                {"const", "var", "struct", "typedef", "procedure", "function", "return", "start", "if", "then", "else",
                 "while", "scan", "print", "int", "float", "bool", "string", "true", "false", "extends" };
    }

    public boolean isPalavraReservada(String palavra){
        for (String palavraReservada: this.palavras_reservadas) {

            // ADIMITINDO QUE A LINGUAGEM É CASE SENSITIVE
            if(palavraReservada.equals(palavra)){
                return true;
            }

            /* CASO A LINGUAGEM NÃO SEJA CASE SENSITEVE
            if(palavraReservada.equalsIgnoreCase(palavra)){
                return true;
            }
            */
        }
        return false;
    }
}
