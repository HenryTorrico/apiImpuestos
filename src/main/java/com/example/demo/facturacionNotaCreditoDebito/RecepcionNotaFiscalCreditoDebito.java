
package com.example.demo.facturacionNotaCreditoDebito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para recepcionNotaFiscalCreditoDebito complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="recepcionNotaFiscalCreditoDebito">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SolicitudServicioRecepcionNotaFiscalCreditoDebito" type="{https://siat.impuestos.gob.bo/}solicitudRecepcionFactura"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recepcionNotaFiscalCreditoDebito", propOrder = {
    "solicitudServicioRecepcionNotaFiscalCreditoDebito"
})
public class RecepcionNotaFiscalCreditoDebito {

    @XmlElement(name = "SolicitudServicioRecepcionNotaFiscalCreditoDebito", required = true)
    protected SolicitudRecepcionFactura solicitudServicioRecepcionNotaFiscalCreditoDebito;

    /**
     * Obtiene el valor de la propiedad solicitudServicioRecepcionNotaFiscalCreditoDebito.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudRecepcionFactura }
     *     
     */
    public SolicitudRecepcionFactura getSolicitudServicioRecepcionNotaFiscalCreditoDebito() {
        return solicitudServicioRecepcionNotaFiscalCreditoDebito;
    }

    /**
     * Define el valor de la propiedad solicitudServicioRecepcionNotaFiscalCreditoDebito.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudRecepcionFactura }
     *     
     */
    public void setSolicitudServicioRecepcionNotaFiscalCreditoDebito(SolicitudRecepcionFactura value) {
        this.solicitudServicioRecepcionNotaFiscalCreditoDebito = value;
    }

}
