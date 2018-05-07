package io.github.carlosthe19916.beans;

import java.math.BigDecimal;

public class TotalBean {

    private BigDecimal pagar;
    private BigDecimal otrosCargos;
    private BigDecimal descuento;

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

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }
}
