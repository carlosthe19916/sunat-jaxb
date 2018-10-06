package io.github.carlosthe19916.sunatjaxb.mappers.core;

import io.github.carlosthe19916.sunatjaxb.catalogos.Catalogo17;

public class GlobalCore21MapperDefaults extends AbstractCoreDefaults implements Core21MapperDefaults {

    private volatile static GlobalCore21MapperDefaults instance;

    private Catalogo17 tipoOperacion;

    private GlobalCore21MapperDefaults() {
        // Singleton
    }

    public static GlobalCore21MapperDefaults getInstance() {
        if (instance == null) {
            synchronized (GlobalCore21MapperDefaults.class) {
                if (instance == null) {
                    instance = new GlobalCore21MapperDefaults();
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

}
