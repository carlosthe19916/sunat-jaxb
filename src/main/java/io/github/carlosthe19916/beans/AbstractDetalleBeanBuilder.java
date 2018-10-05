package io.github.carlosthe19916.beans;

import io.github.carlosthe19916.beans.catalogs.Catalogo7;

import java.math.BigDecimal;

public abstract class AbstractDetalleBeanBuilder<Builder extends AbstractDetalleBeanBuilder, Bean extends DetalleBean> {

    protected final Bean detalle;

    protected AbstractDetalleBeanBuilder(Bean bean) {
        detalle = bean;
    }

    protected abstract Builder getDetalleBuilder();

    public Builder unidadMedida(String unidadMedida) {
        detalle.setUnidadMedida(unidadMedida);
        return getDetalleBuilder();
    }

    public Builder descripcion(String descripcion) {
        detalle.setDescripcion(descripcion);
        return getDetalleBuilder();
    }

    public Builder codigoTipoIgv(Catalogo7 tipoAfectacionIgv) {
        detalle.setTipoAfectacionIgv(tipoAfectacionIgv);
        return getDetalleBuilder();
    }

    public Builder codigoTipoIsc(String codigoTipoIsc) {
        detalle.setCodigoTipoIsc(codigoTipoIsc);
        return getDetalleBuilder();
    }

    public Builder cantidad(BigDecimal cantidad) {
        detalle.setCantidad(cantidad);
        return getDetalleBuilder();
    }

    public Builder valorUnitario(BigDecimal valorUnitario) {
        detalle.setValorUnitario(valorUnitario);
        return getDetalleBuilder();
    }

    public Builder precioUnitario(BigDecimal precioUnitario) {
        detalle.setPrecioUnitario(precioUnitario);
        return getDetalleBuilder();
    }

    public Builder subtotal(BigDecimal subtotal) {
        detalle.setSubtotal(subtotal);
        return getDetalleBuilder();
    }

    public Builder total(BigDecimal total) {
        detalle.setTotal(total);
        return getDetalleBuilder();
    }

    public Builder totalIgv(BigDecimal totalIgv) {
        detalle.setTotalIgv(totalIgv);
        return getDetalleBuilder();
    }

    public Builder totalIsc(BigDecimal totalIsc) {
        detalle.setTotalIsc(totalIsc);
        return getDetalleBuilder();
    }

}
