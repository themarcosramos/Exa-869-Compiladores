package Util;

public enum Classe {

    SALVAR(1), IMPRMIR(2), ABRIR(3), VISUALIZAR(4), FECHAR(5);

    private final int classeValor;

    Classe(int classe){
        classeValor = classe;
    }
    public int getClasse(){
        return classeValor;
    }
}
