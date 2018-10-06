package io.github.carlosthe19916.sunatjaxb.beans;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public abstract class AbstractTotalBean {

    @NotNull
    private BigDecimal pagar;
    private BigDecimal otrosCargos;
    private BigDecimal descuentoGlobal;

    private String pagarLetras;

    public BigDecimal getPagar() {
        return pagar;
    }

    public void setPagar(BigDecimal pagar) {
        this.pagar = pagar;
    }

    public BigDecimal getOtrosCargos() {
        return otrosCargos;
    }

    public void setOtrosCargos(BigDecimal otrosCargos) {
        this.otrosCargos = otrosCargos;
    }

    public BigDecimal getDescuentoGlobal() {
        return descuentoGlobal;
    }

    public void setDescuentoGlobal(BigDecimal descuentoGlobal) {
        this.descuentoGlobal = descuentoGlobal;
    }

    public String getPagarLetras() {
        return pagarLetras;
    }

    public void setPagarLetras(String pagarLetras) {
        this.pagarLetras = pagarLetras;
    }
}
