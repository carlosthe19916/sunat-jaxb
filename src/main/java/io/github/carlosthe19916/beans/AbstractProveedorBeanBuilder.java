package io.github.carlosthe19916.beans;

public abstract class AbstractProveedorBeanBuilder<Builder extends AbstractProveedorBeanBuilder, Bean extends AbstractProveedorBean> {

    protected final Bean proveedor;

    protected AbstractProveedorBeanBuilder(Bean proveedor) {
        this.proveedor = proveedor;
    }

    protected abstract Builder getProveedorBuilder();

    public Builder codigoTipoDocumento(String codigoTipoDocumento) {
        proveedor.setCodigoTipoDocumento(codigoTipoDocumento);
        return getProveedorBuilder();
    }

    public Builder numeroDocumento(String numeroDocumento) {
        proveedor.setNumeroDocumento(numeroDocumento);
        return getProveedorBuilder();
    }

    public Builder nombreComercial(String nombreComercial) {
        proveedor.setNombreComercial(nombreComercial);
        return getProveedorBuilder();
    }

    public Builder razonSocial(String razonSocial) {
        proveedor.setRazonSocial(razonSocial);
        return getProveedorBuilder();
    }

    public Builder codigoPostal(String codigoPostal) {
        proveedor.setCodigoPostal(codigoPostal);
        return getProveedorBuilder();
    }

}
