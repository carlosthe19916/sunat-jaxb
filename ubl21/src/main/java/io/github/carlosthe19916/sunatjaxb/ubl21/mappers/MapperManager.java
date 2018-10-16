package io.github.carlosthe19916.sunatjaxb.ubl21.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class MapperManager {

    private static volatile MapperManager instance;

    private List<Invoice20Mapper> invoice20Mappers;
    private List<Invoice21Mapper> invoice21Mappers;

    private MapperManager() {
    }

    public static MapperManager getInstance() {
        if (instance == null) {
            synchronized (MapperManager.class) {
                if (instance == null) {
                    instance = new MapperManager();
                }
            }
        }
        return instance;
    }

    public List<Invoice20Mapper> getInvoice20Mappers() {
        if (invoice20Mappers == null) {
            synchronized (MapperManager.class) {
                if (invoice20Mappers == null) {
                    invoice20Mappers = new ArrayList<>();
                    for (Invoice20Mapper mapper : ServiceLoader.load(Invoice20Mapper.class)) {
                        invoice20Mappers.add(mapper);
                    }
                    invoice20Mappers.sort((t1, t2) -> t2.order() - t1.order());
                }
            }
        }
        return invoice20Mappers;
    }

    public List<Invoice21Mapper> getInvoice21Mappers() {
        if (invoice21Mappers == null) {
            synchronized (MapperManager.class) {
                if (invoice21Mappers == null) {
                    invoice21Mappers = new ArrayList<>();
                    for (Invoice21Mapper mapper : ServiceLoader.load(Invoice21Mapper.class)) {
                        invoice21Mappers.add(mapper);
                    }
                    invoice21Mappers.sort(new MapperComparator());
                }
            }
        }
        return invoice21Mappers;
    }

}
