package io.github.carlosthe19916.sunatjaxb.ubl21;

import io.github.carlosthe19916.sunatjaxb.core.catalogos.Catalogo17;
import io.github.carlosthe19916.sunatjaxb.ubl21.mappers.MappersManager;

import java.math.BigDecimal;
import java.util.TimeZone;

public class UBL21GlobalConfig {

    private static volatile UBL21GlobalConfig instance;

    private boolean defaultMappersActive;

    private TimeZone timeZone;
    private BigDecimal igvDecimalValue;

    // Invoice
    private Catalogo17 invoiceTipoOperacion;

    private UBL21GlobalConfig() {
//        String defaultTimeZone = System.getenv(MapperConstants.DEFAULT_TIME_ZONE);
//        if (defaultTimeZone != null) {
//            this.timeZone = TimeZone.getTimeZone(defaultTimeZone);
//        }
//
//        String defaultTipoOperacion = System.getenv(MapperConstants.DEFAULT_TIPO_OPERACION);
//        if (defaultTipoOperacion != null) {
//            this.invoiceTipoOperacion = Catalogo.valueOfCode(Catalogo17.class, defaultTipoOperacion);
//        }
//
//        String defaultIgvDecimalValue = System.getenv(MapperConstants.DEFAULT_IGV_DECIMAL_VALUE);
//        if (defaultIgvDecimalValue != null) {
//            this.igvDecimalValue = new BigDecimal(defaultIgvDecimalValue);
//        }
    }

    public static UBL21GlobalConfig getInstance() {
        if (instance == null) {
            synchronized (MappersManager.class) {
                if (instance == null) {
                    instance = new UBL21GlobalConfig();
                }
            }
        }
        return instance;
    }

    public boolean isDefaultMappersActive() {
        return defaultMappersActive;
    }

    public void setDefaultMappersActive(boolean defaultMappersActive) {
        this.defaultMappersActive = defaultMappersActive;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public BigDecimal getIgvDecimalValue() {
        return igvDecimalValue;
    }

    public void setIgvDecimalValue(BigDecimal igvDecimalValue) {
        this.igvDecimalValue = igvDecimalValue;
    }

    public Catalogo17 getInvoiceTipoOperacion() {
        return invoiceTipoOperacion;
    }

    public void setInvoiceTipoOperacion(Catalogo17 invoiceTipoOperacion) {
        this.invoiceTipoOperacion = invoiceTipoOperacion;
    }

}
