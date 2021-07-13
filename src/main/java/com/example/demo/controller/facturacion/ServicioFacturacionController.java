package com.example.demo.controller.facturacion;


import com.example.demo.configurate.LocalDateConverter;
import com.example.demo.controller.facturacionOperaciones.FacturacionOperacionesController;
import com.example.demo.facturacion.*;
import com.example.demo.service.CufdService;
import com.example.demo.service.CuisService;
import com.example.demo.service.TokenService;
import com.example.demo.xml.Cabecera;
import com.example.demo.xml.Detalle;
import com.example.demo.xml.DocumentoFiscalDTO;
import com.thoughtworks.xstream.XStream;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.GZIPOutputStream;


@RestController
@RequestMapping(value = "/servicioFacturacion", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ServicioFacturacionController  {

    @Autowired
    TokenService tokenService;

    @Autowired
    CuisService cuisService;

    @Autowired
    CufdService cufdService;

    @Autowired
    FacturacionOperacionesController facturacionOperacionesController;

    public static String algoritmoHash(byte[] pArchivo, String algorithm) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(pArchivo);
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
        }
        catch (Exception e) {
            System.out.println("Error generando Hash");
        }
        return hashValue;
    }

    public static String obtenerSHA2(byte[] archivo) {

        String vSha2 = algoritmoHash(archivo,"SHA-256");

        return vSha2;

    }
    private static byte[] encodeBase64(String valor) {
        try {
            return Base64.getEncoder().encode(valor.getBytes("UTF-8"));
        } catch (Exception e) {
            System.out.println("mensaje " + e.getMessage());
        }
        return null;
    }

    private static byte[] compress(byte[] content) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(content);
            gzipOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static byte[] compressToEncodeBase64(byte[] compressed) {
        // Convert to base64
        return Base64.getEncoder().encode(compressed);
    }
    public static LinkedList area51(String xml) throws IOException {
        /*//4) Obtener el arreglo de bytes Base64 del archivo generado
        byte[] encodeBase64 = encodeBase64(xml);
        //5)Comprimir el arreglo Base64 en formato Gzip
        byte[] compress = compress(encodeBase64);
        //6) Obtener el arreglo Base64 del comprimido Gzip (Cadena que se envía en la etiqueta archivo)*/
        byte[] base64Compress = compress(xml);

        //7) Obtener el HASH del arreglo (Se envía en la etiqueta hashArchivo)
        //String hash = obtenerSHA2(base64Compress);
        String hash =  obtenerSHA2(base64Compress);
        System.out.println("hash:"+hash);
        LinkedList hashYArchivo=new LinkedList();
        hashYArchivo.add(hash);
        hashYArchivo.add(base64Compress);
        System.out.println("entro");
        return hashYArchivo;
    }

    public String convert(DocumentoFiscalDTO documentoFiscalDTO) throws Exception{
        LocalDateConverter conv = new LocalDateConverter();
        XStream xstream = new XStream();
        xstream.registerConverter(conv);
        String dataXml = xstream.toXML(documentoFiscalDTO);
        return dataXml;
    }
    /*Answer ans1=new Answer(101,"java is a programming language","ravi");
    Answer ans2=new Answer(102,"java is a platform","john");

    ArrayList<Answer> list=new ArrayList<Answer>();
    list.add(ans1);
    list.add(ans2);

    Question que=new Question(1,"What is java?",list);
    marshallerObj.marshal(que, new FileOutputStream("question.xml"));  */
    public static byte[] compress(String data) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length());
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data.getBytes());
        gzip.close();
        byte[] compressed = bos.toByteArray();
        bos.close();
        return compressed;
    }

    @RequestMapping(value="/recepcionFactura", method=RequestMethod.POST)
    public String recepcionFactura(@RequestBody Map<String, Object> data) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(now.toString(), formatter);
        System.out.println("hola");
        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTime.toString());
        System.out.println(xmlGregorianCalendar.toString());
        DocumentoFiscalDTO documentoFiscalDTO=new DocumentoFiscalDTO();
        Cabecera cabecera=new Cabecera();
        List<Detalle> listaDetalle=((List<Detalle>) data.get("detalle"));
        cabecera.setNitEmisor(((Number) data.get("nitEmisor")).longValue());
        cabecera.setRazonSocialEmisor((String) data.get("razonSocialEmisor"));
        cabecera.setTelefono((String) data.get("telefono"));
        cabecera.setNumeroFactura(((Number) data.get("numeroFactura")).longValue());
        cabecera.setCodigoSucursal(((Number) data.get("codigoSucursal")).longValue());
        cabecera.setDireccionsucursal((String) data.get("direccionsucursal"));
        cabecera.setCodigoPuntoVenta(((Number) data.get("codigoPuntoVenta")).longValue());
        cabecera.setFechaEmision(now);
        cabecera.setNombreRazonSocial((String) data.get("nombreRazonSocial"));
        cabecera.setCodigoTipoDocumentoIdentidad(((Number) data.get("codigoTipoDocumentoIdentidad")).longValue());
        cabecera.setNumeroDocumento((String) data.get("numeroDocumento"));
        cabecera.setComplemento((String) data.get("complemento"));
        cabecera.setCodigoCliente((String) data.get("codigoCliente"));
        cabecera.setCodigoMetodoPago(((Number) data.get("codigoMetodoPago")).longValue());
        cabecera.setNumeroTarjeta(((Number) data.get("numeroTarjeta")).longValue());
        cabecera.setMontoTotal(BigDecimal.valueOf((Double)  data.get("montoTotal")));
        cabecera.setMontoTotalSujetoIva(BigDecimal.valueOf((Double)  data.get("montoTotalSujetoIva")));
        cabecera.setCodigoMoneda(((Number) data.get("codigoMoneda")).longValue());
        cabecera.setTipoCambio(BigDecimal.valueOf((Double)   data.get("tipoCambio")));
        cabecera.setMontoTotalMoneda(BigDecimal.valueOf((Double)  data.get("montoTotalMoneda")));
        cabecera.setUsuario((String) data.get("usuario"));
        cabecera.setCodigoDocumentoSector(((Number) data.get("codigoDocumentoSector")).longValue());
        documentoFiscalDTO.setCabecera(cabecera);
        documentoFiscalDTO.setDetalle(listaDetalle);
        ServicioFacturacion recepcionFactura = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudRecepcionFactura solicitudRecepcionFactura=new SolicitudRecepcionFactura();
        solicitudRecepcionFactura.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudRecepcionFactura.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudRecepcionFactura.setNit(((Number) data.get("nit")).longValue());
        solicitudRecepcionFactura.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudRecepcionFactura.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudRecepcionFactura.setCodigoPuntoVenta((Integer)data.get("codigoPuntoVenta"));
        String archivo = convert(documentoFiscalDTO);
        System.out.println("archivo");
        List files=area51(archivo);
        System.out.println("doc");
        solicitudRecepcionFactura.setArchivo((byte[]) files.get(1));
        solicitudRecepcionFactura.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudRecepcionFactura.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudRecepcionFactura.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
       /* DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

*/
        solicitudRecepcionFactura.setFechaEnvio(xmlGregorianCalendar);
        solicitudRecepcionFactura.setHashArchivo(files.get(0).toString());
        solicitudRecepcionFactura.setCufd((String) data.get("cufd"));
        solicitudRecepcionFactura.setCuis((String) data.get("cuis"));
        Client client = ClientProxy.getClient(recepcionFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=recepcionFactura.recepcionFactura(solicitudRecepcionFactura);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return respuestaRecepcion.getCodigoRecepcion();
    }

    private ServicioFacturacion getServicioFacturacion() {
        ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServicioFacturacion.class);
        factory.setAddress("https://pilotosiatservicios.impuestos.gob.bo/v1/ServicioFacturacionComputarizada?wsdl");
        return (ServicioFacturacion) factory.create();
    }


    @RequestMapping(value="/anulacionFactura", method=RequestMethod.POST)
    public String anulacionFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {
        ServicioFacturacion anulacionFactura = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudAnulacion solicitudAnulacion=new SolicitudAnulacion();
        solicitudAnulacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudAnulacion.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudAnulacion.setNit(((Number) data.get("nit")).longValue());
        solicitudAnulacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudAnulacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudAnulacion.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudAnulacion.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudAnulacion.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudAnulacion.setCodigoMotivo((Integer) data.get("codigoMotivo"));
        solicitudAnulacion.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudAnulacion.setCuis(cuisService.findCuis().get(0).getCuis());
        String nit=(String) data.get("nit");
        String fechaHora=(String) data.get("fechaHora");
        String sucursal=(String) data.get("codigoSucursal");
        String modalidad=(String) data.get("codigoModalidad");
        String tipoEmision=(String) data.get("tipoEmision");
        String tipoFactura=(String) data.get("tipoFactura");
        String tipoDocSector=(String) data.get("tipoDocSector");
        String numeroFactura=(String) data.get("numeroFactura");
        String puntoVenta=(String) data.get("codigoPuntoVenta");
        solicitudAnulacion.setCuf(facturacionOperacionesController.getCuf(nit,fechaHora,sucursal,modalidad,tipoEmision,tipoFactura,tipoDocSector,numeroFactura,puntoVenta));

        Client client = ClientProxy.getClient(anulacionFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=anulacionFactura.anulacionFactura(solicitudAnulacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return respuestaRecepcion.getCodigoRecepcion();
    }
    @RequestMapping(value="/recepcionMasivaFacturas", method=RequestMethod.POST)
    public String recepcionMasivaFacturas(@RequestBody Map<String, Object> data) throws IOException, JSONException, DatatypeConfigurationException, ParseException {
        ServicioFacturacion recepcionMasivaFactura = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudRecepcionMasiva solicitudRecepcionMasiva=new SolicitudRecepcionMasiva();
        solicitudRecepcionMasiva.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudRecepcionMasiva.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudRecepcionMasiva.setNit(((Number) data.get("nit")).longValue());
        solicitudRecepcionMasiva.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudRecepcionMasiva.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudRecepcionMasiva.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudRecepcionMasiva.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudRecepcionMasiva.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudRecepcionMasiva.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudRecepcionMasiva.setCuis(cuisService.findCuis().get(0).getCuis());
        solicitudRecepcionMasiva.setCantidadFacturas((Integer) data.get("cantidadFacturas"));
        String archivo = (String) data.get("archivo");
        List files=area51(archivo);
        solicitudRecepcionMasiva.setArchivo((byte[]) files.get(1));
        solicitudRecepcionMasiva.setHashArchivo(files.get(0).toString());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = format.parse((String) data.get("fechaHoraFinEvento"));
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        solicitudRecepcionMasiva.setFechaEnvio(xmlGregCal);
        Client client = ClientProxy.getClient(recepcionMasivaFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=recepcionMasivaFactura.recepcionMasivaFactura(solicitudRecepcionMasiva);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaRecepcion.getCodigoRecepcion();
    }
    @RequestMapping(value="/recepcionPaqueteFacturas", method=RequestMethod.POST)
    public String recepcionPaqueteFacturas(@RequestBody Map<String, Object> data) throws IOException, JSONException, DatatypeConfigurationException, ParseException {

        ServicioFacturacion recepcionPaqueteFacturas = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudRecepcionPaquete solicitudRecepcionPaquete=new SolicitudRecepcionPaquete();
        solicitudRecepcionPaquete.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudRecepcionPaquete.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudRecepcionPaquete.setNit(((Number) data.get("nit")).longValue());
        solicitudRecepcionPaquete.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudRecepcionPaquete.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudRecepcionPaquete.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudRecepcionPaquete.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudRecepcionPaquete.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudRecepcionPaquete.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudRecepcionPaquete.setCuis(cuisService.findCuis().get(0).getCuis());
        solicitudRecepcionPaquete.setCantidadFacturas((Integer) data.get("cantidadFacturas"));
        String archivo = (String) data.get("archivo");
        List files=area51(archivo);
        solicitudRecepcionPaquete.setArchivo((byte[]) files.get(1));
        solicitudRecepcionPaquete.setHashArchivo(files.get(0).toString());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = format.parse((String) data.get("fechaHoraFinEvento"));
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        solicitudRecepcionPaquete.setFechaEnvio(xmlGregCal);
        solicitudRecepcionPaquete.setCodigoEvento((long) data.get("codigoEvento"));
        Client client = ClientProxy.getClient(recepcionPaqueteFacturas);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=recepcionPaqueteFacturas.recepcionPaqueteFactura(solicitudRecepcionPaquete);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaRecepcion.getCodigoRecepcion();
    }
    @RequestMapping(value="/reversionAnulacionFactura", method=RequestMethod.POST)
    public String reversionAnulacionFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacion reversionAnulacionFactura = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudReversionAnulacion solicitudReversionAnulacion=new SolicitudReversionAnulacion();
        solicitudReversionAnulacion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudReversionAnulacion.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudReversionAnulacion.setNit(((Number) data.get("nit")).longValue());
        solicitudReversionAnulacion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudReversionAnulacion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudReversionAnulacion.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudReversionAnulacion.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudReversionAnulacion.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudReversionAnulacion.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudReversionAnulacion.setCuis(cuisService.findCuis().get(0).getCuis());
        String nit=(String) data.get("nit");
        String fechaHora=(String) data.get("fechaHora");
        String sucursal=(String) data.get("codigoSucursal");
        String modalidad=(String) data.get("codigoModalidad");
        String tipoEmision=(String) data.get("tipoEmision");
        String tipoFactura=(String) data.get("tipoFactura");
        String tipoDocSector=(String) data.get("tipoDocSector");
        String numeroFactura=(String) data.get("numeroFactura");
        String puntoVenta=(String) data.get("codigoPuntoVenta");
        solicitudReversionAnulacion.setCuf(facturacionOperacionesController.getCuf(nit,fechaHora,sucursal,modalidad,tipoEmision,tipoFactura,tipoDocSector,numeroFactura,puntoVenta));
        Client client = ClientProxy.getClient(reversionAnulacionFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=reversionAnulacionFactura.reversionAnulacionFactura(solicitudReversionAnulacion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaRecepcion.getCodigoRecepcion();
    }
    @RequestMapping(value="/validacionRecepcionMasivaFactura", method=RequestMethod.POST)
    public String validacionRecepcionMasivaFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacion validacionRecepcionMasivaFactura = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudValidacionRecepcion solicitudValidacionRecepcion=new SolicitudValidacionRecepcion();
        solicitudValidacionRecepcion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudValidacionRecepcion.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudValidacionRecepcion.setNit(((Number) data.get("nit")).longValue());
        solicitudValidacionRecepcion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudValidacionRecepcion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudValidacionRecepcion.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudValidacionRecepcion.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudValidacionRecepcion.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudValidacionRecepcion.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudValidacionRecepcion.setCuis(cuisService.findCuis().get(0).getCuis());
        solicitudValidacionRecepcion.setCodigoRecepcion((String) data.get("codigoRecepcion"));
        Client client = ClientProxy.getClient(validacionRecepcionMasivaFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=validacionRecepcionMasivaFactura.validacionRecepcionMasivaFactura(solicitudValidacionRecepcion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaRecepcion.getCodigoRecepcion();
    }
    @RequestMapping(value="/validacionRecepcionPaqueteFactura", method=RequestMethod.POST)
    public String validacionRecepcionPaqueteFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacion validacionRecepcionPaqueteFactura = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudValidacionRecepcion solicitudValidacionRecepcion=new SolicitudValidacionRecepcion();
        solicitudValidacionRecepcion.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudValidacionRecepcion.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudValidacionRecepcion.setNit(((Number) data.get("nit")).longValue());
        solicitudValidacionRecepcion.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudValidacionRecepcion.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudValidacionRecepcion.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudValidacionRecepcion.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudValidacionRecepcion.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudValidacionRecepcion.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudValidacionRecepcion.setCuis(cuisService.findCuis().get(0).getCuis());
        solicitudValidacionRecepcion.setCodigoRecepcion((String) data.get("codigoRecepcion"));
        Client client = ClientProxy.getClient(validacionRecepcionPaqueteFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=validacionRecepcionPaqueteFactura.validacionRecepcionPaqueteFactura(solicitudValidacionRecepcion);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView("index.html");
        return respuestaRecepcion.getCodigoRecepcion();
    }
    @RequestMapping(value="/verificacionEstadoFactura", method=RequestMethod.POST)
    public String verificacionEstadoFactura(@RequestBody Map<String, Object> data) throws IOException, JSONException {

        ServicioFacturacion verificacionEstadoFactura = getServicioFacturacion();
        RespuestaRecepcion respuestaRecepcion=new RespuestaRecepcion();
        SolicitudVerificacionEstado solicitudVerificacionEstado=new SolicitudVerificacionEstado();
        solicitudVerificacionEstado.setCodigoAmbiente((Integer) data.get("codigoAmbiente"));
        solicitudVerificacionEstado.setCodigoModalidad((Integer) data.get("codigoModalidad"));
        solicitudVerificacionEstado.setNit(((Number) data.get("nit")).longValue());
        solicitudVerificacionEstado.setCodigoSistema((String) data.get("codigoSistema"));
        solicitudVerificacionEstado.setCodigoSucursal((Integer) data.get("codigoSucursal"));
        solicitudVerificacionEstado.setCodigoDocumentoSector((Integer) data.get("codigoDocumentoSector"));
        solicitudVerificacionEstado.setCodigoEmision((Integer) data.get("codigoEmision"));
        solicitudVerificacionEstado.setTipoFacturaDocumento((Integer) data.get("tipoFacturaDocumento"));
        solicitudVerificacionEstado.setCufd(cufdService.findCufd().get(0).getCufd());
        solicitudVerificacionEstado.setCuis(cuisService.findCuis().get(0).getCuis());
        String nit=(String) data.get("nit");
        String fechaHora=(String) data.get("fechaHora");
        String sucursal=(String) data.get("codigoSucursal");
        String modalidad=(String) data.get("codigoModalidad");
        String tipoEmision=(String) data.get("tipoEmision");
        String tipoFactura=(String) data.get("tipoFactura");
        String tipoDocSector=(String) data.get("tipoDocSector");
        String numeroFactura=(String) data.get("numeroFactura");
        String puntoVenta=(String) data.get("codigoPuntoVenta");
        solicitudVerificacionEstado.setCuf(facturacionOperacionesController.getCuf(nit,fechaHora,sucursal,modalidad,tipoEmision,tipoFactura,tipoDocSector,numeroFactura,puntoVenta));
        Client client = ClientProxy.getClient(verificacionEstadoFactura);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            respuestaRecepcion=verificacionEstadoFactura.verificacionEstadoFactura(solicitudVerificacionEstado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return respuestaRecepcion.getCodigoRecepcion();
    }
    @RequestMapping(value="/verificarComunicacion", method=RequestMethod.POST)
    public int verificarComunicacion() throws IOException, JSONException {
        int valor=0;
        ServicioFacturacion verificarComunicacion = getServicioFacturacion();
        Client client = ClientProxy.getClient(verificarComunicacion);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Authorization", Arrays.asList("Token " + tokenService.findToken().get(0).getTokenUsuario()));
        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        client.getInInterceptors().add(new LoggingInInterceptor());
        try {
            valor= verificarComunicacion.verificarComunicacion();
            System.out.println("verificacion"+valor);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return valor;
    }
}
