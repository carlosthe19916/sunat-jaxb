package sunat.names.specification.ubl.peru.schema.xsd.retention_1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * Created by lxpary on 27/12/16.
 */
@XmlRegistry
public class RetentionFactory {
    private final static QName _Retention_QNAME = new QName(
            "urn:sunat:names:specification:ubl:peru:schema:xsd:Retention-1", "Retention");

    public RetentionFactory() {
    }

    @XmlElementDecl(namespace = "urn:sunat:names:specification:ubl:peru:schema:xsd:Retention-1", name = "Retention")
    public JAXBElement<RetentionType> createRetention(RetentionType value) {
        return new JAXBElement<RetentionType>(_Retention_QNAME, RetentionType.class, null, value);
    }
}
