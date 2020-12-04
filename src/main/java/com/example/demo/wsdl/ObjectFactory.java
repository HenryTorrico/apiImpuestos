
package com.example.demo.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.demo.wsdl package. 
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

    private final static QName _Token_QNAME = new QName("https://siat.impuestos.gob.bo/", "token");
    private final static QName _TokenResponse_QNAME = new QName("https://siat.impuestos.gob.bo/", "tokenResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.demo.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TokenResponse }
     * 
     */
    public TokenResponse createTokenResponse() {
        return new TokenResponse();
    }

    /**
     * Create an instance of {@link Token }
     * 
     */
    public Token createToken() {
        return new Token();
    }

    /**
     * Create an instance of {@link DatosUsuarioRequest }
     * 
     */
    public DatosUsuarioRequest createDatosUsuarioRequest() {
        return new DatosUsuarioRequest();
    }

    /**
     * Create an instance of {@link StrMensajeAplicacionDto }
     * 
     */
    public StrMensajeAplicacionDto createStrMensajeAplicacionDto() {
        return new StrMensajeAplicacionDto();
    }

    /**
     * Create an instance of {@link UsuarioAutenticadoDto }
     * 
     */
    public UsuarioAutenticadoDto createUsuarioAutenticadoDto() {
        return new UsuarioAutenticadoDto();
    }

    /**
     * Create an instance of {@link ListaMensajesAplicacion }
     * 
     */
    public ListaMensajesAplicacion createListaMensajesAplicacion() {
        return new ListaMensajesAplicacion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Token }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "token")
    public JAXBElement<Token> createToken(Token value) {
        return new JAXBElement<Token>(_Token_QNAME, Token.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://siat.impuestos.gob.bo/", name = "tokenResponse")
    public JAXBElement<TokenResponse> createTokenResponse(TokenResponse value) {
        return new JAXBElement<TokenResponse>(_TokenResponse_QNAME, TokenResponse.class, null, value);
    }

}
