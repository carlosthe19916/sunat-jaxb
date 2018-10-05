package io.github.carlosthe19916.beans;

import io.github.carlosthe19916.beans.catalogs.Catalogo1;

import java.util.ArrayList;
import java.util.List;

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

    public Builder fecha(FechaBean fecha) {
        invoice.setFecha(fecha);
        return getInvoiceBuilder();
    }

    public Builder totalInformacionAdicional(TotalInformacionAdicionalBean totalInformacionAdicional) {
        invoice.setTotalInformacionAdicional(totalInformacionAdicional);
        return getInvoiceBuilder();
    }

    public Builder addDetalle(DetalleBean detalle) {
        List<DetalleBean> list = invoice.getDetalle();
        if (list == null) {
            list = new ArrayList<>();
            invoice.setDetalle(list);
        }
        invoice.getDetalle().add(detalle);
        return getInvoiceBuilder();
    }

    public Builder codigoGeneradoPorSoftware(String codigoGeneradoPorSoftware) {
        invoice.setCodigoGeneradoPorSoftware(codigoGeneradoPorSoftware);
        return getInvoiceBuilder();
    }

    public Builder guiaRemisionRelacionada(GuiaRemisionRelacionadaBean guiaRemisionRelacionada) {
        invoice.setGuiaRemisionRelacionada(guiaRemisionRelacionada);
        return getInvoiceBuilder();
    }

    public Builder otroDocumentoRelacionado(OtroDocumentoRelacionadoBean otroDocumentoRelacionado) {
        invoice.setOtroDocumentoRelacionado(otroDocumentoRelacionado);
        return getInvoiceBuilder();
    }

}
