
package com.example.demo.facturacionOperaciones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para registroFacturasRegularizacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="registroFacturasRegularizacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SolicitudRegFactRegularizacion" type="{https://siat.impuestos.gob.bo/}solicitudRegFactRegularizacion"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registroFacturasRegularizacion", propOrder = {
    "solicitudRegFactRegularizacion"
})
public class RegistroFacturasRegularizacion {

    @XmlElement(name = "SolicitudRegFactRegularizacion", required = true)
    protected SolicitudRegFactRegularizacion solicitudRegFactRegularizacion;

    /**
     * Obtiene el valor de la propiedad solicitudRegFactRegularizacion.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudRegFactRegularizacion }
     *     
     */
    public SolicitudRegFactRegularizacion getSolicitudRegFactRegularizacion() {
        return solicitudRegFactRegularizacion;
    }

    /**
     * Define el valor de la propiedad solicitudRegFactRegularizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudRegFactRegularizacion }
     *     
     */
    public void setSolicitudRegFactRegularizacion(SolicitudRegFactRegularizacion value) {
        this.solicitudRegFactRegularizacion = value;
    }

}
