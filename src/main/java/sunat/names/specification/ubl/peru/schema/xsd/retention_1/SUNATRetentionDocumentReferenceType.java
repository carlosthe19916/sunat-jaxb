package sunat.names.specification.ubl.peru.schema.xsd.retention_1;

import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PaymentType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IssueDateType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TotalInvoiceAmountType;
import un.unece.uncefact.codelist.specification._54217._2001.CurrencyCodeContentType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SUNATRetentionDocumentReferenceType", propOrder = { "id", "issueDate", "totalInvoiceAmount",
        "payment", "sunatRetentionInformation" })
public class SUNATRetentionDocumentReferenceType {
    @XmlElement(name = "ID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected IDType id;
    @XmlElement(name = "IssueDate", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected IssueDateType issueDate;
    @XmlElement(name = "TotalInvoiceAmount", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected TotalInvoiceAmountType totalInvoiceAmount;
    @XmlElement(name = "Payment", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    protected PaymentType payment;
    @XmlElement(name = "SUNATRetentionInformation", namespace = "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1" )
    protected SUNATRetentionInformationType sunatRetentionInformation;

    /**
     * Gets the value of the id property.
     * 
     * @return possible object is {@link IDType }
     * 
     */
    public IDType getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *            allowed object is {@link IDType }
     * 
     */
    public void setID(IDType value) {
        this.id = value;
    }

    /**
     * Gets the value of the issueDate property.
     * 
     * @return possible object is {@link IssueDateType }
     * 
     */
    public IssueDateType getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the value of the issueDate property.
     * 
     * @param value
     *            allowed object is {@link IssueDateType }
     * 
     */
    public void setIssueDate(IssueDateType value) {
        this.issueDate = value;
    }

    /**
     * Gets the value of the totalInvoiceAmount property.
     * 
     * @return possible object is {@link TotalInvoiceAmountType }
     * 
     */
    public TotalInvoiceAmountType getTotalInvoiceAmount() {
        return totalInvoiceAmount;
    }

    /**
     * Sets the value of the totalInvoiceAmount property.
     * 
     * @param value
     *            allowed object is {@link TotalInvoiceAmountType }
     * 
     */
    public void setTotalInvoiceAmount(TotalInvoiceAmountType value) {
        this.totalInvoiceAmount = value;
    }

    /**
     * Gets the value of the payment property.
     * 
     * @return possible object is {@link PaymentType }
     * 
     */
    public PaymentType getPayment() {
        return payment;
    }

    /**
     * Sets the value of the payment property.
     * 
     * @param value
     *            allowed object is {@link PaymentType }
     * 
     */
    public void setPayment(PaymentType value) {
        this.payment = value;
    }

    /**
     * Gets the value of the sunatRetentionInformation property.
     * 
     * @return possible object is {@link SUNATRetentionInformationType }
     * 
     */
    public SUNATRetentionInformationType getSUNATRetentionInformation() {
        return sunatRetentionInformation;
    }

    /**
     * Sets the value of the sunatRetentionInformation property.
     * 
     * @param value
     *            allowed object is {@link SUNATRetentionInformationType }
     * 
     */
    public void setSUNATRetentionInformation(SUNATRetentionInformationType value) {
        this.sunatRetentionInformation = value;
    }

    @Nonnull
    public IDType setID(@Nullable final String valueParam) {
        IDType aObj = getID();
        if (aObj == null) {
            aObj = new IDType();
            aObj.setValue(valueParam);
            setID(aObj);
        } else {
            aObj.setValue(valueParam);
        }
        return aObj;
    }

    @Nonnull
    public IssueDateType setIssueDate(@Nullable final XMLGregorianCalendar valueParam) {
        IssueDateType aObj = getIssueDate();
        if (aObj == null) {
            aObj = new IssueDateType();
            aObj.setValue(valueParam);
            setIssueDate(aObj);
        } else {
            aObj.setValue(valueParam);
        }
        return aObj;
    }

    public TotalInvoiceAmountType setTotalInvoiceAmount(@Nullable final BigDecimal valueParam,
            @Nullable final String currencyID) {
        TotalInvoiceAmountType aObj = getTotalInvoiceAmount();
        if (aObj == null) {
            aObj = new TotalInvoiceAmountType();
            aObj.setValue(valueParam);
            aObj.setCurrencyID(CurrencyCodeContentType.valueOf(currencyID));
            setTotalInvoiceAmount(aObj);
        } else {
            aObj.setValue(valueParam);
            aObj.setCurrencyID(CurrencyCodeContentType.valueOf(currencyID));
        }
        return aObj;
    }
}
