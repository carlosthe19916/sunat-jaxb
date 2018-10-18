package io.github.carlosthe19916.sunatjaxb.core.catalogos;

import java.util.stream.Stream;

public interface Catalogo {

    String getCode();

    static <T extends Catalogo> T valueOfCode(Class<T> enumType, String code) {
        return Stream.of(enumType.getEnumConstants())
                .filter(p -> p.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

}
