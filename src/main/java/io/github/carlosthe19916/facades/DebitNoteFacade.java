package io.github.carlosthe19916.facades;

import io.github.carlosthe19916.beans.config.ubl21.GlobalUBL21Defaults;
import io.github.carlosthe19916.beans.exceptions.DebitNote21BeanValidacionException;
import io.github.carlosthe19916.beans.fillouts.DebitNote21FillOut;
import io.github.carlosthe19916.beans.fillouts.FillOutManager;
import io.github.carlosthe19916.beans.ubl.ubl21.DebitNote21Bean;
import io.github.carlosthe19916.types.BeanToType21;

import java.util.List;

public class DebitNoteFacade {

    private DebitNoteFacade() {
        // Just static methods
    }

    public static DebitNote21Bean fillOut(DebitNote21Bean creditNote, DebitNote21FillOut... fillOuts) {
        GlobalUBL21Defaults defaults = GlobalUBL21Defaults.getInstance();

        for (DebitNote21FillOut fillOut : fillOuts) {
            creditNote = fillOut.fillIn(creditNote);
        }

        if (defaults.getAplicarCalculosInternosAutomaticos()) {
            List<DebitNote21FillOut> debitNote21FillOuts = FillOutManager.getInstance().getDebitNote21FillOuts();
            for (DebitNote21FillOut fillOut : debitNote21FillOuts) {
                creditNote = fillOut.fillIn(creditNote);
            }
        }

        return creditNote;
    }

    public static oasis.names.specification.ubl.schema.xsd.debitnote_21.DebitNoteType toDebitNoteType(DebitNote21Bean debitNote) throws DebitNote21BeanValidacionException {
        return BeanToType21.toDebitNoteType(debitNote);
    }

}
