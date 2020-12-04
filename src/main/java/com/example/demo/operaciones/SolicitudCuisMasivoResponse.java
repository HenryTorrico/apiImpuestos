
package com.example.demo.operaciones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para solicitudCuisMasivoResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="solicitudCuisMasivoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RespuestaCuisMasivo" type="{https://siat.impuestos.gob.bo/}respuestaCuisMasivo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudCuisMasivoResponse", propOrder = {
    "respuestaCuisMasivo"
})
public class SolicitudCuisMasivoResponse {

    @XmlElement(name = "RespuestaCuisMasivo")
    protected RespuestaCuisMasivo respuestaCuisMasivo;

    /**
     * Obtiene el valor de la propiedad respuestaCuisMasivo.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaCuisMasivo }
     *     
     */
    public RespuestaCuisMasivo getRespuestaCuisMasivo() {
        return respuestaCuisMasivo;
    }

    /**
     * Define el valor de la propiedad respuestaCuisMasivo.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaCuisMasivo }
     *     
     */
    public void setRespuestaCuisMasivo(RespuestaCuisMasivo value) {
        this.respuestaCuisMasivo = value;
    }

}
