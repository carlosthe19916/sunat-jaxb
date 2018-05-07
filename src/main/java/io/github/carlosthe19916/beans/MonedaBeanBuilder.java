package io.github.carlosthe19916.beans;

import java.math.BigDecimal;

public class MonedaBeanBuilder {

    private final MonedaBean moneda;

    public MonedaBeanBuilder() {
        moneda = new MonedaBean();
    }

    public static MonedaBeanBuilder Moneda() {
        return new MonedaBeanBuilder();
    }

    public MonedaBeanBuilder codigo(String codigo) {
        moneda.setCodigo(codigo);
        return this;
    }

    public MonedaBeanBuilder tipoCambio(BigDecimal tipoCambio) {
        moneda.setTipoCambio(tipoCambio);
        return this;
    }

    public MonedaBean build() {
        return moneda;
    }
}
