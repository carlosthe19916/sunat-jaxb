package io.github.carlosthe19916.sunatjaxb.ubl21.mappers;

import java.math.BigDecimal;
import java.util.TimeZone;

public abstract class AbstractCoreDefaults implements CoreMapperDefaults {

    private boolean internalMappersApplied;
    private TimeZone timeZone;
    private BigDecimal igvDecimalValue;

    @Override
    public boolean isInternalMappersApplied() {
        return internalMappersApplied;
    }

    public void setInternalMappersApplied(boolean internalmappersapplied) {
        this.internalMappersApplied = internalmappersapplied;
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
