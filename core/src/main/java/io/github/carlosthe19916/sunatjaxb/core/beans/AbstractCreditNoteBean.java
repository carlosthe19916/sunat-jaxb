package io.github.carlosthe19916.sunatjaxb.core.beans;

import io.github.carlosthe19916.sunatjaxb.core.catalogos.Catalogo9;

import javax.validation.constraints.NotNull;

public abstract class AbstractCreditNoteBean extends AbstractNoteBean {

    @NotNull
    private Catalogo9 tipoNotaCredito;

    public Catalogo9 getTipoNotaCredito() {
        return tipoNotaCredito;
    }

    public void setTipoNotaCredito(Catalogo9 tipoNotaCredito) {
        this.tipoNotaCredito = tipoNotaCredito;
    }
}
