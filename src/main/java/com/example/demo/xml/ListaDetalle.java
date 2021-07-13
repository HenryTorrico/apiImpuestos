package com.example.demo.xml;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class ListaDetalle {

    private List<Detalle> detalleList;

    @XmlElement
    public List<Detalle> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<Detalle> detalleList) {
        this.detalleList = detalleList;
    }
}
