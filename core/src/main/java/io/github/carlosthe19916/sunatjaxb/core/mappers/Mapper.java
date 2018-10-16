package io.github.carlosthe19916.sunatjaxb.core.mappers;

public interface Mapper<T> {

    T map(T t);

    boolean isInternal();

    int order();

}
