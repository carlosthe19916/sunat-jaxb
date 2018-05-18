package oasis.names.specification.ubl.schema.xsd;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SimpleNamespaceContext implements NamespaceContext {

    private Map<String, String> prefixMap = new HashMap<>();

    public SimpleNamespaceContext(String prefix, String uri) {
        prefixMap.put(prefix, uri);
    }

    public void addPrefixMapping(String prefix, String uri) {
        prefixMap.put(prefix, uri);
    }

    @Override
    public String getNamespaceURI(String prefix) {
        if (prefixMap.containsKey(prefix)) {
            return prefixMap.get(prefix);
        }
        return null;
    }

    @Override
    public String getPrefix(String s) {
        return null;
    }

    @Override
    public Iterator getPrefixes(String s) {
        return null;
    }

}
