package io.github.carlosthe19916.beans.config;

import java.math.BigDecimal;
import java.util.TimeZone;

public interface UBLDefaults {

    String getUblVersion();

    String getCustomizationId();

    boolean getAplicarCalculosInternosAutomaticos();

    TimeZone getTimeZone();

    BigDecimal getIgvValue();

}
