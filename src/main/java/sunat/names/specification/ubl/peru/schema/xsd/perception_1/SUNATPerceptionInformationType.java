package sunat.names.specification.ubl.peru.schema.xsd.perception_1;

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
@XmlType(name = "SUNATPerceptionInformationType", propOrder = { "sunatPerceptionAmount",
        "sunatPerceptionDate", "sunatNetTotalCashed", "exchangeRate" })
public class SUNATPerceptionInformationType {
    @XmlElement(name = "SUNATPerceptionAmount", namespace = "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1" , required = true)
    protected AmountType sunatPerceptionAmount;
    @XmlElement(name = "SUNATPerceptionDate", namespace = "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1" , required = true)
    protected DateType sunatPerceptionDate;
    @XmlElement(name = "SUNATNetTotalCashed", namespace = "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1" , required = true)
    protected AmountType sunatNetTotalCashed;
    @XmlElement(name = "ExchangeRate", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    protected ExchangeRateType exchangeRate;

    public AmountType getSunatPerceptionAmount() {
        return sunatPerceptionAmount;
    }

    public void setSunatPerceptionAmount(AmountType sunatPerceptionAmount) {
        this.sunatPerceptionAmount = sunatPerceptionAmount;
    }

    public DateType getSunatPerceptionDate() {
        return sunatPerceptionDate;
    }

    public void setSunatPerceptionDate(DateType sunatPerceptionDate) {
        this.sunatPerceptionDate = sunatPerceptionDate;
    }

    public AmountType getSunatNetTotalCashed() {
        return sunatNetTotalCashed;
    }

    public void setSunatNetTotalCashed(AmountType sunatNetTotalCashed) {
        this.sunatNetTotalCashed = sunatNetTotalCashed;
    }

    public ExchangeRateType getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRateType exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public AmountType setSunatPerceptionAmount(@Nullable final BigDecimal valueParam) {
        AmountType aObj = getSunatPerceptionAmount();
        if (aObj == null) {
            aObj = new AmountType(valueParam);
            setSunatPerceptionAmount(aObj);
        } else {
            aObj.setValue(valueParam);
        }
        return aObj;
    }

    @Nonnull
    public DateType setSunatPerceptionDate(@Nullable final XMLGregorianCalendar valueParam) {
        DateType aObj = getSunatPerceptionDate();
        if (aObj == null) {
            aObj = new DateType(valueParam);
            setSunatPerceptionDate(aObj);
        } else {
            aObj.setValue(valueParam);
        }
        return aObj;
    }

    public AmountType setSunatNetTotalCashed(@Nullable final BigDecimal valueParam,
            @Nullable final String currencyID) {
        AmountType aObj = getSunatNetTotalCashed();
        if (aObj == null) {
            aObj = new AmountType(valueParam);
            aObj.setCurrencyID(currencyID);
            setSunatNetTotalCashed(aObj);
        } else {
            aObj.setValue(valueParam);
            aObj.setCurrencyID(currencyID);
        }
        return aObj;
    }
}
