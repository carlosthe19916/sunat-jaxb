package io.github.carlosthe19916.beans;


import io.github.carlosthe19916.utils.StringUtils;

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
        String documentId = StringUtils.padLeft(String.valueOf(numero), 8, "0");
        note.setNumero(documentId);
        return getCreditNoteBuilder();
    }

    public Builder codigoGeneradoPorSoftware(String codigoGeneradoPorSoftware) {
        note.setCodigoGeneradoPorSoftware(codigoGeneradoPorSoftware);
        return getCreditNoteBuilder();
    }

}
