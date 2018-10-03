package io.github.carlosthe19916.beans.ubl.ubl21;

import io.github.carlosthe19916.beans.catalogs.Catalog51;

import java.util.TimeZone;

public class GlobalUBL21Defaults extends AbstractUBL21Defaults {

    private static GlobalUBL21Defaults instance = new GlobalUBL21Defaults();

    private GlobalUBL21Defaults() {
        // Singleton
    }

    public static GlobalUBL21Defaults getInstance() {
        if (instance == null) {
            synchronized (GlobalUBL21Defaults.class) {
                if (instance == null) {
                    instance = new GlobalUBL21Defaults();
                }
            }
        }
        return instance;
    }

    @Override
    public Catalog51 getTipoOperacion() {
        return Catalog51.VENTA_INTERNA;
    }

}
