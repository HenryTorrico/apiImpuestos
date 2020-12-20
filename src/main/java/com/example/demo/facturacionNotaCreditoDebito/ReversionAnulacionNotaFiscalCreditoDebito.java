
package com.example.demo.facturacionNotaCreditoDebito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para reversionAnulacionNotaFiscalCreditoDebito complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="reversionAnulacionNotaFiscalCreditoDebito">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SolicitudServicioReversionAnulacionNotaFiscalCreditoDebito" type="{https://siat.impuestos.gob.bo/}solicitudReversionAnulacion"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reversionAnulacionNotaFiscalCreditoDebito", propOrder = {
    "solicitudServicioReversionAnulacionNotaFiscalCreditoDebito"
})
public class ReversionAnulacionNotaFiscalCreditoDebito {

    @XmlElement(name = "SolicitudServicioReversionAnulacionNotaFiscalCreditoDebito", required = true)
    protected SolicitudReversionAnulacion solicitudServicioReversionAnulacionNotaFiscalCreditoDebito;

    /**
     * Obtiene el valor de la propiedad solicitudServicioReversionAnulacionNotaFiscalCreditoDebito.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudReversionAnulacion }
     *     
     */
    public SolicitudReversionAnulacion getSolicitudServicioReversionAnulacionNotaFiscalCreditoDebito() {
        return solicitudServicioReversionAnulacionNotaFiscalCreditoDebito;
    }

    /**
     * Define el valor de la propiedad solicitudServicioReversionAnulacionNotaFiscalCreditoDebito.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudReversionAnulacion }
     *     
     */
    public void setSolicitudServicioReversionAnulacionNotaFiscalCreditoDebito(SolicitudReversionAnulacion value) {
        this.solicitudServicioReversionAnulacionNotaFiscalCreditoDebito = value;
    }

}
