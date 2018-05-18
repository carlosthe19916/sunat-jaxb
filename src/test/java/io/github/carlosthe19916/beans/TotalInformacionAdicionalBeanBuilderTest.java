package io.github.carlosthe19916.beans;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TotalInformacionAdicionalBeanBuilderTest {

    @Test
    public void testBuilder() {
        TotalInformacionAdicionalBean bean = TotalInformacionAdicionalBeanBuilder.TotalInformacionAdicionalBean()
                .gravado(new BigDecimal("16.25"))
                .exonerado(new BigDecimal("15.42"))
                .inafecto(new BigDecimal("0.50"))
                .gratuito(new BigDecimal("0"))
                .build();

        Assert.assertNotNull(bean);
        Assert.assertEquals(bean.getGravado(), new BigDecimal("16.25"));
        Assert.assertEquals(bean.getExonerado(), new BigDecimal("15.42"));
        Assert.assertEquals(bean.getInafecto(), new BigDecimal("0.50"));
        Assert.assertEquals(bean.getGratuito(), new BigDecimal("0"));
    }

}
