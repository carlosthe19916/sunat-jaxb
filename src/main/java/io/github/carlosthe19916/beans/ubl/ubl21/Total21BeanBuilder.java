package io.github.carlosthe19916.beans.ubl.ubl21;

import io.github.carlosthe19916.beans.AbstractTotalBeanBuilder;

import java.math.BigDecimal;

public class Total21BeanBuilder extends AbstractTotalBeanBuilder<Total21BeanBuilder, Total21Bean> {

    public static Total21BeanBuilder builder() {
        return new Total21BeanBuilder();
    }

    private Total21BeanBuilder() {
        super(new Total21Bean());
    }

    public Total21Bean build() {
        return getBean();
    }

    @Override
    public Total21BeanBuilder getBuilder() {
        return this;
    }

    public Total21BeanBuilder anticipos(BigDecimal anticipos) {
        getBean().setAnticipos(anticipos);
        return this;
    }

    public Total21BeanBuilder extensionAmount(BigDecimal extensionAmount) {
        getBean().setExtensionAmount(extensionAmount);
        return this;
    }

    public Total21BeanBuilder inclusiveAmount(BigDecimal inclusiveAmount) {
        getBean().setInclusiveAmount(inclusiveAmount);
        return this;
    }

}
