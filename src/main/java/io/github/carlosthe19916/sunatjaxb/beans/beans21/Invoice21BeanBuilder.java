package io.github.carlosthe19916.sunatjaxb.beans.beans21;

import io.github.carlosthe19916.sunatjaxb.beans.*;

import java.util.ArrayList;
import java.util.List;

public class Invoice21BeanBuilder extends AbstractInvoiceBeanBuilder<Invoice21BeanBuilder, Invoice21Bean> {

    public static Invoice21BeanBuilder builder() {
        return new Invoice21BeanBuilder();
    }

    private Invoice21BeanBuilder() {
        super(new Invoice21Bean());
    }

    public Invoice21Bean build() {
        return invoice;
    }

    @Override
    public Invoice21BeanBuilder getInvoiceBuilder() {
        return this;
    }

    public Total21BeanBuilder total() {
        return new Total21BeanBuilder();
    }

    public TotalInformacionAdicional21BeanBuilder totalInformacionAdicional() {
        return new TotalInformacionAdicional21BeanBuilder();
    }

    public Impuestos21BeanBuilder impuestos() {
        return new Impuestos21BeanBuilder();
    }

    public Proveedor21BeanBuilder proveedor() {
        return new Proveedor21BeanBuilder();
    }

    public Cliente21BeanBuilder cliente() {
        return new Cliente21BeanBuilder();
    }

    public Moneda21BeanBuilder moneda() {
        return new Moneda21BeanBuilder();
    }

    public GuiaRemisionRelacionada21BeanBuilder guiaRemisionRelacionada() {
        return new GuiaRemisionRelacionada21BeanBuilder();
    }

    public OtroDocumentoRelacionado21BeanBuilder otroDocumentoRelacionado() {
        return new OtroDocumentoRelacionado21BeanBuilder();
    }

    public Fecha21BeanBuilder fecha() {
        return new Fecha21BeanBuilder();
    }

    public Detalle21BeanBuilder addDetalle() {
        return new Detalle21BeanBuilder();
    }

    public Firmante21BeanBuilder firmante() {
        return new Firmante21BeanBuilder();
    }

    public class Total21BeanBuilder extends AbstractTotal21BeanBuilder<Total21BeanBuilder, Total21Bean> {
        private Total21BeanBuilder() {
            super(new Total21Bean());
        }

        @Override
        protected Total21BeanBuilder getTotalBuilder() {
            return this;
        }

        public Invoice21BeanBuilder end() {
            invoice.setTotal(total);
            return Invoice21BeanBuilder.this;
        }
    }

    public class TotalInformacionAdicional21BeanBuilder extends AbstractTotalInformacionAdicionalBeanBuilder<TotalInformacionAdicional21BeanBuilder, TotalInformacionAdicionalBean> {
        private TotalInformacionAdicional21BeanBuilder() {
            super(new TotalInformacionAdicionalBean());
        }

        @Override
        protected TotalInformacionAdicional21BeanBuilder getTotalInformacionAdicionalBuilder() {
            return this;
        }

        public Invoice21BeanBuilder end() {
            invoice.setTotalInformacionAdicional(totalInformacionAdicional);
            return Invoice21BeanBuilder.this;
        }
    }

    public class Impuestos21BeanBuilder extends AbstractImpuestos21BeanBuilder<Impuestos21BeanBuilder, Impuestos21Bean> {
        private Impuestos21BeanBuilder() {
            super(new Impuestos21Bean());
        }

        @Override
        protected Impuestos21BeanBuilder getImpuestosBuilder() {
            return this;
        }

        public Invoice21BeanBuilder end() {
            invoice.setImpuestos(impuestos);
            return Invoice21BeanBuilder.this;
        }
    }

    public class Proveedor21BeanBuilder extends AbstractProveedor21BeanBuilder<Proveedor21BeanBuilder, Proveedor21Bean> {
        private Proveedor21BeanBuilder() {
            super(new Proveedor21Bean());
        }

        @Override
        protected Proveedor21BeanBuilder getProveedorBuilder() {
            return this;
        }

