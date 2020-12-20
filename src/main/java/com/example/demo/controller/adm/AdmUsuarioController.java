/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller.adm;


import com.example.demo.model.TokenModel;
import com.example.demo.operaciones.ServicioFacturacionCodigos;

import java.io.IOException;

import java.sql.Timestamp;
import java.util.*;

import com.example.demo.operaciones.VerificarComunicacionResponse;
import com.example.demo.service.TokenService;
import com.example.demo.wsdl.*;
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

/**
 *
 * @author ruth
 */
@RestController
@RequestMapping(value = "/admUsuario", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AdmUsuarioController {

    @Autowired
    TokenService tokenService;


    private int ver=0;

    @RequestMapping(value="/authenticate", method= RequestMethod.GET)
    public String index(@RequestBody Map<String, Object> data) throws Exception {
        if(!tokenService.findToken().isEmpty()) {
            TokenModel tokenModel=tokenService.findToken().get(0);
            long fourHours = 4 * 60 * 60 * 1000L;
            Date date = new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            if ((tokenModel.getDateCreated().getTime() - ts.getTime()) > fourHours) {
                ClientProxyFactoryBean factoryToken = new JaxWsProxyFactoryBean();
                factoryToken.setServiceClass(ServicioAutenticacionSoap.class);
                factoryToken.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioAutenticacionSoap?wsdl");
                ServicioAutenticacionSoap service = (ServicioAutenticacionSoap) factoryToken.create();
                DatosUsuarioRequest datosUsuarioRequest = new DatosUsuarioRequest();
                datosUsuarioRequest.setNit((long) data.get("nit"));
                datosUsuarioRequest.setLogin((String) data.get("login"));
                datosUsuarioRequest.setPassword((String) data.get("password"));
                if (service.token(datosUsuarioRequest).isOk()) {
                    tokenModel.setTokenUsuario(service.token(datosUsuarioRequest).getToken());
                    tokenService.updateToken(tokenModel);
                } else {
                    List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
                    for (StrMensajeAplicacionDto m : lista) {
                        System.out.println("Error: " + m.getDescripcion());
                    }
                }
            }
        }
        else{
            Date date= new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            TokenModel newToken=new TokenModel();
            ClientProxyFactoryBean factoryToken = new JaxWsProxyFactoryBean();
            factoryToken.setServiceClass(ServicioAutenticacionSoap.class);
            factoryToken.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioAutenticacionSoap?wsdl");
            ServicioAutenticacionSoap service = (ServicioAutenticacionSoap) factoryToken.create();
            DatosUsuarioRequest datosUsuarioRequest = new DatosUsuarioRequest();
            datosUsuarioRequest.setNit((long) data.get("nit"));
            datosUsuarioRequest.setLogin((String) data.get("login"));
            datosUsuarioRequest.setPassword((String) data.get("password"));
            if (service.token(datosUsuarioRequest).isOk()) {
                newToken.setDateCreated(ts);
                newToken.setTokenUsuario(service.token(datosUsuarioRequest).getToken());
                tokenService.saveToken(newToken);
            } else {
                List<StrMensajeAplicacionDto> lista = service.token(datosUsuarioRequest).getMensajes();
                for (StrMensajeAplicacionDto m : lista) {
                    System.out.println("Error: " + m.getDescripcion());
                }
            }
        }
        return tokenService.findToken().get(0).getTokenUsuario();
    }


}
