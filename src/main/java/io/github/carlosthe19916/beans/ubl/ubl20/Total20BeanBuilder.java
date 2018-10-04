package io.github.carlosthe19916.beans.ubl.ubl20;

import io.github.carlosthe19916.beans.AbstractTotalBeanBuilder;

public class Total20BeanBuilder extends AbstractTotalBeanBuilder<Total20BeanBuilder, Total20Bean> {

    public static Total20BeanBuilder builder() {
        return new Total20BeanBuilder();
    }

    private Total20BeanBuilder() {
        super(new Total20Bean());
    }

    public Total20Bean build() {
        return getBean();
    }

    @Override
    public Total20BeanBuilder getBuilder() {
        return this;
    }

}
