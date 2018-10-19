package io.github.carlosthe19916.sunatjaxb.ubl21.beans;

import io.github.carlosthe19916.sunatjaxb.core.beans.AbstractNoteBean;
import io.github.carlosthe19916.sunatjaxb.core.beans.ClienteBean;
import io.github.carlosthe19916.sunatjaxb.core.catalogos.Catalogo10;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DebitNote21Bean extends AbstractNoteBean {

    @NotNull
    private Catalogo10 tipoNotaDebito;

    @Valid
    @NotNull
    private Total21Bean total;

    @Valid
    @NotNull
    private Proveedor21Bean proveedor;

    @Valid
    @NotNull
    private ClienteBean cliente;

    @Valid
    @NotNull
    private Impuestos21Bean impuestos;

    public Proveedor21Bean getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor21Bean proveedor) {
        this.proveedor = proveedor;
    }

    public Total21Bean getTotal() {
        return total;
    }

    public void setTotal(Total21Bean total) {
        this.total = total;
    }

    public ClienteBean getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
    }

    public Impuestos21Bean getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Impuestos21Bean impuestos) {
        this.impuestos = impuestos;
    }

    @NotNull
    public Catalogo10 getTipoNotaDebito() {
        return tipoNotaDebito;
    }

    public void setTipoNotaDebito(@NotNull Catalogo10 tipoNotaDebito) {
        this.tipoNotaDebito = tipoNotaDebito;
    }
}
