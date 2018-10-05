package io.github.carlosthe19916.beans;

import io.github.carlosthe19916.beans.catalogs.Catalogo12;

import javax.validation.constraints.NotNull;

public class OtroDocumentoRelacionadoBean {

    @NotNull
    private String documentoRelacionado;

    @NotNull
    private Catalogo12 tipoDocumentoRelacionado;


    public String getDocumentoRelacionado() {
        return documentoRelacionado;
    }

    public void setDocumentoRelacionado(String documentoRelacionado) {
        this.documentoRelacionado = documentoRelacionado;
    }

    public Catalogo12 getTipoDocumentoRelacionado() {
        return tipoDocumentoRelacionado;
    }

    public void setTipoDocumentoRelacionado(Catalogo12 tipoDocumentoRelacionado) {
        this.tipoDocumentoRelacionado = tipoDocumentoRelacionado;
    }
}
