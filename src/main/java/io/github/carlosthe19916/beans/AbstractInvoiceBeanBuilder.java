package io.github.carlosthe19916.beans;

import io.github.carlosthe19916.beans.catalogs.Catalogo1;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractInvoiceBeanBuilder<Builder extends AbstractInvoiceBeanBuilder, Bean extends AbstractInvoiceBean> {

    private final Bean bean;

    public AbstractInvoiceBeanBuilder(Bean bean) {
        this.bean = bean;
    }

    public Bean getBean() {
        return bean;
    }

    public abstract Builder getBuilder();

    public Builder serie(String serie) {
        bean.setSerie(serie);
        return getBuilder();
    }

    public Builder numero(Integer numero) {
        bean.setNumero(numero);
        return getBuilder();
    }

    public Builder tipoComprobante(Catalogo1 tipoComprobante) {
        bean.setTipoDocumento(tipoComprobante);
        return getBuilder();
    }

    public Builder fecha(FechaBean fecha) {
        bean.setFecha(fecha);
        return getBuilder();
    }

    public Builder moneda(MonedaBean moneda) {
        bean.setMoneda(moneda);
        return getBuilder();
    }

    public Builder totalInformacionAdicional(TotalInformacionAdicionalBean totalInformacionAdicional) {
        bean.setTotalInformacionAdicional(totalInformacionAdicional);
        return getBuilder();
    }

    public Builder addDetalle(DetalleBean detalle) {
        List<DetalleBean> list = bean.getDetalle();
        if (list == null) {
            list = new ArrayList<>();
            bean.setDetalle(list);
        }
        bean.getDetalle().add(detalle);
        return getBuilder();
    }

    public Builder codigoGeneradoPorSoftware(String codigoGeneradoPorSoftware) {
        bean.setCodigoGeneradoPorSoftware(codigoGeneradoPorSoftware);
        return getBuilder();
    }

    public Builder guiaRemisionRelacionada(GuiaRemisionRelacionadaBean guiaRemisionRelacionada) {
        bean.setGuiaRemisionRelacionada(guiaRemisionRelacionada);
        return getBuilder();
    }

    public Builder otroDocumentoRelacionado(OtroDocumentoRelacionadoBean otroDocumentoRelacionado) {
        bean.setOtroDocumentoRelacionado(otroDocumentoRelacionado);
        return getBuilder();
    }

}
