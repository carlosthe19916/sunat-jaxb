package io.github.carlosthe19916.beans;

import java.math.BigDecimal;

public abstract class AbstractImpuestosBeanBuilder<Builder extends AbstractImpuestosBeanBuilder, Bean extends ImpuestosBean> {

    protected final Bean bean;

    public AbstractImpuestosBeanBuilder(Bean bean) {
        this.bean = bean;
    }

    public Bean getBean() {
        return bean;
    }

    public abstract Builder getBuilder();

    public Builder igv(BigDecimal igv) {
        bean.setIgv(igv);
        return getBuilder();
    }

    public Builder isc(BigDecimal isc) {
        bean.setIsc(isc);
        return getBuilder();
    }

    public Builder otros(BigDecimal otros) {
        bean.setOtros(otros);
        return getBuilder();
    }

}
