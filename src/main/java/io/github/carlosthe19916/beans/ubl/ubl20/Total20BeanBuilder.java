package io.github.carlosthe19916.beans.ubl.ubl20;

import io.github.carlosthe19916.beans.TotalBean;
import io.github.carlosthe19916.beans.TotalBeanBuilder;
import io.github.carlosthe19916.beans.ubl.ubl21.Total21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.Total21BeanBuilder;

import java.math.BigDecimal;

public class Total20BeanBuilder extends TotalBeanBuilder<Total20BeanBuilder> {

    private final Total20Bean total;

    private Total20BeanBuilder() {
        super(new Total21Bean());
        total = (Total20Bean) super.total;
    }

    public static Total20BeanBuilder builder() {
        return new Total20BeanBuilder();
    }

    public Total20Bean build() {
        return total;
    }

    @Override
    public Total20BeanBuilder getThis() {
        return this;
    }
}
