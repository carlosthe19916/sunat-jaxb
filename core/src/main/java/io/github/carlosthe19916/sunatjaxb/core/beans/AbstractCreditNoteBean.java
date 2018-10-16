package io.github.carlosthe19916.sunatjaxb.core.beans;

import io.github.carlosthe19916.sunatjaxb.core.catalogos.Catalogo9;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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
