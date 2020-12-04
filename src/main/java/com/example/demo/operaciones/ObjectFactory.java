
package com.example.demo.operaciones;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.demo.facturacion package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SolicitudCuis_QNAME = new QName("https://siat.impuestos.gob.bo/", "solicitudCuis");
    private final static QName _SolicitudCuisMasivo_QNAME = new QName("https://siat.impuestos.gob.bo/", "solicitudCuisMasivo");
    private final static QName _VerificarComunicacion_QNAME = new QName("https://siat.impuestos.gob.bo/", "verificarComunicacion");
    private final static QName _VerificarComunicacionResponse_QNAME = new QName("https://siat.impuestos.gob.bo/", "verificarComunicacionResponse");
    private final static QName _SolicitudCuisResponse_QNAME = new QName("https://siat.impuestos.gob.bo/", "solicitudCuisResponse");
    private final static QName _SolicitudCufd_QNAME = new QName("https://siat.impuestos.gob.bo/", "solicitudCufd");
    private final static QName _SolicitudCufdMasivo_QNAME = new QName("https://siat.impuestos.gob.bo/", "solicitudCufdMasivo");
    private final static QName _SolicitudCufdMasivoResponse_QNAME = new QName("https://siat.impuestos.gob.bo/", "solicitudCufdMasivoResponse");
    private final static QName _NotificaCertificadoRevocadoResponse_QNAME = new QName("https://siat.impuestos.gob.bo/", "notificaCertificadoRevocadoResponse");
    private final static QName _SolicitudCufdResponse_QNAME = new QName("https://siat.impuestos.gob.bo/", "solicitudCufdResponse");
    private final static QName _SolicitudCuisMasivoResponse_QNAME = new QName("https://siat.impuestos.gob.bo/", "solicitudCuisMasivoResponse");
    private final static QName _NotificaCertificadoRevocado_QNAME = new QName("https://siat.impuestos.gob.bo/", "notificaCertificadoRevocado");
    private final static QName _SolicitudOperacionesCufdCodigoPuntoVenta_QNAME = new QName("", "codigoPuntoVenta");
    private final static QName _SolicitudNotifcaRevocadoFechaRevocacion_QNAME = new QName("", "fechaRevocacion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.demo.facturacion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NotificaCertificadoRevocado }
     * 
     */
    public NotificaCertificadoRevocado createNotificaCertificadoRevocado() {
        return new NotificaCertificadoRevocado();
    }

    /**
     * Create an instance of {@link SolicitudCuisResponse }
     * 
     */
    public SolicitudCuisResponse createSolicitudCuisResponse() {
        return new SolicitudCuisResponse();
    }

    /**
     * Create an instance of {@link SolicitudCuisMasivo }
     * 
     */
    public SolicitudCuisMasivo createSolicitudCuisMasivo() {
        return new SolicitudCuisMasivo();
    }

    /**
     * Create an instance of {@link VerificarComunicacion }
     * 
     */
    public VerificarComunicacion createVerificarComunicacion() {
        return new VerificarComunicacion();
    }

    /**
     * Create an instance of {@link VerificarComunicacionResponse }
     * 
     */
    public VerificarComunicacionResponse createVerificarComunicacionResponse() {
        return new VerificarComunicacionResponse();
    }

    /**
     * Create an instance of {@link SolicitudCuis }
     * 
     */
    public SolicitudCuis createSolicitudCuis() {
        return new SolicitudCuis();
    }

    /**
     * Create an instance of {@link SolicitudCufdResponse }
     * 
     */
    public SolicitudCufdResponse createSolicitudCufdResponse() {
        return new SolicitudCufdResponse();
    }

    /**
     * Create an instance of {@link SolicitudCuisMasivoResponse }
     * 
     */
    public SolicitudCuisMasivoResponse createSolicitudCuisMasivoResponse() {
        return new SolicitudCuisMasivoResponse();
    }

    /**
     * Create an instance of {@link NotificaCertificadoRevocadoResponse }
     * 
     */
    public NotificaCertificadoRevocadoResponse createNotificaCertificadoRevocadoResponse() {
        return new NotificaCertificadoRevocadoResponse();
    }

    /**
     * Create an instance of {@link SolicitudCufd }
     * 
     */
    public SolicitudCufd createSolicitudCufd() {
        return new SolicitudCufd();
    }

    /**
     * Create an instance of {@link SolicitudCufdMasivo }
     * 
     */
    public SolicitudCufdMasivo createSolicitudCufdMasivo() {
        return new SolicitudCufdMasivo();
    }

    /**
     * Create an instance of {@link SolicitudCufdMasivoResponse }
     * 
     */
    public SolicitudCufdMasivoResponse createSolicitudCufdMasivoResponse() {
        return new SolicitudCufdMasivoResponse();
    }

    /**
     * Create an instance of {@link SolicitudOperacionesCufd }
     * 
     */
    public SolicitudOperacionesCufd createSolicitudOperacionesCufd() {
        return new SolicitudOperacionesCufd();
    }

    /**
     * Create an instance of {@link RespuestaListaRegistroCufdSoapDto }
     * 
     */
    public RespuestaListaRegistroCufdSoapDto createRespuestaListaRegistroCufdSoapDto() {
        return new RespuestaListaRegistroCufdSoapDto();
    }

    /**
     * Create an instance of {@link RespuestaCufd }
     * 
     */
    public RespuestaCufd createRespuestaCufd() {
        return new RespuestaCufd();
    }

    /**
     * Create an instance of {@link SolicitudMasivo }
     * 
     */
    public SolicitudMasivo createSolicitudMasivo() {
        return new SolicitudMasivo();
    }

    /**
     * Create an instance of {@link RespuestaListaRegistroCuisSoapDto }
     * 
     */
    public RespuestaListaRegistroCuisSoapDto createRespuestaListaRegistroCuisSoapDto() {
        return new RespuestaListaRegistroCuisSoapDto();
    }

    /**
     * Create an instance of {@link SolicitudCuisMasivoSistemas }
     * 
     */
    public SolicitudCuisMasivoSistemas createSolicitudCuisMasivoSistemas() {
        return new SolicitudCuisMasivoSistemas();
    }

    /**
     * Create an instance of {@link RespuestaCufdMasivo }
     * 
     */
    public RespuestaCufdMasivo createRespuestaCufdMasivo() {
        return new RespuestaCufdMasivo();
    }

    /**
     * Create an instance of {@link SolicitudNotifcaRevocado }
     * 
     */
    public SolicitudNotifcaRevocado createSolicitudNotifcaRevocado() {
        return new SolicitudNotifcaRevocado();
    }

    /**
     * Create an instance of {@link RespuestaNotificaRevocado }
     * 
     */
    public RespuestaNotificaRevocado createRespuestaNotificaRevocado() {
        return new RespuestaNotificaRevocado();
    }

    /**
     * Create an instance of {@link SolicitudOperacionesCuis }
     * 
     */
    public SolicitudOperacionesCuis createSolicitudOperacionesCuis() {
        return new SolicitudOperacionesCuis();
    }

    /**
     * Create an instance of {@link RespuestaCuisMasivo }
     * 
     */
    public RespuestaCuisMasivo createRespuestaCuisMasivo() {
        return new RespuestaCuisMasivo();
    }

    /**
     * Create an instance of {@link MensajeServicio }
     * 
     */
    public MensajeServicio createMensajeServicio() {
        return new MensajeServicio();
    }

    /**
     * Create an instance of {@link SolicitudListaCuisDto }
     * 
     */
    public SolicitudListaCuisDto createSolicitudListaCuisDto() {
        return new SolicitudListaCuisDto();
    }

    /**
     * Create an instance of {@link SolicitudListaCufdDto }
     * 
     */
    public SolicitudListaCufdDto createSolicitudListaCufdDto() {
        return new SolicitudListaCufdDto();
    }

    /**
     * Create an instance of {@link RespuestaCuis }
     * 
     */
    public RespuestaCuis createRespuestaCuis() {
        return new RespuestaCuis();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitudCuis }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "solicitudCuis")
    public JAXBElement<SolicitudCuis> createSolicitudCuis(SolicitudCuis value) {
        return new JAXBElement<SolicitudCuis>(_SolicitudCuis_QNAME, SolicitudCuis.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitudCuisMasivo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "solicitudCuisMasivo")
    public JAXBElement<SolicitudCuisMasivo> createSolicitudCuisMasivo(SolicitudCuisMasivo value) {
        return new JAXBElement<SolicitudCuisMasivo>(_SolicitudCuisMasivo_QNAME, SolicitudCuisMasivo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificarComunicacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "verificarComunicacion")
    public JAXBElement<VerificarComunicacion> createVerificarComunicacion(VerificarComunicacion value) {
        return new JAXBElement<VerificarComunicacion>(_VerificarComunicacion_QNAME, VerificarComunicacion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificarComunicacionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "verificarComunicacionResponse")
    public JAXBElement<VerificarComunicacionResponse> createVerificarComunicacionResponse(VerificarComunicacionResponse value) {
        return new JAXBElement<VerificarComunicacionResponse>(_VerificarComunicacionResponse_QNAME, VerificarComunicacionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitudCuisResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "solicitudCuisResponse")
    public JAXBElement<SolicitudCuisResponse> createSolicitudCuisResponse(SolicitudCuisResponse value) {
        return new JAXBElement<SolicitudCuisResponse>(_SolicitudCuisResponse_QNAME, SolicitudCuisResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitudCufd }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "solicitudCufd")
    public JAXBElement<SolicitudCufd> createSolicitudCufd(SolicitudCufd value) {
        return new JAXBElement<SolicitudCufd>(_SolicitudCufd_QNAME, SolicitudCufd.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitudCufdMasivo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "solicitudCufdMasivo")
    public JAXBElement<SolicitudCufdMasivo> createSolicitudCufdMasivo(SolicitudCufdMasivo value) {
        return new JAXBElement<SolicitudCufdMasivo>(_SolicitudCufdMasivo_QNAME, SolicitudCufdMasivo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitudCufdMasivoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "solicitudCufdMasivoResponse")
    public JAXBElement<SolicitudCufdMasivoResponse> createSolicitudCufdMasivoResponse(SolicitudCufdMasivoResponse value) {
        return new JAXBElement<SolicitudCufdMasivoResponse>(_SolicitudCufdMasivoResponse_QNAME, SolicitudCufdMasivoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificaCertificadoRevocadoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "notificaCertificadoRevocadoResponse")
    public JAXBElement<NotificaCertificadoRevocadoResponse> createNotificaCertificadoRevocadoResponse(NotificaCertificadoRevocadoResponse value) {
        return new JAXBElement<NotificaCertificadoRevocadoResponse>(_NotificaCertificadoRevocadoResponse_QNAME, NotificaCertificadoRevocadoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitudCufdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "solicitudCufdResponse")
    public JAXBElement<SolicitudCufdResponse> createSolicitudCufdResponse(SolicitudCufdResponse value) {
        return new JAXBElement<SolicitudCufdResponse>(_SolicitudCufdResponse_QNAME, SolicitudCufdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitudCuisMasivoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "solicitudCuisMasivoResponse")
    public JAXBElement<SolicitudCuisMasivoResponse> createSolicitudCuisMasivoResponse(SolicitudCuisMasivoResponse value) {
        return new JAXBElement<SolicitudCuisMasivoResponse>(_SolicitudCuisMasivoResponse_QNAME, SolicitudCuisMasivoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificaCertificadoRevocado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "notificaCertificadoRevocado")
    public JAXBElement<NotificaCertificadoRevocado> createNotificaCertificadoRevocado(NotificaCertificadoRevocado value) {
        return new JAXBElement<NotificaCertificadoRevocado>(_NotificaCertificadoRevocado_QNAME, NotificaCertificadoRevocado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "codigoPuntoVenta", scope = SolicitudOperacionesCufd.class)
    public JAXBElement<Integer> createSolicitudOperacionesCufdCodigoPuntoVenta(Integer value) {
        return new JAXBElement<Integer>(_SolicitudOperacionesCufdCodigoPuntoVenta_QNAME, Integer.class, SolicitudOperacionesCufd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fechaRevocacion", scope = SolicitudNotifcaRevocado.class)
    public JAXBElement<XMLGregorianCalendar> createSolicitudNotifcaRevocadoFechaRevocacion(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SolicitudNotifcaRevocadoFechaRevocacion_QNAME, XMLGregorianCalendar.class, SolicitudNotifcaRevocado.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "codigoPuntoVenta", scope = SolicitudOperacionesCuis.class)
    public JAXBElement<Integer> createSolicitudOperacionesCuisCodigoPuntoVenta(Integer value) {
        return new JAXBElement<Integer>(_SolicitudOperacionesCufdCodigoPuntoVenta_QNAME, Integer.class, SolicitudOperacionesCuis.class, value);
    }

}