        public Invoice21BeanBuilder end() {
            invoice.setProveedor(proveedor);
            return Invoice21BeanBuilder.this;
        }
    }

    public class Cliente21BeanBuilder extends AbstractClienteBeanBuilder<Cliente21BeanBuilder, ClienteBean> {
        private Cliente21BeanBuilder() {
            super(new ClienteBean());
        }

        @Override
        protected Cliente21BeanBuilder getClienteBuilder() {
            return this;
        }

        public Invoice21BeanBuilder end() {
            invoice.setCliente(cliente);
            return Invoice21BeanBuilder.this;
        }
    }

    public class Moneda21BeanBuilder extends AbstractMonedaBeanBuilder<Moneda21BeanBuilder, MonedaBean> {

        private Moneda21BeanBuilder() {
            super(new MonedaBean());
        }

        @Override
        protected Moneda21BeanBuilder getMonedaBuilder() {
            return this;
        }

        public Invoice21BeanBuilder end() {
            invoice.setMoneda(moneda);
            return Invoice21BeanBuilder.this;
        }

    }

    public class GuiaRemisionRelacionada21BeanBuilder extends AbstractGuiaRemisionRelacionadaBeanBuilder<GuiaRemisionRelacionada21BeanBuilder, GuiaRemisionRelacionadaBean> {
        private GuiaRemisionRelacionada21BeanBuilder() {
            super(new GuiaRemisionRelacionadaBean());
        }

        @Override
        protected GuiaRemisionRelacionada21BeanBuilder getGuiaRemisionRelacionadaBuilder() {
            return this;
        }

        public Invoice21BeanBuilder end() {
            invoice.setGuiaRemisionRelacionada(guiaRemisionRelacionada);
            return Invoice21BeanBuilder.this;
        }
    }

    public class OtroDocumentoRelacionado21BeanBuilder extends AbstractOtroDocumentoRelacionadoBeanBuilder<OtroDocumentoRelacionado21BeanBuilder, OtroDocumentoRelacionadoBean> {
        private OtroDocumentoRelacionado21BeanBuilder() {
            super(new OtroDocumentoRelacionadoBean());
        }

        @Override
        protected OtroDocumentoRelacionado21BeanBuilder getOtroDocumentoRelacionadoBuilder() {
            return this;
        }

        public Invoice21BeanBuilder end() {
            invoice.setOtroDocumentoRelacionado(otroDocumentoRelacionado);
            return Invoice21BeanBuilder.this;
        }
    }

    public class Detalle21BeanBuilder extends AbstractDetalleBeanBuilder<Detalle21BeanBuilder, DetalleBean> {
        private Detalle21BeanBuilder() {
            super(new DetalleBean());
        }

        @Override
        protected Detalle21BeanBuilder getDetalleBuilder() {
            return this;
        }

        public Invoice21BeanBuilder end() {
            List<DetalleBean> list = invoice.getDetalle();
            if (list == null) {
                list = new ArrayList<>();
                invoice.setDetalle(list);
            }
            invoice.getDetalle().add(detalle);
            return Invoice21BeanBuilder.this;
        }
    }

    public class Fecha21BeanBuilder extends AbstractFechaBeanBuilder<Fecha21BeanBuilder, FechaBean> {
        private Fecha21BeanBuilder() {
            super(new FechaBean());
        }

        @Override
        protected Fecha21BeanBuilder getFechaBuilder() {
            return this;
        }

        public Invoice21BeanBuilder end() {
            invoice.setFecha(fecha);
            return Invoice21BeanBuilder.this;
        }
    }

    public class Firmante21BeanBuilder extends AbstractFirmanteBeanBuilder<Firmante21BeanBuilder, FirmanteBean> {
        private Firmante21BeanBuilder() {
            super(new FirmanteBean());
        }

        @Override
        protected Firmante21BeanBuilder getFirmanteBuilder() {
            return this;
        }

        public Invoice21BeanBuilder end() {
            invoice.setFirmante(firmante);
            return Invoice21BeanBuilder.this;
        }
    }
}
