package io.github.carlosthe19916.beans.catalogs;

public enum Catalogo6 implements Catalogo {

    DOC_TRIB_NO_DOM_SIN_RUC("0"),
    DNI("1"),
    EXTRANJERIA("4"),
    RUC("6"),
    PASAPORTE("7"),
    DEC_DIPLOMATICA("A");

    private final String codigo;

    @Override
    public String getCode() {
        return codigo;
    }

    Catalogo6(String codigo) {
        this.codigo = codigo;
    }

}
