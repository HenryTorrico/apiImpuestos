/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller.operaciones;


import com.example.demo.model.CufdModel;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.Timestamp;
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




    @RequestMapping(value="/solicitudCuis", method= RequestMethod.GET)
    public String solicitudCuis(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        CuisModel cuisModel=new CuisModel();
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacionCodigos.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        System.out.println("hola bola");
        ServicioFacturacionCodigos solicitudCuis = (ServicioFacturacionCodigos) factory.create();
        System.out.println("hola bola2");
        SolicitudOperacionesCuis solicitudOperacionesCuis=new SolicitudOperacionesCuis();
        solicitudOperacionesCuis.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudOperacionesCuis.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudOperacionesCuis.setNit(((Number) data.get("nit")).longValue());
        solicitudOperacionesCuis.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudOperacionesCuis.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        Client client = ClientProxy.getClient(solicitudCuis);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            Date date= new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            cuisModel.setCuis(solicitudCuis.solicitudCuis(solicitudOperacionesCuis).getCodigo());
            cuisModel.setDateCreated(ts);
            cuisService.saveCuis(cuisModel);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return cuisService.findCuis().get(0).getCuis();
    }

   @RequestMapping(value="/solicitudCuisMasivo", method= RequestMethod.GET)
   public List<RespuestaListaRegistroCuisSoapDto> solicitudCuisMasivo(@RequestBody Map<String, Object> data) throws IOException, JSONException {

       ServicioFacturacionCodigos solicitudCuisMasivo = getServicioFacturacionCodigos();
       SolicitudCuisMasivoSistemas solicitudCuisMasivoSistemas=new SolicitudCuisMasivoSistemas();
       SolicitudListaCuisDto listaCuisDto=new SolicitudListaCuisDto();
       solicitudCuisMasivoSistemas.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
       solicitudCuisMasivoSistemas.setCodigoModalidad((Integer) data.get("codigoModalidad"));
       solicitudCuisMasivoSistemas.setNit(((Number) data.get("nit")).longValue());
       solicitudCuisMasivoSistemas.setCodigoSistema((String) data.get("codigoSistema"));
       listaCuisDto.setCodigoSucursal((Integer) data.get("codigoSucursal"));
       listaCuisDto.setCodigoPuntoVenta((Integer) data.get("codigoSucursal"));
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
    @RequestMapping(value="/solicitudCufdMasivo", method= RequestMethod.GET)
    public List<RespuestaListaRegistroCufdSoapDto> solicitudCufdMasivo(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        ServicioFacturacionCodigos servicioFacturacionCodigos = getServicioFacturacionCodigos();
        SolicitudMasivo solicitudMasivo=new SolicitudMasivo();
        SolicitudListaCuisDto listaCuisDto=new SolicitudListaCuisDto();
        solicitudMasivo.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudMasivo.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudMasivo.setNit(((Number) data.get("nit")).longValue());
        solicitudMasivo.setCodigoSistema((String) data.get("codigoSistema"));
        listaCuisDto.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        listaCuisDto.setCodigoPuntoVenta((Integer) data.get("codigoSucursal"));
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
   @RequestMapping(value="/solicitudCufd", method= RequestMethod.GET)
   public String solicitudCufd(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        CufdModel cufdModel;
        if(!cufdService.findCufd().isEmpty()){
            cufdModel=cufdService.findCufd().get(0);
            long oneDay = 24 * 60 * 60 * 1000L;
            Date date = new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            if ((cufdModel.getDateCreated().getTime() - ts.getTime()) > oneDay) {
                ServicioFacturacionCodigos solicitudCufd = getServicioFacturacionCodigos();
                SolicitudOperacionesCufd solicitudOperacionesCufd=new SolicitudOperacionesCufd();
                solicitudOperacionesCufd.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
                solicitudOperacionesCufd.setCodigoModalidad((Integer) data.get("codigoModalidad"));
                solicitudOperacionesCufd.setNit(((Number) data.get("nit")).longValue());
                solicitudOperacionesCufd.setCodigoSistema((String) data.get("codigoSistema"));
                solicitudOperacionesCufd.setCodigoSucursal((Integer) data.get("codigoSucursal"));
                solicitudOperacionesCufd.setCuis(cuisService.findCuis().get(0).getCuis());
                Client client1 = ClientProxy.getClient(solicitudCufd);
                Map<String, List<String>> headers = new HashMap<String, List<String>>();
                headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
                client1.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
                client1.getOutInterceptors().add(new LoggingOutInterceptor());
                client1.getInInterceptors().add(new LoggingInInterceptor());
                try {
                    cufdModel.setCufd(cufdService.findCufd().get(0).getCufd());
                    cufdService.updateCufd(cufdModel);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        else {
            cufdModel = new CufdModel();
            ServicioFacturacionCodigos solicitudCufd = getServicioFacturacionCodigos();
            SolicitudOperacionesCufd solicitudOperacionesCufd = new SolicitudOperacionesCufd();
            solicitudOperacionesCufd.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
            solicitudOperacionesCufd.setCodigoModalidad((Integer) data.get("codigoModalidad"));
            solicitudOperacionesCufd.setNit(((Number) data.get("nit")).longValue());
            solicitudOperacionesCufd.setCodigoSistema((String) data.get("codigoSistema"));
            solicitudOperacionesCufd.setCodigoSucursal((Integer) data.get("codigoSucursal"));
            solicitudOperacionesCufd.setCuis(cuisService.findCuis().get(0).getCuis());
            Client client1 = ClientProxy.getClient(solicitudCufd);
            Map<String, List<String>> headers = new HashMap<String, List<String>>();
            headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
            client1.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
            client1.getOutInterceptors().add(new LoggingOutInterceptor());
            client1.getInInterceptors().add(new LoggingInInterceptor());
            try {
                Date date= new Date();
                long time = date.getTime();
                Timestamp ts = new Timestamp(time);
                cufdModel.setDateCreated(ts);
                cufdModel.setCufd(solicitudCufd.solicitudCufd(solicitudOperacionesCufd).getCodigo());
                cufdService.saveCufd(cufdModel);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
       return cufdService.findCufd().get(0).getCufd();
   }
    @RequestMapping(value="/solicitudNotificaRevocado", method= RequestMethod.GET)
    public List<MensajeServicio> solicitudNotificaRevocado(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacionCodigos solicitudRevocado = getServicioFacturacionCodigos();
        SolicitudNotifcaRevocado solicitudNotifcaRevocado=new SolicitudNotifcaRevocado();
        solicitudNotifcaRevocado.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudNotifcaRevocado.setRazonRevocacion((String) data.get("razonRevocacion"));
        solicitudNotifcaRevocado.setCertificado((String) data.get("certificado"));
        solicitudNotifcaRevocado.setNit(((Number) data.get("nit")).longValue());
        solicitudNotifcaRevocado.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudNotifcaRevocado.setCodigoSucursal((Integer) data.get("codigoSucursal"));
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
    @RequestMapping(value="/verificarComunicacion", method= RequestMethod.GET)
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
