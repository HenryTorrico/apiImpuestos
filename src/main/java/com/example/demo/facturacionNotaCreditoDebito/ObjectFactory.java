
package com.example.demo.facturacionNotaCreditoDebito;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.demo.facturacionNotaCreditoDebito package. 
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

    private final static QName _VerificacionEstadoNotaFiscalCreditoDebitoResponse_QNAME = new QName("https://siat.impuestos.gob.bo/", "verificacionEstadoNotaFiscalCreditoDebitoResponse");
    private final static QName _VerificarComunicacion_QNAME = new QName("https://siat.impuestos.gob.bo/", "verificarComunicacion");
    private final static QName _VerificarComunicacionResponse_QNAME = new QName("https://siat.impuestos.gob.bo/", "verificarComunicacionResponse");
    private final static QName _AnulacionNotaFiscalCreditoDebito_QNAME = new QName("https://siat.impuestos.gob.bo/", "anulacionNotaFiscalCreditoDebito");
    private final static QName _ReversionAnulacionNotaFiscalCreditoDebito_QNAME = new QName("https://siat.impuestos.gob.bo/", "reversionAnulacionNotaFiscalCreditoDebito");
    private final static QName _RecepcionNotaFiscalCreditoDebito_QNAME = new QName("https://siat.impuestos.gob.bo/", "recepcionNotaFiscalCreditoDebito");
    private final static QName _RecepcionNotaFiscalCreditoDebitoResponse_QNAME = new QName("https://siat.impuestos.gob.bo/", "recepcionNotaFiscalCreditoDebitoResponse");
    private final static QName _AnulacionNotaFiscalCreditoDebitoResponse_QNAME = new QName("https://siat.impuestos.gob.bo/", "anulacionNotaFiscalCreditoDebitoResponse");
    private final static QName _VerificacionEstadoNotaFiscalCreditoDebito_QNAME = new QName("https://siat.impuestos.gob.bo/", "verificacionEstadoNotaFiscalCreditoDebito");
    private final static QName _ReversionAnulacionNotaFiscalCreditoDebitoResponse_QNAME = new QName("https://siat.impuestos.gob.bo/", "reversionAnulacionNotaFiscalCreditoDebitoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.demo.facturacionNotaCreditoDebito
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AnulacionNotaFiscalCreditoDebitoResponse }
     * 
     */
    public AnulacionNotaFiscalCreditoDebitoResponse createAnulacionNotaFiscalCreditoDebitoResponse() {
        return new AnulacionNotaFiscalCreditoDebitoResponse();
    }

    /**
     * Create an instance of {@link VerificacionEstadoNotaFiscalCreditoDebito }
     * 
     */
    public VerificacionEstadoNotaFiscalCreditoDebito createVerificacionEstadoNotaFiscalCreditoDebito() {
        return new VerificacionEstadoNotaFiscalCreditoDebito();
    }

    /**
     * Create an instance of {@link ReversionAnulacionNotaFiscalCreditoDebitoResponse }
     * 
     */
    public ReversionAnulacionNotaFiscalCreditoDebitoResponse createReversionAnulacionNotaFiscalCreditoDebitoResponse() {
        return new ReversionAnulacionNotaFiscalCreditoDebitoResponse();
    }

    /**
     * Create an instance of {@link AnulacionNotaFiscalCreditoDebito }
     * 
     */
    public AnulacionNotaFiscalCreditoDebito createAnulacionNotaFiscalCreditoDebito() {
        return new AnulacionNotaFiscalCreditoDebito();
    }

    /**
     * Create an instance of {@link ReversionAnulacionNotaFiscalCreditoDebito }
     * 
     */
    public ReversionAnulacionNotaFiscalCreditoDebito createReversionAnulacionNotaFiscalCreditoDebito() {
        return new ReversionAnulacionNotaFiscalCreditoDebito();
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
     * Create an instance of {@link VerificacionEstadoNotaFiscalCreditoDebitoResponse }
     * 
     */
    public VerificacionEstadoNotaFiscalCreditoDebitoResponse createVerificacionEstadoNotaFiscalCreditoDebitoResponse() {
        return new VerificacionEstadoNotaFiscalCreditoDebitoResponse();
    }

    /**
     * Create an instance of {@link RecepcionNotaFiscalCreditoDebito }
     * 
     */
    public RecepcionNotaFiscalCreditoDebito createRecepcionNotaFiscalCreditoDebito() {
        return new RecepcionNotaFiscalCreditoDebito();
    }

    /**
     * Create an instance of {@link RecepcionNotaFiscalCreditoDebitoResponse }
     * 
     */
    public RecepcionNotaFiscalCreditoDebitoResponse createRecepcionNotaFiscalCreditoDebitoResponse() {
        return new RecepcionNotaFiscalCreditoDebitoResponse();
    }

    /**
     * Create an instance of {@link SolicitudAnulacion }
     * 
     */
    public SolicitudAnulacion createSolicitudAnulacion() {
        return new SolicitudAnulacion();
    }

    /**
     * Create an instance of {@link MensajeRecepcion }
     * 
     */
    public MensajeRecepcion createMensajeRecepcion() {
        return new MensajeRecepcion();
    }

    /**
     * Create an instance of {@link RespuestaRecepcion }
     * 
     */
    public RespuestaRecepcion createRespuestaRecepcion() {
        return new RespuestaRecepcion();
    }

    /**
     * Create an instance of {@link RespuestaFacturacion }
     * 
     */
    public RespuestaFacturacion createRespuestaFacturacion() {
        return new RespuestaFacturacion();
    }

    /**
     * Create an instance of {@link SolicitudVerificacionEstado }
     * 
     */
    public SolicitudVerificacionEstado createSolicitudVerificacionEstado() {
        return new SolicitudVerificacionEstado();
    }

    /**
     * Create an instance of {@link MensajeServicio }
     * 
     */
    public MensajeServicio createMensajeServicio() {
        return new MensajeServicio();
    }

    /**
     * Create an instance of {@link SolicitudReversionAnulacion }
     * 
     */
    public SolicitudReversionAnulacion createSolicitudReversionAnulacion() {
        return new SolicitudReversionAnulacion();
    }

    /**
     * Create an instance of {@link SolicitudRecepcionFactura }
     * 
     */
    public SolicitudRecepcionFactura createSolicitudRecepcionFactura() {
        return new SolicitudRecepcionFactura();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificacionEstadoNotaFiscalCreditoDebitoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "verificacionEstadoNotaFiscalCreditoDebitoResponse")
    public JAXBElement<VerificacionEstadoNotaFiscalCreditoDebitoResponse> createVerificacionEstadoNotaFiscalCreditoDebitoResponse(VerificacionEstadoNotaFiscalCreditoDebitoResponse value) {
        return new JAXBElement<VerificacionEstadoNotaFiscalCreditoDebitoResponse>(_VerificacionEstadoNotaFiscalCreditoDebitoResponse_QNAME, VerificacionEstadoNotaFiscalCreditoDebitoResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link AnulacionNotaFiscalCreditoDebito }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "anulacionNotaFiscalCreditoDebito")
    public JAXBElement<AnulacionNotaFiscalCreditoDebito> createAnulacionNotaFiscalCreditoDebito(AnulacionNotaFiscalCreditoDebito value) {
        return new JAXBElement<AnulacionNotaFiscalCreditoDebito>(_AnulacionNotaFiscalCreditoDebito_QNAME, AnulacionNotaFiscalCreditoDebito.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReversionAnulacionNotaFiscalCreditoDebito }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "reversionAnulacionNotaFiscalCreditoDebito")
    public JAXBElement<ReversionAnulacionNotaFiscalCreditoDebito> createReversionAnulacionNotaFiscalCreditoDebito(ReversionAnulacionNotaFiscalCreditoDebito value) {
        return new JAXBElement<ReversionAnulacionNotaFiscalCreditoDebito>(_ReversionAnulacionNotaFiscalCreditoDebito_QNAME, ReversionAnulacionNotaFiscalCreditoDebito.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecepcionNotaFiscalCreditoDebito }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "recepcionNotaFiscalCreditoDebito")
    public JAXBElement<RecepcionNotaFiscalCreditoDebito> createRecepcionNotaFiscalCreditoDebito(RecepcionNotaFiscalCreditoDebito value) {
        return new JAXBElement<RecepcionNotaFiscalCreditoDebito>(_RecepcionNotaFiscalCreditoDebito_QNAME, RecepcionNotaFiscalCreditoDebito.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecepcionNotaFiscalCreditoDebitoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "recepcionNotaFiscalCreditoDebitoResponse")
    public JAXBElement<RecepcionNotaFiscalCreditoDebitoResponse> createRecepcionNotaFiscalCreditoDebitoResponse(RecepcionNotaFiscalCreditoDebitoResponse value) {
        return new JAXBElement<RecepcionNotaFiscalCreditoDebitoResponse>(_RecepcionNotaFiscalCreditoDebitoResponse_QNAME, RecepcionNotaFiscalCreditoDebitoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnulacionNotaFiscalCreditoDebitoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "anulacionNotaFiscalCreditoDebitoResponse")
    public JAXBElement<AnulacionNotaFiscalCreditoDebitoResponse> createAnulacionNotaFiscalCreditoDebitoResponse(AnulacionNotaFiscalCreditoDebitoResponse value) {
        return new JAXBElement<AnulacionNotaFiscalCreditoDebitoResponse>(_AnulacionNotaFiscalCreditoDebitoResponse_QNAME, AnulacionNotaFiscalCreditoDebitoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificacionEstadoNotaFiscalCreditoDebito }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "verificacionEstadoNotaFiscalCreditoDebito")
    public JAXBElement<VerificacionEstadoNotaFiscalCreditoDebito> createVerificacionEstadoNotaFiscalCreditoDebito(VerificacionEstadoNotaFiscalCreditoDebito value) {
        return new JAXBElement<VerificacionEstadoNotaFiscalCreditoDebito>(_VerificacionEstadoNotaFiscalCreditoDebito_QNAME, VerificacionEstadoNotaFiscalCreditoDebito.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReversionAnulacionNotaFiscalCreditoDebitoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "reversionAnulacionNotaFiscalCreditoDebitoResponse")
    public JAXBElement<ReversionAnulacionNotaFiscalCreditoDebitoResponse> createReversionAnulacionNotaFiscalCreditoDebitoResponse(ReversionAnulacionNotaFiscalCreditoDebitoResponse value) {
        return new JAXBElement<ReversionAnulacionNotaFiscalCreditoDebitoResponse>(_ReversionAnulacionNotaFiscalCreditoDebitoResponse_QNAME, ReversionAnulacionNotaFiscalCreditoDebitoResponse.class, null, value);
    }

}
