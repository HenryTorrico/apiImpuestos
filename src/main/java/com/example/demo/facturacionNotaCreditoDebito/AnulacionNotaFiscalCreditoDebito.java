
package com.example.demo.facturacionNotaCreditoDebito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anulacionNotaFiscalCreditoDebito complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="anulacionNotaFiscalCreditoDebito">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SolicitudServicioAnulacionNotaFiscalCreditoDebito" type="{https://siat.impuestos.gob.bo/}solicitudAnulacion"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "anulacionNotaFiscalCreditoDebito", propOrder = {
    "solicitudServicioAnulacionNotaFiscalCreditoDebito"
})
public class AnulacionNotaFiscalCreditoDebito {

    @XmlElement(name = "SolicitudServicioAnulacionNotaFiscalCreditoDebito", required = true)
    protected SolicitudAnulacion solicitudServicioAnulacionNotaFiscalCreditoDebito;

    /**
     * Obtiene el valor de la propiedad solicitudServicioAnulacionNotaFiscalCreditoDebito.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudAnulacion }
     *     
     */
    public SolicitudAnulacion getSolicitudServicioAnulacionNotaFiscalCreditoDebito() {
        return solicitudServicioAnulacionNotaFiscalCreditoDebito;
    }

    /**
     * Define el valor de la propiedad solicitudServicioAnulacionNotaFiscalCreditoDebito.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudAnulacion }
     *     
     */
    public void setSolicitudServicioAnulacionNotaFiscalCreditoDebito(SolicitudAnulacion value) {
        this.solicitudServicioAnulacionNotaFiscalCreditoDebito = value;
    }

}
