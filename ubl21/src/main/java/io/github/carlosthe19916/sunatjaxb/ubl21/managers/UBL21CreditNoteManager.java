package io.github.carlosthe19916.sunatjaxb.ubl21.managers;

import com.helger.ubl21.UBL21Writer;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.serialize.MicroWriter;
import com.helger.xml.namespace.MapBasedNamespaceContext;
import com.helger.xml.serialize.write.XMLWriterSettings;
import io.github.carlosthe19916.sunatjaxb.core.mappers.DefaultMapperComparator;
import io.github.carlosthe19916.sunatjaxb.core.utils.BeanUtils;
import io.github.carlosthe19916.sunatjaxb.ubl21.UBL21GlobalConfig;
import io.github.carlosthe19916.sunatjaxb.ubl21.beans.CreditNote21Bean;
import io.github.carlosthe19916.sunatjaxb.ubl21.mappers.CreditNote21Mapper;
import io.github.carlosthe19916.sunatjaxb.ubl21.mappers.MappersManager;
import io.github.carlosthe19916.sunatjaxb.ubl21.utils.Bean21ToType;
import oasis.names.specification.ubl.schema.xsd.creditnote_21.CreditNoteType;
import org.w3c.dom.Document;

import javax.validation.ConstraintViolation;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UBL21CreditNoteManager {

    public Set<ConstraintViolation<CreditNote21Bean>> validate(CreditNote21Bean creditNote) {
        return BeanUtils.validate(creditNote);
    }

    public CreditNote21Bean map(CreditNote21Bean creditNote, CreditNote21Mapper... mappers) {
        UBL21GlobalConfig ubl21GlobalConfig = UBL21GlobalConfig.getInstance();
        MappersManager mappersManager = MappersManager.getInstance();

        List<CreditNote21Mapper> mappersToApply = Stream.concat(
                mappersManager.getCreditNoteMappers().stream(),
                Stream.of(mappers)
        )
                .filter(p -> p.isInternal() == ubl21GlobalConfig.isDefaultMappersActive())
                .sorted(new DefaultMapperComparator())
                .collect(Collectors.toList());
        for (CreditNote21Mapper mapper : mappersToApply) {
            creditNote = mapper.map(creditNote);
        }

        return creditNote;
    }

    public CreditNoteType toCreditNoteType(CreditNote21Bean creditNote) {
        return Bean21ToType.toCreditNoteType(creditNote);
    }

    public Document writeToDocument(CreditNoteType creditNoteType) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        writeToStream(creditNoteType, os);
        return UBL21DocumentManager.byteToDocument(os.toByteArray());
    }

    public void writeToStream(CreditNoteType creditNoteType, OutputStream os) {
        MapBasedNamespaceContext defaultNamespace = UBL21DocumentManager.getBasedNamespaceContext("urn:oasis:names:specification:ubl21:schema:xsd:CreditNote-2");
        IMicroDocument iMicroDocument = UBL21Writer.creditNote().getAsMicroDocument(creditNoteType);
        if (iMicroDocument == null) {
            throw new IllegalStateException("Invalid Jaxb");
        }
        XMLWriterSettings xmlWriterSettings = new XMLWriterSettings().setNamespaceContext(defaultNamespace).setPutNamespaceContextPrefixesInRoot(true);
        MicroWriter.writeToStream(iMicroDocument, os, xmlWriterSettings);
    }
    
}
