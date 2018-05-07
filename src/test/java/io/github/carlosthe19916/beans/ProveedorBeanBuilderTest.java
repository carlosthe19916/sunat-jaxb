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
                .direccion("Jr. Ayacucho 65")
                .region("Ayacucho")
                .provincia("Huamanga")
                .distrito("Jesús Nazareno")
                .codigoPais("PE")
                .build();

        Assert.assertNotNull(bean);
        Assert.assertEquals(bean.getCodigoTipoDocumento(), "06");
        Assert.assertEquals(bean.getNumeroDocumento(), "10467793549");
        Assert.assertEquals(bean.getNombreComercial(), "Wolsnut4");
        Assert.assertEquals(bean.getRazonSocial(), "Wolsnut4 S.A.");
        Assert.assertEquals(bean.getCodigoPostal(), "050101");
        Assert.assertEquals(bean.getDireccion(), "Jr. Ayacucho 65");
        Assert.assertEquals(bean.getRegion(), "Ayacucho");
        Assert.assertEquals(bean.getProvincia(), "Huamanga");
        Assert.assertEquals(bean.getDistrito(), "Jesús Nazareno");
        Assert.assertEquals(bean.getCodigoPais(), "PE");
    }


}
