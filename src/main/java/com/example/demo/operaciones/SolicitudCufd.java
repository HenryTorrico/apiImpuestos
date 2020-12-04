
package com.example.demo.operaciones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para solicitudCufd complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="solicitudCufd">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SolicitudOperaciones" type="{https://siat.impuestos.gob.bo/}solicitudOperacionesCufd"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudCufd", propOrder = {
    "solicitudOperaciones"
})
public class SolicitudCufd {

    @XmlElement(name = "SolicitudOperaciones", required = true)
    protected SolicitudOperacionesCufd solicitudOperaciones;

    /**
     * Obtiene el valor de la propiedad solicitudOperaciones.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudOperacionesCufd }
     *     
     */
    public SolicitudOperacionesCufd getSolicitudOperaciones() {
        return solicitudOperaciones;
    }

    /**
     * Define el valor de la propiedad solicitudOperaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudOperacionesCufd }
     *     
     */
    public void setSolicitudOperaciones(SolicitudOperacionesCufd value) {
        this.solicitudOperaciones = value;
    }

}
