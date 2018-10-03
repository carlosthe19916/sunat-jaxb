package io.github.carlosthe19916.beans.ubl.ubl21;

import io.github.carlosthe19916.beans.ImpuestosBean;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Impuestos21Bean extends ImpuestosBean {
    
    private BigDecimal igvAfecto;
    private BigDecimal iscAfecto;
    private BigDecimal otrosAfecto;

    public Impuestos21Bean() {
    }

    public Impuestos21Bean(ImpuestosBean impuestos) {
        super(impuestos);
    }

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
