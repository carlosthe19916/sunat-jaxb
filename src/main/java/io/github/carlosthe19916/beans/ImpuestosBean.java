package io.github.carlosthe19916.beans;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ImpuestosBean {

    @NotNull
    private BigDecimal igv;
    private BigDecimal isc;

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
}
