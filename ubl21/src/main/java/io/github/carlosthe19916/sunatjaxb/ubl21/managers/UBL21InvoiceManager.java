package io.github.carlosthe19916.sunatjaxb.ubl21.managers;

import com.helger.ubl21.UBL21Writer;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.serialize.MicroWriter;
import com.helger.xml.namespace.MapBasedNamespaceContext;
import com.helger.xml.serialize.write.XMLWriterSettings;
import io.github.carlosthe19916.sunatjaxb.core.mappers.DefaultMapperComparator;
import io.github.carlosthe19916.sunatjaxb.core.utils.BeanUtils;
import io.github.carlosthe19916.sunatjaxb.ubl21.UBL21GlobalConfig;
import io.github.carlosthe19916.sunatjaxb.ubl21.beans.Invoice21Bean;
import io.github.carlosthe19916.sunatjaxb.ubl21.mappers.Invoice21Mapper;
import io.github.carlosthe19916.sunatjaxb.ubl21.mappers.MappersManager;
import io.github.carlosthe19916.sunatjaxb.ubl21.utils.Bean21ToType;
import oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType;
import org.w3c.dom.Document;

import javax.validation.ConstraintViolation;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UBL21InvoiceManager {

    public Set<ConstraintViolation<Invoice21Bean>> validate(Invoice21Bean invoice) {
        return BeanUtils.validate(invoice);
    }

    public Invoice21Bean map(Invoice21Bean invoice, Invoice21Mapper... mappers) {
        UBL21GlobalConfig ubl21GlobalConfig = UBL21GlobalConfig.getInstance();
        MappersManager mappersManager = MappersManager.getInstance();

        List<Invoice21Mapper> mappersToApply = Stream.concat(
                mappersManager.getInvoiceMappers().stream(),
                Stream.of(mappers)
        )
                .filter(p -> p.isInternal() == ubl21GlobalConfig.isDefaultMappersActive())
                .sorted(new DefaultMapperComparator())
                .collect(Collectors.toList());
        for (Invoice21Mapper mapper : mappersToApply) {
            invoice = mapper.map(invoice);
        }

        return invoice;
    }

    public InvoiceType toInvoiceType(Invoice21Bean invoice) {
        return Bean21ToType.toInvoiceType(invoice);
    }

    public Document writeToDocument(InvoiceType invoiceType) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        writeToStream(invoiceType, os);
        return UBL21DocumentManager.byteToDocument(os.toByteArray());
    }

    public void writeToStream(InvoiceType invoiceType, OutputStream os) {
        MapBasedNamespaceContext defaultNamespace = UBL21DocumentManager.getBasedNamespaceContext("urn:oasis:names:specification:ubl21:schema:xsd:Invoice-2");
        IMicroDocument iMicroDocument = UBL21Writer.invoice().getAsMicroDocument(invoiceType);
        if (iMicroDocument == null) {
            throw new IllegalStateException("Invalid Jaxb");
        }
        XMLWriterSettings xmlWriterSettings = new XMLWriterSettings().setNamespaceContext(defaultNamespace).setPutNamespaceContextPrefixesInRoot(true);
        MicroWriter.writeToStream(iMicroDocument, os, xmlWriterSettings);
    }

}
