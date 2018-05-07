package io.github.carlosthe19916.beans;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ImpuestosBeanBuilderTest {

    @Test
    public void testBuilder() {
        ImpuestosBean bean = ImpuestosBeanBuilder.Impuestos()
                .igv(new BigDecimal("16.25"))
                .isc(new BigDecimal("0"))
                .build();

        Assert.assertNotNull(bean);
        Assert.assertEquals(bean.getIgv(), new BigDecimal("16.25"));
        Assert.assertEquals(bean.getIsc(), new BigDecimal("0"));
    }

}
