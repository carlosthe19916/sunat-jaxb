package io.github.carlosthe19916.sunatjaxb.core.catalogos;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CatalogoTest {

    @Test
    public void test_shouldGetValueFromCode() {
        Catalogo1 value = Catalogo.valueOfCode(Catalogo1.class, "03");
        Assert.assertNotNull(value);
        Assert.assertEquals(Catalogo1.BOLETA, value);

        value = Catalogo.valueOfCode(Catalogo1.class, "00");
        Assert.assertNull(value);
    }
}