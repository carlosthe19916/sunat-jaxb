package io.github.carlosthe19916.sunatjaxb.beans;

import io.github.carlosthe19916.sunatjaxb.catalogos.Catalogo9;

public abstract class AbstractCreditNoteDiscrepancyResponseBeanBuilder<Builder extends AbstractCreditNoteDiscrepancyResponseBeanBuilder, Bean extends CreditNoteDiscrepancyResponseBean> {

    protected final CreditNoteDiscrepancyResponseBean discrepancyResponse;

    protected AbstractCreditNoteDiscrepancyResponseBeanBuilder(Bean bean) {
        this.discrepancyResponse = bean;
    }

    protected abstract Builder getDiscrepancyResponseBuilder();

    public Builder documentoReferencia(String documentoReferencia) {
        discrepancyResponse.setReferenceID(documentoReferencia);
        return getDiscrepancyResponseBuilder();
    }

    public Builder tipoNotaCredito(Catalogo9 tipoNota) {
        discrepancyResponse.setResponseCode(tipoNota);
        return getDiscrepancyResponseBuilder();
    }

    public Builder sustento(String sustento) {
        discrepancyResponse.setDescription(sustento);
        return getDiscrepancyResponseBuilder();
    }
}
