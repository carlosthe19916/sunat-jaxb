package io.github.carlosthe19916.sunatjaxb.core.beans;

import io.github.carlosthe19916.sunatjaxb.core.catalogos.Catalogo6;

import javax.validation.constraints.NotNull;

public class AbstractProveedorBean {

    @NotNull
    protected Catalogo6 codigoTipoDocumento;

    @NotNull
    protected String numeroDocumento;

    @NotNull
    protected String nombreComercial;

    @NotNull
    protected String razonSocial;

    @NotNull
    protected String codigoPostal;

    public Catalogo6 getCodigoTipoDocumento() {
        return codigoTipoDocumento;
    }

    public void setCodigoTipoDocumento(Catalogo6 codigoTipoDocumento) {
        this.codigoTipoDocumento = codigoTipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

}
