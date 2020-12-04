package com.example.demo.controller.facturacion;


import com.example.demo.facturacion.*;
import com.example.demo.operaciones.RespuestaCuis;
import com.example.demo.operaciones.ServicioFacturacionCodigos;
import com.example.demo.operaciones.SolicitudOperacionesCuis;
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

    private String _TOKENFORFACTURACION = null;

    private RespuestaCuis respuestaCuis=new RespuestaCuis();

    @RequestMapping(value="/recepcionFactura", method=RequestMethod.GET)
    public ModelAndView recepcionFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        System.out.println(data.get("codigoAmbiente"));
        System.out.println(data.get("codigoSistema"));
        System.out.println(data.get("nit"));
        System.out.println(data.get("codigoModalidad"));
        System.out.println(data.get("codigoSucursal"));
        ClientProxyFactoryBean factoryToken = new JaxWsProxyFactoryBean();
        factoryToken.setServiceClass(ServicioAutenticacionSoap.class);
        factoryToken.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioAutenticacionSoap?wsdl");
        ServicioAutenticacionSoap service = (ServicioAutenticacionSoap) factoryToken.create();
        DatosUsuarioRequest datosUsuarioRequest = new DatosUsuarioRequest();
        datosUsuarioRequest.setNit((long) 1002329022);
        datosUsuarioRequest.setLogin("bap2394882");
        datosUsuarioRequest.setPassword("Jmolina1");
        if (service.token(datosUsuarioRequest).isOk()) {
            String token = service.token(datosUsuarioRequest).getToken();
            _TOKENFORFACTURACION=token;
            System.out.println("token es: " + _TOKENFORFACTURACION);
        } else {
            List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
            for (StrMensajeAplicacionDto m : lista) {
                System.out.println("Error " + m.getDescripcion());
            }
        }
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacion.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        ServicioFacturacion recepcionFactura = (ServicioFacturacion) factory.create();

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
        solicitudRecepcionFactura.setCufd((String) data.get("cufd"));
        solicitudRecepcionFactura.setCuis((String) data.get("cuis"));

        Client client = ClientProxy.getClient(recepcionFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList(_TOKENFORFACTURACION));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
            respuestaRecepcion=recepcionFactura.recepcionFactura(solicitudRecepcionFactura);
            System.out.println(respuestaCuis.getCodigo());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }


    @RequestMapping(value="/anulacionFactura", method=RequestMethod.GET)
    public ModelAndView anulacionFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        System.out.println(data.get("codigoAmbiente"));
        System.out.println(data.get("codigoSistema"));
        System.out.println(data.get("nit"));
        System.out.println(data.get("codigoModalidad"));
        System.out.println(data.get("codigoSucursal"));
        ClientProxyFactoryBean factoryToken = new JaxWsProxyFactoryBean();
        factoryToken.setServiceClass(ServicioAutenticacionSoap.class);
        factoryToken.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioAutenticacionSoap?wsdl");
        ServicioAutenticacionSoap service = (ServicioAutenticacionSoap) factoryToken.create();
        DatosUsuarioRequest datosUsuarioRequest = new DatosUsuarioRequest();
        datosUsuarioRequest.setNit((long) 1002329022);
        datosUsuarioRequest.setLogin("bap2394882");
        datosUsuarioRequest.setPassword("Jmolina1");
        if (service.token(datosUsuarioRequest).isOk()) {
            String token = service.token(datosUsuarioRequest).getToken();
            _TOKENFORFACTURACION=token;
            System.out.println("token es: " + _TOKENFORFACTURACION);
        } else {
            List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
            for (StrMensajeAplicacionDto m : lista) {
                System.out.println("Error " + m.getDescripcion());
            }
        }
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacion.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        ServicioFacturacion anulacionFactura = (ServicioFacturacion) factory.create();

        SolicitudAnulacion solicitudAnulacion=new SolicitudAnulacion();
        solicitudAnulacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudAnulacion.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudAnulacion.setNit(((Number) data.get("nit")).longValue());
        solicitudAnulacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudAnulacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudAnulacion.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudAnulacion.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudAnulacion.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudAnulacion.setCuf((String) data.get("cuf"));
        solicitudAnulacion.setCodigoMotivo((Integer) data.get("codigoMotivo"));
        solicitudAnulacion.setCufd((String) data.get("cufd"));
        solicitudAnulacion.setCuis((String) data.get("cuis"));

        Client client = ClientProxy.getClient(anulacionFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList(_TOKENFORFACTURACION));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
            respuestaRecepcion=anulacionFactura.anulacionFactura(solicitudAnulacion);
            System.out.println(respuestaCuis.getCodigo());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }
    @RequestMapping(value="/recepcionMasivaFacturas", method=RequestMethod.GET)
    public ModelAndView recepcionMasivaFacturas(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        System.out.println(data.get("codigoAmbiente"));
        System.out.println(data.get("codigoSistema"));
        System.out.println(data.get("nit"));
        System.out.println(data.get("codigoModalidad"));
        System.out.println(data.get("codigoSucursal"));
        ClientProxyFactoryBean factoryToken = new JaxWsProxyFactoryBean();
        factoryToken.setServiceClass(ServicioAutenticacionSoap.class);
        factoryToken.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioAutenticacionSoap?wsdl");
        ServicioAutenticacionSoap service = (ServicioAutenticacionSoap) factoryToken.create();
        DatosUsuarioRequest datosUsuarioRequest = new DatosUsuarioRequest();
        datosUsuarioRequest.setNit((long) 1002329022);
        datosUsuarioRequest.setLogin("bap2394882");
        datosUsuarioRequest.setPassword("Jmolina1");
        if (service.token(datosUsuarioRequest).isOk()) {
            String token = service.token(datosUsuarioRequest).getToken();
            _TOKENFORFACTURACION=token;
            System.out.println("token es: " + _TOKENFORFACTURACION);
        } else {
            List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
            for (StrMensajeAplicacionDto m : lista) {
                System.out.println("Error " + m.getDescripcion());
            }
        }
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacion.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        ServicioFacturacion recepcionMasivaFactura = (ServicioFacturacion) factory.create();

        SolicitudRecepcionMasiva solicitudRecepcionMasiva=new SolicitudRecepcionMasiva();
        solicitudRecepcionMasiva.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudRecepcionMasiva.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudRecepcionMasiva.setNit(((Number) data.get("nit")).longValue());
        solicitudRecepcionMasiva.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudRecepcionMasiva.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudRecepcionMasiva.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudRecepcionMasiva.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudRecepcionMasiva.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudRecepcionMasiva.setCufd((String) data.get("cufd"));
        solicitudRecepcionMasiva.setCuis((String) data.get("cuis"));
        solicitudRecepcionMasiva.setCantidadFacturas((Integer) data.get("cantidadFacturas"));
        solicitudRecepcionMasiva.setArchivo((byte[]) data.get("cuis"));
        solicitudRecepcionMasiva.setHashArchivo((String) data.get("cuis"));
        solicitudRecepcionMasiva.setFechaEnvio((XMLGregorianCalendar) data.get("cuis"));

        Client client = ClientProxy.getClient(recepcionMasivaFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList(_TOKENFORFACTURACION));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
            respuestaRecepcion=recepcionMasivaFactura.recepcionMasivaFactura(solicitudRecepcionMasiva);
            System.out.println(respuestaCuis.getCodigo());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }
    @RequestMapping(value="/recepcionPaqueteFacturas", method=RequestMethod.GET)
    public ModelAndView recepcionPaqueteFacturas(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        System.out.println(data.get("codigoAmbiente"));
        System.out.println(data.get("codigoSistema"));
        System.out.println(data.get("nit"));
        System.out.println(data.get("codigoModalidad"));
        System.out.println(data.get("codigoSucursal"));
        ClientProxyFactoryBean factoryToken = new JaxWsProxyFactoryBean();
        factoryToken.setServiceClass(ServicioAutenticacionSoap.class);
        factoryToken.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioAutenticacionSoap?wsdl");
        ServicioAutenticacionSoap service = (ServicioAutenticacionSoap) factoryToken.create();
        DatosUsuarioRequest datosUsuarioRequest = new DatosUsuarioRequest();
        datosUsuarioRequest.setNit((long) 1002329022);
        datosUsuarioRequest.setLogin("bap2394882");
        datosUsuarioRequest.setPassword("Jmolina1");
        if (service.token(datosUsuarioRequest).isOk()) {
            String token = service.token(datosUsuarioRequest).getToken();
            _TOKENFORFACTURACION=token;
            System.out.println("token es: " + _TOKENFORFACTURACION);
        } else {
            List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
            for (StrMensajeAplicacionDto m : lista) {
                System.out.println("Error " + m.getDescripcion());
            }
        }
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacion.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        ServicioFacturacion recepcionPaqueteFacturas = (ServicioFacturacion) factory.create();

        SolicitudRecepcionPaquete solicitudRecepcionPaquete=new SolicitudRecepcionPaquete();
        solicitudRecepcionPaquete.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudRecepcionPaquete.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudRecepcionPaquete.setNit(((Number) data.get("nit")).longValue());
        solicitudRecepcionPaquete.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudRecepcionPaquete.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudRecepcionPaquete.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudRecepcionPaquete.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudRecepcionPaquete.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudRecepcionPaquete.setCufd((String) data.get("cufd"));
        solicitudRecepcionPaquete.setCuis((String) data.get("cuis"));
        solicitudRecepcionPaquete.setCantidadFacturas((Integer) data.get("cantidadFacturas"));
        solicitudRecepcionPaquete.setArchivo((byte[]) data.get("cuis"));
        solicitudRecepcionPaquete.setHashArchivo((String) data.get("cuis"));
        solicitudRecepcionPaquete.setFechaEnvio((XMLGregorianCalendar) data.get("cuis"));
        solicitudRecepcionPaquete.setCodigoEvento((long) data.get("codigoEvento"));
        Client client = ClientProxy.getClient(recepcionPaqueteFacturas);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList(_TOKENFORFACTURACION));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
            respuestaRecepcion=recepcionPaqueteFacturas.recepcionPaqueteFactura(solicitudRecepcionPaquete);
            System.out.println(respuestaCuis.getCodigo());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }
    @RequestMapping(value="/reversionAnulacionFactura", method=RequestMethod.GET)
    public ModelAndView reversionAnulacionFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        System.out.println(data.get("codigoAmbiente"));
        System.out.println(data.get("codigoSistema"));
        System.out.println(data.get("nit"));
        System.out.println(data.get("codigoModalidad"));
        System.out.println(data.get("codigoSucursal"));
        ClientProxyFactoryBean factoryToken = new JaxWsProxyFactoryBean();
        factoryToken.setServiceClass(ServicioAutenticacionSoap.class);
        factoryToken.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioAutenticacionSoap?wsdl");
        ServicioAutenticacionSoap service = (ServicioAutenticacionSoap) factoryToken.create();
        DatosUsuarioRequest datosUsuarioRequest = new DatosUsuarioRequest();
        datosUsuarioRequest.setNit((long) 1002329022);
        datosUsuarioRequest.setLogin("bap2394882");
        datosUsuarioRequest.setPassword("Jmolina1");
        if (service.token(datosUsuarioRequest).isOk()) {
            String token = service.token(datosUsuarioRequest).getToken();
            _TOKENFORFACTURACION=token;
            System.out.println("token es: " + _TOKENFORFACTURACION);
        } else {
            List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
            for (StrMensajeAplicacionDto m : lista) {
                System.out.println("Error " + m.getDescripcion());
            }
        }
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacion.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        ServicioFacturacion reversionAnulacionFactura = (ServicioFacturacion) factory.create();

        SolicitudReversionAnulacion solicitudReversionAnulacion=new SolicitudReversionAnulacion();
        solicitudReversionAnulacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudReversionAnulacion.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudReversionAnulacion.setNit(((Number) data.get("nit")).longValue());
        solicitudReversionAnulacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudReversionAnulacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudReversionAnulacion.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudReversionAnulacion.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudReversionAnulacion.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudReversionAnulacion.setCufd((String) data.get("cufd"));
        solicitudReversionAnulacion.setCuis((String) data.get("cuis"));
        solicitudReversionAnulacion.setCuf((String) data.get("cuf"));
        Client client = ClientProxy.getClient(reversionAnulacionFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList(_TOKENFORFACTURACION));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
            respuestaRecepcion=reversionAnulacionFactura.reversionAnulacionFactura(solicitudReversionAnulacion);
            System.out.println(respuestaCuis.getCodigo());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }
    @RequestMapping(value="/validacionRecepcionMasivaFactura", method=RequestMethod.GET)
    public ModelAndView validacionRecepcionMasivaFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        System.out.println(data.get("codigoAmbiente"));
        System.out.println(data.get("codigoSistema"));
        System.out.println(data.get("nit"));
        System.out.println(data.get("codigoModalidad"));
        System.out.println(data.get("codigoSucursal"));
        ClientProxyFactoryBean factoryToken = new JaxWsProxyFactoryBean();
        factoryToken.setServiceClass(ServicioAutenticacionSoap.class);
        factoryToken.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioAutenticacionSoap?wsdl");
        ServicioAutenticacionSoap service = (ServicioAutenticacionSoap) factoryToken.create();
        DatosUsuarioRequest datosUsuarioRequest = new DatosUsuarioRequest();
        datosUsuarioRequest.setNit((long) 1002329022);
        datosUsuarioRequest.setLogin("bap2394882");
        datosUsuarioRequest.setPassword("Jmolina1");
        if (service.token(datosUsuarioRequest).isOk()) {
            String token = service.token(datosUsuarioRequest).getToken();
            _TOKENFORFACTURACION=token;
            System.out.println("token es: " + _TOKENFORFACTURACION);
        } else {
            List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
            for (StrMensajeAplicacionDto m : lista) {
                System.out.println("Error " + m.getDescripcion());
            }
        }
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacion.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        ServicioFacturacion validacionRecepcionMasivaFactura = (ServicioFacturacion) factory.create();

        SolicitudValidacionRecepcion solicitudValidacionRecepcion=new SolicitudValidacionRecepcion();
        solicitudValidacionRecepcion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudValidacionRecepcion.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudValidacionRecepcion.setNit(((Number) data.get("nit")).longValue());
        solicitudValidacionRecepcion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudValidacionRecepcion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudValidacionRecepcion.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudValidacionRecepcion.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudValidacionRecepcion.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudValidacionRecepcion.setCufd((String) data.get("cufd"));
        solicitudValidacionRecepcion.setCuis((String) data.get("cuis"));
        solicitudValidacionRecepcion.setCodigoRecepcion((String) data.get("codigoRecepcion"));
        Client client = ClientProxy.getClient(validacionRecepcionMasivaFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList(_TOKENFORFACTURACION));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
            respuestaRecepcion=validacionRecepcionMasivaFactura.validacionRecepcionMasivaFactura(solicitudValidacionRecepcion);
            System.out.println(respuestaCuis.getCodigo());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }
    @RequestMapping(value="/validacionRecepcionPaqueteFactura", method=RequestMethod.GET)
    public ModelAndView validacionRecepcionPaqueteFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        System.out.println(data.get("codigoAmbiente"));
        System.out.println(data.get("codigoSistema"));
        System.out.println(data.get("nit"));
        System.out.println(data.get("codigoModalidad"));
        System.out.println(data.get("codigoSucursal"));
        ClientProxyFactoryBean factoryToken = new JaxWsProxyFactoryBean();
        factoryToken.setServiceClass(ServicioAutenticacionSoap.class);
        factoryToken.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioAutenticacionSoap?wsdl");
        ServicioAutenticacionSoap service = (ServicioAutenticacionSoap) factoryToken.create();
        DatosUsuarioRequest datosUsuarioRequest = new DatosUsuarioRequest();
        datosUsuarioRequest.setNit((long) 1002329022);
        datosUsuarioRequest.setLogin("bap2394882");
        datosUsuarioRequest.setPassword("Jmolina1");
        if (service.token(datosUsuarioRequest).isOk()) {
            String token = service.token(datosUsuarioRequest).getToken();
            _TOKENFORFACTURACION=token;
            System.out.println("token es: " + _TOKENFORFACTURACION);
        } else {
            List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
            for (StrMensajeAplicacionDto m : lista) {
                System.out.println("Error " + m.getDescripcion());
            }
        }
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacion.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        ServicioFacturacion validacionRecepcionPaqueteFactura = (ServicioFacturacion) factory.create();

        SolicitudValidacionRecepcion solicitudValidacionRecepcion=new SolicitudValidacionRecepcion();
        solicitudValidacionRecepcion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudValidacionRecepcion.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudValidacionRecepcion.setNit(((Number) data.get("nit")).longValue());
        solicitudValidacionRecepcion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudValidacionRecepcion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudValidacionRecepcion.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudValidacionRecepcion.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudValidacionRecepcion.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudValidacionRecepcion.setCufd((String) data.get("cufd"));
        solicitudValidacionRecepcion.setCuis((String) data.get("cuis"));
        solicitudValidacionRecepcion.setCodigoRecepcion((String) data.get("codigoRecepcion"));
        Client client = ClientProxy.getClient(validacionRecepcionPaqueteFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList(_TOKENFORFACTURACION));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
            respuestaRecepcion=validacionRecepcionPaqueteFactura.validacionRecepcionPaqueteFactura(solicitudValidacionRecepcion);
            System.out.println(respuestaCuis.getCodigo());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }
    @RequestMapping(value="/verificacionEstadoFactura", method=RequestMethod.GET)
    public ModelAndView verificacionEstadoFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        System.out.println(data.get("codigoAmbiente"));
        System.out.println(data.get("codigoSistema"));
        System.out.println(data.get("nit"));
        System.out.println(data.get("codigoModalidad"));
        System.out.println(data.get("codigoSucursal"));
        ClientProxyFactoryBean factoryToken = new JaxWsProxyFactoryBean();
        factoryToken.setServiceClass(ServicioAutenticacionSoap.class);
        factoryToken.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioAutenticacionSoap?wsdl");
        ServicioAutenticacionSoap service = (ServicioAutenticacionSoap) factoryToken.create();
        DatosUsuarioRequest datosUsuarioRequest = new DatosUsuarioRequest();
        datosUsuarioRequest.setNit((long) 1002329022);
        datosUsuarioRequest.setLogin("bap2394882");
        datosUsuarioRequest.setPassword("Jmolina1");
        if (service.token(datosUsuarioRequest).isOk()) {
            String token = service.token(datosUsuarioRequest).getToken();
            _TOKENFORFACTURACION=token;
            System.out.println("token es: " + _TOKENFORFACTURACION);
        } else {
            List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
            for (StrMensajeAplicacionDto m : lista) {
                System.out.println("Error " + m.getDescripcion());
            }
        }
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacion.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        ServicioFacturacion verificacionEstadoFactura = (ServicioFacturacion) factory.create();

        SolicitudVerificacionEstado solicitudVerificacionEstado=new SolicitudVerificacionEstado();
        solicitudVerificacionEstado.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudVerificacionEstado.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudVerificacionEstado.setNit(((Number) data.get("nit")).longValue());
        solicitudVerificacionEstado.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudVerificacionEstado.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudVerificacionEstado.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudVerificacionEstado.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudVerificacionEstado.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudVerificacionEstado.setCufd((String) data.get("cufd"));
        solicitudVerificacionEstado.setCuis((String) data.get("cuis"));
        solicitudVerificacionEstado.setCuf((String) data.get("cuf"));
        Client client = ClientProxy.getClient(verificacionEstadoFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList(_TOKENFORFACTURACION));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
            respuestaRecepcion=verificacionEstadoFactura.verificacionEstadoFactura(solicitudVerificacionEstado);
            System.out.println(respuestaCuis.getCodigo());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }
    @RequestMapping(value="/verificarComunicacion", method=RequestMethod.GET)
    public ModelAndView verificarComunicacion(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        System.out.println(data.get("codigoAmbiente"));
        System.out.println(data.get("codigoSistema"));
        System.out.println(data.get("nit"));
        System.out.println(data.get("codigoModalidad"));
        System.out.println(data.get("codigoSucursal"));
        ClientProxyFactoryBean factoryToken = new JaxWsProxyFactoryBean();
        factoryToken.setServiceClass(ServicioAutenticacionSoap.class);
        factoryToken.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioAutenticacionSoap?wsdl");
        ServicioAutenticacionSoap service = (ServicioAutenticacionSoap) factoryToken.create();
        DatosUsuarioRequest datosUsuarioRequest = new DatosUsuarioRequest();
        datosUsuarioRequest.setNit((long) 1002329022);
        datosUsuarioRequest.setLogin("bap2394882");
        datosUsuarioRequest.setPassword("Jmolina1");
        if (service.token(datosUsuarioRequest).isOk()) {
            String token = service.token(datosUsuarioRequest).getToken();
            _TOKENFORFACTURACION=token;
            System.out.println("token es: " + _TOKENFORFACTURACION);
        } else {
            List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
            for (StrMensajeAplicacionDto m : lista) {
                System.out.println("Error " + m.getDescripcion());
            }
        }
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacion.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        ServicioFacturacion verificarComunicacion = (ServicioFacturacion) factory.create();
        Client client = ClientProxy.getClient(verificarComunicacion);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList(_TOKENFORFACTURACION));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            int valor= verificarComunicacion.verificarComunicacion();
            System.out.println(valor);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }
}
