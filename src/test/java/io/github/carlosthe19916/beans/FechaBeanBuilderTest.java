package io.github.carlosthe19916.beans;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class FechaBeanBuilderTest {

    @Test
    public void testBuilder() {
        Calendar fechaEmision = Calendar.getInstance();
        fechaEmision.set(2018, Calendar.MAY, 7);

        Calendar fechaVencimiento = Calendar.getInstance();
        fechaVencimiento.set(2018, Calendar.JUNE, 20);

        FechaBean bean = FechaBeanBuilder.FechaBean()
                .fechaEmision(fechaEmision.getTime())
                .fechaVencimiento(fechaVencimiento.getTime())
                .build();

        Assert.assertNotNull(bean);
        Assert.assertEquals(bean.getFechaEmision(), fechaEmision.getTime());
        Assert.assertEquals(bean.getFechaVencimiento(), fechaVencimiento.getTime());
    }

}
