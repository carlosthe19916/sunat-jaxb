package io.github.carlosthe19916.beans;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class InvoiceBean {

    @NotNull
    @Size(min = 4, max = 4)
    private String serie;

    @NotNull
    private Integer numero;

    @NotNull
    private String codigoTipoComprobante;

    @Valid
    @NotNull
    private FechaBean fecha;

    @Valid
    @NotNull
    private MonedaBean moneda;

    @Valid
    @NotNull
    private TotalInformacionAdicionalBean totalInformacionAdicional;

    @Valid
    @NotNull
    private ClienteBean cliente;

    @Valid
    @NotNull
    private ProveedorBean proveedor;

    @Size(min = 1)
    @NotNull
    private List<@Valid DetalleBean> detalle;

    public InvoiceBean() {
    }

    public InvoiceBean(InvoiceBean invoice) {
        serie = invoice.serie;
        numero = invoice.numero;
        codigoTipoComprobante = invoice.codigoTipoComprobante;
        fecha = invoice.fecha;
        moneda = invoice.moneda;
        totalInformacionAdicional = invoice.totalInformacionAdicional;
        cliente = invoice.cliente;
        proveedor = invoice.proveedor;
        detalle = invoice.detalle;
    }

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

    public FechaBean getFecha() {
        return fecha;
    }

    public void setFecha(FechaBean fecha) {
        this.fecha = fecha;
    }

    public MonedaBean getMoneda() {
        return moneda;
    }

    public void setMoneda(MonedaBean moneda) {
        this.moneda = moneda;
    }

    public TotalInformacionAdicionalBean getTotalInformacionAdicional() {
        return totalInformacionAdicional;
    }

    public void setTotalInformacionAdicional(TotalInformacionAdicionalBean totalInformacionAdicional) {
        this.totalInformacionAdicional = totalInformacionAdicional;
    }

    public ClienteBean getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
    }

    public ProveedorBean getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorBean proveedor) {
        this.proveedor = proveedor;
    }

    public List<DetalleBean> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleBean> detalle) {
        this.detalle = detalle;
    }

}
