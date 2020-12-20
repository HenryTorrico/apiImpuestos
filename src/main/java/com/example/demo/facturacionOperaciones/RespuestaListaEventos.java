
package com.example.demo.facturacionOperaciones;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para respuestaListaEventos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="respuestaListaEventos">
 *   &lt;complexContent>
 *     &lt;extension base="{https://siat.impuestos.gob.bo/}respuestaServicio">
 *       &lt;sequence>
 *         &lt;element name="codigoRecepcionEventoSignificativo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="listaCodigos" type="{https://siat.impuestos.gob.bo/}eventosSignificativosDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "respuestaListaEventos", propOrder = {
    "codigoRecepcionEventoSignificativo",
    "listaCodigos"
})
public class RespuestaListaEventos
    extends RespuestaServicio
{

    protected Long codigoRecepcionEventoSignificativo;
    @XmlElement(nillable = true)
    protected List<EventosSignificativosDto> listaCodigos;

    /**
     * Obtiene el valor de la propiedad codigoRecepcionEventoSignificativo.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCodigoRecepcionEventoSignificativo() {
        return codigoRecepcionEventoSignificativo;
    }

    /**
     * Define el valor de la propiedad codigoRecepcionEventoSignificativo.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCodigoRecepcionEventoSignificativo(Long value) {
        this.codigoRecepcionEventoSignificativo = value;
    }

    /**
     * Gets the value of the listaCodigos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaCodigos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaCodigos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventosSignificativosDto }
     * 
     * 
     */
    public List<EventosSignificativosDto> getListaCodigos() {
        if (listaCodigos == null) {
            listaCodigos = new ArrayList<EventosSignificativosDto>();
        }
        return this.listaCodigos;
    }

}
