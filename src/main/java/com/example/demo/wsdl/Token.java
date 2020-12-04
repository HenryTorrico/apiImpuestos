
package com.example.demo.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para token complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="token">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatosUsuarioRequest" type="{https://siat.impuestos.gob.bo/}DatosUsuarioRequest"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "token", propOrder = {
    "datosUsuarioRequest"
})
public class Token {

    @XmlElement(name = "DatosUsuarioRequest", required = true)
    protected DatosUsuarioRequest datosUsuarioRequest;

    /**
     * Obtiene el valor de la propiedad datosUsuarioRequest.
     * 
     * @return
     *     possible object is
     *     {@link DatosUsuarioRequest }
     *     
     */
    public DatosUsuarioRequest getDatosUsuarioRequest() {
        return datosUsuarioRequest;
    }

    /**
     * Define el valor de la propiedad datosUsuarioRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosUsuarioRequest }
     *     
     */
    public void setDatosUsuarioRequest(DatosUsuarioRequest value) {
        this.datosUsuarioRequest = value;
    }

}
