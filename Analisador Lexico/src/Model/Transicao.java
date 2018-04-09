package Model;

import Util.Estado;

public class Transicao {

    Estado[][] tabela_de_trasicao; // armazena a tabela de transição que define o automato do analisador
    int numero_estados; // número de estados possíveis no automato (Linhas da tabela de transição)
    int numero_entradas_alfabeto; // núemro de possíveis entradas (Colunas da tabela de transição)

    public Transicao() {
        // Assumindo que o automato não vai mudar então os valores são definidos manualmente (visto que já se sabe a estrutura da tabela)
        this.numero_estados = 24; // Conferir estados em Util.Estado
        this.numero_entradas_alfabeto =  127; // TABELA ASCII até o 126
        this.tabela_de_trasicao = new Estado[this.numero_entradas_alfabeto][this.numero_estados];
        //Inicializa a tabela com todos os elementos setados em null
        for(int i = 0; i < this.numero_estados; i++){
            for(int j = 0; j < this.numero_entradas_alfabeto; j++){
                this.tabela_de_trasicao[i][j] = null;
            }
        }
        // Chama a função para inicializar os estados na tabela de transição com base no automato do analisador (ver arquivo .atm no dir raiz)
        this.inicializarMatriz();
    }

    private void inicializarMatriz() {
        /*  Seta os estados que envolvem transição sengindo o padrão:
                Matriz[entradas][estados] = novo Estado
        */


    }
}
