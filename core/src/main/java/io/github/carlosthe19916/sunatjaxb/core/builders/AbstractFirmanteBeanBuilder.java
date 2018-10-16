package io.github.carlosthe19916.sunatjaxb.core.builders;

import io.github.carlosthe19916.sunatjaxb.core.beans.FirmanteBean;

public abstract class AbstractFirmanteBeanBuilder<Builder extends AbstractFirmanteBeanBuilder, Bean extends FirmanteBean> {

    protected final Bean firmante;

    protected AbstractFirmanteBeanBuilder(Bean bean) {
        this.firmante = bean;
    }

    protected abstract Builder getFirmanteBuilder();

    public Builder idFirma(String idFirma) {
        firmante.setIdFirma(idFirma);
        return getFirmanteBuilder();
    }

    public Builder idEmpresa(String idEmpresa) {
        firmante.setIdEmpresa(idEmpresa);
        return getFirmanteBuilder();
    }

    public Builder nombreEmpresa(String nombreEmpresa) {
        firmante.setNombreEmpresa(nombreEmpresa);
        return getFirmanteBuilder();
    }

}
