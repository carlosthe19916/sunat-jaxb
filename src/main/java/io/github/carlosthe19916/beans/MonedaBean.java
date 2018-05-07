package io.github.carlosthe19916.beans;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class MonedaBean {

    @NotNull
    private String codigo;
    private BigDecimal tipoCambio;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
}
