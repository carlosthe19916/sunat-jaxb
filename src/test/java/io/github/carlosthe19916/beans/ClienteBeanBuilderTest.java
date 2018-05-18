package io.github.carlosthe19916.beans;

import org.junit.Assert;
import org.junit.Test;

public class ClienteBeanBuilderTest {

    @Test
    public void testBuilder() {
        ClienteBean bean = ClienteBeanBuilder.ClienteBean()
                .codigoTipoDocumento("06")
                .numeroDocumento("10467793549")
                .nombre("Wolsnut4")
                .direccion("Jr. Ayacucho 65")
                .email("wolsnut4@gmail.com")
                .build();

        Assert.assertNotNull(bean);
        Assert.assertEquals(bean.getCodigoTipoDocumento(), "06");
        Assert.assertEquals(bean.getNumeroDocumento(), "10467793549");
        Assert.assertEquals(bean.getNombre(), "Wolsnut4");
        Assert.assertEquals(bean.getDireccion(), "Jr. Ayacucho 65");
        Assert.assertEquals(bean.getEmail(), "wolsnut4@gmail.com");
    }

}
