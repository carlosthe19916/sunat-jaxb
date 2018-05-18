package io.github.carlosthe19916.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class InvoiceAfectadoBean {

    @NotNull
    @Size(min = 4, max = 4)
    private String serie;

    @NotNull
    private Integer numero;

    @NotNull
    private String codigoTipoComprobante;

    private Date fechaEmision;

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

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
}
