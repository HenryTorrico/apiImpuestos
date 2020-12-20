package com.example.demo.controller.facturacionNotaCreditoDebito;


import com.example.demo.controller.facturacionOperaciones.FacturacionOperacionesController;
import com.example.demo.controller.operaciones.ServicioOperacionesController;
import com.example.demo.facturacionNotaCreditoDebito.*;
import com.example.demo.operaciones.RespuestaCuis;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/facNotaCreditoDebito", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class FacturacionNotaCreditoDebitoController {

    @Autowired
    TokenService tokenService;

    @Autowired
    CuisService cuisService;

    @Autowired
    CufdService cufdService;

    @Autowired
    FacturacionOperacionesController facturacionOperacionesController;

    @RequestMapping(value="/anulacionNotaFiscal", method= RequestMethod.GET)
    public String anulacionNotaFiscal(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacion anulacionNotaFiscal = getServicio();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudAnulacion solicitudAnulacion=new SolicitudAnulacion();
        solicitudAnulacion.setCodigoMotivo((Integer) data.get("codigoMotivo"));
        solicitudAnulacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudAnulacion.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudAnulacion.setNit(((Number) data.get("nit")).longValue());
        solicitudAnulacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudAnulacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudAnulacion.setCodigoPuntoVenta((Integer)data.get("codigoPuntoVenta"));
        solicitudAnulacion.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudAnulacion.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudAnulacion.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
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
        Client client = ClientProxy.getClient(anulacionNotaFiscal);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=anulacionNotaFiscal.anulacionNotaFiscalCreditoDebito(solicitudAnulacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return respuestaRecepcion.getCodigoRecepcion();
    }
    @RequestMapping(value="/recepcionNotaFiscal", method= RequestMethod.GET)
    public String recepcionNotaFiscal(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        ServicioFacturacion recepcionNotaFiscal = getServicio();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudRecepcionFactura solicitudRecepcionFactura=new SolicitudRecepcionFactura();
        solicitudRecepcionFactura.setHashArchivo((String) data.get("hashArchivo"));
        solicitudRecepcionFactura.setFechaEnvio((XMLGregorianCalendar) data.get("fechaEnvio"));
        solicitudRecepcionFactura.setArchivo((byte[]) data.get("archivo"));
        solicitudRecepcionFactura.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudRecepcionFactura.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudRecepcionFactura.setNit(((Number) data.get("nit")).longValue());
        solicitudRecepcionFactura.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudRecepcionFactura.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudRecepcionFactura.setCodigoPuntoVenta((Integer)data.get("codigoPuntoVenta"));
        solicitudRecepcionFactura.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudRecepcionFactura.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudRecepcionFactura.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudRecepcionFactura.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudRecepcionFactura.setCuis(cuisService.findCuis().get(0).getCuis());
        Client client = ClientProxy.getClient(recepcionNotaFiscal);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=recepcionNotaFiscal.recepcionNotaFiscalCreditoDebito(solicitudRecepcionFactura);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return respuestaRecepcion.getCodigoRecepcion();
    }

    @RequestMapping(value="/reversionAnulacionNotaFiscal", method= RequestMethod.GET)
    public String reversionAnulacionNotaFiscal(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacion reversionAnulacionNotaFiscal = getServicio();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudReversionAnulacion solicitudReversionAnulacion=new SolicitudReversionAnulacion();

        solicitudReversionAnulacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudReversionAnulacion.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudReversionAnulacion.setNit(((Number) data.get("nit")).longValue());
        solicitudReversionAnulacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudReversionAnulacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudReversionAnulacion.setCodigoPuntoVenta((Integer)data.get("codigoPuntoVenta"));
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
        Client client = ClientProxy.getClient(reversionAnulacionNotaFiscal);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=reversionAnulacionNotaFiscal.reversionAnulacionNotaFiscalCreditoDebito(solicitudReversionAnulacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return respuestaRecepcion.getCodigoRecepcion();
    }

    private ServicioFacturacion getServicio() {
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacion.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioFacturacionNotaCreditoDebito?wsdl");
        return (ServicioFacturacion) factory.create();
    }

    @RequestMapping(value="/verificacionEstadoNotaFiscal", method= RequestMethod.GET)
    public String verificacionEstadoNotaFiscal(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacion verificacionEstadoNotaFiscal = getServicio();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudVerificacionEstado solicitudVerificacionEstado=new SolicitudVerificacionEstado();
        solicitudVerificacionEstado.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudVerificacionEstado.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudVerificacionEstado.setNit(((Number) data.get("nit")).longValue());
        solicitudVerificacionEstado.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudVerificacionEstado.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudVerificacionEstado.setCodigoPuntoVenta((Integer)data.get("codigoPuntoVenta"));
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
        Client client = ClientProxy.getClient(verificacionEstadoNotaFiscal);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=verificacionEstadoNotaFiscal.verificacionEstadoNotaFiscalCreditoDebito(solicitudVerificacionEstado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaRecepcion.getCodigoRecepcion();
    }

    @RequestMapping(value="/verificarComunicacion", method= RequestMethod.GET)
    public int verificarComunicacion() throws IOException, JSONException {
        int respuestaRecepcion=0;
        ServicioFacturacion verificarComunicacion = getServicio();
        Client client = ClientProxy.getClient(verificarComunicacion);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=verificarComunicacion.verificarComunicacion();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaRecepcion;
    }

}
