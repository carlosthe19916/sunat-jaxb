package io.github.carlosthe19916.sunatjaxb.ubl20.beans;

import io.github.carlosthe19916.sunatjaxb.beans.*;

import java.util.ArrayList;
import java.util.List;

public class Invoice20BeanBuilder extends AbstractInvoiceBeanBuilder<Invoice20BeanBuilder, Invoice20Bean> {

    public static Invoice20BeanBuilder builder() {
        return new Invoice20BeanBuilder();
    }

    private Invoice20BeanBuilder() {
        super(new Invoice20Bean());
    }

    public Invoice20Bean build() {
        return invoice;
    }

    @Override
    public Invoice20BeanBuilder getInvoiceBuilder() {
        return this;
    }

    public Invoice20BeanBuilder observaciones(String observaciones) {
        invoice.setObservaciones(observaciones);
        return this;
    }

    public Total20BeanBuilder total() {
        return new Total20BeanBuilder();
    }

    public TotalInformacionAdicional20BeanBuilder totalInformacionAdicional() {
        return new TotalInformacionAdicional20BeanBuilder();
    }

    public Impuestos20BeanBuilder impuestos() {
        return new Impuestos20BeanBuilder();
    }

    public Proveedor20BeanBuilder proveedor() {
        return new Proveedor20BeanBuilder();
    }

    public Cliente20BeanBuilder cliente() {
        return new Cliente20BeanBuilder();
    }

    public Moneda20BeanBuilder moneda() {
        return new Moneda20BeanBuilder();
    }

    public GuiaRemisionRelacionada20BeanBuilder guiaRemisionRelacionada() {
        return new GuiaRemisionRelacionada20BeanBuilder();
    }

    public OtroDocumentoRelacionado20BeanBuilder otroDocumentoRelacionado() {
        return new OtroDocumentoRelacionado20BeanBuilder();
    }

    public Fecha20BeanBuilder fecha() {
        return new Fecha20BeanBuilder();
    }


    public Detalle20BeanBuilder addDetalle() {
        return new Detalle20BeanBuilder();
    }

    public Firmante20BeanBuilder firmante() {
        return new Firmante20BeanBuilder();
    }

    public class Total20BeanBuilder extends AbstractTotal20BeanBuilder<Total20BeanBuilder, Total20Bean> {
        private Total20BeanBuilder() {
            super(new Total20Bean());
        }

        @Override
        protected Total20BeanBuilder getTotalBuilder() {
            return this;
        }

        public Invoice20BeanBuilder end() {
            invoice.setTotal(total);
            return Invoice20BeanBuilder.this;
        }

    }

    public class TotalInformacionAdicional20BeanBuilder extends AbstractTotalInformacionAdicionalBeanBuilder<TotalInformacionAdicional20BeanBuilder, TotalInformacionAdicionalBean> {
        private TotalInformacionAdicional20BeanBuilder() {
            super(new TotalInformacionAdicionalBean());
        }

        @Override
        protected TotalInformacionAdicional20BeanBuilder getTotalInformacionAdicionalBuilder() {
            return this;
        }

        public Invoice20BeanBuilder end() {
            invoice.setTotalInformacionAdicional(totalInformacionAdicional);
            return Invoice20BeanBuilder.this;
        }
    }

    public class Impuestos20BeanBuilder extends AbstractImpuestos20BeanBuilder<Impuestos20BeanBuilder, Impuestos20Bean> {
        private Impuestos20BeanBuilder() {
            super(new Impuestos20Bean());
        }

        @Override
        protected Impuestos20BeanBuilder getImpuestosBuilder() {
            return this;
        }

        public Invoice20BeanBuilder end() {
            invoice.setImpuestos(impuestos);
            return Invoice20BeanBuilder.this;
        }
    }

    public class Proveedor20BeanBuilder extends AbstractProveedor20BeanBuilder<Proveedor20BeanBuilder, Proveedor20Bean> {
        private Proveedor20BeanBuilder() {
            super(new Proveedor20Bean());
        }

        @Override
        protected Proveedor20BeanBuilder getProveedorBuilder() {
            return this;
        }

