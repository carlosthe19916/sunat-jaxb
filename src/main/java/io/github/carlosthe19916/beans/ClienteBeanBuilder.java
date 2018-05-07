package io.github.carlosthe19916.beans;

public class ClienteBeanBuilder {

    private final ClienteBean cliente;

    public ClienteBeanBuilder() {
        this.cliente = new ClienteBean();
    }

    public static ClienteBeanBuilder ClienteBean() {
        return new ClienteBeanBuilder();
    }

    public ClienteBeanBuilder codigoTipoDocumento(String codigoTipoDocumento) {
        cliente.setCodigoTipoDocumento(codigoTipoDocumento);
        return this;
    }

    public ClienteBeanBuilder numeroDocumento(String numeroDocumento) {
        cliente.setNumeroDocumento(numeroDocumento);
        return this;
    }

    public ClienteBeanBuilder nombre(String nombre) {
        cliente.setNombre(nombre);
        return this;
    }

    public ClienteBeanBuilder email(String email) {
        cliente.setEmail(email);
        return this;
    }

    public ClienteBeanBuilder direccion(String direccion) {
        cliente.setDireccion(direccion);
        return this;
    }

    public ClienteBean build() {
        return cliente;
    }

}
