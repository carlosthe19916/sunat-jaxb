package io.github.carlosthe19916.beans;

import java.math.BigDecimal;

public abstract class AbstractTotalInformacionAdicionalBeanBuilder<Builder extends AbstractTotalInformacionAdicionalBeanBuilder, Bean extends TotalInformacionAdicionalBean> {

    protected final Bean totalInformacionAdicional;

    protected AbstractTotalInformacionAdicionalBeanBuilder(Bean totalInformacionAdicional) {
        this.totalInformacionAdicional = totalInformacionAdicional;
    }

    protected abstract Builder getTotalInformacionAdicionalBuilder();

    public Builder gravado(BigDecimal gravado) {
        totalInformacionAdicional.setGravado(gravado);
        return getTotalInformacionAdicionalBuilder();
    }

    public Builder inafecto(BigDecimal inafecto) {
        totalInformacionAdicional.setInafecto(inafecto);
        return getTotalInformacionAdicionalBuilder();
    }

    public Builder exonerado(BigDecimal exonerado) {
        totalInformacionAdicional.setExonerado(exonerado);
        return getTotalInformacionAdicionalBuilder();
    }

    public Builder gratuito(BigDecimal gratuito) {
        totalInformacionAdicional.setGratuito(gratuito);
        return getTotalInformacionAdicionalBuilder();
    }

}
