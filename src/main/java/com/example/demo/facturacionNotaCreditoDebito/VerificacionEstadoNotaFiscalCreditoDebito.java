
package com.example.demo.facturacionNotaCreditoDebito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para verificacionEstadoNotaFiscalCreditoDebito complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="verificacionEstadoNotaFiscalCreditoDebito">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SolicitudServicioVerificacionEstadoNotaFiscalCreditoDebito" type="{https://siat.impuestos.gob.bo/}solicitudVerificacionEstado"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verificacionEstadoNotaFiscalCreditoDebito", propOrder = {
    "solicitudServicioVerificacionEstadoNotaFiscalCreditoDebito"
})
public class VerificacionEstadoNotaFiscalCreditoDebito {

    @XmlElement(name = "SolicitudServicioVerificacionEstadoNotaFiscalCreditoDebito", required = true)
    protected SolicitudVerificacionEstado solicitudServicioVerificacionEstadoNotaFiscalCreditoDebito;

    /**
     * Obtiene el valor de la propiedad solicitudServicioVerificacionEstadoNotaFiscalCreditoDebito.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudVerificacionEstado }
     *     
     */
    public SolicitudVerificacionEstado getSolicitudServicioVerificacionEstadoNotaFiscalCreditoDebito() {
        return solicitudServicioVerificacionEstadoNotaFiscalCreditoDebito;
    }

    /**
     * Define el valor de la propiedad solicitudServicioVerificacionEstadoNotaFiscalCreditoDebito.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudVerificacionEstado }
     *     
     */
    public void setSolicitudServicioVerificacionEstadoNotaFiscalCreditoDebito(SolicitudVerificacionEstado value) {
        this.solicitudServicioVerificacionEstadoNotaFiscalCreditoDebito = value;
    }

}
