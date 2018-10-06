package io.github.carlosthe19916.sunatjaxb.mappers.core;

public class GlobalCore20MapperDefaults extends AbstractCoreDefaults implements Core20MapperDefaults {

    private volatile static GlobalCore20MapperDefaults instance;

    private GlobalCore20MapperDefaults() {
        // Singleton
    }

    public static GlobalCore20MapperDefaults getInstance() {
        if (instance == null) {
            synchronized (GlobalCore20MapperDefaults.class) {
                if (instance == null) {
                    instance = new GlobalCore20MapperDefaults();
                }
            }
        }
        return instance;
    }

}
