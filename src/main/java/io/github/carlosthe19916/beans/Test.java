package io.github.carlosthe19916.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Test {

    private String serie;
    private Integer numero;
    private String codigoTipoComprobante;

    private Date fechaEmision;
    private Date fechaVencimiento;

    private String moneda;
    private BigDecimal tipoCambio;

    private BigDecimal totalIgv;
    private BigDecimal totalIsc;

    private BigDecimal totalPagar;
    private BigDecimal totalDescuentoGlobal;
    private BigDecimal totalOtrosCargos;

    private BigDecimal totalGravado;
    private BigDecimal totalInafecto;
    private BigDecimal totalExonerado;
    private BigDecimal totalGratuito;

    private String observaciones;

    private ClienteBean cliente;
    private ProveedorBean proveedor;

    private List<TypeLineBean> detalle;

    public InvoiceBuilder serie(String serie) {
        this.serie = serie;
        return this;
    }

    public InvoiceBuilder numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public InvoiceBuilder codigoTipoComprobante(String codigoTipoComprobante) {
        this.codigoTipoComprobante = codigoTipoComprobante;
        return this;
    }

    public InvoiceBuilder fechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
        return this;
    }

    public InvoiceBuilder fechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
        return this;
    }

    public InvoiceBuilder moneda(String moneda) {
        this.moneda = moneda;
        return this;
    }

    public InvoiceBuilder tipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
        return this;
    }

    public InvoiceBuilder totalIgv(BigDecimal totalIgv) {
        this.totalIgv = totalIgv;
        return this;
    }

    public InvoiceBuilder totalIsc(BigDecimal totalIsc) {
        this.totalIsc = totalIsc;
        return this;
    }

    public InvoiceBuilder totalPagar(BigDecimal totalPagar) {
        this.totalPagar = totalPagar;
        return this;
    }

    public InvoiceBuilder totalDescuentoGlobal(BigDecimal totalDescuentoGlobal) {
        this.totalDescuentoGlobal = totalDescuentoGlobal;
        return this;
    }

    public InvoiceBuilder totalOtrosCargos(BigDecimal totalOtrosCargos) {
        this.totalOtrosCargos = totalOtrosCargos;
        return this;
    }

    public InvoiceBuilder totalGravado(BigDecimal totalGravado) {
        this.totalGravado = totalGravado;
        return this;
    }

    public InvoiceBuilder totalInafecto(BigDecimal totalInafecto) {
        this.totalInafecto = totalInafecto;
        return this;
    }

    public InvoiceBuilder totalExonerado(BigDecimal totalExonerado) {
        this.totalExonerado = totalExonerado;
        return this;
    }

    public InvoiceBuilder totalGratuito(BigDecimal totalGratuito) {
        this.totalGratuito = totalGratuito;
        return this;
    }

    public InvoiceBuilder observaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }

    public ClienteBean.ClienteBuilder<InvoiceBuilder> cliente() {
        return new ClienteBean.ClienteBuilder<>(cliente -> {
            this.cliente = cliente;
            return this;
        });
    }

    public InvoiceBuilder proveedor(ProveedorBean proveedor) {
        this.proveedor = proveedor;
        return this;
    }

}
