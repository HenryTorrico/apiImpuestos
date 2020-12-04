/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller.adm;


import com.example.demo.operaciones.ServicioFacturacionCodigos;

import java.io.IOException;

import java.util.*;

import com.example.demo.operaciones.VerificarComunicacionResponse;
import com.example.demo.wsdl.*;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.message.Message;

import org.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ruth
 */
@RestController
@RequestMapping(value = "/admUsuario", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AdmUsuarioController {

    private String _TOKENFORFACTURACION = null;

    @RequestMapping(value="/authenticate", method= RequestMethod.GET)
    public ModelAndView index(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        System.out.println(data.get("login"));
        System.out.println(data.get("nit"));
        System.out.println(data.get("password"));

        ClientProxyFactoryBean factoryToken = new JaxWsProxyFactoryBean();
        factoryToken.setServiceClass(ServicioAutenticacionSoap.class);
        factoryToken.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioAutenticacionSoap?wsdl");
        ServicioAutenticacionSoap service = (ServicioAutenticacionSoap) factoryToken.create();
        DatosUsuarioRequest datosUsuarioRequest = new DatosUsuarioRequest();
        datosUsuarioRequest.setNit(((Number)data.get("nit")).longValue());
        datosUsuarioRequest.setLogin(data.get("login").toString());
        datosUsuarioRequest.setPassword(data.get("password").toString());
        service.token(datosUsuarioRequest);
        UsuarioAutenticadoDto usuarioAutenticadoDto=new UsuarioAutenticadoDto();
        usuarioAutenticadoDto.setToken(service.token(datosUsuarioRequest).getToken());
        System.out.println("token fuera: "+usuarioAutenticadoDto.getToken());
        if (service.token(datosUsuarioRequest).isOk()) {
            String token = service.token(datosUsuarioRequest).getToken();
            _TOKENFORFACTURACION=token;
            System.out.println("token dentro: " + _TOKENFORFACTURACION);
        } else {
            List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
            for (StrMensajeAplicacionDto m : lista) {
                System.out.println("Error: " + m.getDescripcion());
            }
        }
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacionCodigos.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
        ServicioFacturacionCodigos serviceFacturacion = (ServicioFacturacionCodigos) factory.create();
        Client client = ClientProxy.getClient(serviceFacturacion);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList(_TOKENFORFACTURACION));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());

        try {
            VerificarComunicacionResponse verificarComunicacionResponse=new VerificarComunicacionResponse();
            verificarComunicacionResponse.setReturn(serviceFacturacion.verificarComunicacion().intValue());
            System.out.println(verificarComunicacionResponse.getReturn());
		    System.out.println("funca");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
    ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }
    /*@GetMapping("/getParametros/{login}")
    public ResponseEntity<ConsultaParametros> getParametros(@PathVariable("login") String login) {
        ConsultaParametros obj = admUsuarioService.consultaParametros(login);
        if (obj == null) {
            throw new ModelNotFoundException("NO ENCONTRADO: " + login);
        }

        return new ResponseEntity<ConsultaParametros>(obj, HttpStatus.OK);
    }*/

}
