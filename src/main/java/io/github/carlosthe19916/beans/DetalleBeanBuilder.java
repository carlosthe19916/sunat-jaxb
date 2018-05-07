package io.github.carlosthe19916.beans;

import java.math.BigDecimal;

public class DetalleBeanBuilder {

    private final DetalleBean detalle;

    public DetalleBeanBuilder() {
        detalle = new DetalleBean();
    }

    public static DetalleBeanBuilder Detalle() {
        return new DetalleBeanBuilder();
    }

    public DetalleBeanBuilder unidadMedida(String unidadMedida) {
        detalle.setUnidadMedida(unidadMedida);
        return this;
    }

    public DetalleBeanBuilder descripcion(String descripcion) {
        detalle.setDescripcion(descripcion);
        return this;
    }

    public DetalleBeanBuilder codigoTipoIgv(String codigoTipoIgv) {
        detalle.setCodigoTipoIgv(codigoTipoIgv);
        return this;
    }

    public DetalleBeanBuilder codigoTipoIsc(String codigoTipoIsc) {
        detalle.setCodigoTipoIsc(codigoTipoIsc);
        return this;
    }

    public DetalleBeanBuilder cantidad(BigDecimal cantidad) {
        detalle.setCantidad(cantidad);
        return this;
    }

    public DetalleBeanBuilder valorUnitario(BigDecimal valorUnitario) {
        detalle.setValorUnitario(valorUnitario);
        return this;
    }

    public DetalleBeanBuilder precioUnitario(BigDecimal precioUnitario) {
        detalle.setPrecioUnitario(precioUnitario);
        return this;
    }

    public DetalleBeanBuilder subtotal(BigDecimal subtotal) {
        detalle.setSubtotal(subtotal);
        return this;
    }

    public DetalleBeanBuilder total(BigDecimal total) {
        detalle.setTotal(total);
        return this;
    }

    public DetalleBeanBuilder totalIgv(BigDecimal totalIgv) {
        detalle.setTotalIgv(totalIgv);
        return this;
    }

    public DetalleBeanBuilder totalIsc(BigDecimal totalIsc) {
        detalle.setTotalIsc(totalIsc);
        return this;
    }

    public DetalleBean build() {
        return detalle;
    }
}
