package com.example.demo.controller.adm;

import com.example.demo.model.CufdModel;
import com.example.demo.model.CuisModel;
import com.example.demo.model.TokenModel;
import com.example.demo.model.User;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.sql.Timestamp;
import java.util.*;


@RestController
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

        if ((ts.getTime()-tokenModel.getDateCreated().getTime()) > fourHours) {
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
        CufdModel cufdModel;
        if(!cufdService.findCufd().isEmpty()){
            cufdModel=cufdService.findCufd().get(0);
            Calendar oneDayAgo = Calendar.getInstance();
            oneDayAgo.add(Calendar.DAY_OF_MONTH, -1);
            Date oneDayAgoDate = oneDayAgo.getTime();
            if (cufdModel.getDateCreated().before(oneDayAgoDate)) {
                ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
                factory.setServiceClass(ServicioFacturacionCodigos.class);
                factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
                ServicioFacturacionCodigos solicitudCufd = (ServicioFacturacionCodigos) factory.create();
                SolicitudOperacionesCufd solicitudOperacionesCufd=new SolicitudOperacionesCufd();
                solicitudOperacionesCufd.setCodigoAmbiente(2);
                solicitudOperacionesCufd.setCodigoModalidad(2);
                solicitudOperacionesCufd.setNit(1002329022);
                solicitudOperacionesCufd.setCodigoSistema("6805EF31E16AB1944EC804E");
                solicitudOperacionesCufd.setCodigoSucursal(0);
                JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
                codigoPuntoVenta.setValue(0);
                solicitudOperacionesCufd.setCodigoPuntoVenta(codigoPuntoVenta);
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
            ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setServiceClass(ServicioFacturacionCodigos.class);
            factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
            ServicioFacturacionCodigos solicitudCufd = (ServicioFacturacionCodigos) factory.create();
            SolicitudOperacionesCufd solicitudOperacionesCufd=new SolicitudOperacionesCufd();
            solicitudOperacionesCufd.setCodigoAmbiente(2);
            solicitudOperacionesCufd.setCodigoModalidad(2);
            solicitudOperacionesCufd.setNit(1002329022);
            solicitudOperacionesCufd.setCodigoSistema("6805EF31E16AB1944EC804E");
            solicitudOperacionesCufd.setCodigoSucursal(0);
            JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
            codigoPuntoVenta.setValue(0);
            solicitudOperacionesCufd.setCodigoPuntoVenta(codigoPuntoVenta);
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
    }
    public void verificarCuis(){
        CuisModel cuisModel;
        if(!cuisService.findCuis().isEmpty()) {
            cuisModel = cuisService.findCuis().get(0);
            Calendar oneYearAgo = Calendar.getInstance();
            oneYearAgo.add(Calendar.DAY_OF_MONTH, -365);
            Date oneYearAgoDate = oneYearAgo.getTime();
            System.out.println(cuisModel.getDateCreated().before(oneYearAgoDate));
            if (cuisModel.getDateCreated().before(oneYearAgoDate)) {
                ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
                factory.setServiceClass(ServicioFacturacionCodigos.class);
                factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
                ServicioFacturacionCodigos solicitudCuis = (ServicioFacturacionCodigos) factory.create();
                SolicitudOperacionesCuis solicitudOperacionesCuis=new SolicitudOperacionesCuis();
                solicitudOperacionesCuis.setCodigoAmbiente(2);
                solicitudOperacionesCuis.setCodigoModalidad(2);
                solicitudOperacionesCuis.setNit(1002329022);
                solicitudOperacionesCuis.setCodigoSistema("6805EF31E16AB1944EC804E");
                solicitudOperacionesCuis.setCodigoSucursal(0);
                JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
                codigoPuntoVenta.setValue(0);
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
        else{
            cuisModel=new CuisModel();
            ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setServiceClass(ServicioFacturacionCodigos.class);
            factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionCodigos?wsdl");
            ServicioFacturacionCodigos solicitudCuis = (ServicioFacturacionCodigos) factory.create();
            SolicitudOperacionesCuis solicitudOperacionesCuis=new SolicitudOperacionesCuis();
            solicitudOperacionesCuis.setCodigoAmbiente(2);
            solicitudOperacionesCuis.setCodigoModalidad(2);
            solicitudOperacionesCuis.setNit(1002329022);
            solicitudOperacionesCuis.setCodigoSistema("6805EF31E16AB1944EC804E");
            solicitudOperacionesCuis.setCodigoSucursal(0);
            JAXBElement<Integer> codigoPuntoVenta =  new JAXBElement(new QName("codigoPuntoVenta"), Integer.class, null);
            codigoPuntoVenta.setValue(0);
            solicitudOperacionesCuis.setCodigoPuntoVenta(codigoPuntoVenta);
            Client client1 = ClientProxy.getClient(solicitudCuis);
            Map<String, List<String>> headers = new HashMap<String, List<String>>();
            headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
            client1.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
            client1.getOutInterceptors().add(new LoggingOutInterceptor());
            client1.getInInterceptors().add(new LoggingInInterceptor());
            try {
                Date date= new Date();
                long time = date.getTime();
                Timestamp ts = new Timestamp(time);
                cuisModel.setDateCreated(ts);
                cuisModel.setCuis(solicitudCuis.solicitudCuis(solicitudOperacionesCuis).getCodigo());
                cuisService.saveCuis(cuisModel);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    @RequestMapping(value="/", method=RequestMethod.POST)
    public ModelAndView home() throws Exception {
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
        ModelAndView mv=new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value="/home", method=RequestMethod.POST)
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
        verificarCuis();
        String username = (String) data.get("username");
        String password = (String) data.get("password");
        if ((user.getUsername().equals(username))&&(user.getPassword().equals(password))) {
            verificacion = 1;
        }
        verificarCufd();

        return verificacion;
    }
}
