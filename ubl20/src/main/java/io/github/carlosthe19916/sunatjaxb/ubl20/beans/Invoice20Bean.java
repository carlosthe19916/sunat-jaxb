package io.github.carlosthe19916.sunatjaxb.ubl20.beans;

import io.github.carlosthe19916.sunatjaxb.beans.AbstractInvoiceBean;
import io.github.carlosthe19916.sunatjaxb.beans.ClienteBean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Invoice20Bean extends AbstractInvoiceBean {

    private String observaciones;

    @Valid
    @NotNull
    private Total20Bean total;

    @Valid
    @NotNull
    private Impuestos20Bean impuestos;

    @Valid
    @NotNull
    private ClienteBean cliente;

    @Valid
    @NotNull
    private Proveedor20Bean proveedor;

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Total20Bean getTotal() {
        return total;
    }

    public void setTotal(Total20Bean total) {
        this.total = total;
    }

    public Impuestos20Bean getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Impuestos20Bean impuestos) {
        this.impuestos = impuestos;
    }

    public ClienteBean getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
    }

    public Proveedor20Bean getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor20Bean proveedor) {
        this.proveedor = proveedor;
    }
}
