package sunat.names.specification.ubl.peru.schema.xsd.perception_1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * Created by lxpary on 27/12/16.
 */
@XmlRegistry
public class PerceptionFactory {
    private final static QName _Perception_QNAME = new QName(
            "urn:sunat:names:specification:ubl:peru:schema:xsd:Perception-1", "Perception");

    public PerceptionFactory() {
    }

    @XmlElementDecl(namespace = "urn:sunat:names:specification:ubl:peru:schema:xsd:Perception-1", name = "Perception")
    public JAXBElement<PerceptionType> createPerception(PerceptionType value) {
        return new JAXBElement<PerceptionType>(_Perception_QNAME, PerceptionType.class, null, value);
    }
}
