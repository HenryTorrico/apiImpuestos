/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller.operaciones;


import com.example.demo.model.CuisModel;
import com.example.demo.operaciones.*;
import com.example.demo.service.CufdService;
import com.example.demo.service.CuisService;
import com.example.demo.service.TokenService;
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
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author ruth
 */
@RestController
@RequestMapping(value = "/servicioOperaciones", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ServicioOperacionesController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CuisService cuisService;

    @Autowired
    private CufdService cufdService;




    @RequestMapping(value="/solicitudCuis", method= RequestMethod.POST)
    public String solicitudCuis(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        CuisModel cuisModel;
        /*if(!cuisService.findCuis().isEmpty()) {
            cuisModel = cuisService.findCuis().get(0);
            long oneYear = 8760 * 60 * 60 * 1000L;
            Date date = new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            if ((cuisModel.getDateCreated().getTime() - ts.getTime()) > oneYear) {
                ServicioFacturacionCodigos solicitudCuis = getServicioFacturacionCodigos();
                SolicitudOperacionesCuis solicitudOperacionesCuis = new SolicitudOperacionesCuis();
                solicitudOperacionesCuis.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
                solicitudOperacionesCuis.setCodigoModalidad((Integer) data.get("codigoModalidad"));
                solicitudOperacionesCuis.setNit(((Number) data.get("nit")).longValue());
                solicitudOperacionesCuis.setCodigoSistema((String) data.get("codigoSistema"));
                solicitudOperacionesCuis.setCodigoSucursal((Integer) data.get("codigoSucursal"));
                JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
                codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
                solicitudOperacionesCuis.setCodigoPuntoVenta(codigoPuntoVenta);
                Client client1 = ClientProxy.getClient(solicitudCuis);
                Map<String, List<String>> headers = new HashMap<String, List<String>>();
                headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
                client1.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
                client1.getOutInterceptors().add(new LoggingOutInterceptor());
                client1.getInInterceptors().add(new LoggingInInterceptor());
                try {
                    cuisModel.setCuis(solicitudCuis.solicitudCuis(solicitudOperacionesCuis).getCodigo());
                    cuisService.updateCuis(cuisModel);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        else{*/
            //cuisModel=new CuisModel();
        String cuis="";
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacionCodigos.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        ServicioFacturacionCodigos solicitudCuis =(ServicioFacturacionCodigos) factory.create();
        SolicitudOperacionesCuis solicitudOperacionesCuis = new SolicitudOperacionesCuis();
        solicitudOperacionesCuis.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudOperacionesCuis.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudOperacionesCuis.setNit(((Number) data.get("nit")).longValue());
        solicitudOperacionesCuis.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudOperacionesCuis.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        System.out.println("codigo"+codigoPuntoVenta.getName());
        solicitudOperacionesCuis.setCodigoPuntoVenta(codigoPuntoVenta);
        Client client = ClientProxy.getClient(solicitudCuis);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());

        try {
                RespuestaCuis respuestaCuis=solicitudCuis.solicitudCuis(solicitudOperacionesCuis);
                return respuestaCuis.getCodigo();
                //cuisModel.setDateCreated(ts);
                //cuisModel.setCuis(solicitudCuis.solicitudCuis(solicitudOperacionesCuis).getCodigo());
                //System.out.println(cuisModel.getCuis());
                //cuisService.saveCuis(cuisModel);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
       // }
        return null;
    }

    String callCuis(int codigoAmbiente, int codigoModalidad, Number nit, String codigoSistema, int codigoSucursal, JAXBElement<Integer> codigoPuntoVenta) throws InterruptedException {
        String cuis="";
        ServicioFacturacionCodigos solicitudCuis = getServicioFacturacionCodigos();
        SolicitudOperacionesCuis solicitudOperacionesCuis = new SolicitudOperacionesCuis();
        solicitudOperacionesCuis.setCodigoAmbiente(codigoAmbiente);
        solicitudOperacionesCuis.setCodigoModalidad(codigoModalidad);
        solicitudOperacionesCuis.setNit((long) nit);
        solicitudOperacionesCuis.setCodigoSistema(codigoSistema);
        solicitudOperacionesCuis.setCodigoSucursal(codigoSucursal);
        solicitudOperacionesCuis.setCodigoPuntoVenta(codigoPuntoVenta);
        Client client1 = ClientProxy.getClient(solicitudCuis);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client1.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client1.getOutInterceptors().add(new LoggingOutInterceptor());
        client1.getInInterceptors().add(new LoggingInInterceptor());
        try {
            cuis= solicitudCuis.solicitudCuis(solicitudOperacionesCuis).getCodigo();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(cuis);
        Thread.sleep(10000);
        return cuis;
    }
    @RequestMapping(value="/solicitudCufd", method= RequestMethod.POST)
    public String solicitudCufd(@RequestBody Map<String, Object> data) throws IOException, JSONException, InterruptedException {
        String cufd="";
        int codigoAmbiente= (Integer) data.get("codigoAmbiente");
        int codigoModalidad= (Integer) data.get("codigoModalidad");
        Number nit= ((Number) data.get("nit")).longValue();
        String codigoSistema= (String) data.get("codigoSistema");
        int codigoSucursal= (Integer) data.get("codigoSucursal");
        String cuis = (String) data.get("cuis");
        JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        codigoPuntoVenta.setValue((Integer) data.get("codigoPuntoVenta"));
        /*String cuis;
        cuis=callCuis(codigoAmbiente,codigoModalidad,nit,codigoSistema,codigoSucursal,codigoPuntoVenta);*/
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacionCodigos.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        ServicioFacturacionCodigos solicitudCufd =(ServicioFacturacionCodigos) factory.create();
        SolicitudOperacionesCufd solicitudOperacionesCufd=new SolicitudOperacionesCufd();
        solicitudOperacionesCufd.setCodigoAmbiente(codigoAmbiente);
        solicitudOperacionesCufd.setCodigoModalidad(codigoModalidad);
        solicitudOperacionesCufd.setNit((Long) nit);
        solicitudOperacionesCufd.setCodigoSistema(codigoSistema);
        solicitudOperacionesCufd.setCodigoSucursal(codigoSucursal);
        solicitudOperacionesCufd.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudOperacionesCufd.setCuis(cuis);
        Client client1 = ClientProxy.getClient(solicitudCufd);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client1.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client1.getOutInterceptors().add(new LoggingOutInterceptor());
        client1.getInInterceptors().add(new LoggingInInterceptor());
        try {
            cufd=solicitudCufd.solicitudCufd(solicitudOperacionesCufd).getCodigo();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(cufd);
        return cufd;
    }

   @RequestMapping(value="/solicitudCuisMasivo", method= RequestMethod.POST)
   public List<RespuestaListaRegistroCuisSoapDto> solicitudCuisMasivo(@RequestBody Map<String, Object> data) throws IOException, JSONException {

       ServicioFacturacionCodigos solicitudCuisMasivo = getServicioFacturacionCodigos();
       SolicitudCuisMasivoSistemas solicitudCuisMasivoSistemas=new SolicitudCuisMasivoSistemas();
       SolicitudListaCuisDto listaCuisDto=new SolicitudListaCuisDto();
       solicitudCuisMasivoSistemas.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
       solicitudCuisMasivoSistemas.setCodigoModalidad((Integer) data.get("codigoModalidad"));
       solicitudCuisMasivoSistemas.setNit(((Number) data.get("nit")).longValue());
       solicitudCuisMasivoSistemas.setCodigoSistema((String) data.get("codigoSistema"));
       listaCuisDto.setCodigoSucursal((Integer) data.get("codigoSucursal"));
       listaCuisDto.setCodigoPuntoVenta((Integer) data.get("codigoPuntoVenta"));
       Client client = ClientProxy.getClient(solicitudCuisMasivo);
       Map<String, List<String>> headers = new HashMap<String, List<String>>();
       headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
       client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
       client.getOutInterceptors().add(new LoggingOutInterceptor());
       client.getInInterceptors().add(new LoggingInInterceptor());
       RespuestaCuisMasivo respuestaCuisMasivo=new RespuestaCuisMasivo();
       try {
           respuestaCuisMasivo=solicitudCuisMasivo.solicitudCuisMasivo(solicitudCuisMasivoSistemas);
       } catch (Exception e) {
           System.out.println("Error: " + e.getMessage());
       }
       ModelAndView mv = new ModelAndView("index.html");
       return respuestaCuisMasivo.getListaRespuestasCuis();

   }
    @RequestMapping(value="/solicitudCufdMasivo", method= RequestMethod.POST)
    public List<RespuestaListaRegistroCufdSoapDto> solicitudCufdMasivo(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        ServicioFacturacionCodigos servicioFacturacionCodigos = getServicioFacturacionCodigos();
        SolicitudMasivo solicitudMasivo=new SolicitudMasivo();
        SolicitudListaCuisDto listaCuisDto=new SolicitudListaCuisDto();
        solicitudMasivo.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudMasivo.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudMasivo.setNit(((Number) data.get("nit")).longValue());
        solicitudMasivo.setCodigoSistema((String) data.get("codigoSistema"));
        listaCuisDto.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        listaCuisDto.setCodigoPuntoVenta((Integer) data.get("codigoPuntoVenta"));
        Client client = ClientProxy.getClient(servicioFacturacionCodigos);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        RespuestaCufdMasivo respuestaCufdMasivo=new RespuestaCufdMasivo();
        try {
            respuestaCufdMasivo=servicioFacturacionCodigos.solicitudCufdMasivo(solicitudMasivo);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaCufdMasivo.getListaRespuestasCufd();

    }

    @RequestMapping(value="/solicitudNotificaRevocado", method= RequestMethod.POST)
    public List<MensajeServicio> solicitudNotificaRevocado(@RequestBody Map<String, Object> data) throws IOException, JSONException, ParseException, DatatypeConfigurationException {

        ServicioFacturacionCodigos solicitudRevocado = getServicioFacturacionCodigos();
        SolicitudNotifcaRevocado solicitudNotifcaRevocado=new SolicitudNotifcaRevocado();
        solicitudNotifcaRevocado.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudNotifcaRevocado.setRazonRevocacion((String) data.get("razonRevocacion"));
        solicitudNotifcaRevocado.setCertificado((String) data.get("certificado"));
        solicitudNotifcaRevocado.setNit(((Number) data.get("nit")).longValue());
        solicitudNotifcaRevocado.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudNotifcaRevocado.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = format.parse((String) data.get("fechaRevocacion"));
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        JAXBElement<XMLGregorianCalendar> fechaRevocado =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
        fechaRevocado.setValue(xmlGregCal);
        solicitudNotifcaRevocado.setFechaRevocacion(fechaRevocado);
        solicitudNotifcaRevocado.setCuis(cuisService.findCuis().get(0).getCuis());
        Client client1 = ClientProxy.getClient(solicitudRevocado);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client1.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client1.getOutInterceptors().add(new LoggingOutInterceptor());
        client1.getInInterceptors().add(new LoggingInInterceptor());
        RespuestaNotificaRevocado respuestaNotificaRevocado=new RespuestaNotificaRevocado();
        try {
            respuestaNotificaRevocado=solicitudRevocado.notificaCertificadoRevocado(solicitudNotifcaRevocado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaNotificaRevocado.getMensajeServicioList();

    }
    @RequestMapping(value="/verificarComunicacion", method= RequestMethod.POST)
    public int verificarComunicacion() throws IOException, JSONException {
        int respuesta=0;
        ServicioFacturacionCodigos verificarComunicacion = getServicioFacturacionCodigos();
        Client client1 = ClientProxy.getClient(verificarComunicacion);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client1.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client1.getOutInterceptors().add(new LoggingOutInterceptor());
        client1.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuesta=verificarComunicacion.verificarComunicacion();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(respuesta);
        return respuesta;

    }

    private ServicioFacturacionCodigos getServicioFacturacionCodigos() {
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacionCodigos.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        return (ServicioFacturacionCodigos) factory.create();
    }

}
