package Model;


import java.io.FileNotFoundException;
import java.io.IOException;
import Util.Estado;


  /*
   *@author Gabriel Miranda e Marcos Ramos
   */
public class Analisador {

    // variáveis de controle da leitura do documento
    private char inicio_lexema; // variável que armazena o lexema que está sendo lido
    private char lookahead; // variável que busca a proxima informação (caracter) da entrada
    private Estado estado_atual; // armazena o estado atual do lexema (no automato)
    private Estado ultimo_estado_valido; // armazena o ultimo estado válido como token do lexema (com base no automato)
    private FileInputStream archiveCodigoFonte; //FileInputStream que aponta para o arquivo do codigo fonte .
    private int  linha;     // A linha atualmente em  analise  no arquivo do codigo fonte.
    private int  coluna;   // A coluna atualmente em analise  no arquivo do codigo fonte.
    private  boolean fim;   // varaivel para determina o fim do arquivo.

}
    /* A  função a seguir carrgeda para o analisador lexico o arquivo que contém o código fonte */
    public void carregarCodigoFonte(String file) {
        try {

            this.archiveCodigoFonte = new FileInputStream(new File(file));

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* A  função a seguir  busca o próximo tocken
    * @return o o tocken encontrado */
    private int readNextoDados() throws IOException {
        int dados  = archiveCodigoFonte.read();

        if(dados == (int)'\n') {
            this.linha++;
            this.coluna = 0;
        } else if(dados == (int)'\t') {
            this.coluna += 4;
        } else if(dados >= 32) {
            this.coluna++;
        }
        return dados;
    }

   