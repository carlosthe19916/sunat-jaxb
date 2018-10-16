package io.github.carlosthe19916.sunatjaxb.ubl21.beans;

import io.github.carlosthe19916.sunatjaxb.beans.AbstractInvoiceBean;
import io.github.carlosthe19916.sunatjaxb.beans.ClienteBean;
import io.github.carlosthe19916.sunatjaxb.catalogos.Catalogo17;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Invoice21Bean extends AbstractInvoiceBean {

    @NotNull
    private Catalogo17 tipoOperacion;

    @Valid
    @NotNull
    private Total21Bean total;

    @Valid
    @NotNull
    private Impuestos21Bean impuestos;

    @Valid
    @NotNull
    private ClienteBean cliente;

    @Valid
    @NotNull
    private Proveedor21Bean proveedor;

    public Catalogo17 getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(Catalogo17 tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Total21Bean getTotal() {
        return total;
    }

    public void setTotal(Total21Bean total) {
        this.total = total;
    }

    public Impuestos21Bean getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Impuestos21Bean impuestos) {
        this.impuestos = impuestos;
    }

    public ClienteBean getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
    }

    public Proveedor21Bean getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor21Bean proveedor) {
        this.proveedor = proveedor;
    }
}
