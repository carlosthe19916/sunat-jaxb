package io.github.carlosthe19916.beans;

import io.github.carlosthe19916.beans.catalogs.Catalogo1;

public abstract class AbstractDocumentReferenceBeanBuilder<Builder extends AbstractDocumentReferenceBeanBuilder, Bean extends DocumentReferenceBean> {
    protected final DocumentReferenceBean documentReference;

    protected AbstractDocumentReferenceBeanBuilder(Bean bean) {
        this.documentReference = bean;
    }

    protected abstract Builder getDocumentoReferenciaBuilder();

    public Builder documentoRelacionado(String documentoRelacionado) {
        documentReference.setDocumentoRelacionado(documentoRelacionado);
        return getDocumentoReferenciaBuilder();
    }

    public Builder tipoDocumentoRelacionado(Catalogo1 tipoDocumentoRelacionado) {
        documentReference.setTipoDocumentoRelacionado(tipoDocumentoRelacionado);
        return getDocumentoReferenciaBuilder();
    }
}
