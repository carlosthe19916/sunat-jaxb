package io.github.carlosthe19916.beans.ubl.ubl21;

import io.github.carlosthe19916.beans.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CreditNote21Bean extends AbstractNoteBean {

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
    private CreditNoteDiscrepancyResponseBean documentoRelacionado;

    @Valid
    @NotNull
    private DocumentReferenceBean documentoModificado;

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

    public CreditNoteDiscrepancyResponseBean getDocumentoRelacionado() {
        return documentoRelacionado;
    }

    public void setDocumentoRelacionado(CreditNoteDiscrepancyResponseBean documentoRelacionado) {
        this.documentoRelacionado = documentoRelacionado;
    }

    public DocumentReferenceBean getDocumentoModificado() {
        return documentoModificado;
    }

    public void setDocumentoModificado(DocumentReferenceBean documentoModificado) {
        this.documentoModificado = documentoModificado;
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
}
