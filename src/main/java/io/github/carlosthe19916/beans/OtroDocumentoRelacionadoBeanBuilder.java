package io.github.carlosthe19916.beans;

import io.github.carlosthe19916.beans.catalogs.Catalogo12;

public class OtroDocumentoRelacionadoBeanBuilder {

    private final OtroDocumentoRelacionadoBean otroDocumentoRelacionado;

    public OtroDocumentoRelacionadoBeanBuilder() {
        this.otroDocumentoRelacionado = new OtroDocumentoRelacionadoBean();
    }

    public static OtroDocumentoRelacionadoBeanBuilder builder() {
        return new OtroDocumentoRelacionadoBeanBuilder();
    }

    public OtroDocumentoRelacionadoBean build() {
        return otroDocumentoRelacionado;
    }

    public OtroDocumentoRelacionadoBeanBuilder documentoRelacionado(String documentoRelacionado) {
        otroDocumentoRelacionado.setDocumentoRelacionado(documentoRelacionado);
        return this;
    }

    public OtroDocumentoRelacionadoBeanBuilder tipoDocumentoRelacionado(Catalogo12 tipoDocumentoRelacionado) {
        otroDocumentoRelacionado.setTipoDocumentoRelacionado(tipoDocumentoRelacionado);
        return this;
    }

}
