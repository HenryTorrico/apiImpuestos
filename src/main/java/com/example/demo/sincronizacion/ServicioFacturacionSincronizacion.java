
package com.example.demo.sincronizacion;

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
@WebService(name = "ServicioFacturacionSincronizacion", targetNamespace = "https://siat.impuestos.gob.bo/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ServicioFacturacionSincronizacion {


    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaParametricas
     */
    @WebMethod
    @WebResult(name = "RespuestaListaParametricas", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarParametricaMotivoAnulacion", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaMotivoAnulacion")
    @ResponseWrapper(localName = "sincronizarParametricaMotivoAnulacionResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaMotivoAnulacionResponse")
    public RespuestaListaParametricas sincronizarParametricaMotivoAnulacion(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaActividades
     */
    @WebMethod
    @WebResult(name = "RespuestaListaActividades", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarActividades", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarActividades")
    @ResponseWrapper(localName = "sincronizarActividadesResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarActividadesResponse")
    public RespuestaListaActividades sincronizarActividades(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaFechaHora
     */
    @WebMethod
    @WebResult(name = "RespuestaFechaHora", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarFechaHora", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarFechaHora")
    @ResponseWrapper(localName = "sincronizarFechaHoraResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarFechaHoraResponse")
    public RespuestaFechaHora sincronizarFechaHora(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaParametricasLeyendas
     */
    @WebMethod
    @WebResult(name = "RespuestaListaParametricasLeyendas", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarListaLeyendasFactura", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarListaLeyendasFactura")
    @ResponseWrapper(localName = "sincronizarListaLeyendasFacturaResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarListaLeyendasFacturaResponse")
    public RespuestaListaParametricasLeyendas sincronizarListaLeyendasFactura(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaParametricas
     */
    @WebMethod
    @WebResult(name = "RespuestaListaParametricas", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarParametricaTipoHabitacion", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTipoHabitacion")
    @ResponseWrapper(localName = "sincronizarParametricaTipoHabitacionResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTipoHabitacionResponse")
    public RespuestaListaParametricas sincronizarParametricaTipoHabitacion(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaActividadesDocumentoSector
     */
    @WebMethod
    @WebResult(name = "RespuestaListaActividadesDocumentoSector", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarListaActividadesDocumentoSector", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarListaActividadesDocumentoSector")
    @ResponseWrapper(localName = "sincronizarListaActividadesDocumentoSectorResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarListaActividadesDocumentoSectorResponse")
    public RespuestaListaActividadesDocumentoSector sincronizarListaActividadesDocumentoSector(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaParametricas
     */
    @WebMethod
    @WebResult(name = "RespuestaListaParametricas", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarParametricaTipoDocumentoIdentidad", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTipoDocumentoIdentidad")
    @ResponseWrapper(localName = "sincronizarParametricaTipoDocumentoIdentidadResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTipoDocumentoIdentidadResponse")
    public RespuestaListaParametricas sincronizarParametricaTipoDocumentoIdentidad(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaParametricas
     */
    @WebMethod
    @WebResult(name = "RespuestaListaParametricas", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarParametricaUnidadMedida", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaUnidadMedida")
    @ResponseWrapper(localName = "sincronizarParametricaUnidadMedidaResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaUnidadMedidaResponse")
    public RespuestaListaParametricas sincronizarParametricaUnidadMedida(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaParametricas
     */
    @WebMethod
    @WebResult(name = "RespuestaListaParametricas", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarParametricaTipoDocumentoSector", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTipoDocumentoSector")
    @ResponseWrapper(localName = "sincronizarParametricaTipoDocumentoSectorResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTipoDocumentoSectorResponse")
    public RespuestaListaParametricas sincronizarParametricaTipoDocumentoSector(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaParametricas
     */
    @WebMethod
    @WebResult(name = "RespuestaListaParametricas", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarParametricaTiposFactura", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTiposFactura")
    @ResponseWrapper(localName = "sincronizarParametricaTiposFacturaResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTiposFacturaResponse")
    public RespuestaListaParametricas sincronizarParametricaTiposFactura(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @return
     *     returns java.lang.Integer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "verificarComunicacion", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.VerificarComunicacion")
    @ResponseWrapper(localName = "verificarComunicacionResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.VerificarComunicacionResponse")
    public Integer verificarComunicacion();

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaParametricas
     */
    @WebMethod
    @WebResult(name = "RespuestaListaParametricas", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarListaMensajesServicios", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarListaMensajesServicios")
    @ResponseWrapper(localName = "sincronizarListaMensajesServiciosResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarListaMensajesServiciosResponse")
    public RespuestaListaParametricas sincronizarListaMensajesServicios(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaParametricas
     */
    @WebMethod
    @WebResult(name = "RespuestaListaParametricas", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarParametricaTipoMetodoPago", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTipoMetodoPago")
    @ResponseWrapper(localName = "sincronizarParametricaTipoMetodoPagoResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTipoMetodoPagoResponse")
    public RespuestaListaParametricas sincronizarParametricaTipoMetodoPago(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaParametricas
     */
    @WebMethod
    @WebResult(name = "RespuestaListaParametricas", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarParametricaEventosSignificativos", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaEventosSignificativos")
    @ResponseWrapper(localName = "sincronizarParametricaEventosSignificativosResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaEventosSignificativosResponse")
    public RespuestaListaParametricas sincronizarParametricaEventosSignificativos(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaParametricas
     */
    @WebMethod
    @WebResult(name = "RespuestaListaParametricas", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarParametricaTipoPuntoVenta", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTipoPuntoVenta")
    @ResponseWrapper(localName = "sincronizarParametricaTipoPuntoVentaResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTipoPuntoVentaResponse")
    public RespuestaListaParametricas sincronizarParametricaTipoPuntoVenta(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaProductos
     */
    @WebMethod
    @WebResult(name = "RespuestaListaProductos", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarListaProductosServicios", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarListaProductosServicios")
    @ResponseWrapper(localName = "sincronizarListaProductosServiciosResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarListaProductosServiciosResponse")
    public RespuestaListaProductos sincronizarListaProductosServicios(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaParametricas
     */
    @WebMethod
    @WebResult(name = "RespuestaListaParametricas", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarParametricaTipoEmision", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTipoEmision")
    @ResponseWrapper(localName = "sincronizarParametricaTipoEmisionResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTipoEmisionResponse")
    public RespuestaListaParametricas sincronizarParametricaTipoEmision(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaParametricas
     */
    @WebMethod
    @WebResult(name = "RespuestaListaParametricas", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarParametricaPaisOrigen", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaPaisOrigen")
    @ResponseWrapper(localName = "sincronizarParametricaPaisOrigenResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaPaisOrigenResponse")
    public RespuestaListaParametricas sincronizarParametricaPaisOrigen(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

    /**
     * 
     * @param solicitudSincronizacion
     * @return
     *     returns com.example.demo.sincronizacion.RespuestaListaParametricas
     */
    @WebMethod
    @WebResult(name = "RespuestaListaParametricas", targetNamespace = "")
    @RequestWrapper(localName = "sincronizarParametricaTipoMoneda", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTipoMoneda")
    @ResponseWrapper(localName = "sincronizarParametricaTipoMonedaResponse", targetNamespace = "https://siat.impuestos.gob.bo/", className = "com.example.demo.sincronizacion.SincronizarParametricaTipoMonedaResponse")
    public RespuestaListaParametricas sincronizarParametricaTipoMoneda(
        @WebParam(name = "SolicitudSincronizacion", targetNamespace = "")
        SolicitudSincronizacion solicitudSincronizacion);

}