/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller.operaciones;


import com.example.demo.controller.adm.AdmUsuarioController;
import com.example.demo.operaciones.*;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;

/**
 *
 * @author ruth
 */
@RestController
@RequestMapping(value = "/servicioOperaciones", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ServicioOperacionesController {

    private String _TOKENFORFACTURACION = null;

    private RespuestaCuis respuestaCuis=new RespuestaCuis();



    @RequestMapping(value="/solicitudCuis", method= RequestMethod.GET)
    public ModelAndView solicitudCuis(@RequestBody Map<String, Object> data) throws IOException, JSONException {
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
        factory.setServiceClass(ServicioFacturacionCodigos.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        ServicioFacturacionCodigos solicitudCuis = (ServicioFacturacionCodigos) factory.create();
        SolicitudOperacionesCuis solicitudOperacionesCuis=new SolicitudOperacionesCuis();
        solicitudOperacionesCuis.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudOperacionesCuis.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudOperacionesCuis.setNit(((Number) data.get("nit")).longValue());
        solicitudOperacionesCuis.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudOperacionesCuis.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        Client client = ClientProxy.getClient(solicitudCuis);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList(_TOKENFORFACTURACION));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaCuis=solicitudCuis.solicitudCuis(solicitudOperacionesCuis);
            System.out.println(respuestaCuis.getCodigo());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }





        ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }

   /* @GetMapping("/sincronizarFechaHora/{login}")
    public ResponseEntity<Respuesta> sincronizarFechaHora(@PathVariable("login") String login) {
        Respuesta obj = servicio.sincronizarFechaHora(login);
        if (obj == null) {
            throw new ModelNotFoundException("Error en la red u otro");
        }
        return new ResponseEntity<Respuesta>(obj, HttpStatus.OK);
    }

    @GetMapping("/getConfiguracionVigenteBySucursal/{idSucursal}")
    public ResponseEntity<ApiConfiguracion> getConfiguracionVigenteBySucursal(@PathVariable("idSucursal") Long idSucursal) {
        ApiConfiguracion obj = servicio.getConfiguracionVigenteBySucursal(idSucursal);
        if (obj == null) {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + idSucursal);
        }
        return new ResponseEntity<ApiConfiguracion>(obj, HttpStatus.OK);
    }

    @GetMapping("/getConfiguracionVigenteByPuntoVenta/{idPuntoVenta}")
    public ResponseEntity<ApiConfiguracion> getConfiguracionVigenteByPuntoVenta(@PathVariable("idPuntoVenta") Long idPuntoVenta) {
        ApiConfiguracion obj = servicio.getConfiguracionVigenteByPuntoVenta(idPuntoVenta);
        if (obj == null) {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + idPuntoVenta);
        }
        return new ResponseEntity<ApiConfiguracion>(obj, HttpStatus.OK);
    }
*/
   @RequestMapping(value="/solicitudCuisMasivo", method= RequestMethod.GET)
   public ModelAndView solicitudCuisMasivo(@RequestBody Map<String, Object> data) throws IOException, JSONException {
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
           _TOKENFORFACTURACION = token;
           System.out.println("token es: " + _TOKENFORFACTURACION);
       } else {
           List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
           for (StrMensajeAplicacionDto m : lista) {
               System.out.println("Error " + m.getDescripcion());
           }
       }
       ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
       factory.setServiceClass(ServicioFacturacionCodigos.class);
       factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
       ServicioFacturacionCodigos solicitudCuisMasivo = (ServicioFacturacionCodigos) factory.create();
       SolicitudCuisMasivoSistemas solicitudCuisMasivoSistemas=new SolicitudCuisMasivoSistemas();
       SolicitudListaCuisDto listaCuisDto=new SolicitudListaCuisDto();
       solicitudCuisMasivoSistemas.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
       solicitudCuisMasivoSistemas.setCodigoModalidad((Integer) data.get("codigoModalidad"));
       solicitudCuisMasivoSistemas.setNit(((Number) data.get("nit")).longValue());
       solicitudCuisMasivoSistemas.setCodigoSistema((String) data.get("codigoSistema"));
       listaCuisDto.setCodigoSucursal((Integer) data.get("codigoSucursal"));
       listaCuisDto.setCodigoPuntoVenta((Integer) data.get("codigoSucursal"));
       List<SolicitudListaCuisDto> listaSolicitud=new LinkedList<>();
       solicitudCuisMasivoSistemas.setDatosSolicitud(listaSolicitud);
       Client client = ClientProxy.getClient(solicitudCuisMasivo);
       Map<String, List<String>> headers = new HashMap<String, List<String>>();
       headers.put("Authorization", Arrays.asList(_TOKENFORFACTURACION));
       client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
       client.getOutInterceptors().add(new LoggingOutInterceptor());
       client.getInInterceptors().add(new LoggingInInterceptor());
       RespuestaCuisMasivo respuestaCuisMasivo;
       try {
           respuestaCuisMasivo=solicitudCuisMasivo.solicitudCuisMasivo(solicitudCuisMasivoSistemas);
           System.out.println(respuestaCuisMasivo.getListaRespuestasCuis().get(0));
       } catch (Exception e) {
           System.out.println("Error: " + e.getMessage());
       }
       ModelAndView mv = new ModelAndView("index.html");
       return mv;

   }
    @RequestMapping(value="/solicitudCufdMasivo", method= RequestMethod.GET)
    public ModelAndView solicitudCufdMasivo(@RequestBody Map<String, Object> data) throws IOException, JSONException {
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
            _TOKENFORFACTURACION = token;
            System.out.println("token es: " + _TOKENFORFACTURACION);
        } else {
            List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
            for (StrMensajeAplicacionDto m : lista) {
                System.out.println("Error " + m.getDescripcion());
            }
        }
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacionCodigos.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        ServicioFacturacionCodigos servicioFacturacionCodigos = (ServicioFacturacionCodigos) factory.create();
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
        headers.put("Authorization", Arrays.asList(_TOKENFORFACTURACION));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());

        try {
            RespuestaCufdMasivo respuestaCufdMasivo;
            respuestaCufdMasivo=servicioFacturacionCodigos.solicitudCufdMasivo(solicitudMasivo);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return mv;

    }
   @RequestMapping(value="/solicitudCufd", method= RequestMethod.GET)
   public ModelAndView solicitudCufd(@RequestBody Map<String, Object> data) throws IOException, JSONException {
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
           _TOKENFORFACTURACION = token;
           System.out.println("token es: " + _TOKENFORFACTURACION);
       } else {
           List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
           for (StrMensajeAplicacionDto m : lista) {
               System.out.println("Error " + m.getDescripcion());
           }
       }
       ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
       factory.setServiceClass(ServicioFacturacionCodigos.class);
       factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
       ServicioFacturacionCodigos solicitudCufd = (ServicioFacturacionCodigos) factory.create();
       SolicitudOperacionesCufd solicitudOperacionesCufd=new SolicitudOperacionesCufd();
       solicitudOperacionesCufd.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
       solicitudOperacionesCufd.setCodigoModalidad((Integer) data.get("codigoModalidad"));
       solicitudOperacionesCufd.setNit(((Number) data.get("nit")).longValue());
       solicitudOperacionesCufd.setCodigoSistema((String) data.get("codigoSistema"));
       solicitudOperacionesCufd.setCodigoSucursal((Integer) data.get("codigoSucursal"));
       solicitudOperacionesCufd.setCuis(respuestaCuis.getCodigo());
       Client client1 = ClientProxy.getClient(solicitudCufd);
       Map<String, List<String>> headers = new HashMap<String, List<String>>();
       headers.put("Authorization", Arrays.asList(_TOKENFORFACTURACION));
       client1.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
       client1.getOutInterceptors().add(new LoggingOutInterceptor());
       client1.getInInterceptors().add(new LoggingInInterceptor());
       RespuestaCufd respuestaCufd;
       try {
           respuestaCufd=solicitudCufd.solicitudCufd(solicitudOperacionesCufd);
           System.out.println(respuestaCufd.getCodigo());
       } catch (Exception e) {
           System.out.println("Error: " + e.getMessage());
       }
       ModelAndView mv = new ModelAndView("index.html");
       return mv;

   }
    @RequestMapping(value="/solicitudNotificaRevocado", method= RequestMethod.GET)
    public ModelAndView solicitudNotificaRevocado(@RequestBody Map<String, Object> data) throws IOException, JSONException {
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
            _TOKENFORFACTURACION = token;
            System.out.println("token es: " + _TOKENFORFACTURACION);
        } else {
            List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
            for (StrMensajeAplicacionDto m : lista) {
                System.out.println("Error " + m.getDescripcion());
            }
        }
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacionCodigos.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        ServicioFacturacionCodigos solicitudRevocado = (ServicioFacturacionCodigos) factory.create();
        SolicitudNotifcaRevocado solicitudNotifcaRevocado=new SolicitudNotifcaRevocado();
        solicitudNotifcaRevocado.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudNotifcaRevocado.setRazonRevocacion((String) data.get("razonRevocacion"));
        solicitudNotifcaRevocado.setCertificado((String) data.get("certificado"));
        solicitudNotifcaRevocado.setNit(((Number) data.get("nit")).longValue());
        solicitudNotifcaRevocado.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudNotifcaRevocado.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudNotifcaRevocado.setCuis(respuestaCuis.getCodigo());
        Client client1 = ClientProxy.getClient(solicitudRevocado);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList(_TOKENFORFACTURACION));
        client1.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client1.getOutInterceptors().add(new LoggingOutInterceptor());
        client1.getInInterceptors().add(new LoggingInInterceptor());
        RespuestaNotificaRevocado respuestaNotificaRevocado;
        try {
            respuestaNotificaRevocado=solicitudRevocado.notificaCertificadoRevocado(solicitudNotifcaRevocado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return mv;

    }

}
