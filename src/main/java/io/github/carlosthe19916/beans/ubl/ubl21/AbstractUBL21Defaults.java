package io.github.carlosthe19916.beans.ubl.ubl21;

import io.github.carlosthe19916.beans.catalogs.Catalog51;

import java.util.TimeZone;

public abstract class AbstractUBL21Defaults implements UBL21Defaults {

    @Override
    public Catalog51 getTipoOperacion() {
        return null;
    }

    @Override
    public boolean calculoAutomatico() {
        return false;
    }
}
