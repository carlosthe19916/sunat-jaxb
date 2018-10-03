package io.github.carlosthe19916.beans;

import org.junit.Assert;
import org.junit.Test;

public class ProveedorBeanBuilderTest {

    @Test
    public void testBuilder() {
        ProveedorBean bean = ProveedorBeanBuilder.ProveedorBean()
                .codigoTipoDocumento("06")
                .numeroDocumento("10467793549")
                .nombreComercial("Wolsnut4")
                .razonSocial("Wolsnut4 S.A.")
                .codigoPostal("050101")
                .build();

        Assert.assertNotNull(bean);
        Assert.assertEquals(bean.getCodigoTipoDocumento(), "06");
        Assert.assertEquals(bean.getNumeroDocumento(), "10467793549");
        Assert.assertEquals(bean.getNombreComercial(), "Wolsnut4");
        Assert.assertEquals(bean.getRazonSocial(), "Wolsnut4 S.A.");
        Assert.assertEquals(bean.getCodigoPostal(), "050101");
    }


}
