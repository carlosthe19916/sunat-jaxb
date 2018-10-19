package io.github.carlosthe19916.sunatjaxb.ubl21.beans;

import io.github.carlosthe19916.sunatjaxb.core.beans.AbstractTotalBean;

import java.math.BigDecimal;

public class Total21Bean extends AbstractTotalBean {

    private BigDecimal anticipos;
    private BigDecimal extensionAmount;
    private BigDecimal inclusiveAmount;

    public BigDecimal getAnticipos() {
        return anticipos;
    }

    public void setAnticipos(BigDecimal anticipos) {
        this.anticipos = anticipos;
    }

    public BigDecimal getExtensionAmount() {
        return extensionAmount;
    }

    public void setExtensionAmount(BigDecimal extensionAmount) {
        this.extensionAmount = extensionAmount;
    }

    public BigDecimal getInclusiveAmount() {
        return inclusiveAmount;
    }

    public void setInclusiveAmount(BigDecimal inclusiveAmount) {
        this.inclusiveAmount = inclusiveAmount;
    }
}
