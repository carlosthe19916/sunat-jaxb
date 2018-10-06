package io.github.carlosthe19916.sunatjaxb.config;

import io.github.carlosthe19916.sunatjaxb.catalogos.Catalogo17;

public class GlobalUBL21Defaults extends AbstractUBLDefaults implements UBL21Defaults {

    private volatile static GlobalUBL21Defaults instance;

    private String ublVersion = "2.1";
    private String customizationId = "2.0";

    private Catalogo17 tipoOperacion = Catalogo17.VENTA_INTERNA;

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
        return tipoOperacion;
    }

    public Catalogo17 setTipoOperacion(Catalogo17 tipoOperacion) {
        return this.tipoOperacion = tipoOperacion;
    }

    public String getUBLVersion() {
        return ublVersion;
    }

    public void setUBLVersion(String ublVersion) {
        this.ublVersion = ublVersion;
    }

    public String getCustomizationID() {
        return customizationId;
    }

    public void setCustomizationID(String customizationId) {
        this.customizationId = customizationId;
    }
}
