package io.github.carlosthe19916.sunatjaxb.mappers.core;

import java.math.BigDecimal;
import java.util.TimeZone;

public interface CoreMapperDefaults {

    boolean isInternalMappersApplied();

    TimeZone getTimeZone();

    BigDecimal getIgvDecimalValue();

}
