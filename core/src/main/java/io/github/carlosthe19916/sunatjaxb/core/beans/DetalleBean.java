package io.github.carlosthe19916.sunatjaxb.core.beans;

import io.github.carlosthe19916.sunatjaxb.core.catalogos.Catalogo7;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DetalleBean {

    @NotNull
    private String unidadMedida;

    @NotNull
    private String descripcion;

    @NotNull
    private BigDecimal cantidad;

    @NotNull
    private BigDecimal valorUnitario;

    @NotNull
    private BigDecimal precioUnitario;

    @NotNull
    private BigDecimal subtotal;

    @NotNull
    private BigDecimal total;

    private Catalogo7 tipoAfectacionIgv;
    private BigDecimal totalIgv;
    private BigDecimal totalIgvAfectado;
    private BigDecimal valorIgv;

    private String tipoAfectacionIsc;
    private BigDecimal totalIsc;
    private BigDecimal totalIscAfectado;
    private BigDecimal valorIsc;

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

    public String getTipoAfectacionIsc() {
        return tipoAfectacionIsc;
    }

    public void setTipoAfectacionIsc(String tipoAfectacionIsc) {
        this.tipoAfectacionIsc = tipoAfectacionIsc;
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

    public BigDecimal getTotalIgvAfectado() {
        return totalIgvAfectado;
    }

    public void setTotalIgvAfectado(BigDecimal totalIgvAfectado) {
        this.totalIgvAfectado = totalIgvAfectado;
    }

    public BigDecimal getTotalIscAfectado() {
        return totalIscAfectado;
    }

    public void setTotalIscAfectado(BigDecimal totalIscAfectado) {
        this.totalIscAfectado = totalIscAfectado;
    }

    public BigDecimal getValorIgv() {
        return valorIgv;
    }

    public void setValorIgv(BigDecimal valorIgv) {
        this.valorIgv = valorIgv;
    }

    public BigDecimal getValorIsc() {
        return valorIsc;
    }

    public void setValorIsc(BigDecimal valorIsc) {
        this.valorIsc = valorIsc;
    }
}
