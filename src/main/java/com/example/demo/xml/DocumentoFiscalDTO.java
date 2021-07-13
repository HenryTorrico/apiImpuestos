/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.xml;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 *
 * @author ruth
 */
@XmlRootElement
public class DocumentoFiscalDTO extends PropertyNamingStrategy {

    private Cabecera cabecera;
    private List<Detalle> listaDetalle;
    private boolean valido;

    @XmlElement

    public Cabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(Cabecera cabecera) {
        this.cabecera = cabecera;
    }

    @XmlElement
    public List<Detalle> getDetalle() {
        return listaDetalle;
    }


    public void setDetalle(List<Detalle> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }


    public boolean isValido() {
        return valido;
    }


    public void setValido(boolean valido) {
        this.valido = valido;
    }

}
