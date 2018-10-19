package io.github.carlosthe19916.sunatjaxb.ubl21.mappers;

import io.github.carlosthe19916.sunatjaxb.core.mappers.DefaultMapperComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class MappersManager {

    private static volatile MappersManager instance;

    private List<Invoice21Mapper> invoiceMappers;
    private List<DebitNote21Mapper> debitNoteMappers;
    private List<CreditNote21Mapper> creditNoteMappers;

    private MappersManager() {
    }

    public static MappersManager getInstance() {
        if (instance == null) {
            synchronized (MappersManager.class) {
                if (instance == null) {
                    instance = new MappersManager();
                }
            }
        }
        return instance;
    }

    public List<Invoice21Mapper> getInvoiceMappers() {
        if (invoiceMappers == null) {
            synchronized (MappersManager.class) {
                if (invoiceMappers == null) {
                    invoiceMappers = new ArrayList<>();
                    for (Invoice21Mapper mapper : ServiceLoader.load(Invoice21Mapper.class)) {
                        invoiceMappers.add(mapper);
                    }
                    invoiceMappers.sort(new DefaultMapperComparator());
                }
            }
        }
        return invoiceMappers;
    }

    public List<CreditNote21Mapper> getCreditNoteMappers() {
        if (creditNoteMappers == null) {
            synchronized (MappersManager.class) {
                if (creditNoteMappers == null) {
                    creditNoteMappers = new ArrayList<>();
                    for (CreditNote21Mapper mapper : ServiceLoader.load(CreditNote21Mapper.class)) {
                        creditNoteMappers.add(mapper);
                    }
                    creditNoteMappers.sort((t1, t2) -> t2.order() - t1.order());
                }
            }
        }
        return creditNoteMappers;
    }

    public List<DebitNote21Mapper> getDebitNoteMappers() {
        if (debitNoteMappers == null) {
            synchronized (MappersManager.class) {
                if (debitNoteMappers == null) {
                    debitNoteMappers = new ArrayList<>();
                    for (DebitNote21Mapper mapper : ServiceLoader.load(DebitNote21Mapper.class)) {
                        debitNoteMappers.add(mapper);
                    }
                    debitNoteMappers.sort((t1, t2) -> t2.order() - t1.order());
                }
            }
        }
        return debitNoteMappers;
    }

}
