package io.github.carlosthe19916.beans;

public class ProveedorBeanBuilder {

    private final ProveedorBean proveedor;

    public ProveedorBeanBuilder() {
        proveedor = new ProveedorBean();
    }

    public static ProveedorBeanBuilder ProveedorBean() {
        return new ProveedorBeanBuilder();
    }

    public ProveedorBeanBuilder codigoTipoDocumento(String codigoTipoDocumento) {
        proveedor.setCodigoTipoDocumento(codigoTipoDocumento);
        return this;
    }

    public ProveedorBeanBuilder numeroDocumento(String numeroDocumento) {
        proveedor.setNumeroDocumento(numeroDocumento);
        return this;
    }

    public ProveedorBeanBuilder nombreComercial(String nombreComercial) {
        proveedor.setNombreComercial(nombreComercial);
        return this;
    }

    public ProveedorBeanBuilder razonSocial(String razonSocial) {
        proveedor.setRazonSocial(razonSocial);
        return this;
    }

    public ProveedorBeanBuilder codigoPostal(String codigoPostal) {
        proveedor.setCodigoPostal(codigoPostal);
        return this;
    }

    public ProveedorBeanBuilder direccion(String direccion) {
        proveedor.setDireccion(direccion);
        return this;
    }

    public ProveedorBeanBuilder provincia(String provincia) {
        proveedor.setProvincia(provincia);
        return this;
    }

    public ProveedorBeanBuilder distrito(String distrito) {
        proveedor.setDistrito(distrito);
        return this;
    }

    public ProveedorBeanBuilder region(String region) {
        proveedor.setRegion(region);
        return this;
    }

    public ProveedorBeanBuilder codigoPais(String codigoPais) {
        proveedor.setCodigoPais(codigoPais);
        return this;
    }

    public ProveedorBean build() {
        return proveedor;
    }
}
