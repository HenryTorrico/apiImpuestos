
package com.example.demo.operaciones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para solicitudCuisResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="solicitudCuisResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RespuestaCuis" type="{https://siat.impuestos.gob.bo/}respuestaCuis" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudCuisResponse", propOrder = {
    "respuestaCuis"
})
public class SolicitudCuisResponse {

    @XmlElement(name = "RespuestaCuis")
    protected RespuestaCuis respuestaCuis;

    /**
     * Obtiene el valor de la propiedad respuestaCuis.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaCuis }
     *     
     */
    public RespuestaCuis getRespuestaCuis() {
        return respuestaCuis;
    }

    /**
     * Define el valor de la propiedad respuestaCuis.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaCuis }
     *     
     */
    public void setRespuestaCuis(RespuestaCuis value) {
        this.respuestaCuis = value;
    }

}
