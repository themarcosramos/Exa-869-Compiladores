package Model;



import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Util.Classe;
import Util.Estado;
  /*
   *@author Gabriel Miranda e Marcos Ramos
   */
public class AnalisadorLexico {

     // variáveis de controle da leitura do documento
      private String lexema; // variável que armazena o lexema que está sendo lido
      private char lookahead; // variável que busca a proxima informação (caracter) da entrada
      private Estado estado_atual; // armazena o estado atual do lexema (no automato)
      private Estado ultimo_estado_valido; // armazena o ultimo estado válido como token do lexema (com base no automato)
      private int  linha;     // A linha atualmente em  analise  no arquivo do codigo fonte.
      private boolean quebra_linha; // indicador de quebra de linha
      private boolean fim;   // varaivel para determina o fim do arquivo.
      private Scanner scanner; // scanner do arquivo de entrada
      private Scanner arquivo_saida; // arquivo de saida do analisador
      private Transicao tabela_transicao; // matriz de transição
      private ArrayList<Token> tokens; // lista de tokens lidos
      private ArrayList<Token> erros; // lista de tokens mal formados
      private boolean manter_entrada; // varificador de repetição de entrada

      public AnalisadorLexico(){

      }

      public void iniciarLeitura(){
          //carrega o arquivo de entrada
          try {
              this.carregarCodigoFonte();
          } catch (FileNotFoundException e) {
              System.out.println("Erro ao tentar ler arquivo de entrada");
          }
          //inicializa matriz de transições
          this.tabela_transicao = new Transicao();
          //////////////////////////////////////////////////////////////
          //setando variaveis iniciais
          this.manter_entrada = false;
          this.tokens = new ArrayList<>();
          this.erros = new ArrayList<>();
          this.estado_atual = Estado.Inicial;
          this.ultimo_estado_valido = Estado.Inicial;
          this.lexema = "";
          this.linha = 1;
          this.quebra_linha = false;
          this.fim = false;

          while(!this.fim){
              this.lookahead = this.getEntrada();
              //Verifica a transição para o próximo estado
              Estado prox_estado = this.tabela_transicao.transicao(this.estado_atual, this.lookahead);

              if(prox_estado == null){ // TOKEN FINALIZADO
                  // 1º passo: finalizar e armazenar o TOKEN
                  this.lexema = this.lexema.trim(); // Removendo os ' ' desnecessários
                  this.lexema = this.lexema.replaceAll("\n", ""); // removendo as quebras de linha
                  this.lexema = this.lexema.replaceAll("\t", ""); // revomento tabulação
                  if(this.estado_atual == Estado.UEV){ // UEV = '-' + ' ' -> Operador Aritmetico
                      //define o estado atual como operador aritmético
                      this.estado_atual = Estado.OPAR;
                  }else if(this.estado_atual == Estado.COML){
                      // ignorar demais caracteres até a quebra de linha
                      char entrada_aux;
                      do{
                          entrada_aux = this.getEntrada();
                          this.lexema += Character.toString(entrada_aux);
                      }while((int)entrada_aux != 10 && (int)entrada_aux != 3); // executa enquanto não achar quebra de linha ou fim de arquivo
                      this.lexema = this.lexema.trim(); // Removendo os ' ' desnecessários
                      this.lexema = this.lexema.replaceAll("\n", ""); // removendo as quebras de linha
                      this.lexema = this.lexema.replaceAll("\t", ""); // revomento tabulação
                  }
                  // MONTA OS TOKENS
                  if(this.estado_atual == Estado.TMF){
                      //montar 2 tokens numero + delimitador
                      String[] lexemas = this.lexema.split(".");
                      this.tokens.add(new Token(Estado.NUM, this.linha, lexemas[0], false));
                      this.tokens.add(new Token(Estado.DEL, this.linha, lexemas[1], false));
                      this.manter_entrada = true;
                  }else if(this.tabela_transicao.isFinal(this.estado_atual)){ // Caso não caia em nenhum caso específico e seja um estado final
                      //verifica se é palavra reservada
                      this.isPalavraReservada();
                      this.tokens.add(new Token(this.estado_atual, this.linha, this.lexema, false));
                      this.manter_entrada = true;
                  }else{ // se não for um estado final
                      //montar token de erro e adicionar a lista de erros
                      if(!this.lexema.equals("")){
                          this.erros.add(new Token(this.estado_atual, this.linha, this.lexema, true));
                      }
                  }
                  // reiniciando variáveis de controle do token
                  this.estado_atual = Estado.Inicial;
                  this.ultimo_estado_valido = Estado.Inicial;
                  this.lexema = "";
              }else{ // TOKEN EM FORMAÇÂO
                    this.lexema += Character.toString(this.lookahead); // adiciona o novo caracter ao lexema
                    this.estado_atual = prox_estado;
                    if(this.tabela_transicao.isFinal(prox_estado)){ // caso seja um estado final, adicionar ao UEV
                        this.ultimo_estado_valido = prox_estado;
                    }
              }
          }
          try {
              this.salvarResultado();
          } catch (IOException e) {
              System.out.println("Não foi possível salvar os dados no arquivo externo");
          }
      }

