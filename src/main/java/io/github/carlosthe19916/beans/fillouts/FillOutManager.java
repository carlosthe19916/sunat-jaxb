package io.github.carlosthe19916.beans.fillouts;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class FillOutManager {

    private static volatile FillOutManager instance;

    private List<Invoice20FillOut> invoice20FillOut;
    private List<Invoice21FillOut> invoice21FillOut;
    private List<CreditNote21FillOut> creditNote21FillOut;
    private List<DebitNote21FillOut> debitNote21FillOut;

    private FillOutManager() {

    }

    public static FillOutManager getInstance() {
        if (instance == null) {
            synchronized (FillOutManager.class) {
                if (instance == null) {
                    instance = new FillOutManager();
                }
            }
        }
        return instance;
    }

    public List<Invoice20FillOut> getInvoice20FillOuts() {
        if (invoice20FillOut == null) {
            synchronized (FillOutManager.class) {
                if (invoice20FillOut == null) {
                    invoice20FillOut = new ArrayList<>();
                    for (Invoice20FillOut fillOut : ServiceLoader.load(Invoice20FillOut.class)) {
                        invoice20FillOut.add(fillOut);
                    }
                    invoice20FillOut.sort((t1, t2) -> t2.order() - t1.order());
                }
            }
        }
        return invoice20FillOut;
    }

    public List<Invoice21FillOut> getInvoice21FillOuts() {
        if (invoice21FillOut == null) {
            synchronized (FillOutManager.class) {
                if (invoice21FillOut == null) {
                    invoice21FillOut = new ArrayList<>();
                    for (Invoice21FillOut fillOut : ServiceLoader.load(Invoice21FillOut.class)) {
                        invoice21FillOut.add(fillOut);
                    }
                    invoice21FillOut.sort((t1, t2) -> t2.order() - t1.order());
                }
            }
        }
        return invoice21FillOut;
    }

    public List<CreditNote21FillOut> getCreditNote21FillOuts() {
        if (creditNote21FillOut == null) {
            synchronized (FillOutManager.class) {
                if (creditNote21FillOut == null) {
                    creditNote21FillOut = new ArrayList<>();
                    for (CreditNote21FillOut fillOut : ServiceLoader.load(CreditNote21FillOut.class)) {
                        creditNote21FillOut.add(fillOut);
                    }
                    creditNote21FillOut.sort((t1, t2) -> t2.order() - t1.order());
                }
            }
        }
        return creditNote21FillOut;
    }

    public List<DebitNote21FillOut> getDebitNote21FillOuts() {
        if (debitNote21FillOut == null) {
            synchronized (FillOutManager.class) {
                if (debitNote21FillOut == null) {
                    debitNote21FillOut = new ArrayList<>();
                    for (DebitNote21FillOut fillOut : ServiceLoader.load(DebitNote21FillOut.class)) {
                        debitNote21FillOut.add(fillOut);
                    }
                    debitNote21FillOut.sort((t1, t2) -> t2.order() - t1.order());
                }
            }
        }
        return debitNote21FillOut;
    }
}
