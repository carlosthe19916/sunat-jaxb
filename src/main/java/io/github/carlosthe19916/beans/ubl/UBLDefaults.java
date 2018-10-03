package io.github.carlosthe19916.beans.ubl;

import java.math.BigDecimal;
import java.util.TimeZone;

public interface UBLDefaults {

    default TimeZone getTimeZone() {
        return TimeZone.getTimeZone("America/Lima");
    }

    default BigDecimal getIgv() {
        return new BigDecimal("0.18");
    }

    // default BigDecimal getIsc() {
    //     return new BigDecimal("0.10");
    // }

}
