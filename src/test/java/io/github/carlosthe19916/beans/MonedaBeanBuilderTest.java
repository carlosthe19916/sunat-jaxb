package io.github.carlosthe19916.beans;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class MonedaBeanBuilderTest {

    @Test
    public void testBuilder() {
        MonedaBean bean = MonedaBeanBuilder.Moneda()
                .codigo("PEN")
                .tipoCambio(new BigDecimal("3.215"))
                .build();

        Assert.assertNotNull(bean);
        Assert.assertEquals(bean.getCodigo(), "PEN");
        Assert.assertEquals(bean.getTipoCambio(), new BigDecimal("3.215"));
    }

}
