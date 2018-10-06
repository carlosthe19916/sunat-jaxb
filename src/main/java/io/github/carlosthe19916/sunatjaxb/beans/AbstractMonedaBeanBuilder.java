package io.github.carlosthe19916.sunatjaxb.beans;

import java.math.BigDecimal;

public abstract class AbstractMonedaBeanBuilder<Builder extends AbstractMonedaBeanBuilder, Bean extends MonedaBean> {

    protected final Bean moneda;

    protected AbstractMonedaBeanBuilder(Bean moneda) {
        this.moneda = moneda;
    }

    protected abstract Builder getMonedaBuilder();

    public Builder codigo(String codigo) {
        moneda.setCodigo(codigo);
        return getMonedaBuilder();
    }

    public Builder tipoCambio(BigDecimal tipoCambio) {
        moneda.setTipoCambio(tipoCambio);
        return getMonedaBuilder();
    }

}
