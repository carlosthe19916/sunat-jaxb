package io.github.carlosthe19916.sunatjaxb.core.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class MonedaBean {

    @NotNull
    @Size(min = 3, max = 3)
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
