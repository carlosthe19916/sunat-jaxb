package io.github.carlosthe19916.sunatjaxb.beans;

import io.github.carlosthe19916.sunatjaxb.catalogos.Catalogo10;

import javax.validation.constraints.NotNull;

public class DebitNoteDiscrepancyResponseBean extends DiscrepancyResponseBean {
    @NotNull
    private Catalogo10 responseCode;

    public Catalogo10 getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Catalogo10 responseCode) {
        this.responseCode = responseCode;
    }
}
