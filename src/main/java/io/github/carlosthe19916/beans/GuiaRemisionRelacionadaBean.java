package io.github.carlosthe19916.beans;

import io.github.carlosthe19916.beans.catalogs.Catalogo1;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.TimeZone;

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
