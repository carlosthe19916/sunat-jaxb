package io.github.carlosthe19916.sunatjaxb.config;

import java.math.BigDecimal;
import java.util.TimeZone;

public interface UBLDefaults {

    String getUBLVersion();

    String getCustomizationID();

    boolean isInternalMappersApplied();

    TimeZone getTimeZone();

    BigDecimal getIgvDecimalValue();

}
