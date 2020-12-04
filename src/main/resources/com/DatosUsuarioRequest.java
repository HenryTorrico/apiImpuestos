
package com;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosUsuarioRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosUsuarioRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="client" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="login" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nit" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoClienteId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tipoUsuarioId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosUsuarioRequest", propOrder = {
    "client",
    "ip",
    "login",
    "nit",
    "password",
    "tipoClienteId",
    "tipoUsuarioId"
})
public class DatosUsuarioRequest {

    protected String client;
    protected String ip;
    @XmlElement(required = true)
    protected String login;
    protected Long nit;
    @XmlElement(required = true)
    protected String password;
    protected Integer tipoClienteId;
    protected Integer tipoUsuarioId;

    /**
     * Obtiene el valor de la propiedad client.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClient() {
        return client;
    }

    /**
     * Define el valor de la propiedad client.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClient(String value) {
        this.client = value;
    }

    /**
     * Obtiene el valor de la propiedad ip.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIp() {
        return ip;
    }

    /**
     * Define el valor de la propiedad ip.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * Obtiene el valor de la propiedad login.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogin() {
        return login;
    }

    /**
     * Define el valor de la propiedad login.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogin(String value) {
        this.login = value;
    }

    /**
     * Obtiene el valor de la propiedad nit.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNit() {
        return nit;
    }

    /**
     * Define el valor de la propiedad nit.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNit(Long value) {
        this.nit = value;
    }

    /**
     * Obtiene el valor de la propiedad password.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define el valor de la propiedad password.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoClienteId.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipoClienteId() {
        return tipoClienteId;
    }

    /**
     * Define el valor de la propiedad tipoClienteId.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipoClienteId(Integer value) {
        this.tipoClienteId = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoUsuarioId.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    /**
     * Define el valor de la propiedad tipoUsuarioId.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipoUsuarioId(Integer value) {
        this.tipoUsuarioId = value;
    }

}
