package io.github.carlosthe19916.beans.config.ubl20;

import io.github.carlosthe19916.beans.config.AbstractUBLDefaults;

public class GlobalUBL20Defaults extends AbstractUBLDefaults implements UBL20Defaults {

    private volatile static GlobalUBL20Defaults instance = new GlobalUBL20Defaults();

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
