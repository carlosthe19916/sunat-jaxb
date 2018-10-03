package io.github.carlosthe19916.beans;

import io.github.carlosthe19916.beans.ubl.ubl20.Proveedor20Bean;

public class ProveedorBeanBuilder {

    private final ProveedorBean proveedor;

    /*
     * Get a new instance
     */
    public static ProveedorBeanBuilder ProveedorBean() {
        return new ProveedorBeanBuilder();
    }

    /**
     * Constructor
     */
    public ProveedorBeanBuilder() {
        proveedor = new ProveedorBean();
    }

    /**
     * Build
     */
    public ProveedorBean build() {
        return proveedor;
    }


    // Specializations

    public Proveedor20BeanBuilder ubl20() {
        return new Proveedor20BeanBuilder();
    }

    public Proveedor21BeanBuilder ubl21() {
        return new Proveedor21BeanBuilder();
    }


    // Build process

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


    // UBL20

    public class Proveedor20BeanBuilder {
        private final Proveedor20Bean proveedor20;

        private Proveedor20BeanBuilder() {
            proveedor20 = new Proveedor20Bean(proveedor);
        }

        public Proveedor20Bean build() {
            return proveedor20;
        }

        public Proveedor20BeanBuilder direccion(String direccion) {
            proveedor20.setDireccion(direccion);
            return this;
        }

        public Proveedor20BeanBuilder provincia(String provincia) {
            proveedor20.setProvincia(provincia);
            return this;
        }

        public Proveedor20BeanBuilder distrito(String distrito) {
            proveedor20.setDistrito(distrito);
            return this;
        }

        public Proveedor20BeanBuilder region(String region) {
            proveedor20.setRegion(region);
            return this;
        }

        public Proveedor20BeanBuilder codigoPais(String codigoPais) {
            proveedor20.setCodigoPais(codigoPais);
            return this;
        }

    }


    // UBL21

    public class Proveedor21BeanBuilder {

        private final Proveedor20Bean proveedor21;

        private Proveedor21BeanBuilder() {
            proveedor21 = new Proveedor20Bean(proveedor);
        }

        public Proveedor20Bean build() {
            return proveedor21;
        }

    }

}
