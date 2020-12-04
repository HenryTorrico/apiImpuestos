
package com.example.demo.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para strMensajeAplicacionDto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="strMensajeAplicacionDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="codigoRelacionado" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionUi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="siglaSistema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoDestinoId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "strMensajeAplicacionDto", propOrder = {
    "codigo",
    "codigoRelacionado",
    "descripcion",
    "descripcionUi",
    "estadoId",
    "siglaSistema",
    "tipoDestinoId"
})
public class StrMensajeAplicacionDto {

    protected Integer codigo;
    protected Integer codigoRelacionado;
    protected String descripcion;
    protected String descripcionUi;
    protected String estadoId;
    protected String siglaSistema;
    protected Integer tipoDestinoId;

    /**
     * Obtiene el valor de la propiedad codigo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Define el valor de la propiedad codigo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigo(Integer value) {
        this.codigo = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoRelacionado.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigoRelacionado() {
        return codigoRelacionado;
    }

    /**
     * Define el valor de la propiedad codigoRelacionado.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigoRelacionado(Integer value) {
        this.codigoRelacionado = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionUi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionUi() {
        return descripcionUi;
    }

    /**
     * Define el valor de la propiedad descripcionUi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionUi(String value) {
        this.descripcionUi = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoId() {
        return estadoId;
    }

    /**
     * Define el valor de la propiedad estadoId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoId(String value) {
        this.estadoId = value;
    }

    /**
     * Obtiene el valor de la propiedad siglaSistema.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiglaSistema() {
        return siglaSistema;
    }

    /**
     * Define el valor de la propiedad siglaSistema.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiglaSistema(String value) {
        this.siglaSistema = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDestinoId.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipoDestinoId() {
        return tipoDestinoId;
    }

    /**
     * Define el valor de la propiedad tipoDestinoId.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipoDestinoId(Integer value) {
        this.tipoDestinoId = value;
    }

}
