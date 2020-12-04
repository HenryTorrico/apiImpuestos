
package com.example.demo.operaciones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para solicitudCuis complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="solicitudCuis">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SolicitudOperacionesCuis" type="{https://siat.impuestos.gob.bo/}solicitudOperacionesCuis"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudCuis", propOrder = {
    "solicitudOperacionesCuis"
})
public class SolicitudCuis {

    @XmlElement(name = "SolicitudOperacionesCuis", required = true)
    protected SolicitudOperacionesCuis solicitudOperacionesCuis;

    /**
     * Obtiene el valor de la propiedad solicitudOperacionesCuis.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudOperacionesCuis }
     *     
     */
    public SolicitudOperacionesCuis getSolicitudOperacionesCuis() {
        return solicitudOperacionesCuis;
    }

    /**
     * Define el valor de la propiedad solicitudOperacionesCuis.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudOperacionesCuis }
     *     
     */
    public void setSolicitudOperacionesCuis(SolicitudOperacionesCuis value) {
        this.solicitudOperacionesCuis = value;
    }

}
