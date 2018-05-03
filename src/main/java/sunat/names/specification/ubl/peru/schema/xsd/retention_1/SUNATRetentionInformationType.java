package sunat.names.specification.ubl.peru.schema.xsd.retention_1;

import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_21.ExchangeRateType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.AmountType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_21.DateType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SUNATRetentionInformationType", propOrder = { "sunatRetentionAmount", "sunatRetentionDate",
        "sunatNetTotalPaid", "exchangeRate" })
public class SUNATRetentionInformationType {

    @XmlElement(name = "SUNATRetentionAmount", namespace = "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1" , required = true)
    protected AmountType sunatRetentionAmount;
    @XmlElement(name = "SUNATRetentionDate", namespace = "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1" , required = true)
    protected DateType sunatRetentionDate;
    @XmlElement(name = "SUNATNetTotalPaid", namespace = "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1" , required = true)
    protected AmountType sunatNetTotalPaid;
    @XmlElement(name = "ExchangeRate", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    protected ExchangeRateType exchangeRate;

    /**
     * Gets the value of the sunatRetentionAmount property.
     * 
     * @return possible object is {@link AmountType }
     * 
     */
    public AmountType getSUNATRetentionAmount() {
        return sunatRetentionAmount;
    }

    /**
     * Sets the value of the sunatRetentionAmount property.
     * 
     * @param value
     *            allowed object is {@link AmountType }
     * 
     */
    public void setSUNATRetentionAmount(AmountType value) {
        this.sunatRetentionAmount = value;
    }

    /**
     * Gets the value of the sunatRetentionDate property.
     * 
     * @return possible object is {@link SUNATRetentionDateType }
     * 
     */
    public DateType getSUNATRetentionDate() {
        return sunatRetentionDate;
    }

    /**
     * Sets the value of the sunatRetentionDate property.
     * 
     * @param value
     *            allowed object is {@link SUNATRetentionDateType }
     * 
     */
    public void setSUNATRetentionDate(DateType value) {
        this.sunatRetentionDate = value;
    }

    /**
     * Gets the value of the sunatNetTotalPaid property.
     * 
     * @return possible object is {@link AmountType }
     * 
     */
    public AmountType getSUNATNetTotalPaid() {
        return sunatNetTotalPaid;
    }

    /**
     * Sets the value of the sunatNetTotalPaid property.
     * 
     * @param value
     *            allowed object is {@link AmountType }
     * 
     */
    public void setSUNATNetTotalPaid(AmountType value) {
        this.sunatNetTotalPaid = value;
    }

    /**
     * Gets the value of the exchangeRate property.
     * 
     * @return possible object is {@link ExchangeRateType }
     * 
     */
    public ExchangeRateType getExchangeRate() {
        return exchangeRate;
    }

    /**
     * Sets the value of the exchangeRate property.
     * 
     * @param value
     *            allowed object is {@link ExchangeRateType }
     * 
     */
    public void setExchangeRate(ExchangeRateType value) {
        this.exchangeRate = value;
    }

    public AmountType setSUNATRetentionAmount(@Nullable final BigDecimal valueParam) {
        AmountType aObj = getSUNATRetentionAmount();
        if (aObj == null) {
            aObj = new AmountType(valueParam);
            setSUNATRetentionAmount(aObj);
        } else {
            aObj.setValue(valueParam);
        }
        return aObj;
    }

    @Nonnull
    public DateType setSUNATRetentionDate(@Nullable final XMLGregorianCalendar valueParam) {
        DateType aObj = getSUNATRetentionDate();
        if (aObj == null) {
            aObj = new DateType(valueParam);
            setSUNATRetentionDate(aObj);
        } else {
            aObj.setValue(valueParam);
        }
        return aObj;
    }

    public AmountType setSUNATNetTotalPaid(@Nullable final BigDecimal valueParam,
            @Nullable final String currencyID) {
        AmountType aObj = getSUNATNetTotalPaid();
        if (aObj == null) {
            aObj = new AmountType(valueParam);
            aObj.setCurrencyID(currencyID);
            setSUNATNetTotalPaid(aObj);
        } else {
            aObj.setValue(valueParam);
            aObj.setCurrencyID(currencyID);
        }
        return aObj;
    }
}
