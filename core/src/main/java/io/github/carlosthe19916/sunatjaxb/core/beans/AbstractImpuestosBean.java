package io.github.carlosthe19916.sunatjaxb.core.beans;

import java.math.BigDecimal;

public abstract class AbstractImpuestosBean {

    private BigDecimal igv;
    private BigDecimal isc;
    private BigDecimal otros;

    public BigDecimal getIgv() {
        return igv;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }

    public BigDecimal getIsc() {
        return isc;
    }

    public void setIsc(BigDecimal isc) {
        this.isc = isc;
    }

    public BigDecimal getOtros() {
        return otros;
    }

    public void setOtros(BigDecimal otros) {
        this.otros = otros;
    }
}
