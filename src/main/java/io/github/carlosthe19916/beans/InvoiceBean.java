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
    private TotalBean total;

    @Valid
    @NotNull
    private MonedaBean moneda;

    @Valid
    @NotNull
    private ImpuestosBean impuestos;

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
    private List<@Valid TypeLineBean> detalle;

    private String observaciones;

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

    public TotalBean getTotal() {
        return total;
    }

    public void setTotal(TotalBean total) {
        this.total = total;
    }

    public MonedaBean getMoneda() {
        return moneda;
    }

    public void setMoneda(MonedaBean moneda) {
        this.moneda = moneda;
    }

    public ImpuestosBean getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(ImpuestosBean impuestos) {
        this.impuestos = impuestos;
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

    public List<TypeLineBean> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<TypeLineBean> detalle) {
        this.detalle = detalle;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
