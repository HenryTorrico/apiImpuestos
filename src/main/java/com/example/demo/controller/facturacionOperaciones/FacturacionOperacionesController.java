package com.example.demo.controller.facturacionOperaciones;


import com.example.demo.facturacionOperaciones.*;
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

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/facOperaciones", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class FacturacionOperacionesController {

    @Autowired
    TokenService tokenService;

    @Autowired
    CufdService cufdService;

    @Autowired
    CuisService cuisService;

    @RequestMapping(value="/cierreOperacionesSistema", method= RequestMethod.GET)
    public String cierreOperacionesSistema(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        ServicioFacturacionOperaciones cierreOperacionesSistema = getServicioFacturacionOperaciones();
        RespuestaCierreSistemas respuestaCierreSistemas=new RespuestaCierreSistemas();
        SolicitudOperaciones solicitudOperaciones=new SolicitudOperaciones();
        solicitudOperaciones.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudOperaciones.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudOperaciones.setNit(((Number) data.get("nit")).longValue());
        solicitudOperaciones.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudOperaciones.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudOperaciones.setCodigoPuntoVenta((JAXBElement<Integer>) data.get("codigoPuntoVenta"));
        solicitudOperaciones.setCuis(cuisService.findCuis().get(0).getCuis());
        Client client = ClientProxy.getClient(cierreOperacionesSistema);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaCierreSistemas=cierreOperacionesSistema.cierreOperacionesSistema(solicitudOperaciones);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaCierreSistemas.getCodigoSistema();
    }
    @RequestMapping(value="/cierrePuntoVenta", method= RequestMethod.GET)
    public List<MensajeServicio> cierrePuntoVenta(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionOperaciones cierrePuntoVenta = getServicioFacturacionOperaciones();
        RespuestaCierrePuntoVenta respuestaCierrePuntoVenta=new RespuestaCierrePuntoVenta();
        SolicitudCierrePuntoVenta solicitudCierrePuntoVenta=new SolicitudCierrePuntoVenta();
        solicitudCierrePuntoVenta.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudCierrePuntoVenta.setNit(((Number) data.get("nit")).longValue());
        solicitudCierrePuntoVenta.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudCierrePuntoVenta.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudCierrePuntoVenta.setCodigoPuntoVenta((Integer) data.get("codigoPuntoVenta"));
        solicitudCierrePuntoVenta.setCuis(cuisService.findCuis().get(0).getCuis());
        Client client = ClientProxy.getClient(cierrePuntoVenta);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaCierrePuntoVenta=cierrePuntoVenta.cierrePuntoVenta(solicitudCierrePuntoVenta);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaCierrePuntoVenta.getMensajeServicioList();
    }
    @RequestMapping(value="/consultaEventoSignificativo", method= RequestMethod.GET)
    public long consultaEventoSignificativo(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionOperaciones consultaEventoSignificativo = getServicioFacturacionOperaciones();
        RespuestaListaEventos respuestaListaEventos=new RespuestaListaEventos();
        SolicitudConsultaEvento solicitudConsultaEvento=new SolicitudConsultaEvento();
        solicitudConsultaEvento.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudConsultaEvento.setNit(((Number) data.get("nit")).longValue());
        solicitudConsultaEvento.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudConsultaEvento.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudConsultaEvento.setCodigoPuntoVenta((JAXBElement<Integer>) data.get("codigoPuntoVenta"));
        solicitudConsultaEvento.setCuis(cuisService.findCuis().get(0).getCuis());
        solicitudConsultaEvento.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudConsultaEvento.setFechaEvento((XMLGregorianCalendar) data.get("fechaEvento"));

        Client client = ClientProxy.getClient(consultaEventoSignificativo);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaEventos=consultaEventoSignificativo.consultaEventoSignificativo(solicitudConsultaEvento);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return respuestaListaEventos.getCodigoRecepcionEventoSignificativo();
    }
    @RequestMapping(value="/consultaPuntoVenta", method= RequestMethod.GET)
    public List<PuntosVentasDto> consultaPuntoVenta(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacionOperaciones.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioFacturacionNotaCreditoDebito?wsdl");
        RespuestaConsultaPuntoVenta respuestaConsultaPuntoVenta=new RespuestaConsultaPuntoVenta();
        ServicioFacturacionOperaciones consultaPuntoVenta = (ServicioFacturacionOperaciones) factory.create();
        SolicitudConsultaPuntoVenta solicitudConsultaPuntoVenta=new SolicitudConsultaPuntoVenta();
        solicitudConsultaPuntoVenta.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudConsultaPuntoVenta.setNit(((Number) data.get("nit")).longValue());
        solicitudConsultaPuntoVenta.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudConsultaPuntoVenta.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudConsultaPuntoVenta.setCuis(cuisService.findCuis().get(0).getCuis());

        Client client = ClientProxy.getClient(consultaPuntoVenta);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaConsultaPuntoVenta=consultaPuntoVenta.consultaPuntoVenta(solicitudConsultaPuntoVenta);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return respuestaConsultaPuntoVenta.getListaPuntosVentas();
    }
    @RequestMapping(value="/registroEventoSignifcativo", method= RequestMethod.POST)
    public long registroEventoSignifcativo(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionOperaciones registroEventoSignifcativo = getServicioFacturacionOperaciones();
        RespuestaListaEventos respuestaListaEventos=new RespuestaListaEventos();
        SolicitudEventoSignificativo solicitudEventoSignificativo=new SolicitudEventoSignificativo();
        solicitudEventoSignificativo.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudEventoSignificativo.setNit(((Number) data.get("nit")).longValue());
        solicitudEventoSignificativo.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudEventoSignificativo.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudEventoSignificativo.setCuis(cuisService.findCuis().get(0).getCuis());
        solicitudEventoSignificativo.setFechaHoraFinEvento((XMLGregorianCalendar) data.get("fechaHoraFinEvento"));
        solicitudEventoSignificativo.setDescripcion((String) data.get("descripcion"));
        solicitudEventoSignificativo.setCufdEvento((String) data.get("cufdEvento"));
        solicitudEventoSignificativo.setCodigoMotivoEvento((Integer) data.get("codigoMotivoEvento"));
        solicitudEventoSignificativo.setFechaHoraInicioEvento((XMLGregorianCalendar) data.get("fechaHoraInicioEvento"));
        solicitudEventoSignificativo.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudEventoSignificativo.setCodigoPuntoVenta((JAXBElement<Integer>) data.get("codigoPuntoVenta"));
        Client client = ClientProxy.getClient(registroEventoSignifcativo);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaListaEventos=registroEventoSignifcativo.registroEventoSignificativo(solicitudEventoSignificativo);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return respuestaListaEventos.getCodigoRecepcionEventoSignificativo();
    }

    @RequestMapping(value="/verificarComunicacion", method= RequestMethod.GET)
    public int verificarComunicacion() throws IOException, JSONException {

        ServicioFacturacionOperaciones verificarComunicacion = getServicioFacturacionOperaciones();
        int resp=0;
        Client client = ClientProxy.getClient(verificarComunicacion);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            resp=verificarComunicacion.verificarComunicacion();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return resp;
    }
    public  String completeToCero(String pString, int pMaxChar)
    {
        String vNewString = pString;
        if (pString.length() < pMaxChar)
        {
            for (int i = pString.length(); i < pMaxChar; i++)
            {
                vNewString = "0".concat(vNewString);
            }
        }
        return vNewString;
    }
    public String calculaDigitoMod11(String cadena, int numDig, int limMult, boolean x10)
    {
        int mult, suma, i, n, dig;
        for(n = 1; n <= numDig; n++) {
            suma = 0;
            mult = 2;
            for(i = cadena.length() - 1; i >= 0; i--) {
                suma += (mult * Integer.parseInt(cadena.substring(i, i + 1)));
                if (++mult > limMult) mult = 2;
            }
            if (x10) {
                dig = ((suma * 10) % 11) % 10;
            }
            else {
                dig = suma % 11;
            }
            if (dig == 10) {
                cadena += "1";
            }
            if (dig == 11) {
                cadena += "0";
            }
            if (dig < 10) {
                cadena += String.valueOf(dig);
            }
        }
        return cadena.substring(cadena.length() - numDig, cadena.length());
    }


    public String getCuf(String nit, String fechaHora, String sucursal, String modalidad, String tipoEmision, String tipoFactura, String tipoDocSector, String numeroFactura, String puntoVenta){
        String cadena="";
        String nitFilled=completeToCero(nit,13);
        String fechaHoraFilled=completeToCero(fechaHora,17);
        String sucursalFilled=completeToCero(sucursal,4);
        String documentoSectorFilled=completeToCero(tipoDocSector,2);
        String numFacturaFilled=completeToCero(numeroFactura,10);
        String puntoVentaFilled=completeToCero(puntoVenta,4);
        cadena=nitFilled+fechaHoraFilled+sucursalFilled+modalidad+tipoEmision+tipoFactura+documentoSectorFilled+numFacturaFilled+puntoVentaFilled;
        String modulo11=calculaDigitoMod11(cadena,1,9,false);
        cadena=cadena+modulo11;
        BigInteger cuf=new BigInteger(cadena);
        return cuf.toString(16).toUpperCase();
    }
    @RequestMapping(value="/registroFacturasRegularizacion", method= RequestMethod.POST)
    public List<MensajeServicio> registroFacturasRegularizacion(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        ServicioFacturacionOperaciones registroFacturasRegularizacion = getServicioFacturacionOperaciones();
        RespuestaRegFactRegularizacion respuestaRegFactRegularizacion=new RespuestaRegFactRegularizacion();
        SolicitudRegFactRegularizacion solicitudRegFactRegularizacion=new SolicitudRegFactRegularizacion();
        solicitudRegFactRegularizacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudRegFactRegularizacion.setNit(((Number) data.get("nit")).longValue());
        solicitudRegFactRegularizacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudRegFactRegularizacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudRegFactRegularizacion.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudRegFactRegularizacion.setCuis(cuisService.findCuis().get(0).getCuis());
        solicitudRegFactRegularizacion.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudRegFactRegularizacion.setCodigoPuntoVenta((JAXBElement<Integer>) data.get("codigoPuntoVenta"));
        solicitudRegFactRegularizacion.setActividadEconomica((String) data.get("actividadEconomica"));
        solicitudRegFactRegularizacion.setDireccion((String) data.get("direccion"));
        String nit=(String) data.get("nit");
        String fechaHora=(String) data.get("fechaHora");
        String sucursal=(String) data.get("codigoSucursal");
        String modalidad=(String) data.get("codigoModalidad");
        String tipoEmision=(String) data.get("tipoEmision");
        String tipoFactura=(String) data.get("tipoFactura");
        String tipoDocSector=(String) data.get("tipoDocSector");
        String numeroFactura=(String) data.get("numeroFactura");
        String puntoVenta=(String) data.get("codigoPuntoVenta");
        solicitudRegFactRegularizacion.setCuf(getCuf(nit,fechaHora,sucursal,modalidad,tipoEmision,tipoFactura,tipoDocSector,numeroFactura,puntoVenta));
        Client client = ClientProxy.getClient(registroFacturasRegularizacion);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRegFactRegularizacion=registroFacturasRegularizacion.registroFacturasRegularizacion(solicitudRegFactRegularizacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaRegFactRegularizacion.getMensajeServicioList();
    }

    private ServicioFacturacionOperaciones getServicioFacturacionOperaciones() {
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacionOperaciones.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioFacturacionNotaCreditoDebito?wsdl");
        return (ServicioFacturacionOperaciones) factory.create();
    }

    @RequestMapping(value="/registroPuntoVenta", method= RequestMethod.POST)
    public int registroPuntoVenta(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionOperaciones registroPuntoVenta = getServicioFacturacionOperaciones();
        SolicitudRegistroPuntoVenta solicitudRegistroPuntoVenta=new SolicitudRegistroPuntoVenta();
        solicitudRegistroPuntoVenta.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudRegistroPuntoVenta.setNit(((Number) data.get("nit")).longValue());
        solicitudRegistroPuntoVenta.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudRegistroPuntoVenta.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudRegistroPuntoVenta.setCuis(cuisService.findCuis().get(0).getCuis());
        solicitudRegistroPuntoVenta.setDescripcion((String) data.get("descripcion"));
        solicitudRegistroPuntoVenta.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudRegistroPuntoVenta.setNombrePuntoVenta((String) data.get("nombrePuntoVenta"));
        solicitudRegistroPuntoVenta.setCodigoTipoPuntoVenta((Integer) data.get("codigoTipoPuntoVenta"));
        RespuestaRegistroPuntoVenta respuestaRegistroPuntoVenta=new RespuestaRegistroPuntoVenta();
        Client client = ClientProxy.getClient(registroPuntoVenta);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRegistroPuntoVenta=registroPuntoVenta.registroPuntoVenta(solicitudRegistroPuntoVenta);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaRegistroPuntoVenta.getCodigoPuntoVenta();
    }
}
