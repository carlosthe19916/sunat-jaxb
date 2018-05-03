package io.github.carlosthe19916.beans;

public class NoteBean extends TypeBean {

    private InvoiceBean invoiceAfectado;
    private String codigoMotivo;

    public InvoiceBean getInvoiceAfectado() {
        return invoiceAfectado;
    }

    public void setInvoiceAfectado(InvoiceBean invoiceAfectado) {
        this.invoiceAfectado = invoiceAfectado;
    }

    public String getCodigoMotivo() {
        return codigoMotivo;
    }

    public void setCodigoMotivo(String codigoMotivo) {
        this.codigoMotivo = codigoMotivo;
    }
}
