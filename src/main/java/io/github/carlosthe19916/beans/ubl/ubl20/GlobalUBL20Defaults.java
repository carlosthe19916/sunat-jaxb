package io.github.carlosthe19916.beans.ubl.ubl20;

public class GlobalUBL20Defaults extends AbstractUBL20Defaults {

    private static GlobalUBL20Defaults instance = new GlobalUBL20Defaults();

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

}
