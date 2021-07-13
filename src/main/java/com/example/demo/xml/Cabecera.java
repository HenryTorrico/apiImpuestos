/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.xml;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Cabecera implements Serializable {
    public String getRazonSocialEmisor() {
        return razonSocialEmisor;
    }

    public void setRazonSocialEmisor(String razonSocialEmisor) {
        this.razonSocialEmisor = razonSocialEmisor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Long getNumeroNotaCreditoDebito() {
        return numeroNotaCreditoDebito;
    }

    public void setNumeroNotaCreditoDebito(Long numeroNotaCreditoDebito) {
        this.numeroNotaCreditoDebito = numeroNotaCreditoDebito;
    }

    public Long getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(Long codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public String getDireccionsucursal() {
        return direccionsucursal;
    }

    public void setDireccionsucursal(String direccionsucursal) {
        this.direccionsucursal = direccionsucursal;
    }

    public Long getCodigoPuntoVenta() {
        return codigoPuntoVenta;
    }

    public void setCodigoPuntoVenta(Long codigoPuntoVenta) {
        this.codigoPuntoVenta = codigoPuntoVenta;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public Long getCodigoTipoDocumentoIdentidad() {
        return codigoTipoDocumentoIdentidad;
    }

    public void setCodigoTipoDocumentoIdentidad(Long codigoTipoDocumentoIdentidad) {
        this.codigoTipoDocumentoIdentidad = codigoTipoDocumentoIdentidad;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Long getCodigoMetodoPago() {
        return codigoMetodoPago;
    }

    public void setCodigoMetodoPago(Long codigoMetodoPago) {
        this.codigoMetodoPago = codigoMetodoPago;
    }

    public Long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public BigDecimal getMontoTotalSujetoIva() {
        return montoTotalSujetoIva;
    }

    public void setMontoTotalSujetoIva(BigDecimal montoTotalSujetoIva) {
        this.montoTotalSujetoIva = montoTotalSujetoIva;
    }

    public Long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(Long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public BigDecimal getMontoTotalMoneda() {
        return montoTotalMoneda;
    }

    public void setMontoTotalMoneda(BigDecimal montoTotalMoneda) {
        this.montoTotalMoneda = montoTotalMoneda;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getCodigoDocumentoSector() {
        return codigoDocumentoSector;
    }

    public void setCodigoDocumentoSector(Long codigoDocumentoSector) {
        this.codigoDocumentoSector = codigoDocumentoSector;
    }

    public String getDireccionComprador() {
        return direccionComprador;
    }

    public void setDireccionComprador(String direccionComprador) {
        this.direccionComprador = direccionComprador;
    }

    public String getIncoterm() {
        return incoterm;
    }

    public void setIncoterm(String incoterm) {
        this.incoterm = incoterm;
    }

    public String getIncotermDetalle() {
        return incotermDetalle;
    }

    public void setIncotermDetalle(String incotermDetalle) {
        this.incotermDetalle = incotermDetalle;
    }

    public String getPuertoDestino() {
        return puertoDestino;
    }

    public void setPuertoDestino(String puertoDestino) {
        this.puertoDestino = puertoDestino;
    }

    public String getLugarDestino() {
        return lugarDestino;
    }

    public void setLugarDestino(String lugarDestino) {
        this.lugarDestino = lugarDestino;
    }

    public Long getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(Long codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getCostosGastosNacionales() {
        return costosGastosNacionales;
    }

    public void setCostosGastosNacionales(String costosGastosNacionales) {
        this.costosGastosNacionales = costosGastosNacionales;
    }

    public BigDecimal getTotalGastosNacionalesFob() {
        return totalGastosNacionalesFob;
    }

    public void setTotalGastosNacionalesFob(BigDecimal totalGastosNacionalesFob) {
        this.totalGastosNacionalesFob = totalGastosNacionalesFob;
    }

    public String getCostosGastosInternacionales() {
        return costosGastosInternacionales;
    }

    public void setCostosGastosInternacionales(String costosGastosInternacionales) {
        this.costosGastosInternacionales = costosGastosInternacionales;
    }

    public BigDecimal getTotalGastosInternacionales() {
        return totalGastosInternacionales;
    }

    public void setTotalGastosInternacionales(BigDecimal totalGastosInternacionales) {
        this.totalGastosInternacionales = totalGastosInternacionales;
    }

    public String getNumeroDescripcionPaquetesBultos() {
        return numeroDescripcionPaquetesBultos;
    }

    public void setNumeroDescripcionPaquetesBultos(String numeroDescripcionPaquetesBultos) {
        this.numeroDescripcionPaquetesBultos = numeroDescripcionPaquetesBultos;
    }

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }

    public LocalDateTime getFechaEmisionFactura() {
        return fechaEmisionFactura;
    }

    public void setFechaEmisionFactura(LocalDateTime fechaEmisionFactura) {
        this.fechaEmisionFactura = fechaEmisionFactura;
    }

    public BigDecimal getMontoTotalOriginal() {
        return montoTotalOriginal;
    }

    public void setMontoTotalOriginal(BigDecimal montoTotalOriginal) {
        this.montoTotalOriginal = montoTotalOriginal;
    }

    public BigDecimal getMontoTotalDevuelto() {
        return montoTotalDevuelto;
    }

    public void setMontoTotalDevuelto(BigDecimal montoTotalDevuelto) {
        this.montoTotalDevuelto = montoTotalDevuelto;
    }

    public BigDecimal getMontoDescuentoCreditoDebito() {
        return montoDescuentoCreditoDebito;
    }

    public void setMontoDescuentoCreditoDebito(BigDecimal montoDescuentoCreditoDebito) {
        this.montoDescuentoCreditoDebito = montoDescuentoCreditoDebito;
    }

    public BigDecimal getMontoEfectivoCreditoDebito() {
        return montoEfectivoCreditoDebito;
    }

    public void setMontoEfectivoCreditoDebito(BigDecimal montoEfectivoCreditoDebito) {
        this.montoEfectivoCreditoDebito = montoEfectivoCreditoDebito;
    }

    private Long nitEmisor;
    private String razonSocialEmisor;
    private String telefono;
    private Long numeroFactura;
    private Long numeroNotaCreditoDebito;
    private Long codigoSucursal;
    private String direccionsucursal;
    private Long codigoPuntoVenta;
    private LocalDateTime fechaEmision;
    private String nombreRazonSocial;
    private Long codigoTipoDocumentoIdentidad;
    private String numeroDocumento;
    private String complemento;
    private String codigoCliente;
    private Long codigoMetodoPago;
    private Long numeroTarjeta;
    private BigDecimal montoTotal;
    private BigDecimal montoTotalSujetoIva;
    private Long codigoMoneda;
    private BigDecimal tipoCambio;
    private BigDecimal montoTotalMoneda;
    private String usuario;
    private Long codigoDocumentoSector;
    ////////// EXPORTACION
    private String direccionComprador;
    private String incoterm;
    private String incotermDetalle;
    private String puertoDestino;
    private String lugarDestino;
    private Long codigoPais;
    private String costosGastosNacionales;
    private BigDecimal totalGastosNacionalesFob;
    private String costosGastosInternacionales;
    private BigDecimal totalGastosInternacionales;
    private String numeroDescripcionPaquetesBultos;
    private String informacionAdicional;

    ///ncd
    private LocalDateTime fechaEmisionFactura;
    private BigDecimal montoTotalOriginal;
    private BigDecimal montoTotalDevuelto;
    private BigDecimal montoDescuentoCreditoDebito;
    private BigDecimal montoEfectivoCreditoDebito;

    public Long getNitEmisor() {
        return nitEmisor;
    }

    public void setNitEmisor(Long nitEmisor) {
        this.nitEmisor = nitEmisor;
    }
}
