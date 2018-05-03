package io.github.carlosthe19916.beans;

import java.util.Date;

public class BajaBean {

    private String serie;
    private Integer numero;

    private Date fechaEmision;
    private String motivoBaja;
    private InvoiceBean invoiceAfectado;

    private SupplierBean supplier;

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public InvoiceBean getInvoiceAfectado() {
        return invoiceAfectado;
    }

    public void setInvoiceAfectado(InvoiceBean invoiceAfectado) {
        this.invoiceAfectado = invoiceAfectado;
    }

    public SupplierBean getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierBean supplier) {
        this.supplier = supplier;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
