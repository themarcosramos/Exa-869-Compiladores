package Model;

import Util.Estado;

public class Analisador {

    // variáveis de controle da leitura do documento
    private char inicio_lexema; // variável que armazena o lexema que está sendo lido
    private char lookahead; // variável que busca a proxima informação (caracter) da entrada
    private Estado estado_atual; // armazena o estado atual do lexema (no automato)
    private Estado ultimo_estado_valido; // armazena o ultimo estado válido como token do lexema (com base no automato)

}
