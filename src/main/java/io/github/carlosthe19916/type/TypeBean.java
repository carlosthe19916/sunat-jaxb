package io.github.carlosthe19916.type;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TypeBean {
    private String serieNumero;

    private String observaciones;

    private Date fechaEmision;
    private Date fechaVencimiento;

    private BigDecimal totalPagar;
    private BigDecimal totalDescuentoGlobal;
    private BigDecimal totalOtrosCargos;

    private String moneda;
    private BigDecimal tipoCambio;

    private String codigoTipoDocumentoCliente;
    private String numeroDocumentoCliente;
    private String nombreCliente;
    private String emailCliente;
    private String direccionCliente;

    private List<TypeLineBean> detalle;

    public String getObservaciones() {
        return observaciones;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public BigDecimal getTotalPagar() {
        return totalPagar;
    }

    public BigDecimal getTotalDescuentoGlobal() {
        return totalDescuentoGlobal;
    }

    public BigDecimal getTotalOtrosCargos() {
        return totalOtrosCargos;
    }

    public String getMoneda() {
        return moneda;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public String getCodigoTipoDocumentoCliente() {
        return codigoTipoDocumentoCliente;
    }

    public String getNumeroDocumentoCliente() {
        return numeroDocumentoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public List<TypeLineBean> getDetalle() {
        return detalle;
    }

    public String getSerieNumero() {
        return serieNumero;
    }
}
