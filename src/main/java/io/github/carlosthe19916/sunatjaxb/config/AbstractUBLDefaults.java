package io.github.carlosthe19916.sunatjaxb.config;

import java.math.BigDecimal;
import java.util.TimeZone;

public abstract class AbstractUBLDefaults implements UBLDefaults {

    private boolean aplicarCalculosInternosAutomaticos = false;
    private TimeZone timeZone = TimeZone.getTimeZone("America/Lima");
    private BigDecimal igvDecimalValue = new BigDecimal("0.18");

    @Override
    public boolean applyInternalMappers() {
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
    public BigDecimal getIgvDecimalValue() {
        return igvDecimalValue;
    }

    public void setIgvDecimalValue(BigDecimal igvDecimalValue) {
        this.igvDecimalValue = igvDecimalValue;
    }

}
