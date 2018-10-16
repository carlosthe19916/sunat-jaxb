package io.github.carlosthe19916.sunatjaxb.core.beans;

import io.github.carlosthe19916.sunatjaxb.core.catalogos.Catalogo10;
import io.github.carlosthe19916.sunatjaxb.core.catalogos.Catalogo9;

import javax.validation.constraints.NotNull;

public abstract class AbstractDebitNoteBean extends AbstractNoteBean {

    @NotNull
    private Catalogo10 tipoNotaDebito;

    public Catalogo10 getTipoNotaDebito() {
        return tipoNotaDebito;
    }

    public void setTipoNotaDebito(Catalogo10 tipoNotaDebito) {
        this.tipoNotaDebito = tipoNotaDebito;
    }
}
