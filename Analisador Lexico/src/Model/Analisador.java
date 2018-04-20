package Model;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import Util.Estado;
  /*
   *@author Gabriel Miranda e Marcos Ramos
   */
public class Analisador {

     // variáveis de controle da leitura do documento
      private String lexema; // variável que armazena o lexema que está sendo lido
      private char lookahead; // variável que busca a proxima informação (caracter) da entrada
      private Estado estado_atual; // armazena o estado atual do lexema (no automato)
      private Estado ultimo_estado_valido; // armazena o ultimo estado válido como token do lexema (com base no automato)
      private int  linha;     // A linha atualmente em  analise  no arquivo do codigo fonte.
      private int  coluna;   // A coluna atualmente em analise  no arquivo do codigo fonte.
      private boolean fim;   // varaivel para determina o fim do arquivo.
      private Scanner scanner; // scanner do arquivo de entrada
      private Scanner arquivo_saida; // arquivo de saida do analisador
      private Transicao transicao; // matriz de transição
      private ArrayList<Token> tokens; // lista de tokens lidos
      private boolean quebra_linha;// variavel para identificar a quebra de linha do arquivo que vai ser lido
      private boolean fim_arquivo;// varaviel para identificar que a leitura do arquivo chegou ao fim
      private String linha_lida; // variavel para identificar  as linhas já lidas  do arquivo
      private int index_de_leitura; // variavel que  identificar a idexação da leitura do arquivo

      public Analisador(){

      }

      public static void main(String[] args) {
          Analisador analisador = new Analisador();
          analisador.iniciarLeitura();
      }

      public void iniciarLeitura(){
          //carrega o arquivo de entrada
          try {
              this.carregarCodigoFonte();
          } catch (FileNotFoundException e) {
              System.out.println("Erro ao tentar ler arquivo de entrada");
          }
          //inicializa matriz de transições
          this.transicao = new Transicao();
          //////////////////////////////////////////////////////////////
          //setando variaveis iniciais
          this.tokens = new ArrayList<>(); // criação da lista para armazenar tockens
          this.estado_atual = Estado.Inicial;
          this.ultimo_estado_valido = Estado.Inicial;
          this.lexema = "";
          this.linha = 0;
          this.coluna = 0;
          this.fim = false;
          this.fim_arquivo = false;
          this.quebra_linha = true;
          this.linha_lida = "";
          this.index_de_leitura = 0;

          //loop de leitura do arquivo de entrada
          while(!this.fim){
              Estado prox_estado = Estado.Inicial;
              this.lookahead = this.getEntrada(); // pega o proximo dado de entrada
              if(this.fim_arquivo){
                  prox_estado = null;
              }else if(this.quebra_linha){
                  if(this.estado_atual.getEstadoValor() == 10){
                      //Comentario de bloco -> continuar leitura
                      this.lookahead = this.getEntrada(); // pega o proximo dado de entrada
                      if(this.fim_arquivo) {//verifica se é fim de arquivo
                          prox_estado = null;
                      }
                  }else{
                      prox_estado = null;
                  }
              }else{
                  prox_estado = this.transicao.transicao(this.estado_atual, this.lookahead);
              }
              //verificação caso finalize o token
              if(prox_estado == null){
                    if(this.transicao.isFinal(this.estado_atual)){
                        //caso o token tenha sido formado corretamente
                        if(this.estado_atual.getEstadoValor() == 2){ // verificar se é UEV
                            System.out.println(this.lexema.trim() + " - " + this.estado_atual.toString() + " - número + delimitador");
                        }else{
                            System.out.println(this.lexema.trim() + " - " + this.estado_atual.toString());
                        }
                    }else{ // se não for estado final, TOKEN MAL FORMADO
                        this.estado_atual = Estado.TMF;
                        System.out.println(this.lexema.trim() + " - " + this.estado_atual.toString());
                    }
                    this.index_de_leitura--;
              }else{
                  this.lexema = this.lexema + this.lookahead; // adiciona o lookahead no lexema
                  this.estado_atual = prox_estado;
                  if(this.transicao.isFinal(prox_estado)){ // verifica se é um estado válido se for adiciona ao UEV
                      this.ultimo_estado_valido = prox_estado;
                  }
              }
          }
      }


     // carrega o endereço do arquivo de entrada
     public void carregarCodigoFonte() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("entrada.txt"));
    }

     // Retorna o próximo caracter lido
     private char getEntrada(){
         char entrada = ' ';
         if(this.scanner.hasNextLine() && this.quebra_linha){
             this.linha_lida = this.scanner.nextLine();
             this.quebra_linha = false;
         }else{
             this.fim_arquivo = true;
         }
        // printar dado lido
        System.out.println(linha_lida);
        if(this.index_de_leitura < this.linha_lida.length()){
            entrada = this.linha_lida.charAt(this.index_de_leitura);
            this.index_de_leitura++;
        }else if(this.index_de_leitura == this.linha_lida.length()){
            this.index_de_leitura = 0;
            this.quebra_linha = true;
        }
        return entrada;
    }
}
