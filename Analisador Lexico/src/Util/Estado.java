package Util;

public enum Estado {

    Inicial(0),
    ID(1), //* FINAL *//
    UEV(2),
    TMF(3), //* FINAL *//
    DEL(4), //* FINAL *//
    COML(5), //* FINAL *//
    comp1(6),
    OPAR(7), //* FINAL *//
    OPAR2(8), //* FINAL *//
    comp2(9),
    COMB(10), //* FINAL *//
    NUM(11), //* FINAL *//
    nump1(12),
    nump2(13),
    NUM2(14), //* FINAL *//
    OPRL(15), //* FINAL *//
    OPRL2(16), //* FINAL *//
    OPAR3(17), //* FINAL *//
    OPL(18), //* FINAL *//
    OPL2(19), //* FINAL *//
    oplp(20),
    cadcp1(21),
    cadcp2(22),
    CADC(23); //* FINAL *//

    private final int estadoValor;

    Estado(int estado){
        estadoValor = estado;
    }
    public int getEstado(){
        return estadoValor;
    }
}
