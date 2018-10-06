package io.github.carlosthe19916.sunatjaxb.beans;

import java.math.BigDecimal;

public abstract class AbstractImpuestosBeanBuilder<Builder extends AbstractImpuestosBeanBuilder, Bean extends AbstractImpuestosBean> {

    protected final Bean impuestos;

    protected AbstractImpuestosBeanBuilder(Bean impuestos) {
        this.impuestos = impuestos;
    }

    protected abstract Builder getImpuestosBuilder();

    public Builder igv(BigDecimal igv) {
        impuestos.setIgv(igv);
        return getImpuestosBuilder();
    }

    public Builder isc(BigDecimal isc) {
        impuestos.setIsc(isc);
        return getImpuestosBuilder();
    }

    public Builder otros(BigDecimal otros) {
        impuestos.setOtros(otros);
        return getImpuestosBuilder();
    }

}
