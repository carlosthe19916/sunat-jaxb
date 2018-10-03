package io.github.carlosthe19916.beans.config.ubl21;

import io.github.carlosthe19916.beans.catalogs.Catalog51;
import io.github.carlosthe19916.beans.config.AbstractUBLDefaults;

public class GlobalUBL21Defaults extends AbstractUBLDefaults implements UBL21Defaults {

    private volatile static GlobalUBL21Defaults instance = new GlobalUBL21Defaults();

    private Catalog51 defaultTipoOperacion = Catalog51.VENTA_INTERNA;

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
        return defaultTipoOperacion;
    }

    public Catalog51 setTipoOperacion(Catalog51 defaultTipoOperacion) {
        return this.defaultTipoOperacion = defaultTipoOperacion;
    }

}
