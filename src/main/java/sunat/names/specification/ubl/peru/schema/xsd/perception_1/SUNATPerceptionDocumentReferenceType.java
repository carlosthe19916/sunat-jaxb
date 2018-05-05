package sunat.names.specification.ubl.peru.schema.xsd.perception_1;

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
@XmlType(name = "SUNATPerceptionDocumentReferenceType", propOrder = { "id", "issueDate", "totalInvoiceAmount",
        "payment", "sunatPerceptionInformation" })
public class SUNATPerceptionDocumentReferenceType {
    @XmlElement(name = "ID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected IDType id;
    @XmlElement(name = "IssueDate", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected IssueDateType issueDate;
    @XmlElement(name = "TotalInvoiceAmount", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected TotalInvoiceAmountType totalInvoiceAmount;
    @XmlElement(name = "Payment", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    protected PaymentType payment;
    @XmlElement(name = "SUNATPerceptionInformation", namespace = "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1" )
    protected SUNATPerceptionInformationType sunatPerceptionInformation;

    public IDType getId() {
        return id;
    }

    public void setId(IDType id) {
        this.id = id;
    }

    public IssueDateType getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(IssueDateType issueDate) {
        this.issueDate = issueDate;
    }

    public TotalInvoiceAmountType getTotalInvoiceAmount() {
        return totalInvoiceAmount;
    }

    public void setTotalInvoiceAmount(TotalInvoiceAmountType totalInvoiceAmount) {
        this.totalInvoiceAmount = totalInvoiceAmount;
    }

    public PaymentType getPayment() {
        return payment;
    }

    public void setPayment(PaymentType payment) {
        this.payment = payment;
    }

    public SUNATPerceptionInformationType getSunatPerceptionInformation() {
        return sunatPerceptionInformation;
    }

    public void setSunatPerceptionInformation(SUNATPerceptionInformationType sunatPerceptionInformation) {
        this.sunatPerceptionInformation = sunatPerceptionInformation;
    }

    @Nonnull
    public IDType setId(@Nullable final String valueParam) {
        IDType aObj = getId();
        if (aObj == null) {
            aObj = new IDType();
            aObj.setValue(valueParam);
            setId(aObj);
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
