package io.github.carlosthe19916.beans.ubl;

import java.util.TimeZone;

public interface UBLDefaults {

    default TimeZone getTimeZone() {
        return TimeZone.getTimeZone("America/Lima");
    }

}
