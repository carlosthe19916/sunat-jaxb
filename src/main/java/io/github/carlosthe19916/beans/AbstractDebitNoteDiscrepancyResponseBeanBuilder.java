package io.github.carlosthe19916.beans;

import io.github.carlosthe19916.beans.catalogs.Catalogo10;

public abstract class AbstractDebitNoteDiscrepancyResponseBeanBuilder<Builder extends AbstractDebitNoteDiscrepancyResponseBeanBuilder, Bean extends DebitNoteDiscrepancyResponseBean> {

    protected final DebitNoteDiscrepancyResponseBean discrepancyResponse;

    protected AbstractDebitNoteDiscrepancyResponseBeanBuilder(Bean bean) {
        this.discrepancyResponse = bean;
    }

    protected abstract Builder getDiscrepancyResponseBuilder();

    public Builder documentoReferencia(String documentoReferencia) {
        discrepancyResponse.setReferenceID(documentoReferencia);
        return getDiscrepancyResponseBuilder();
    }

    public Builder tipoNotaDebito(Catalogo10 tipoNota) {
        discrepancyResponse.setResponseCode(tipoNota);
        return getDiscrepancyResponseBuilder();
    }

    public Builder sustento(String sustento) {
        discrepancyResponse.setDescription(sustento);
        return getDiscrepancyResponseBuilder();
    }
}
