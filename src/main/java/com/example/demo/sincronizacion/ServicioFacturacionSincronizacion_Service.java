
package com.example.demo.sincronizacion;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ServicioFacturacionSincronizacion", targetNamespace = "https://siat.impuestos.gob.bo/FacturacionSincronizacion", wsdlLocation = "https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionSincronizacion?wsdl")
public class ServicioFacturacionSincronizacion_Service
    extends Service
{

    private final static URL SERVICIOFACTURACIONSINCRONIZACION_WSDL_LOCATION;
    private final static WebServiceException SERVICIOFACTURACIONSINCRONIZACION_EXCEPTION;
    private final static QName SERVICIOFACTURACIONSINCRONIZACION_QNAME = new QName("https://siat.impuestos.gob.bo/FacturacionSincronizacion", "ServicioFacturacionSincronizacion");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://pilotosiatservicios.impuestos.gob.bo/v1/FacturacionSincronizacion?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SERVICIOFACTURACIONSINCRONIZACION_WSDL_LOCATION = url;
        SERVICIOFACTURACIONSINCRONIZACION_EXCEPTION = e;
    }

    public ServicioFacturacionSincronizacion_Service() {
        super(__getWsdlLocation(), SERVICIOFACTURACIONSINCRONIZACION_QNAME);
    }

    public ServicioFacturacionSincronizacion_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SERVICIOFACTURACIONSINCRONIZACION_QNAME, features);
    }

    public ServicioFacturacionSincronizacion_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICIOFACTURACIONSINCRONIZACION_QNAME);
    }

    public ServicioFacturacionSincronizacion_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICIOFACTURACIONSINCRONIZACION_QNAME, features);
    }

    public ServicioFacturacionSincronizacion_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServicioFacturacionSincronizacion_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ServicioFacturacionSincronizacion
     */
    @WebEndpoint(name = "ServicioFacturacionSincronizacionPort")
    public ServicioFacturacionSincronizacion getServicioFacturacionSincronizacionPort() {
        return super.getPort(new QName("https://siat.impuestos.gob.bo/FacturacionSincronizacion", "ServicioFacturacionSincronizacionPort"), ServicioFacturacionSincronizacion.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ServicioFacturacionSincronizacion
     */
    @WebEndpoint(name = "ServicioFacturacionSincronizacionPort")
    public ServicioFacturacionSincronizacion getServicioFacturacionSincronizacionPort(WebServiceFeature... features) {
        return super.getPort(new QName("https://siat.impuestos.gob.bo/FacturacionSincronizacion", "ServicioFacturacionSincronizacionPort"), ServicioFacturacionSincronizacion.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SERVICIOFACTURACIONSINCRONIZACION_EXCEPTION!= null) {
            throw SERVICIOFACTURACIONSINCRONIZACION_EXCEPTION;
        }
        return SERVICIOFACTURACIONSINCRONIZACION_WSDL_LOCATION;
    }

}
