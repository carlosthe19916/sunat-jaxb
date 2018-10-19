package io.github.carlosthe19916.sunatjaxb.ubl21.managers;

import com.helger.ubl21.UBL21Writer;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.serialize.MicroWriter;
import com.helger.xml.namespace.MapBasedNamespaceContext;
import com.helger.xml.serialize.write.XMLWriterSettings;
import io.github.carlosthe19916.sunatjaxb.core.mappers.DefaultMapperComparator;
import io.github.carlosthe19916.sunatjaxb.core.utils.BeanUtils;
import io.github.carlosthe19916.sunatjaxb.ubl21.UBL21GlobalConfig;
import io.github.carlosthe19916.sunatjaxb.ubl21.beans.DebitNote21Bean;
import io.github.carlosthe19916.sunatjaxb.ubl21.mappers.DebitNote21Mapper;
import io.github.carlosthe19916.sunatjaxb.ubl21.mappers.MappersManager;
import io.github.carlosthe19916.sunatjaxb.ubl21.utils.Bean21ToType;
import oasis.names.specification.ubl.schema.xsd.debitnote_21.DebitNoteType;
import org.w3c.dom.Document;

import javax.validation.ConstraintViolation;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UBL21DebitNoteManager {

    public Set<ConstraintViolation<DebitNote21Bean>> validate(DebitNote21Bean debitNote) {
        return BeanUtils.validate(debitNote);
    }

    public DebitNote21Bean map(DebitNote21Bean debitNote, DebitNote21Mapper... mappers) {
        UBL21GlobalConfig ubl21GlobalConfig = UBL21GlobalConfig.getInstance();
        MappersManager mappersManager = MappersManager.getInstance();

        List<DebitNote21Mapper> mappersToApply = Stream.concat(
                mappersManager.getDebitNoteMappers().stream(),
                Stream.of(mappers)
        )
                .filter(p -> p.isInternal() == ubl21GlobalConfig.isDefaultMappersActive())
                .sorted(new DefaultMapperComparator())
                .collect(Collectors.toList());
        for (DebitNote21Mapper mapper : mappersToApply) {
            debitNote = mapper.map(debitNote);
        }

        return debitNote;
    }

    public DebitNoteType toDebitNoteType(DebitNote21Bean debitNote) {
        return Bean21ToType.toDebitNoteType(debitNote);
    }

    public Document writeToDocument(DebitNoteType debitNoteType) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        writeToStream(debitNoteType, os);
        return UBL21DocumentManager.byteToDocument(os.toByteArray());
    }

    public void writeToStream(DebitNoteType debitNoteType, OutputStream os) {
        MapBasedNamespaceContext defaultNamespace = UBL21DocumentManager.getBasedNamespaceContext("urn:oasis:names:specification:ubl21:schema:xsd:DebitNote-2");
        IMicroDocument iMicroDocument = UBL21Writer.debitNote().getAsMicroDocument(debitNoteType);
        if (iMicroDocument == null) {
            throw new IllegalStateException("Invalid Jaxb");
        }
        XMLWriterSettings xmlWriterSettings = new XMLWriterSettings().setNamespaceContext(defaultNamespace).setPutNamespaceContextPrefixesInRoot(true);
        MicroWriter.writeToStream(iMicroDocument, os, xmlWriterSettings);
    }

}
