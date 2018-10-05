package io.github.carlosthe19916.examples;

import io.github.carlosthe19916.beans.ubl.ubl21.Invoice21BeanBuilder;

/**
 * Created by carlos on 10/4/18.
 */
public class Example {

    public static void main(String[] args) {
        Invoice21BeanBuilder.builder()
                .cliente(null)
                .moneda()
                    .tipoCambio(null)
                    .codigo("")
                    .end()
                .cliente(null)
                .build();
    }

}
