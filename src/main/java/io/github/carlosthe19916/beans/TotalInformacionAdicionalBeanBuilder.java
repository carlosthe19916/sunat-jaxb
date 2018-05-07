package io.github.carlosthe19916.beans;

import java.math.BigDecimal;

public class TotalInformacionAdicionalBeanBuilder {

    private final TotalInformacionAdicionalBean totalInformacionAdicional;

    public TotalInformacionAdicionalBeanBuilder() {
        totalInformacionAdicional = new TotalInformacionAdicionalBean();
    }

    public static TotalInformacionAdicionalBeanBuilder TotalInformacionAdicionalBean() {
        return new TotalInformacionAdicionalBeanBuilder();
    }

    public TotalInformacionAdicionalBeanBuilder gravado(BigDecimal gravado) {
        totalInformacionAdicional.setGravado(gravado);
        return this;
    }

    public TotalInformacionAdicionalBeanBuilder inafecto(BigDecimal inafecto) {
        totalInformacionAdicional.setInafecto(inafecto);
        return this;
    }

    public TotalInformacionAdicionalBeanBuilder exonerado(BigDecimal exonerado) {
        totalInformacionAdicional.setExonerado(exonerado);
        return this;
    }

    public TotalInformacionAdicionalBeanBuilder gratuito(BigDecimal gratuito) {
        totalInformacionAdicional.setGratuito(gratuito);
        return this;
    }

    public TotalInformacionAdicionalBean build() {
        return totalInformacionAdicional;
    }
}
