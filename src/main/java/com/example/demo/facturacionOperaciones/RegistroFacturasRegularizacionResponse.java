
package com.example.demo.facturacionOperaciones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para registroFacturasRegularizacionResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="registroFacturasRegularizacionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RespuestaRegFactRegularizacion" type="{https://siat.impuestos.gob.bo/}respuestaRegFactRegularizacion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registroFacturasRegularizacionResponse", propOrder = {
    "respuestaRegFactRegularizacion"
})
public class RegistroFacturasRegularizacionResponse {

    @XmlElement(name = "RespuestaRegFactRegularizacion")
    protected RespuestaRegFactRegularizacion respuestaRegFactRegularizacion;

    /**
     * Obtiene el valor de la propiedad respuestaRegFactRegularizacion.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaRegFactRegularizacion }
     *     
     */
    public RespuestaRegFactRegularizacion getRespuestaRegFactRegularizacion() {
        return respuestaRegFactRegularizacion;
    }

    /**
     * Define el valor de la propiedad respuestaRegFactRegularizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaRegFactRegularizacion }
     *     
     */
    public void setRespuestaRegFactRegularizacion(RespuestaRegFactRegularizacion value) {
        this.respuestaRegFactRegularizacion = value;
    }

}
