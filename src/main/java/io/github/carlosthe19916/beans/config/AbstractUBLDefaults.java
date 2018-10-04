package io.github.carlosthe19916.beans.config;

import java.math.BigDecimal;
import java.util.TimeZone;

public abstract class AbstractUBLDefaults implements UBLDefaults {

    private boolean aplicarCalculosInternosAutomaticos = false;
    private TimeZone timeZone = TimeZone.getTimeZone("America/Lima");

    private BigDecimal igvValue = new BigDecimal("0.18");

    @Override
    public boolean getAplicarCalculosInternosAutomaticos() {
        return aplicarCalculosInternosAutomaticos;
    }

    public void setAplicarCalculosInternosAutomaticos(boolean aplicarCalculosInternosAutomaticos) {
        this.aplicarCalculosInternosAutomaticos = aplicarCalculosInternosAutomaticos;
    }

    @Override
    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone defaultTimeZone) {
        this.timeZone = defaultTimeZone;
    }

    @Override
    public BigDecimal getIgvValue() {
        return igvValue;
    }

    public void setIgvValue(BigDecimal defaultIgvValue) {
        this.igvValue = defaultIgvValue;
    }

}
