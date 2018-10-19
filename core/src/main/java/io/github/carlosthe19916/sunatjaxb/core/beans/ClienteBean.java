package io.github.carlosthe19916.sunatjaxb.core.beans;

import io.github.carlosthe19916.sunatjaxb.core.catalogos.Catalogo6;

public class ClienteBean {

    private Catalogo6 codigoTipoDocumento;
    private String numeroDocumento;
    private String nombre;
    private String email;
    private String direccion;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
