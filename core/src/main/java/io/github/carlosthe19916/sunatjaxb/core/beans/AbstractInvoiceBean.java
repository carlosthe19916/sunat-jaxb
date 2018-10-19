package io.github.carlosthe19916.sunatjaxb.core.beans;

import io.github.carlosthe19916.sunatjaxb.core.catalogos.Catalogo1;

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
    @Min(value = 1)
    @Max(value = 99_999_999)
    private String numero;

    @NotNull
    private Catalogo1 tipoDocumento;

    private String codigoGeneradoPorSoftware;

    @Valid
    @NotNull
    private FechaBean fecha;

    @Valid
    @NotNull
    private MonedaBean moneda;

    @Valid
    private FirmanteBean firmante;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<DetalleBean> detalle;

    @Valid
    private GuiaRemisionRelacionadaBean guiaRemisionRelacionada;

    @Valid
    private OtroDocumentoRelacionadoBean otroDocumentoRelacionado;

    @Valid
    @NotNull
    private TotalInformacionAdicionalBean totalInformacionAdicional;

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Catalogo1 getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Catalogo1 tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getCodigoGeneradoPorSoftware() {
        return codigoGeneradoPorSoftware;
    }

    public void setCodigoGeneradoPorSoftware(String codigoGeneradoPorSoftware) {
        this.codigoGeneradoPorSoftware = codigoGeneradoPorSoftware;
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

    public FirmanteBean getFirmante() {
        return firmante;
    }

    public void setFirmante(FirmanteBean firmante) {
        this.firmante = firmante;
    }

    public List<DetalleBean> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleBean> detalle) {
        this.detalle = detalle;
    }

    public GuiaRemisionRelacionadaBean getGuiaRemisionRelacionada() {
        return guiaRemisionRelacionada;
    }

    public void setGuiaRemisionRelacionada(GuiaRemisionRelacionadaBean guiaRemisionRelacionada) {
        this.guiaRemisionRelacionada = guiaRemisionRelacionada;
    }

    public OtroDocumentoRelacionadoBean getOtroDocumentoRelacionado() {
        return otroDocumentoRelacionado;
    }

    public void setOtroDocumentoRelacionado(OtroDocumentoRelacionadoBean otroDocumentoRelacionado) {
        this.otroDocumentoRelacionado = otroDocumentoRelacionado;
    }

    public TotalInformacionAdicionalBean getTotalInformacionAdicional() {
        return totalInformacionAdicional;
    }

    public void setTotalInformacionAdicional(TotalInformacionAdicionalBean totalInformacionAdicional) {
        this.totalInformacionAdicional = totalInformacionAdicional;
    }
}
