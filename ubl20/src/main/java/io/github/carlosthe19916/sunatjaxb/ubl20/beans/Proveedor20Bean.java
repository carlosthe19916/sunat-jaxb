package io.github.carlosthe19916.sunatjaxb.ubl20.beans;

import io.github.carlosthe19916.sunatjaxb.beans.AbstractProveedorBean;

import javax.validation.constraints.NotNull;

public class Proveedor20Bean extends AbstractProveedorBean {

    @NotNull
    private String direccion;

    @NotNull
    private String provincia;

    @NotNull
    private String distrito;

    @NotNull
    private String region;

    @NotNull
    private String codigoPais;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }
}
