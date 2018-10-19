package io.github.carlosthe19916.sunatjaxb.ubl21.beans;

import io.github.carlosthe19916.sunatjaxb.core.beans.AbstractImpuestosBean;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Impuestos21Bean extends AbstractImpuestosBean {

    @NotNull
    private BigDecimal igvAfecto;
    private BigDecimal iscAfecto;
    private BigDecimal otrosAfecto;

    public BigDecimal getIgvAfecto() {
        return igvAfecto;
    }

    public void setIgvAfecto(BigDecimal igvAfecto) {
        this.igvAfecto = igvAfecto;
    }

    public BigDecimal getIscAfecto() {
        return iscAfecto;
    }

    public void setIscAfecto(BigDecimal iscAfecto) {
        this.iscAfecto = iscAfecto;
    }

    public BigDecimal getOtrosAfecto() {
        return otrosAfecto;
    }

    public void setOtrosAfecto(BigDecimal otrosAfecto) {
        this.otrosAfecto = otrosAfecto;
    }

}
