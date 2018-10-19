package io.github.carlosthe19916.sunatjaxb.core.beans;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public abstract class AbstractNoteBean {

    @NotNull
    @Size(min = 4, max = 4)
    private String serie;

    @NotNull
    @Min(value = 1)
    @Max(value = 99_999_999)
    private String numero;

    @NotNull
    private String motivoNota;

    @NotNull
    @Valid
    private DocumentoAfectadoBean documentoAfectado;

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
    @Size(min = 1)
    @NotNull
    private List<DetalleBean> detalle;

    private String codigoGeneradoPorSoftware;

    @Valid
    private GuiaRemisionRelacionadaBean guiaRemisionRelacionada;

    @Valid
    private OtroDocumentoRelacionadoBean otroDocumentoRelacionado;

    @Valid
    private FirmanteBean firmante;

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

    public String getCodigoGeneradoPorSoftware() {
        return codigoGeneradoPorSoftware;
    }

    public void setCodigoGeneradoPorSoftware(String codigoGeneradoPorSoftware) {
        this.codigoGeneradoPorSoftware = codigoGeneradoPorSoftware;
    }

    public OtroDocumentoRelacionadoBean getOtroDocumentoRelacionado() {
        return otroDocumentoRelacionado;
    }

    public void setOtroDocumentoRelacionado(OtroDocumentoRelacionadoBean otroDocumentoRelacionado) {
        this.otroDocumentoRelacionado = otroDocumentoRelacionado;
    }

    public FirmanteBean getFirmante() {
        return firmante;
    }

    public void setFirmante(FirmanteBean firmante) {
        this.firmante = firmante;
    }

    public GuiaRemisionRelacionadaBean getGuiaRemisionRelacionada() {
        return guiaRemisionRelacionada;
    }

    public void setGuiaRemisionRelacionada(GuiaRemisionRelacionadaBean guiaRemisionRelacionada) {
        this.guiaRemisionRelacionada = guiaRemisionRelacionada;
    }

    public String getMotivoNota() {
        return motivoNota;
    }

    public void setMotivoNota(String motivoNota) {
        this.motivoNota = motivoNota;
    }

    public DocumentoAfectadoBean getDocumentoAfectado() {
        return documentoAfectado;
    }

    public void setDocumentoAfectado(DocumentoAfectadoBean documentoAfectado) {
        this.documentoAfectado = documentoAfectado;
    }
}
