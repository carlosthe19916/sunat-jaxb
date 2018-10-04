package io.github.carlosthe19916.beans;

public abstract class AbstractProveedorBeanBuilder<Builder extends AbstractProveedorBeanBuilder, Bean extends AbstractProveedorBean> {

    private final Bean bean;

    public AbstractProveedorBeanBuilder(Bean bean) {
        this.bean = bean;
    }

    public Bean getBean() {
        return bean;
    }

    public abstract Builder getBuilder();

    public Builder codigoTipoDocumento(String codigoTipoDocumento) {
        bean.setCodigoTipoDocumento(codigoTipoDocumento);
        return getBuilder();
    }

    public Builder numeroDocumento(String numeroDocumento) {
        bean.setNumeroDocumento(numeroDocumento);
        return getBuilder();
    }

    public Builder nombreComercial(String nombreComercial) {
        bean.setNombreComercial(nombreComercial);
        return getBuilder();
    }

    public Builder razonSocial(String razonSocial) {
        bean.setRazonSocial(razonSocial);
        return getBuilder();
    }

    public Builder codigoPostal(String codigoPostal) {
        bean.setCodigoPostal(codigoPostal);
        return getBuilder();
    }

}
