package io.github.carlosthe19916.beans;

import io.github.carlosthe19916.beans.catalogs.Catalogo7;

import java.math.BigDecimal;

public class DetalleBean {

    private String unidadMedida;
    private String descripcion;

    private BigDecimal cantidad;
    private BigDecimal valorUnitario;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private BigDecimal total;

    private Catalogo7 tipoAfectacionIgv;
    private String codigoTipoIsc;
    private BigDecimal totalIgv;
    private BigDecimal totalIsc;

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoTipoIsc() {
        return codigoTipoIsc;
    }

    public void setCodigoTipoIsc(String codigoTipoIsc) {
        this.codigoTipoIsc = codigoTipoIsc;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotalIgv() {
        return totalIgv;
    }

    public void setTotalIgv(BigDecimal totalIgv) {
        this.totalIgv = totalIgv;
    }

    public BigDecimal getTotalIsc() {
        return totalIsc;
    }

    public void setTotalIsc(BigDecimal totalIsc) {
        this.totalIsc = totalIsc;
    }

    public Catalogo7 getTipoAfectacionIgv() {
        return tipoAfectacionIgv;
    }

    public void setTipoAfectacionIgv(Catalogo7 tipoAfectacionIgv) {
        this.tipoAfectacionIgv = tipoAfectacionIgv;
    }
}
