package io.github.carlosthe19916.beans.ubl.ubl21;

import io.github.carlosthe19916.beans.TotalBean;
import io.github.carlosthe19916.beans.TotalBeanBuilder;

import java.math.BigDecimal;

public class Total21BeanBuilder extends TotalBeanBuilder<Total21BeanBuilder> {

    private final Total21Bean total;

    private Total21BeanBuilder() {
        super(new Total21Bean());
        total = (Total21Bean) super.total;
    }

    public static Total21BeanBuilder builder() {
        return new Total21BeanBuilder();
    }

    public Total21Bean build() {
        return total;
    }

    public Total21BeanBuilder anticipos(BigDecimal anticipos) {
        total.setAnticipos(anticipos);
        return this;
    }

    public Total21BeanBuilder extensionAmount(BigDecimal extensionAmount) {
        total.setExtensionAmount(extensionAmount);
        return this;
    }

    public Total21BeanBuilder inclusiveAmount(BigDecimal inclusiveAmount) {
        total.setInclusiveAmount(inclusiveAmount);
        return this;
    }

    @Override
    public Total21BeanBuilder getThis() {
        return this;
    }
}
