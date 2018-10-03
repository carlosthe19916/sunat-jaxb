package io.github.carlosthe19916.beans;

import javax.validation.constraints.NotNull;

public class ProveedorBean {

    @NotNull
    protected String codigoTipoDocumento;

    @NotNull
    protected String numeroDocumento;

    @NotNull
    protected String nombreComercial;

    @NotNull
    protected String razonSocial;

    @NotNull
    protected String codigoPostal;

    public ProveedorBean() {
    }

    public ProveedorBean(ProveedorBean proveedor) {
        codigoTipoDocumento = proveedor.getCodigoTipoDocumento();
        numeroDocumento = proveedor.getNumeroDocumento();
        nombreComercial = proveedor.getNombreComercial();
        razonSocial = proveedor.getRazonSocial();
        codigoPostal = proveedor.getCodigoPostal();
    }

    public String getCodigoTipoDocumento() {
        return codigoTipoDocumento;
    }

    public void setCodigoTipoDocumento(String codigoTipoDocumento) {
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
