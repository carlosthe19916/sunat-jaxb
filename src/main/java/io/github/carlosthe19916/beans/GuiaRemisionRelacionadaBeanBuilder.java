package io.github.carlosthe19916.beans;

import io.github.carlosthe19916.beans.catalogs.Catalogo1;

public class GuiaRemisionRelacionadaBeanBuilder {

    private final GuiaRemisionRelacionadaBean guiaRemisionRelacionada;

    public GuiaRemisionRelacionadaBeanBuilder() {
        this.guiaRemisionRelacionada = new GuiaRemisionRelacionadaBean();
    }

    public static GuiaRemisionRelacionadaBeanBuilder builder() {
        return new GuiaRemisionRelacionadaBeanBuilder();
    }

    public GuiaRemisionRelacionadaBean build() {
        return guiaRemisionRelacionada;
    }

    public GuiaRemisionRelacionadaBeanBuilder guiaRemision(String guiaRemision) {
        guiaRemisionRelacionada.setGuiaRemision(guiaRemision);
        return this;
    }

    public GuiaRemisionRelacionadaBeanBuilder tipoGuiaRemision(Catalogo1 tipoGuiaRemision) {
        guiaRemisionRelacionada.setTipoGuiaRemision(tipoGuiaRemision);
        return this;
    }

}
