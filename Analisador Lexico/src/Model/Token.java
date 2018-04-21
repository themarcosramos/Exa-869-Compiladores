package Model;

import Util.Classe;
import Util.Estado;

public class Token {

    private Classe classe; // Estado do Token
    private int linha; // Linha onde o Token estava no código-fonte.
    private String lexema; // Conteudo identificado pelo Token
    private boolean erro; // indica se é um token mal formado

    public Token(Estado estado, int linha, String lexema, boolean erro) {
        this.definirEstado(estado);
        this.linha = linha;
        this.lexema = lexema;
        this.erro = erro;
    }

    public void definirEstado(Estado estado){
        if(estado == Estado.PALR){
            this.classe = Classe.PRE;
        }else if(estado == Estado.ID){
            this.classe = Classe.IDE;
        }else if(estado == Estado.NUM || estado == Estado.nump1 || estado == Estado.nump2 || estado == Estado.NUM2){
            this.classe = Classe.NRO;
        }else if(estado == Estado.OPAR || estado == Estado.OPAR2 || estado == Estado.OPAR3){
            this.classe = Classe.ART;
        }else if(estado == Estado.OPRL || estado == Estado.OPRL2){
            this.classe = Classe.REL;
        }else if(estado == Estado.OPL || estado == Estado.OPL2 || estado == Estado.oplp){
            this.classe = Classe.LOG;
        }else if(estado == Estado.DEL){
            this.classe = Classe.DEL;
        }else if(estado == Estado.CADC || estado == Estado.cadcp1 || estado == Estado.cadcp2){
            this.classe = Classe.CAR;
        }else if(estado == Estado.COML || estado == Estado.comp1 || estado == Estado.comp2 || estado == Estado.COMB){
            this.classe = Classe.COM;
        }
    };

    public Classe getEstado() {
        return classe;
    }

    public int getLinha() {
        return linha;
    }

    public String getLexema() {
        return lexema;
    }
}
