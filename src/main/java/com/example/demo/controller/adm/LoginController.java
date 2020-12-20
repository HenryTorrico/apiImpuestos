package com.example.demo.controller.adm;

import com.example.demo.model.CufdModel;
import com.example.demo.model.CuisModel;
import com.example.demo.model.TokenModel;
import com.example.demo.model.User;
import com.example.demo.operaciones.RespuestaCufd;
import com.example.demo.operaciones.ServicioFacturacionCodigos;
import com.example.demo.operaciones.SolicitudOperacionesCufd;
import com.example.demo.operaciones.SolicitudOperacionesCuis;
import com.example.demo.service.CufdService;
import com.example.demo.service.CuisService;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.*;


@Controller
public class LoginController {

    @Autowired
    TokenService tokenService;

    @Autowired
    CufdService cufdService;

    @Autowired
    CuisService cuisService;

    @Autowired
    UserService userService;



    public void verificarToken() throws Exception {
        TokenModel tokenModel=tokenService.findToken().get(0);
        long fourHours = 4 * 60 * 60 * 1000L;
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        System.out.println(tokenModel.getDateCreated().getTime()-ts.getTime());
        System.out.println(ts.getTime()-tokenModel.getDateCreated().getTime());
        System.out.println(fourHours);
        if ((ts.getTime()-tokenModel.getDateCreated().getTime()) > fourHours) {
            System.out.println("we1231");
            ClientProxyFactoryBean factoryToken = new JaxWsProxyFactoryBean();
            factoryToken.setServiceClass(ServicioAutenticacionSoap.class);
            factoryToken.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioAutenticacionSoap?wsdl");
            ServicioAutenticacionSoap service = (ServicioAutenticacionSoap) factoryToken.create();
            DatosUsuarioRequest datosUsuarioRequest = new DatosUsuarioRequest();
            datosUsuarioRequest.setNit((long) 1002329022);
            datosUsuarioRequest.setLogin("bap2394882");
            datosUsuarioRequest.setPassword("Jmolina1");
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
    public void verificarCufd(){
        CufdModel cufdModel=cufdService.findCufd().get(0);
        long oneDay = 24 * 60 * 60 * 1000L;
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        if ((cufdModel.getDateCreated().getTime() - ts.getTime()) > oneDay) {
            ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setServiceClass(ServicioFacturacionCodigos.class);
            factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
            ServicioFacturacionCodigos solicitudCufd = (ServicioFacturacionCodigos) factory.create();
            SolicitudOperacionesCufd solicitudOperacionesCufd=new SolicitudOperacionesCufd();
            solicitudOperacionesCufd.setCodigoAmbiente(2);
            solicitudOperacionesCufd.setCodigoModalidad(1);
            solicitudOperacionesCufd.setNit(1002329022);
            solicitudOperacionesCufd.setCodigoSistema("67D31D5ED063B1C0BA4804F");
            solicitudOperacionesCufd.setCodigoSucursal(0);
            solicitudOperacionesCufd.setCuis(cuisService.findCuis().get(0).getCuis());
            Client client1 = ClientProxy.getClient(solicitudCufd);
            Map<String, List<String>> headers = new HashMap<String, List<String>>();
            headers.put("Authorization", Arrays.asList(tokenService.findToken().get(0).getTokenUsuario()));
            client1.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
            client1.getOutInterceptors().add(new LoggingOutInterceptor());
            client1.getInInterceptors().add(new LoggingInInterceptor());
            try {
                cufdModel.setCufd(solicitudCufd.solicitudCufd(solicitudOperacionesCufd).getCodigo());
                cufdService.updateCufd(cufdModel);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    public void verificarCuis(){
        CuisModel cuisModel=cuisService.findCuis().get(0);
        long oneYear = 8760 * 60 * 60 * 1000L;
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        if ((cuisModel.getDateCreated().getTime() - ts.getTime()) > oneYear) {
            ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setServiceClass(ServicioFacturacionCodigos.class);
            factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
            ServicioFacturacionCodigos solicitudCuis = (ServicioFacturacionCodigos) factory.create();
            SolicitudOperacionesCuis solicitudOperacionesCuis=new SolicitudOperacionesCuis();
            solicitudOperacionesCuis.setCodigoAmbiente(2);
            solicitudOperacionesCuis.setCodigoModalidad(1);
            solicitudOperacionesCuis.setNit(1002329022);
            solicitudOperacionesCuis.setCodigoSistema("67D31D5ED063B1C0BA4804F");
            solicitudOperacionesCuis.setCodigoSucursal(0);
            Client client1 = ClientProxy.getClient(solicitudCuis);
            Map<String, List<String>> headers = new HashMap<String, List<String>>();
            headers.put("Authorization", Arrays.asList(tokenService.findToken().get(0).getTokenUsuario()));
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
    @RequestMapping(value="/", method=RequestMethod.GET)
    public int index(@RequestBody Map<String, Object> data) throws Exception {
        int verificacion = 0;
        verificarToken();
        User user;
        if(!userService.findUser().isEmpty()) {
            user = userService.findUser().get(0);
        }
        else{
            user=new User();
            user.setPassword("Jmolina1");
            user.setUsername("bap123");
            userService.saveUser(user);
        }
        String username = (String) data.get("username");
        String password = (String) data.get("password");
        System.out.println(user.getPassword()+user.getUsername());
        System.out.println(username+password);
        if ((user.getUsername().equals(username))&&(user.getPassword().equals(password))) {
            verificacion = 1;
        }
        System.out.println(verificacion);
        return verificacion;
    }
}
