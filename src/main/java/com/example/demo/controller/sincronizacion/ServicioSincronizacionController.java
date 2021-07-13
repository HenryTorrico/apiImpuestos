package com.example.demo.controller.sincronizacion;



import com.example.demo.service.CufdService;
import com.example.demo.service.CuisService;
import com.example.demo.service.TokenService;
import com.example.demo.sincronizacion.*;
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

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/sincronizacion", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ServicioSincronizacionController {
    @Autowired
    TokenService tokenService;

    @Autowired
    CuisService cuisService;

    @Autowired
    CufdService cufdService;
    @RequestMapping(value="/actividades", method= RequestMethod.POST)
    public List<ActividadesDto> sincronizarActividades(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        ServicioFacturacionSincronizacion sincronizarActividades = getServicioFacturacionSincronizacion();
        RespuestaListaActividades respuestaListaActividades=new RespuestaListaActividades();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(sincronizarActividades);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaActividades=sincronizarActividades.sincronizarActividades(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaActividades.getListaActividades();
    }
    @RequestMapping(value="/fechaHora", method= RequestMethod.POST)
    public String sincronizarFechaHora(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        ServicioFacturacionSincronizacion sincronizarFechaHora = getServicioFacturacionSincronizacion();
        RespuestaFechaHora respuestaFechaHora=new RespuestaFechaHora();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(sincronizarFechaHora);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaFechaHora=sincronizarFechaHora.sincronizarFechaHora(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaFechaHora.getFechaHora();
    }
    @RequestMapping(value="/listaActividadesDocumentos", method= RequestMethod.POST)
    public List<ActividadesDocumentoSectorDto> listaActividadesDocumentos(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionSincronizacion listaActividadesDocumentos = getServicioFacturacionSincronizacion();
        RespuestaListaActividadesDocumentoSector respuestaListaActividadesDocumentoSector=new RespuestaListaActividadesDocumentoSector();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(listaActividadesDocumentos);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaActividadesDocumentoSector=listaActividadesDocumentos.sincronizarListaActividadesDocumentoSector(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaActividadesDocumentoSector.getListaActividadesDocumentoSector();
    }
    @RequestMapping(value="/listaLeyendasFactura", method= RequestMethod.POST)
    public List<ParametricaLeyendasDto> listaLeyendasFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionSincronizacion listaLeyendasFactura = getServicioFacturacionSincronizacion();
        RespuestaListaParametricasLeyendas respuestaListaParametricasLeyendas=new RespuestaListaParametricasLeyendas();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(listaLeyendasFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaParametricasLeyendas=listaLeyendasFactura.sincronizarListaLeyendasFactura(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaParametricasLeyendas.getListaLeyendas();
    }
    @RequestMapping(value="/listaMensajesServicios", method= RequestMethod.POST)
    public List<ParametricasDto> listaMensajesServicios(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionSincronizacion listaMensajesServicios = getServicioFacturacionSincronizacion();
        RespuestaListaParametricas respuestaListaParametricas=new RespuestaListaParametricas();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(listaMensajesServicios);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaParametricas=listaMensajesServicios.sincronizarListaMensajesServicios(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaParametricas.getListaCodigos();
    }
    @RequestMapping(value="/listaProductosServicios", method= RequestMethod.POST)
    public List<ProductosDto> listaProductosServicios(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionSincronizacion listaProductosServicios = getServicioFacturacionSincronizacion();
        RespuestaListaProductos respuestaListaProductos=new RespuestaListaProductos();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(listaProductosServicios);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaProductos=listaProductosServicios.sincronizarListaProductosServicios(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaProductos.getListaCodigos();
    }
    @RequestMapping(value="/arametricaEventos", method= RequestMethod.POST)
    public List<ParametricasDto> arametricaEventos(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionSincronizacion parametricaEventos = getServicioFacturacionSincronizacion();
        RespuestaListaParametricas respuestaListaParametricas=new RespuestaListaParametricas();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(parametricaEventos);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaParametricas=parametricaEventos.sincronizarParametricaEventosSignificativos(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaParametricas.getListaCodigos();
    }
        @RequestMapping(value="/parametricaMotivoAnulacion", method= RequestMethod.POST)
    public List<ParametricasDto> parametricaMotivoAnulacion(@RequestBody Map<String, Object> data) throws IOException, JSONException {

            ServicioFacturacionSincronizacion parametricaMotivoAnulacion = getServicioFacturacionSincronizacion();
            RespuestaListaParametricas respuestaListaParametricas=new RespuestaListaParametricas();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(parametricaMotivoAnulacion);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaParametricas=parametricaMotivoAnulacion.sincronizarParametricaMotivoAnulacion(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaParametricas.getListaCodigos();
    }
    @RequestMapping(value="/parametricaPaisOrigen", method= RequestMethod.POST)
    public List<ParametricasDto> parametricaPaisOrigen(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionSincronizacion parametricaPaisOrigen = getServicioFacturacionSincronizacion();
        RespuestaListaParametricas respuestaListaParametricas=new RespuestaListaParametricas();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(parametricaPaisOrigen);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaParametricas=parametricaPaisOrigen.sincronizarParametricaPaisOrigen(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaParametricas.getListaCodigos();
    }
    @RequestMapping(value="/parametricaTipoDocIdentidad", method= RequestMethod.POST)
    public List<ParametricasDto> parametricaTipoDocIdentidad(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionSincronizacion parametricaTipoDocIdentidad = getServicioFacturacionSincronizacion();
        RespuestaListaParametricas respuestaListaParametricas=new RespuestaListaParametricas();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(parametricaTipoDocIdentidad);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaParametricas=parametricaTipoDocIdentidad.sincronizarParametricaTipoDocumentoIdentidad(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaParametricas.getListaCodigos();
    }
    @RequestMapping(value="/parametricaTipoDocSector", method= RequestMethod.POST)
    public List<ParametricasDto> parametricaTipoDocSector(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionSincronizacion parametricaTipoDocSector = getServicioFacturacionSincronizacion();
        RespuestaListaParametricas respuestaListaParametricas=new RespuestaListaParametricas();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(parametricaTipoDocSector);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaParametricas=parametricaTipoDocSector.sincronizarParametricaTipoDocumentoSector(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaParametricas.getListaCodigos();
    }
    @RequestMapping(value="/parametricaTipoEmision", method= RequestMethod.POST)
    public List<ParametricasDto> parametricaTipoEmision(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionSincronizacion parametricaTipoEmision = getServicioFacturacionSincronizacion();
        RespuestaListaParametricas respuestaListaParametricas=new RespuestaListaParametricas();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(parametricaTipoEmision);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaParametricas=parametricaTipoEmision.sincronizarParametricaTipoEmision(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaParametricas.getListaCodigos();
    }
    @RequestMapping(value="/parametricaTipoHabitacion", method= RequestMethod.POST)
    public List<ParametricasDto> parametricaTipoHabitacion(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionSincronizacion parametricaTipoHabitacion = getServicioFacturacionSincronizacion();
        RespuestaListaParametricas respuestaListaParametricas=new RespuestaListaParametricas();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(parametricaTipoHabitacion);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaParametricas=parametricaTipoHabitacion.sincronizarParametricaTipoHabitacion(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaParametricas.getListaCodigos();
    }
    @RequestMapping(value="/parametricaTipoMetodoPago", method= RequestMethod.POST)
    public List<ParametricasDto> parametricaTipoMetodoPago(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionSincronizacion parametricaTipoMetodoPago = getServicioFacturacionSincronizacion();
        RespuestaListaParametricas respuestaListaParametricas=new RespuestaListaParametricas();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(parametricaTipoMetodoPago);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaParametricas=parametricaTipoMetodoPago.sincronizarParametricaTipoMetodoPago(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaParametricas.getListaCodigos();
    }
    @RequestMapping(value="/parametricaTipoMoneda", method= RequestMethod.POST)
    public List<ParametricasDto> parametricaTipoMoneda(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionSincronizacion parametricaTipoMoneda = getServicioFacturacionSincronizacion();
        RespuestaListaParametricas respuestaListaParametricas=new RespuestaListaParametricas();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(parametricaTipoMoneda);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaParametricas=parametricaTipoMoneda.sincronizarParametricaTipoMoneda(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaParametricas.getListaCodigos();
    }
    @RequestMapping(value="/parametricaTipoPuntoVenta", method= RequestMethod.POST)
    public List<ParametricasDto> parametricaTipoPuntoVenta(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionSincronizacion parametricaTipoPuntoVenta = getServicioFacturacionSincronizacion();
        RespuestaListaParametricas respuestaListaParametricas=new RespuestaListaParametricas();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(parametricaTipoPuntoVenta);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaParametricas=parametricaTipoPuntoVenta.sincronizarParametricaTipoPuntoVenta(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaParametricas.getListaCodigos();
    }
    @RequestMapping(value="/parametricaTiposFactura", method= RequestMethod.POST)
    public List<ParametricasDto> parametricaTiposFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionSincronizacion parametricaTiposFactura = getServicioFacturacionSincronizacion();
        RespuestaListaParametricas respuestaListaParametricas=new RespuestaListaParametricas();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(parametricaTiposFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaParametricas=parametricaTiposFactura.sincronizarParametricaTiposFactura(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaParametricas.getListaCodigos();
    }
    @RequestMapping(value="/parametricaUnidadMedida", method= RequestMethod.POST)
    public List<ParametricasDto> parametricaUnidadMedida(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionSincronizacion parametricaUnidadMedida = getServicioFacturacionSincronizacion();
        RespuestaListaParametricas respuestaListaParametricas=new RespuestaListaParametricas();
        SolicitudSincronizacion solicitudSincronizacion=new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudSincronizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudSincronizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudSincronizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(parametricaUnidadMedida);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaParametricas=parametricaUnidadMedida.sincronizarParametricaUnidadMedida(solicitudSincronizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaListaParametricas.getListaCodigos();
    }

    private ServicioFacturacionSincronizacion getServicioFacturacionSincronizacion() {
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacionSincronizacion.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionSincronizacion?wsdl");
        return (ServicioFacturacionSincronizacion) factory.create();
    }

    @RequestMapping(value="/verificarComunicacion", method= RequestMethod.POST)
    public int verificarComunicacion() throws IOException, JSONException {
        int comunicacion=0;
        ServicioFacturacionSincronizacion verificarComunicacion = getServicioFacturacionSincronizacion();
        Client client = ClientProxy.getClient(verificarComunicacion);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            comunicacion=verificarComunicacion.verificarComunicacion();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return comunicacion;
    }
}
