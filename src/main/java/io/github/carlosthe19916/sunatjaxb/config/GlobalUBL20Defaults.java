package io.github.carlosthe19916.sunatjaxb.config;

public class GlobalUBL20Defaults extends AbstractUBLDefaults implements UBL20Defaults {

    private volatile static GlobalUBL20Defaults instance;

    private String ublVersion = "2.0";
    private String customizationId = "2.0";

    private GlobalUBL20Defaults() {
        // Singleton
    }

    public static GlobalUBL20Defaults getInstance() {
        if (instance == null) {
            synchronized (GlobalUBL20Defaults.class) {
                if (instance == null) {
                    instance = new GlobalUBL20Defaults();
                }
            }
        }
        return instance;
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
