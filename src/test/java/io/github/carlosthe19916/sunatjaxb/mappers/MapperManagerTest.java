package io.github.carlosthe19916.sunatjaxb.mappers;

import io.github.carlosthe19916.sunatjaxb.ObjectWrapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

public class MapperManagerTest {

    /**
     * Should be singleton {@link MapperManager#getInstance()}
     */
    @Test
    public void test_should_be_singleton() throws InterruptedException {
        MapperManager instance = MapperManager.getInstance();
        ObjectWrapper instanceWrapper = new ObjectWrapper(instance);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            instanceWrapper.setObj(MapperManager.getInstance());
        });

        Thread.sleep(100);

        Assert.assertEquals(instance, instanceWrapper.getObj());
    }

}