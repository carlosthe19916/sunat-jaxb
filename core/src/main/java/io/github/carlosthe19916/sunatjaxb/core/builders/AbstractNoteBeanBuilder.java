package io.github.carlosthe19916.sunatjaxb.core.builders;

import io.github.carlosthe19916.sunatjaxb.core.beans.AbstractNoteBean;

public abstract class AbstractNoteBeanBuilder<Builder extends AbstractNoteBeanBuilder, Bean extends AbstractNoteBean> {

    protected final Bean note;

    protected AbstractNoteBeanBuilder(Bean note) {
        this.note = note;
    }

    protected abstract Builder getCreditNoteBuilder();

    public Builder serie(String serie) {
        note.setSerie(serie);
        return getCreditNoteBuilder();
    }

    public Builder numero(Integer numero) {
        note.setNumero(String.valueOf(numero));
        return getCreditNoteBuilder();
    }

    public Builder codigoGeneradoPorSoftware(String codigoGeneradoPorSoftware) {
        note.setCodigoGeneradoPorSoftware(codigoGeneradoPorSoftware);
        return getCreditNoteBuilder();
    }

}
