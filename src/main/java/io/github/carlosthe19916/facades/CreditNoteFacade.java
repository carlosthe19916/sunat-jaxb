package io.github.carlosthe19916.facades;

import io.github.carlosthe19916.beans.config.ubl21.GlobalUBL21Defaults;
import io.github.carlosthe19916.beans.exceptions.CreditNote21BeanValidacionException;
import io.github.carlosthe19916.beans.fillouts.CreditNote21FillOut;
import io.github.carlosthe19916.beans.fillouts.FillOutManager;
import io.github.carlosthe19916.beans.ubl.ubl21.CreditNote21Bean;
import io.github.carlosthe19916.types.BeanToType21;

import java.util.List;

public class CreditNoteFacade {

    private CreditNoteFacade() {
        // Just static methods
    }

    public static CreditNote21Bean fillOut(CreditNote21Bean creditNote, CreditNote21FillOut... fillOuts) {
        GlobalUBL21Defaults defaults = GlobalUBL21Defaults.getInstance();

        for (CreditNote21FillOut fillOut : fillOuts) {
            creditNote = fillOut.fillIn(creditNote);
        }

        if (defaults.getAplicarCalculosInternosAutomaticos()) {
            List<CreditNote21FillOut> creditNote21FillOuts = FillOutManager.getInstance().getCreditNote21FillOuts();
            for (CreditNote21FillOut fillOut : creditNote21FillOuts) {
                creditNote = fillOut.fillIn(creditNote);
            }
        }

        return creditNote;
    }

    public static oasis.names.specification.ubl.schema.xsd.creditnote_21.CreditNoteType toCreditNoteType(CreditNote21Bean creditNote) throws CreditNote21BeanValidacionException {
        return BeanToType21.toCreditNoteType(creditNote);
    }

}
