
package com;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tokenResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="tokenResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UsuarioAutenticadoDto" type="{https://siat.impuestos.gob.bo/}usuarioAutenticadoDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tokenResponse", propOrder = {
    "usuarioAutenticadoDto"
})
public class TokenResponse {

    @XmlElement(name = "UsuarioAutenticadoDto")
    protected UsuarioAutenticadoDto usuarioAutenticadoDto;

    /**
     * Obtiene el valor de la propiedad usuarioAutenticadoDto.
     * 
     * @return
     *     possible object is
     *     {@link UsuarioAutenticadoDto }
     *     
     */
    public UsuarioAutenticadoDto getUsuarioAutenticadoDto() {
        return usuarioAutenticadoDto;
    }

    /**
     * Define el valor de la propiedad usuarioAutenticadoDto.
     * 
     * @param value
     *     allowed object is
     *     {@link UsuarioAutenticadoDto }
     *     
     */
    public void setUsuarioAutenticadoDto(UsuarioAutenticadoDto value) {
        this.usuarioAutenticadoDto = value;
    }

}
