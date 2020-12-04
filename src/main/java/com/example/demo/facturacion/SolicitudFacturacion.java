
package com.example.demo.facturacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para solicitudFacturacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="solicitudFacturacion">
 *   &lt;complexContent>
 *     &lt;extension base="{https://siat.impuestos.gob.bo/}solicitudServicio">
 *       &lt;sequence>
 *         &lt;element name="codigoDocumentoSector" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codigoEmision" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codigoModalidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cufd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cuis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoFacturaDocumento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudFacturacion", propOrder = {
    "codigoDocumentoSector",
    "codigoEmision",
    "codigoModalidad",
    "cufd",
    "cuis",
    "tipoFacturaDocumento"
})
@XmlSeeAlso({
    SolicitudValidacionRecepcion.class,
    SolicitudAnulacion.class,
    SolicitudVerificacionEstado.class,
    SolicitudReversionAnulacion.class,
    SolicitudRecepcion.class
})
public abstract class SolicitudFacturacion
    extends SolicitudServicio
{

    protected int codigoDocumentoSector;
    protected int codigoEmision;
    protected int codigoModalidad;
    @XmlElement(required = true)
    protected String cufd;
    @XmlElement(required = true)
    protected String cuis;
    protected int tipoFacturaDocumento;

    /**
     * Obtiene el valor de la propiedad codigoDocumentoSector.
     * 
     */
    public int getCodigoDocumentoSector() {
        return codigoDocumentoSector;
    }

    /**
     * Define el valor de la propiedad codigoDocumentoSector.
     * 
     */
    public void setCodigoDocumentoSector(int value) {
        this.codigoDocumentoSector = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoEmision.
     * 
     */
    public int getCodigoEmision() {
        return codigoEmision;
    }

    /**
     * Define el valor de la propiedad codigoEmision.
     * 
     */
    public void setCodigoEmision(int value) {
        this.codigoEmision = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoModalidad.
     * 
     */
    public int getCodigoModalidad() {
        return codigoModalidad;
    }

    /**
     * Define el valor de la propiedad codigoModalidad.
     * 
     */
    public void setCodigoModalidad(int value) {
        this.codigoModalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad cufd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCufd() {
        return cufd;
    }

    /**
     * Define el valor de la propiedad cufd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCufd(String value) {
        this.cufd = value;
    }

    /**
     * Obtiene el valor de la propiedad cuis.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuis() {
        return cuis;
    }

    /**
     * Define el valor de la propiedad cuis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuis(String value) {
        this.cuis = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoFacturaDocumento.
     * 
     */
    public int getTipoFacturaDocumento() {
        return tipoFacturaDocumento;
    }

    /**
     * Define el valor de la propiedad tipoFacturaDocumento.
     * 
     */
    public void setTipoFacturaDocumento(int value) {
        this.tipoFacturaDocumento = value;
    }

}
