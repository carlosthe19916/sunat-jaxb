package io.github.carlosthe19916.beans;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NoteBean extends AbstractBean {

    @NotNull
    @Valid
    private InvoiceAfectado invoiceAfectado;

    @NotNull
    private String codigoMotivo;

    public InvoiceAfectado getInvoiceAfectado() {
        return invoiceAfectado;
    }

    public void setInvoiceAfectado(InvoiceAfectado invoiceAfectado) {
        this.invoiceAfectado = invoiceAfectado;
    }

    public String getCodigoMotivo() {
        return codigoMotivo;
    }

    public void setCodigoMotivo(String codigoMotivo) {
        this.codigoMotivo = codigoMotivo;
    }

    public static class InvoiceAfectado {

        @NotNull
        @Size(min = 4, max = 4)
        private String serie;

        @NotNull
        private Integer numero;

        @NotNull
        private String codigoTipoComprobante;

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

        public String getCodigoTipoComprobante() {
            return codigoTipoComprobante;
        }

        public void setCodigoTipoComprobante(String codigoTipoComprobante) {
            this.codigoTipoComprobante = codigoTipoComprobante;
        }
    }
}
