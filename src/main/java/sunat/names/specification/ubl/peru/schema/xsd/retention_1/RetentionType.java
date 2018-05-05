package sunat.names.specification.ubl.peru.schema.xsd.retention_1;

import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.SignatureType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.UBLExtensionsType;
import un.unece.uncefact.codelist.specification._54217._2001.CurrencyCodeContentType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetentionType", namespace = "urn:sunat:names:specification:ubl:peru:schema:xsd:Retention-1", propOrder = {
        "ublExtensions", "ublVersionID", "customizationID", "signature", "id", "issueDate", "agentParty",
        "receiverParty", "sunatRetentionSystemCode", "sunatRetentionPercent", "note",
        "totalInvoiceAmount", "sunatTotalPaid", "sunatRetentionDocumentReference"

})
public class RetentionType  implements Serializable, Cloneable{
    @XmlElement(name = "UBLExtensions", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2")
    protected UBLExtensionsType ublExtensions;

    @XmlElement(name = "UBLVersionID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected UBLVersionIDType ublVersionID;

    @XmlElement(name = "CustomizationID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected CustomizationIDType customizationID;

    @XmlElement(name = "Signature", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    protected List<SignatureType> signature;

    @XmlElement(name = "ID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected IDType id;

    @XmlElement(name = "IssueDate", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected IssueDateType issueDate;

    @XmlElement(name = "AgentParty", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    protected PartyType agentParty;

    @XmlElement(name = "ReceiverParty", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    protected PartyType receiverParty;

    @XmlElement(name = "SUNATRetentionSystemCode", namespace = "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1", required = true)
    protected IDType sunatRetentionSystemCode;

    @XmlElement(name = "SUNATRetentionPercent", namespace = "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1")
    protected PercentType sunatRetentionPercent;

    @XmlElement(name = "Note", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected List<NoteType> note;

    @XmlElement(name = "TotalInvoiceAmount", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected TotalInvoiceAmountType totalInvoiceAmount;

    @XmlElement(name = "SUNATTotalPaid", namespace = "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1" , required = true)
    protected AmountType sunatTotalPaid;

    @XmlElement(name = "SUNATRetentionDocumentReference",namespace = "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1")
    protected List<SUNATRetentionDocumentReferenceType> sunatRetentionDocumentReference;

    public UBLExtensionsType getUblExtensions() {
        return ublExtensions;
    }

    public void setUblExtensions(UBLExtensionsType ublExtensions) {
        this.ublExtensions = ublExtensions;
    }

    public List<SignatureType> getSignature() {
        if (signature == null) {
            signature = new ArrayList<>();
        }
        return signature;
    }

    public UBLVersionIDType getUblVersionID() {
        return ublVersionID;
    }

    public void setUblVersionID(UBLVersionIDType ublVersionID) {
        this.ublVersionID = ublVersionID;
    }

    public CustomizationIDType getCustomizationID() {
        return customizationID;
    }

    public void setCustomizationID(CustomizationIDType customizationID) {
        this.customizationID = customizationID;
    }

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

    public PartyType getAgentParty() {
        return agentParty;
    }

    public void setAgentParty(PartyType agentParty) {
        this.agentParty = agentParty;
    }

    public PartyType getReceiverParty() {
        return receiverParty;
    }

    public void setReceiverParty(PartyType receiverParty) {
        this.receiverParty = receiverParty;
    }

    public IDType getSunatRetentionSystemCode() {
        return sunatRetentionSystemCode;
    }

    public void setSunatRetentionSystemCode(IDType sunatRetentionSystemCode) {
        this.sunatRetentionSystemCode = sunatRetentionSystemCode;
    }

    public PercentType getSunatRetentionPercent() {
        return sunatRetentionPercent;
    }

    public void setSunatRetentionPercent(PercentType sunatRetentionPercent) {
        this.sunatRetentionPercent = sunatRetentionPercent;
    }

    public List<NoteType> getNote() {
        if (note == null) {
            note = new ArrayList<>();
        }
        return note;
    }

    public TotalInvoiceAmountType getTotalInvoiceAmount() {
        return totalInvoiceAmount;
    }

    public void setTotalInvoiceAmount(TotalInvoiceAmountType totalInvoiceAmount) {
        this.totalInvoiceAmount = totalInvoiceAmount;
    }

    public AmountType getSunatTotalPaid() {
        return sunatTotalPaid;
    }

    public void setSunatTotalPaid(AmountType sunatTotalPaid) {
        this.sunatTotalPaid = sunatTotalPaid;
    }

    public List<SUNATRetentionDocumentReferenceType> getSunatRetentionDocumentReference() {
        if (sunatRetentionDocumentReference == null) {
            sunatRetentionDocumentReference = new ArrayList<>();
        }
        return sunatRetentionDocumentReference;
    }

    @Nonnull
    public UBLVersionIDType setUblVersionID(@Nullable final String valueParam) {
        UBLVersionIDType aObj = getUblVersionID();
        if (aObj == null) {
            aObj = new UBLVersionIDType();
            aObj.setValue(valueParam);
            setUblVersionID(aObj);
        } else {
            aObj.setValue(valueParam);
        }
        return aObj;
    }

    @Nonnull
    public CustomizationIDType setCustomizationID(@Nullable final String valueParam) {
        CustomizationIDType aObj = getCustomizationID();
        if (aObj == null) {
            aObj = new CustomizationIDType();
            aObj.setValue(valueParam);
            setCustomizationID(aObj);
        } else {
            aObj.setValue(valueParam);
        }
        return aObj;
    }

    public void addSignature(@Nonnull final SignatureType elem) {
        getSignature().add(elem);
    }

    public void addRetentionDocumentReference(@Nonnull final SUNATRetentionDocumentReferenceType elem) {
        getSunatRetentionDocumentReference().add(elem);
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

    public IDType setSunatRetentionSystemCode(@Nullable final String valueParam) {
        IDType aObj = getSunatRetentionSystemCode();
        if (aObj == null) {
            aObj = new IDType();
            aObj.setValue(valueParam);
            setSunatRetentionSystemCode(aObj);
        } else {
            aObj.setValue(valueParam);
        }
        return aObj;
    }

    public PercentType setSunatRetentionPercent(@Nullable final BigDecimal valueParam) {
        PercentType aObj = getSunatRetentionPercent();
        if (aObj == null) {
            aObj = new PercentType();
            aObj.setValue(valueParam);
            setSunatRetentionPercent(aObj);
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

    public AmountType setSunatTotalPaid(@Nullable final BigDecimal valueParam,
            @Nullable final String currencyID) {
        AmountType aObj = getSunatTotalPaid();
        if (aObj == null) {
            aObj = new AmountType();
            aObj.setValue(valueParam);
            aObj.setCurrencyID(CurrencyCodeContentType.valueOf(currencyID));
            setSunatTotalPaid(aObj);
        } else {
            aObj.setValue(valueParam);
            aObj.setCurrencyID(CurrencyCodeContentType.valueOf(currencyID));
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

    public void addNote(@Nonnull final String elem) {
        NoteType type = new NoteType();
        type.setValue(elem);
        getNote().add(type);
    }
}
