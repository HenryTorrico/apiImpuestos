
package com.example.demo.facturacionNotaCreditoDebito;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ServicioFacturacion", targetNamespace = "https://siat.impuestos.gob.bo/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ServicioFacturacion {


    /**
     * 
     * @return
     *     returns java.lang.Integer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "verificarComunicacion", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.facturacionNotaCreditoDebito.VerificarComunicacion")
    @ResponseWrapper(localName = "verificarComunicacionResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.facturacionNotaCreditoDebito.VerificarComunicacionResponse")
    public Integer verificarComunicacion();

    /**
     * 
     * @param solicitudServicioAnulacionNotaFiscalCreditoDebito
     * @return
     *     returns com.example.demo.facturacionNotaCreditoDebito.RespuestaRecepcion
     */
    @WebMethod
    @WebResult(name = "RespuestaServicioFacturacion", targetNamespace = "")
    @RequestWrapper(localName = "anulacionNotaFiscalCreditoDebito", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.facturacionNotaCreditoDebito.AnulacionNotaFiscalCreditoDebito")
    @ResponseWrapper(localName = "anulacionNotaFiscalCreditoDebitoResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.facturacionNotaCreditoDebito.AnulacionNotaFiscalCreditoDebitoResponse")
    public RespuestaRecepcion anulacionNotaFiscalCreditoDebito(
        @WebParam(name = "SolicitudServicioAnulacionNotaFiscalCreditoDebito", targetNamespace = "")
        SolicitudAnulacion solicitudServicioAnulacionNotaFiscalCreditoDebito);

    /**
     * 
     * @param solicitudServicioReversionAnulacionNotaFiscalCreditoDebito
     * @return
     *     returns com.example.demo.facturacionNotaCreditoDebito.RespuestaRecepcion
     */
    @WebMethod
    @WebResult(name = "RespuestaServicioFacturacion", targetNamespace = "")
    @RequestWrapper(localName = "reversionAnulacionNotaFiscalCreditoDebito", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.facturacionNotaCreditoDebito.ReversionAnulacionNotaFiscalCreditoDebito")
    @ResponseWrapper(localName = "reversionAnulacionNotaFiscalCreditoDebitoResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.facturacionNotaCreditoDebito.ReversionAnulacionNotaFiscalCreditoDebitoResponse")
    public RespuestaRecepcion reversionAnulacionNotaFiscalCreditoDebito(
        @WebParam(name = "SolicitudServicioReversionAnulacionNotaFiscalCreditoDebito", targetNamespace = "")
        SolicitudReversionAnulacion solicitudServicioReversionAnulacionNotaFiscalCreditoDebito);

    /**
     * 
     * @param solicitudServicioVerificacionEstadoNotaFiscalCreditoDebito
     * @return
     *     returns com.example.demo.facturacionNotaCreditoDebito.RespuestaRecepcion
     */
    @WebMethod
    @WebResult(name = "RespuestaServicioFacturacion", targetNamespace = "")
    @RequestWrapper(localName = "verificacionEstadoNotaFiscalCreditoDebito", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.facturacionNotaCreditoDebito.VerificacionEstadoNotaFiscalCreditoDebito")
    @ResponseWrapper(localName = "verificacionEstadoNotaFiscalCreditoDebitoResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.facturacionNotaCreditoDebito.VerificacionEstadoNotaFiscalCreditoDebitoResponse")
    public RespuestaRecepcion verificacionEstadoNotaFiscalCreditoDebito(
        @WebParam(name = "SolicitudServicioVerificacionEstadoNotaFiscalCreditoDebito", targetNamespace = "")
        SolicitudVerificacionEstado solicitudServicioVerificacionEstadoNotaFiscalCreditoDebito);

    /**
     * 
     * @param solicitudServicioRecepcionNotaFiscalCreditoDebito
     * @return
     *     returns com.example.demo.facturacionNotaCreditoDebito.RespuestaRecepcion
     */
    @WebMethod
    @WebResult(name = "RespuestaServicioFacturacion", targetNamespace = "")
    @RequestWrapper(localName = "recepcionNotaFiscalCreditoDebito", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.facturacionNotaCreditoDebito.RecepcionNotaFiscalCreditoDebito")
    @ResponseWrapper(localName = "recepcionNotaFiscalCreditoDebitoResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.facturacionNotaCreditoDebito.RecepcionNotaFiscalCreditoDebitoResponse")
    public RespuestaRecepcion recepcionNotaFiscalCreditoDebito(
        @WebParam(name = "SolicitudServicioRecepcionNotaFiscalCreditoDebito", targetNamespace = "")
        SolicitudRecepcionFactura solicitudServicioRecepcionNotaFiscalCreditoDebito);

}