     // verifica se o lexema é uma palavra reservada
      public void isPalavraReservada(){
          if(this.estado_atual == Estado.ID){
              if(this.tabela_transicao.isPalavraReservada(this.lexema)){
                  this.estado_atual = Estado.PALR;
              }
          }
      }

     // carrega o endereço do arquivo de entrada
     public void carregarCodigoFonte() throws FileNotFoundException {
        // inicializa o scanner de leitura com o delimitador vazio, garantindo assim que o retorno será de um em um caracter
         FileReader arq = new FileReader("src/Entrada/entrada.txt");

        this.scanner = new Scanner(arq).useDelimiter(" ");
    }

     // Retorna o próximo caracter lido
     private char getEntrada(){
         if(this.quebra_linha){
             this.linha++;
             this.quebra_linha = false;
         }
         if(this.manter_entrada){
             this.manter_entrada = false;
             return this.lookahead;
         }else if(this.scanner.hasNext()){ // se houver algum dado a ser lido
             char entrada = this.scanner.next().charAt(0); // lê o próximo dado de entrada
             if((int)entrada != 13){ // verifica se o caracter lido é diferente ENTER
                 return entrada;
             }else{ // se o caracter lido for ENTER
                 if(this.scanner.hasNext()) { // se houver próximo caracter a ser lido
                     this.quebra_linha = true;
                     return this.scanner.next().charAt(0); // lê o próximo dado de entrada
                 }else{ // chegou ao fim do arquivo
                     this.fim = true;
                     return (char)3; // envia o char referente ao FIM DO TEXTO (EOT)
                 }
             }
         }else{ // caso ja tenha chegado ao fim do arquivo
             this.fim = true;
             return (char)3; // envia o char referente ao FIM DO TEXTO (EOT)
         }

    }

    // Salva o resultado da analise em um arquivo .txt externo
     private void salvarResultado() throws IOException {
         FileWriter arqSaida = new FileWriter("src/Saida/AnaliseLexicaSaida.txt.txt");
         BufferedWriter arquivo = new BufferedWriter(arqSaida);
         for (Token token : this.tokens) {
             if(token.getEstado() != Classe.COM){
                 arquivo.write(token.getLinha() + "\t-> " + token.getEstado() + " | " + token.getLexema());
                 arquivo.newLine();
             }
         }
         arquivo.newLine();
         arquivo.write("---------------------------------------------------------------------");
         arquivo.newLine();
         for (Token token : this.erros) {
             arquivo.write(token.getLinha() + "\t-> " + token.getEstado() + " (mal formado) | " + token.getLexema());
             arquivo.newLine();
         }
         arquivo.flush();
         arquivo.close();
     }
}
