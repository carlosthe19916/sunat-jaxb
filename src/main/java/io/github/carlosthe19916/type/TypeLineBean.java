package io.github.carlosthe19916.type;

import java.math.BigDecimal;

public class TypeLineBean {

    private String unidadMedida;
    private String descripcion;
    private String codigoTipoIgv;
    private BigDecimal cantidad;
    private BigDecimal valorUnitario;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private BigDecimal total;
    private BigDecimal totalIgv;
    private BigDecimal totalIsc;

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCodigoTipoIgv() {
        return codigoTipoIgv;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getTotalIgv() {
        return totalIgv;
    }

    public BigDecimal getTotalIsc() {
        return totalIsc;
    }
}
