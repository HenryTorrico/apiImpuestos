package com.example.demo.controller.facturacion;


import com.example.demo.controller.facturacionOperaciones.FacturacionOperacionesController;
import com.example.demo.facturacion.*;
import com.example.demo.operaciones.RespuestaCuis;
import com.example.demo.operaciones.ServicioFacturacionCodigos;
import com.example.demo.operaciones.SolicitudOperacionesCuis;
import com.example.demo.service.CufdService;
import com.example.demo.service.CuisService;
import com.example.demo.service.TokenService;
import com.example.demo.wsdl.DatosUsuarioRequest;
import com.example.demo.wsdl.ServicioAutenticacionSoap;
import com.example.demo.wsdl.StrMensajeAplicacionDto;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.message.Message;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/servicioFacturacion", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ServicioFacturacionController  {

    @Autowired
    TokenService tokenService;

    @Autowired
    CuisService cuisService;

    @Autowired
    CufdService cufdService;

    @Autowired
    FacturacionOperacionesController facturacionOperacionesController;
    @RequestMapping(value="/recepcionFactura", method=RequestMethod.GET)
    public String recepcionFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        ServicioFacturacion recepcionFactura = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudRecepcionFactura solicitudRecepcionFactura=new SolicitudRecepcionFactura();
        solicitudRecepcionFactura.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudRecepcionFactura.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudRecepcionFactura.setNit(((Number) data.get("nit")).longValue());
        solicitudRecepcionFactura.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudRecepcionFactura.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudRecepcionFactura.setCodigoPuntoVenta((Integer)data.get("codigoPuntoVenta"));
        solicitudRecepcionFactura.setArchivo((byte[]) data.get("archivo"));
        solicitudRecepcionFactura.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudRecepcionFactura.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudRecepcionFactura.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudRecepcionFactura.setFechaEnvio((XMLGregorianCalendar) data.get("fechaEnvio"));
        solicitudRecepcionFactura.setHashArchivo((String) data.get("hashArchivo"));
        solicitudRecepcionFactura.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudRecepcionFactura.setCuis(cuisService.findCuis().get(0).getCuis());
        Client client = ClientProxy.getClient(recepcionFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=recepcionFactura.recepcionFactura(solicitudRecepcionFactura);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return respuestaRecepcion.getCodigoRecepcion();
    }

    private ServicioFacturacion getServicioFacturacion() {
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacion.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        return (ServicioFacturacion) factory.create();
    }


    @RequestMapping(value="/anulacionFactura", method=RequestMethod.GET)
    public String anulacionFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        ServicioFacturacion anulacionFactura = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudAnulacion solicitudAnulacion=new SolicitudAnulacion();
        solicitudAnulacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudAnulacion.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudAnulacion.setNit(((Number) data.get("nit")).longValue());
        solicitudAnulacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudAnulacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudAnulacion.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudAnulacion.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudAnulacion.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudAnulacion.setCodigoMotivo((Integer) data.get("codigoMotivo"));
        solicitudAnulacion.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudAnulacion.setCuis(cuisService.findCuis().get(0).getCuis());
        String nit=(String) data.get("nit");
        String fechaHora=(String) data.get("fechaHora");
        String sucursal=(String) data.get("codigoSucursal");
        String modalidad=(String) data.get("codigoModalidad");
        String tipoEmision=(String) data.get("tipoEmision");
        String tipoFactura=(String) data.get("tipoFactura");
        String tipoDocSector=(String) data.get("tipoDocSector");
        String numeroFactura=(String) data.get("numeroFactura");
        String puntoVenta=(String) data.get("codigoPuntoVenta");
        solicitudAnulacion.setCuf(facturacionOperacionesController.getCuf(nit,fechaHora,sucursal,modalidad,tipoEmision,tipoFactura,tipoDocSector,numeroFactura,puntoVenta));

        Client client = ClientProxy.getClient(anulacionFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=anulacionFactura.anulacionFactura(solicitudAnulacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return respuestaRecepcion.getCodigoRecepcion();
    }
    @RequestMapping(value="/recepcionMasivaFacturas", method=RequestMethod.GET)
    public String recepcionMasivaFacturas(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        ServicioFacturacion recepcionMasivaFactura = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudRecepcionMasiva solicitudRecepcionMasiva=new SolicitudRecepcionMasiva();
        solicitudRecepcionMasiva.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudRecepcionMasiva.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudRecepcionMasiva.setNit(((Number) data.get("nit")).longValue());
        solicitudRecepcionMasiva.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudRecepcionMasiva.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudRecepcionMasiva.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudRecepcionMasiva.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudRecepcionMasiva.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudRecepcionMasiva.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudRecepcionMasiva.setCuis(cuisService.findCuis().get(0).getCuis());
        solicitudRecepcionMasiva.setCantidadFacturas((Integer) data.get("cantidadFacturas"));
        solicitudRecepcionMasiva.setArchivo((byte[]) data.get("cuis"));
        solicitudRecepcionMasiva.setHashArchivo(cuisService.findCuis().get(0).getCuis());
        solicitudRecepcionMasiva.setFechaEnvio((XMLGregorianCalendar) data.get("cuis"));
        Client client = ClientProxy.getClient(recepcionMasivaFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=recepcionMasivaFactura.recepcionMasivaFactura(solicitudRecepcionMasiva);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaRecepcion.getCodigoRecepcion();
    }
    @RequestMapping(value="/recepcionPaqueteFacturas", method=RequestMethod.GET)
    public String recepcionPaqueteFacturas(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacion recepcionPaqueteFacturas = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudRecepcionPaquete solicitudRecepcionPaquete=new SolicitudRecepcionPaquete();
        solicitudRecepcionPaquete.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudRecepcionPaquete.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudRecepcionPaquete.setNit(((Number) data.get("nit")).longValue());
        solicitudRecepcionPaquete.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudRecepcionPaquete.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudRecepcionPaquete.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudRecepcionPaquete.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudRecepcionPaquete.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudRecepcionPaquete.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudRecepcionPaquete.setCuis(cuisService.findCuis().get(0).getCuis());
        solicitudRecepcionPaquete.setCantidadFacturas((Integer) data.get("cantidadFacturas"));
        solicitudRecepcionPaquete.setArchivo((byte[]) data.get("cuis"));
        solicitudRecepcionPaquete.setHashArchivo(cuisService.findCuis().get(0).getCuis());
        solicitudRecepcionPaquete.setFechaEnvio((XMLGregorianCalendar) data.get("cuis"));
        solicitudRecepcionPaquete.setCodigoEvento((long) data.get("codigoEvento"));
        Client client = ClientProxy.getClient(recepcionPaqueteFacturas);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=recepcionPaqueteFacturas.recepcionPaqueteFactura(solicitudRecepcionPaquete);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaRecepcion.getCodigoRecepcion();
    }
    @RequestMapping(value="/reversionAnulacionFactura", method=RequestMethod.GET)
    public String reversionAnulacionFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacion reversionAnulacionFactura = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudReversionAnulacion solicitudReversionAnulacion=new SolicitudReversionAnulacion();
        solicitudReversionAnulacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudReversionAnulacion.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudReversionAnulacion.setNit(((Number) data.get("nit")).longValue());
        solicitudReversionAnulacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudReversionAnulacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudReversionAnulacion.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudReversionAnulacion.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudReversionAnulacion.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudReversionAnulacion.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudReversionAnulacion.setCuis(cuisService.findCuis().get(0).getCuis());
        String nit=(String) data.get("nit");
        String fechaHora=(String) data.get("fechaHora");
        String sucursal=(String) data.get("codigoSucursal");
        String modalidad=(String) data.get("codigoModalidad");
        String tipoEmision=(String) data.get("tipoEmision");
        String tipoFactura=(String) data.get("tipoFactura");
        String tipoDocSector=(String) data.get("tipoDocSector");
        String numeroFactura=(String) data.get("numeroFactura");
        String puntoVenta=(String) data.get("codigoPuntoVenta");
        solicitudReversionAnulacion.setCuf(facturacionOperacionesController.getCuf(nit,fechaHora,sucursal,modalidad,tipoEmision,tipoFactura,tipoDocSector,numeroFactura,puntoVenta));
        Client client = ClientProxy.getClient(reversionAnulacionFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=reversionAnulacionFactura.reversionAnulacionFactura(solicitudReversionAnulacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaRecepcion.getCodigoRecepcion();
    }
    @RequestMapping(value="/validacionRecepcionMasivaFactura", method=RequestMethod.GET)
    public String validacionRecepcionMasivaFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacion validacionRecepcionMasivaFactura = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudValidacionRecepcion solicitudValidacionRecepcion=new SolicitudValidacionRecepcion();
        solicitudValidacionRecepcion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudValidacionRecepcion.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudValidacionRecepcion.setNit(((Number) data.get("nit")).longValue());
        solicitudValidacionRecepcion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudValidacionRecepcion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudValidacionRecepcion.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudValidacionRecepcion.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudValidacionRecepcion.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudValidacionRecepcion.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudValidacionRecepcion.setCuis(cuisService.findCuis().get(0).getCuis());
        solicitudValidacionRecepcion.setCodigoRecepcion((String) data.get("codigoRecepcion"));
        Client client = ClientProxy.getClient(validacionRecepcionMasivaFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=validacionRecepcionMasivaFactura.validacionRecepcionMasivaFactura(solicitudValidacionRecepcion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaRecepcion.getCodigoRecepcion();
    }
    @RequestMapping(value="/validacionRecepcionPaqueteFactura", method=RequestMethod.GET)
    public String validacionRecepcionPaqueteFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacion validacionRecepcionPaqueteFactura = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudValidacionRecepcion solicitudValidacionRecepcion=new SolicitudValidacionRecepcion();
        solicitudValidacionRecepcion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudValidacionRecepcion.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudValidacionRecepcion.setNit(((Number) data.get("nit")).longValue());
        solicitudValidacionRecepcion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudValidacionRecepcion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudValidacionRecepcion.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudValidacionRecepcion.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudValidacionRecepcion.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudValidacionRecepcion.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudValidacionRecepcion.setCuis(cuisService.findCuis().get(0).getCuis());
        solicitudValidacionRecepcion.setCodigoRecepcion((String) data.get("codigoRecepcion"));
        Client client = ClientProxy.getClient(validacionRecepcionPaqueteFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=validacionRecepcionPaqueteFactura.validacionRecepcionPaqueteFactura(solicitudValidacionRecepcion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaRecepcion.getCodigoRecepcion();
    }
    @RequestMapping(value="/verificacionEstadoFactura", method=RequestMethod.GET)
    public String verificacionEstadoFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacion verificacionEstadoFactura = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudVerificacionEstado solicitudVerificacionEstado=new SolicitudVerificacionEstado();
        solicitudVerificacionEstado.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudVerificacionEstado.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudVerificacionEstado.setNit(((Number) data.get("nit")).longValue());
        solicitudVerificacionEstado.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudVerificacionEstado.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudVerificacionEstado.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudVerificacionEstado.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudVerificacionEstado.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudVerificacionEstado.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudVerificacionEstado.setCuis(cuisService.findCuis().get(0).getCuis());
        String nit=(String) data.get("nit");
        String fechaHora=(String) data.get("fechaHora");
        String sucursal=(String) data.get("codigoSucursal");
        String modalidad=(String) data.get("codigoModalidad");
        String tipoEmision=(String) data.get("tipoEmision");
        String tipoFactura=(String) data.get("tipoFactura");
        String tipoDocSector=(String) data.get("tipoDocSector");
        String numeroFactura=(String) data.get("numeroFactura");
        String puntoVenta=(String) data.get("codigoPuntoVenta");
        solicitudVerificacionEstado.setCuf(facturacionOperacionesController.getCuf(nit,fechaHora,sucursal,modalidad,tipoEmision,tipoFactura,tipoDocSector,numeroFactura,puntoVenta));
        Client client = ClientProxy.getClient(verificacionEstadoFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=verificacionEstadoFactura.verificacionEstadoFactura(solicitudVerificacionEstado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return respuestaRecepcion.getCodigoRecepcion();
    }
    @RequestMapping(value="/verificarComunicacion", method=RequestMethod.GET)
    public int verificarComunicacion() throws IOException, JSONException {
        int valor=0;
        ServicioFacturacion verificarComunicacion = getServicioFacturacion();
        Client client = ClientProxy.getClient(verificarComunicacion);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            valor= verificarComunicacion.verificarComunicacion();
            System.out.println("verificacion"+valor);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return valor;
    }
}
