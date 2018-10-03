package io.github.carlosthe19916.beans.mappers;

public interface FillOut<T> {

    T fillIn(T t);

    boolean isInternal();

    int order();

}
