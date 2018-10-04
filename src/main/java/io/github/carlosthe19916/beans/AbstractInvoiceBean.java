package io.github.carlosthe19916.beans;

import io.github.carlosthe19916.beans.catalogs.TipoInvoice;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public abstract class AbstractInvoiceBean {

    @NotNull
    @Size(min = 4, max = 4)
    private String serie;

    @NotNull
    @Min(1)
    @Max(99_999_999)
    private Integer numero;

    @NotNull
    private TipoInvoice tipoDocumento;

    @Valid
    @NotNull
    private FechaBean fecha;

    @Valid
    @NotNull
    private MonedaBean moneda;

    @Valid
    @NotNull
    private TotalInformacionAdicionalBean totalInformacionAdicional;

    @Size(min = 1)
    @NotNull
    private List<@Valid DetalleBean> detalle;

    public AbstractInvoiceBean() {
    }

    public AbstractInvoiceBean(AbstractInvoiceBean invoice) {
        serie = invoice.serie;
        numero = invoice.numero;
        tipoDocumento = invoice.tipoDocumento;
        fecha = invoice.fecha;
        moneda = invoice.moneda;
        totalInformacionAdicional = invoice.totalInformacionAdicional;
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

    public TipoInvoice getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoInvoice tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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

    public List<DetalleBean> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleBean> detalle) {
        this.detalle = detalle;
    }

}
