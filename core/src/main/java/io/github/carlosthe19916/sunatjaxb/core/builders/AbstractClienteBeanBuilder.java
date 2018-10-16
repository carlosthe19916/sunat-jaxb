package io.github.carlosthe19916.sunatjaxb.core.builders;

import io.github.carlosthe19916.sunatjaxb.core.beans.ClienteBean;

public abstract class AbstractClienteBeanBuilder<Builder extends AbstractClienteBeanBuilder, Bean extends ClienteBean> {

    protected final Bean cliente;

    protected AbstractClienteBeanBuilder(Bean cliente) {
        this.cliente = cliente;
    }

    protected abstract Builder getClienteBuilder();

    public Builder codigoTipoDocumento(String codigoTipoDocumento) {
        cliente.setCodigoTipoDocumento(codigoTipoDocumento);
        return getClienteBuilder();
    }

    public Builder numeroDocumento(String numeroDocumento) {
        cliente.setNumeroDocumento(numeroDocumento);
        return getClienteBuilder();
    }

    public Builder nombre(String nombre) {
        cliente.setNombre(nombre);
        return getClienteBuilder();
    }

    public Builder email(String email) {
        cliente.setEmail(email);
        return getClienteBuilder();
    }

    public Builder direccion(String direccion) {
        cliente.setDireccion(direccion);
        return getClienteBuilder();
    }
}