        public Invoice20BeanBuilder end() {
            invoice.setProveedor(proveedor);
            return Invoice20BeanBuilder.this;
        }
    }

    public class Cliente20BeanBuilder extends AbstractClienteBeanBuilder<Cliente20BeanBuilder, ClienteBean> {
        private Cliente20BeanBuilder() {
            super(new ClienteBean());
        }

        @Override
        protected Cliente20BeanBuilder getClienteBuilder() {
            return this;
        }

        public Invoice20BeanBuilder end() {
            invoice.setCliente(cliente);
            return Invoice20BeanBuilder.this;
        }
    }

    public class Moneda20BeanBuilder extends AbstractMonedaBeanBuilder<Moneda20BeanBuilder, MonedaBean> {
        private Moneda20BeanBuilder() {
            super(new MonedaBean());
        }

        @Override
        protected Moneda20BeanBuilder getMonedaBuilder() {
            return this;
        }

        public Invoice20BeanBuilder end() {
            invoice.setMoneda(moneda);
            return Invoice20BeanBuilder.this;
        }
    }

    public class GuiaRemisionRelacionada20BeanBuilder extends AbstractGuiaRemisionRelacionadaBeanBuilder<GuiaRemisionRelacionada20BeanBuilder, GuiaRemisionRelacionadaBean> {
        private GuiaRemisionRelacionada20BeanBuilder() {
            super(new GuiaRemisionRelacionadaBean());
        }

        @Override
        protected GuiaRemisionRelacionada20BeanBuilder getGuiaRemisionRelacionadaBuilder() {
            return this;
        }

        public Invoice20BeanBuilder end() {
            invoice.setGuiaRemisionRelacionada(guiaRemisionRelacionada);
            return Invoice20BeanBuilder.this;
        }
    }

    public class OtroDocumentoRelacionado20BeanBuilder extends AbstractOtroDocumentoRelacionadoBeanBuilder<OtroDocumentoRelacionado20BeanBuilder, OtroDocumentoRelacionadoBean> {
        private OtroDocumentoRelacionado20BeanBuilder() {
            super(new OtroDocumentoRelacionadoBean());
        }

        @Override
        protected OtroDocumentoRelacionado20BeanBuilder getOtroDocumentoRelacionadoBuilder() {
            return this;
        }

        public Invoice20BeanBuilder end() {
            invoice.setOtroDocumentoRelacionado(otroDocumentoRelacionado);
            return Invoice20BeanBuilder.this;
        }
    }

    public class Fecha20BeanBuilder extends AbstractFechaBeanBuilder<Fecha20BeanBuilder, FechaBean> {
        private Fecha20BeanBuilder() {
            super(new FechaBean());
        }

        @Override
        protected Fecha20BeanBuilder getFechaBuilder() {
            return this;
        }

        public Invoice20BeanBuilder end() {
            invoice.setFecha(fecha);
            return Invoice20BeanBuilder.this;
        }
    }


    public class Detalle20BeanBuilder extends AbstractDetalleBeanBuilder<Detalle20BeanBuilder, DetalleBean> {
        private Detalle20BeanBuilder() {
            super(new DetalleBean());
        }

        @Override
        protected Detalle20BeanBuilder getDetalleBuilder() {
            return this;
        }

        public Invoice20BeanBuilder end() {
            List<DetalleBean> list = invoice.getDetalle();
            if (list == null) {
                list = new ArrayList<>();
                invoice.setDetalle(list);
            }
            invoice.getDetalle().add(detalle);
            return Invoice20BeanBuilder.this;
        }
    }

    public class Firmante20BeanBuilder extends AbstractFirmanteBeanBuilder<Firmante20BeanBuilder, FirmanteBean> {
        private Firmante20BeanBuilder() {
            super(new FirmanteBean());
        }

        @Override
        protected Firmante20BeanBuilder getFirmanteBuilder() {
            return this;
        }

        public Invoice20BeanBuilder end() {
            invoice.setFirmante(firmante);
            return Invoice20BeanBuilder.this;
        }
    }

}
