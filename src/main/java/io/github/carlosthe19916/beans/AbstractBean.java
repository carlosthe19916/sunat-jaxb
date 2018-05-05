package io.github.carlosthe19916.beans;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public abstract class AbstractBean {

    @NotNull(message = "Serie no puede ser null")
    @Size(min = 4, max = 4, message = "Serie debe de tener 4 digitos")
    private String serie;

    @NotNull(message = "Número no puede ser null")
    private Integer numero;

    @NotNull(message = "Codigo del tipo de comprobante no puede ser null")
    private String codigoTipoComprobante;

    @NotNull
    private Date fechaEmision;
    private Date fechaVencimiento;

    @NotNull
    private String moneda;
    private BigDecimal tipoCambio;

    private BigDecimal totalIgv;
    private BigDecimal totalIsc;

    private BigDecimal totalPagar;
    private BigDecimal totalDescuentoGlobal;
    private BigDecimal totalOtrosCargos;

    private BigDecimal totalGravado;
    private BigDecimal totalInafecto;
    private BigDecimal totalExonerado;
    private BigDecimal totalGratuito;

    private String observaciones;

    @NotNull
    @Valid
    private ClienteBean cliente;

    @NotNull
    @Valid
    private ProveedorBean proveedor;

    private List<TypeLineBean> detalle;

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCodigoTipoComprobante() {
        return codigoTipoComprobante;
    }

    public void setCodigoTipoComprobante(String codigoTipoComprobante) {
        this.codigoTipoComprobante = codigoTipoComprobante;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public BigDecimal getTotalIgv() {
        return totalIgv;
    }

    public void setTotalIgv(BigDecimal totalIgv) {
        this.totalIgv = totalIgv;
    }

    public BigDecimal getTotalIsc() {
        return totalIsc;
    }

    public void setTotalIsc(BigDecimal totalIsc) {
        this.totalIsc = totalIsc;
    }

    public BigDecimal getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(BigDecimal totalPagar) {
        this.totalPagar = totalPagar;
    }

    public BigDecimal getTotalDescuentoGlobal() {
        return totalDescuentoGlobal;
    }

    public void setTotalDescuentoGlobal(BigDecimal totalDescuentoGlobal) {
        this.totalDescuentoGlobal = totalDescuentoGlobal;
    }

    public BigDecimal getTotalOtrosCargos() {
        return totalOtrosCargos;
    }

    public void setTotalOtrosCargos(BigDecimal totalOtrosCargos) {
        this.totalOtrosCargos = totalOtrosCargos;
    }

    public BigDecimal getTotalGravado() {
        return totalGravado;
    }

    public void setTotalGravado(BigDecimal totalGravado) {
        this.totalGravado = totalGravado;
    }

    public BigDecimal getTotalInafecto() {
        return totalInafecto;
    }

    public void setTotalInafecto(BigDecimal totalInafecto) {
        this.totalInafecto = totalInafecto;
    }

    public BigDecimal getTotalExonerado() {
        return totalExonerado;
    }

    public void setTotalExonerado(BigDecimal totalExonerado) {
        this.totalExonerado = totalExonerado;
    }

    public BigDecimal getTotalGratuito() {
        return totalGratuito;
    }

    public void setTotalGratuito(BigDecimal totalGratuito) {
        this.totalGratuito = totalGratuito;
    }

    public String getCodigoTipoDocumentoCliente() {
        return codigoTipoDocumentoCliente;
    }

    public void setCodigoTipoDocumentoCliente(String codigoTipoDocumentoCliente) {
        this.codigoTipoDocumentoCliente = codigoTipoDocumentoCliente;
    }

    public String getNumeroDocumentoCliente() {
        return numeroDocumentoCliente;
    }

    public void setNumeroDocumentoCliente(String numeroDocumentoCliente) {
        this.numeroDocumentoCliente = numeroDocumentoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public ProveedorBean getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorBean proveedor) {
        this.proveedor = proveedor;
    }

    public List<TypeLineBean> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<TypeLineBean> detalle) {
        this.detalle = detalle;
    }

    public ClienteBean getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
    }
}
