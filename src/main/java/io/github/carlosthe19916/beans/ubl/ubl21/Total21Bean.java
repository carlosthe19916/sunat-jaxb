package io.github.carlosthe19916.beans.ubl.ubl21;

import io.github.carlosthe19916.beans.TotalBean;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Total21Bean extends TotalBean {

    @NotNull
    private BigDecimal anticipos;

    @NotNull
    private BigDecimal extensionAmount;

    @NotNull
    private BigDecimal inclusiveAmount;

    public Total21Bean() {
    }

    public Total21Bean(TotalBean total) {
        super(total);
    }

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
