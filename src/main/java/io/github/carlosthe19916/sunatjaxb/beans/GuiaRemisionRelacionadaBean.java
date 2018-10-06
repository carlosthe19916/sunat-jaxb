package io.github.carlosthe19916.sunatjaxb.beans;

import io.github.carlosthe19916.sunatjaxb.catalogos.Catalogo1;

import javax.validation.constraints.NotNull;

public class GuiaRemisionRelacionadaBean {

    @NotNull
    private String guiaRemision;

    @NotNull
    private Catalogo1 tipoGuiaRemision;

    public String getGuiaRemision() {
        return guiaRemision;
    }

    public void setGuiaRemision(String guiaRemision) {
        this.guiaRemision = guiaRemision;
    }

    public Catalogo1 getTipoGuiaRemision() {
        return tipoGuiaRemision;
    }

    public void setTipoGuiaRemision(Catalogo1 tipoGuiaRemision) {
        this.tipoGuiaRemision = tipoGuiaRemision;
    }
}
