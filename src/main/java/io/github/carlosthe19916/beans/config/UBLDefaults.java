package io.github.carlosthe19916.beans.config;

import java.math.BigDecimal;
import java.util.TimeZone;

public interface UBLDefaults {

    boolean getAplicarCalculosInternosAutomaticos();

    TimeZone getTimeZone();

    BigDecimal getIgvValue();

}
