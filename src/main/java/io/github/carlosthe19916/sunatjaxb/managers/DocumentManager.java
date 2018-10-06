package io.github.carlosthe19916.sunatjaxb.managers;

import io.github.carlosthe19916.sunatjaxb.beans.beans20.Invoice20Bean;
import io.github.carlosthe19916.sunatjaxb.beans.beans21.Invoice21Bean;
import io.github.carlosthe19916.sunatjaxb.exceptions.Invoice21BeanValidacionException;
import io.github.carlosthe19916.sunatjaxb.mappers.Invoice21Mapper;
import io.github.carlosthe19916.sunatjaxb.mappers.MapperComparator;
import io.github.carlosthe19916.sunatjaxb.mappers.MapperManager;
import io.github.carlosthe19916.sunatjaxb.mappers.core.GlobalCore21MapperDefaults;
import io.github.carlosthe19916.sunatjaxb.utils.Bean21ToType;
import io.github.carlosthe19916.sunatjaxb.utils.BeanUtils;
import oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DocumentManager {

    private DocumentManager() {
        // Just static methods
    }

    public static Set<ConstraintViolation<Invoice21Bean>> validate(Invoice21Bean invoice) throws Invoice21BeanValidacionException {
        return BeanUtils.validate(invoice);
    }

    public static Invoice21Bean map(Invoice21Bean invoice, Invoice21Mapper... mappers) {
        GlobalCore21MapperDefaults defaults = GlobalCore21MapperDefaults.getInstance();
        List<Invoice21Mapper> mappersToApply = Stream.concat(
                MapperManager.getInstance().getInvoice21Mappers().stream(),
                Stream.of(mappers)
        )
                .filter(p -> p.isInternal() == defaults.isInternalMappersApplied())
                .sorted(new MapperComparator())
                .collect(Collectors.toList());
        for (Invoice21Mapper mapper : mappersToApply) {
            invoice = mapper.map(invoice);
        }
        return invoice;
    }

    public static InvoiceType toInvoiceType(Invoice21Bean invoice) {
        return Bean21ToType.toInvoiceType(invoice);
    }

}
