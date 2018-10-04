package io.github.carlosthe19916.beans.fillouts;

public interface FillOut<T> {

    T fillIn(T t);

    boolean isInternal();

    int order();

}
