package io.github.carlosthe19916.beans;

public class InvoiceBeanBuilder {

    private final InvoiceBean invoice;

    public InvoiceBeanBuilder() {
        this.invoice = new InvoiceBean();
    }

    public static InvoiceBeanBuilder InvoiceBean() {
        return new InvoiceBeanBuilder();
    }

    public InvoiceBeanBuilder serie(String serie) {
        invoice.setSerie(serie);
        return this;
    }

    public InvoiceBeanBuilder numero(Integer numero) {
        invoice.setNumero(numero);
        return this;
    }

    public InvoiceBeanBuilder codigoTipoComprobante(String codigoTipoComprobante) {
        invoice.setCodigoTipoComprobante(codigoTipoComprobante);
        return this;
    }

    public InvoiceBeanBuilder fecha(FechaBean fecha) {
        invoice.setFecha(fecha);
        return this;
    }

    public InvoiceBeanBuilder moneda(MonedaBean moneda) {
        invoice.setMoneda(moneda);
        return this;
    }

    public InvoiceBeanBuilder impuestos(ImpuestosBean impuestos) {
        invoice.setImpuestos(impuestos);
        return this;
    }

    public InvoiceBeanBuilder total(TotalBean total) {
        invoice.setTotal(total);
        return this;
    }

    public InvoiceBeanBuilder totalInformacionAdicional(TotalInformacionAdicionalBean totalInformacionAdicional) {
        invoice.setTotalInformacionAdicional(totalInformacionAdicional);
        return this;
    }

    public InvoiceBeanBuilder observaciones(String observaciones) {
        invoice.setObservaciones(observaciones);
        return this;
    }

    public InvoiceBeanBuilder proveedor(ProveedorBean proveedor) {
        invoice.setProveedor(proveedor);
        return this;
    }

    public InvoiceBeanBuilder cliente(ClienteBean cliente) {
        invoice.setCliente(cliente);
        return this;
    }

    public InvoiceBeanBuilder addDetalle(DetalleBean detalle) {
        invoice.getDetalle().add(detalle);
        return this;
    }

    public InvoiceBean build() {
        return invoice;
    }

}
