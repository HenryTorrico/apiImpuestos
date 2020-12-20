
package com.example.demo.facturacionOperaciones;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para respuestaConsultaPuntoVenta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="respuestaConsultaPuntoVenta">
 *   &lt;complexContent>
 *     &lt;extension base="{https://siat.impuestos.gob.bo/}respuestaServicio">
 *       &lt;sequence>
 *         &lt;element name="listaPuntosVentas" type="{https://siat.impuestos.gob.bo/}puntosVentasDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "respuestaConsultaPuntoVenta", propOrder = {
    "listaPuntosVentas"
})
public class RespuestaConsultaPuntoVenta
    extends RespuestaServicio
{

    @XmlElement(nillable = true)
    protected List<PuntosVentasDto> listaPuntosVentas;

    /**
     * Gets the value of the listaPuntosVentas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaPuntosVentas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaPuntosVentas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PuntosVentasDto }
     * 
     * 
     */
    public List<PuntosVentasDto> getListaPuntosVentas() {
        if (listaPuntosVentas == null) {
            listaPuntosVentas = new ArrayList<PuntosVentasDto>();
        }
        return this.listaPuntosVentas;
    }

}
