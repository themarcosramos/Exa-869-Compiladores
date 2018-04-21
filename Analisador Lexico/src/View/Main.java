package View;

import Model.AnalisadorLexico;

public class Main {

    private static AnalisadorLexico analisadorLexico;

    public static void main(String[] args) {
        analisadorLexico = new AnalisadorLexico();
        analisadorLexico.iniciarLeitura();
    }
}
