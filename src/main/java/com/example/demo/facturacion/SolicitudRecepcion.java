
package com.example.demo.facturacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para solicitudRecepcion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="solicitudRecepcion">
 *   &lt;complexContent>
 *     &lt;extension base="{https://siat.impuestos.gob.bo/}solicitudFacturacion">
 *       &lt;sequence>
 *         &lt;element name="archivo" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="fechaEnvio" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="hashArchivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudRecepcion", propOrder = {
    "archivo",
    "fechaEnvio",
    "hashArchivo"
})
@XmlSeeAlso({
    SolicitudRecepcionPaquete.class,
    SolicitudRecepcionFactura.class,
    SolicitudRecepcionMasiva.class
})
public abstract class SolicitudRecepcion
    extends SolicitudFacturacion
{

    @XmlElement(required = true)
    protected byte[] archivo;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaEnvio;
    @XmlElement(required = true)
    protected String hashArchivo;

    /**
     * Obtiene el valor de la propiedad archivo.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getArchivo() {
        return archivo;
    }

    /**
     * Define el valor de la propiedad archivo.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setArchivo(byte[] value) {
        this.archivo = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaEnvio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * Define el valor de la propiedad fechaEnvio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEnvio(XMLGregorianCalendar value) {
        this.fechaEnvio = value;
    }

    /**
     * Obtiene el valor de la propiedad hashArchivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHashArchivo() {
        return hashArchivo;
    }

    /**
     * Define el valor de la propiedad hashArchivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashArchivo(String value) {
        this.hashArchivo = value;
    }

}
