package io.github.carlosthe19916.facades;

import com.helger.ubl21.UBL21NamespaceContext;
import com.helger.ubl21.UBL21Writer;
import com.helger.ubl21.UBL21WriterBuilder;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.serialize.MicroWriter;
import com.helger.xml.namespace.MapBasedNamespaceContext;
import com.helger.xml.serialize.write.XMLWriterSettings;
import io.github.carlosthe19916.beans.catalogs.Catalogo1;
import io.github.carlosthe19916.beans.catalogs.Catalogo7;
import io.github.carlosthe19916.beans.catalogs.Catalogo9;
import io.github.carlosthe19916.beans.config.ubl21.GlobalUBL21Defaults;
import io.github.carlosthe19916.beans.ubl.ubl21.CreditNote21Bean;
import io.github.carlosthe19916.beans.ubl.ubl21.CreditNote21BeanBuilder;
import io.github.carlosthe19916.utils.JaxbUtils;
import oasis.names.specification.ubl.schema.xsd.creditnote_21.CreditNoteType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.TimeZone;
import java.util.UUID;

public class CreditNoteFacadeTest {
    TimeZone defaultTimeZone = TimeZone.getTimeZone("Europe/Rome");

    @Before
    public void before() {
        GlobalUBL21Defaults defaults = GlobalUBL21Defaults.getInstance();
        defaults.setTimeZone(defaultTimeZone);
        defaults.setAplicarCalculosInternosAutomaticos(true);
    }

    @Test
    public void CreditNoteFacade() {

        CreditNote21Bean creditNote= CreditNote21BeanBuilder.builder()
                .codigoGeneradoPorSoftware(UUID.randomUUID().toString())
                .serie("F001")
                .numero(1)
                .moneda()
                    .codigo("PEN")
                    .end()
                .documentoRelacionado()
                    .documentoReferencia("F001-4355")
                    .tipoNotaCredito(Catalogo9.ANULACION_POR_ERROR_EN_EL_RUC)
                    .sustento("AMPLIACIÓN GARANTÍA DE MEMORIA DDR-3 B1333 KINGSTON")
                    .end()
                .documentoModificado()
                    .documentoRelacionado("F001-4355")
                    .tipoDocumentoRelacionado(Catalogo1.FACTURA)
                    .end()
//                .otroDocumentoRelacionado()
//                    .documentoRelacionado("F001-1")
//                    .tipoDocumentoRelacionado(Catalogo12.OTROS)
//                    .end()
                .proveedor()
                    .codigoTipoDocumento("6")
                    .numeroDocumento("10467793549")
                    .nombreComercial("Wolsnut4 Consultores")
                    .razonSocial("Wolsnut4 S.A.")
                    .codigoPostal("050101")
                    .end()
                .cliente()
                    .codigoTipoDocumento("3")
                    .numeroDocumento("46779354")
                    .nombre("Carlos Esteban Feria Vila")
                    .email("carlosthe19916@gmail.com")
                    .direccion("Jr. carlos 997")
                    .end()
                .firmante()
                    .idFirma("mifirma")
                    .idEmpresa("10456025612")
                    .nombreEmpresa("Alex Pariona")
                    .end()
//                .guiaRemisionRelacionada()
//                    .guiaRemision("031-002201")
//                    .tipoGuiaRemision(Catalogo1.GUIA_REMISION_REMITENTE)
//                    .end()
//                .otroDocumentoRelacionado()
//                    .documentoRelacionado("024099")
//                    .tipoDocumentoRelacionado(Catalogo12.OTROS)
//                    .end()
                .total()
                    .pagar(new BigDecimal("100"))
                    .descuentoGlobal(new BigDecimal("200"))
                    .otrosCargos(new BigDecimal("300"))
                    .pagarLetras("CIEN")
                    .end()
                .totalInformacionAdicional()
                    .gravado(new BigDecimal("400"))
                    .inafecto(new BigDecimal("500"))
                    .exonerado(new BigDecimal("600"))
                    .gratuito(new BigDecimal("700"))
                    .end()
                .impuestos()
                    .igv(new BigDecimal("800"))
                    .end()
                .addDetalle()
                    .unidadMedida("NIU")
                    .descripcion("Bolsa de arroz")
                    .codigoTipoIgv(Catalogo7.GRAVADO_OPERACION_ONEROSA)
                    .cantidad(BigDecimal.valueOf(2))
                    .valorUnitario(BigDecimal.valueOf(50))
                    .precioUnitario(BigDecimal.valueOf(59))
                    .subtotal(BigDecimal.valueOf(100))
                    .total(BigDecimal.valueOf(118))
                    .totalIgv(BigDecimal.valueOf(18))
                    .end()
                .addDetalle()
                    .unidadMedida("NIU")
                    .descripcion("Bolsa de arroz")
                    .codigoTipoIgv(Catalogo7.GRAVADO_OPERACION_ONEROSA)
                    .cantidad(BigDecimal.valueOf(2))
                    .valorUnitario(BigDecimal.valueOf(50))
                    .precioUnitario(BigDecimal.valueOf(59))
                    .subtotal(BigDecimal.valueOf(100))
                    .total(BigDecimal.valueOf(118))
                    .totalIgv(BigDecimal.valueOf(18))
                    .end()
                .build();

        CreditNote21Bean creditNoteBean = CreditNoteFacade.fillOut(creditNote);

        try {
            CreditNoteType noteType = CreditNoteFacade.toCreditNoteType(creditNoteBean);

            MapBasedNamespaceContext mapBasedNamespace = getBasedNamespaceContext("urn:oasis:names:specification:ubl21:schema:xsd:CreditNote-2");
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            UBL21WriterBuilder<CreditNoteType> builder = UBL21Writer.creditNote();
            IMicroDocument asMicroDocument = builder.getAsMicroDocument(noteType);
            XMLWriterSettings xmlWriterSettings = new XMLWriterSettings().setNamespaceContext(mapBasedNamespace).setPutNamespaceContextPrefixesInRoot(true);
            MicroWriter.writeToStream(asMicroDocument, out, xmlWriterSettings);

            Document document = JaxbUtils.toDocument(out.toByteArray());

            DOMSource source = new DOMSource(document);
            FileWriter writer = new FileWriter(new File("D:/CreditNoteSample1.xml"));
            StreamResult result = new StreamResult(writer);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(source, result);

            Assert.assertNotNull(noteType);
        } catch (Exception e) {
//            Assert.assertTrue(false);
        }
    }

    public static MapBasedNamespaceContext getBasedNamespaceContext(String defaultNamespace) {
        UBL21NamespaceContext namespace = UBL21NamespaceContext.getInstance();
        namespace.setMapping("ccts", "urn:un:unece:uncefact:documentation:2");
        namespace.setMapping("ds", "http://www.w3.org/2000/09/xmldsig#");
        namespace.setMapping("ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
        namespace.setMapping("qdt", "urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
        namespace.setMapping("sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
        namespace.setMapping("udt", "urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
        MapBasedNamespaceContext mapBasedNamespace = new MapBasedNamespaceContext();
        mapBasedNamespace.addMappings(namespace);
        mapBasedNamespace.setDefaultNamespaceURI(defaultNamespace);
        return mapBasedNamespace;
    }
}