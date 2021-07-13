/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.xml;

import java.math.BigDecimal;


public class Detalle {

    private String codigoProducto;
    private String descripcion;
    private String codigoNandina;
    private BigDecimal cantidad;
    private BigDecimal unidadMedida;
    private BigDecimal precioUnitario;
    private BigDecimal montoDescuento;
    private BigDecimal subTotal;
    private String numeroSerie;
    private String numeroImei;
    private Long codigoDetalleTransaccion;

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoNandina() {
        return codigoNandina;
    }

    public void setCodigoNandina(String codigoNandina) {
        this.codigoNandina = codigoNandina;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(BigDecimal unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(BigDecimal montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getNumeroImei() {
        return numeroImei;
    }

    public void setNumeroImei(String numeroImei) {
        this.numeroImei = numeroImei;
    }

    public Long getCodigoDetalleTransaccion() {
        return codigoDetalleTransaccion;
    }

    public void setCodigoDetalleTransaccion(Long codigoDetalleTransaccion) {
        this.codigoDetalleTransaccion = codigoDetalleTransaccion;
    }
}
