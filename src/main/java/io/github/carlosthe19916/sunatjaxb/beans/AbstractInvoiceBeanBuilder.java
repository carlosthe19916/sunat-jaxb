package io.github.carlosthe19916.sunatjaxb.beans;

import io.github.carlosthe19916.sunatjaxb.catalogos.Catalogo1;

public abstract class AbstractInvoiceBeanBuilder<Builder extends AbstractInvoiceBeanBuilder, Bean extends AbstractInvoiceBean> {

    protected final Bean invoice;

    protected AbstractInvoiceBeanBuilder(Bean invoice) {
        this.invoice = invoice;
    }

    protected abstract Builder getInvoiceBuilder();

    public Builder serie(String serie) {
        invoice.setSerie(serie);
        return getInvoiceBuilder();
    }

    public Builder numero(Integer numero) {
        invoice.setNumero(numero);
        return getInvoiceBuilder();
    }

    public Builder tipoComprobante(Catalogo1 tipoComprobante) {
        invoice.setTipoDocumento(tipoComprobante);
        return getInvoiceBuilder();
    }

    public Builder codigoGeneradoPorSoftware(String codigoGeneradoPorSoftware) {
        invoice.setCodigoGeneradoPorSoftware(codigoGeneradoPorSoftware);
        return getInvoiceBuilder();
    }

}
