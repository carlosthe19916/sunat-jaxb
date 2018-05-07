package io.github.carlosthe19916.beans;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TotalBeanBuilderTest {

    @Test
    public void testBuilder() {
        TotalBean bean = TotalBeanBuilder.Total()
                .pagar(new BigDecimal("100"))
                .otrosCargos(new BigDecimal("10"))
                .descuento(new BigDecimal("5"))
                .build();

        Assert.assertNotNull(bean);
        Assert.assertEquals(bean.getPagar(), new BigDecimal("100"));
        Assert.assertEquals(bean.getOtrosCargos(), new BigDecimal("10"));
        Assert.assertEquals(bean.getDescuento(), new BigDecimal("5"));
    }

}
