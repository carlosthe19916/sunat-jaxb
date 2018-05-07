package io.github.carlosthe19916.beans;

import java.math.BigDecimal;

public class TotalInformacionAdicionalBean {

    private BigDecimal gravado;
    private BigDecimal inafecto;
    private BigDecimal exonerado;
    private BigDecimal gratuito;

    public BigDecimal getGravado() {
        return gravado;
    }

    public void setGravado(BigDecimal gravado) {
        this.gravado = gravado;
    }

    public BigDecimal getInafecto() {
        return inafecto;
    }

    public void setInafecto(BigDecimal inafecto) {
        this.inafecto = inafecto;
    }

    public BigDecimal getExonerado() {
        return exonerado;
    }

    public void setExonerado(BigDecimal exonerado) {
        this.exonerado = exonerado;
    }

    public BigDecimal getGratuito() {
        return gratuito;
    }

    public void setGratuito(BigDecimal gratuito) {
        this.gratuito = gratuito;
    }
}
