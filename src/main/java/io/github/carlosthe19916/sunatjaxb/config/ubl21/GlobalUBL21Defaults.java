package io.github.carlosthe19916.sunatjaxb.config.ubl21;

import io.github.carlosthe19916.sunatjaxb.catalogos.Catalogo17;
import io.github.carlosthe19916.sunatjaxb.config.AbstractUBLDefaults;

public class GlobalUBL21Defaults extends AbstractUBLDefaults implements UBL21Defaults {

    private volatile static GlobalUBL21Defaults instance = new GlobalUBL21Defaults();

    private Catalogo17 defaultTipoOperacion = Catalogo17.VENTA_INTERNA;
    private String ublVersion = "2.1";
    private String customizationId = "2.0";

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
    public Catalogo17 getTipoOperacion() {
        return defaultTipoOperacion;
    }

    public Catalogo17 setTipoOperacion(Catalogo17 defaultTipoOperacion) {
        return this.defaultTipoOperacion = defaultTipoOperacion;
    }

    public String getUBLVersion() {
        return ublVersion;
    }

    public void setUblVersion(String ublVersion) {
        this.ublVersion = ublVersion;
    }

    public String getCustomizationID() {
        return customizationId;
    }

    public void setCustomizationId(String customizationId) {
        this.customizationId = customizationId;
    }
}
