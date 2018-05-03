package io.github.carlosthe19916.beans;

import java.math.BigDecimal;

public class TypeLineBean {

    private String unidadMedida;
    private String descripcion;
    private String codigoTipoIgv;
    private String codigoTipoIsc;
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

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCodigoTipoIgv(String codigoTipoIgv) {
        this.codigoTipoIgv = codigoTipoIgv;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setTotalIgv(BigDecimal totalIgv) {
        this.totalIgv = totalIgv;
    }

    public void setTotalIsc(BigDecimal totalIsc) {
        this.totalIsc = totalIsc;
    }

    public String getCodigoTipoIsc() {
        return codigoTipoIsc;
    }

    public void setCodigoTipoIsc(String codigoTipoIsc) {
        this.codigoTipoIsc = codigoTipoIsc;
    }
}
