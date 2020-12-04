
package com.example.demo.operaciones;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para respuestaCufdMasivo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="respuestaCufdMasivo">
 *   &lt;complexContent>
 *     &lt;extension base="{https://siat.impuestos.gob.bo/}respuestaServicio">
 *       &lt;sequence>
 *         &lt;element name="listaRespuestasCufd" type="{https://siat.impuestos.gob.bo/}respuestaListaRegistroCufdSoapDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "respuestaCufdMasivo", propOrder = {
    "listaRespuestasCufd"
})
public class RespuestaCufdMasivo
    extends RespuestaServicio
{

    @XmlElement(nillable = true)
    protected List<RespuestaListaRegistroCufdSoapDto> listaRespuestasCufd;

    /**
     * Gets the value of the listaRespuestasCufd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaRespuestasCufd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaRespuestasCufd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RespuestaListaRegistroCufdSoapDto }
     * 
     * 
     */
    public List<RespuestaListaRegistroCufdSoapDto> getListaRespuestasCufd() {
        if (listaRespuestasCufd == null) {
            listaRespuestasCufd = new ArrayList<RespuestaListaRegistroCufdSoapDto>();
        }
        return this.listaRespuestasCufd;
    }

}
