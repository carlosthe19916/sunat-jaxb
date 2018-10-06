package io.github.carlosthe19916.sunatjaxb.mappers;

import java.util.Comparator;

public class MapperComparator implements Comparator<Mapper> {

    @Override
    public int compare(Mapper t1, Mapper t2) {
        return t2.order() - t1.order();
    }

}
