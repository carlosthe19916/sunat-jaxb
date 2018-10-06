package io.github.carlosthe19916.sunatjaxb.mappers;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

public class MapperManagerTest {

    /**
     * Should be singleton {@link MapperManager#getInstance()}
     */
    @Test
    public void test_should_be_singleton() {
        MapperManager instance1 = MapperManager.getInstance();
        
        MapperManager instance2 = MapperManager.getInstance();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                instance2 = MapperManager.getInstance();
            }
        });

        MapperManager instance2 = MapperManager.getInstance();
    }

}