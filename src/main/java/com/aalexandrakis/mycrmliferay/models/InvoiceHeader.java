/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aalexandrakis.mycrmliferay.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aalexandrakis
 */
@Entity
@Table(name = "invoiceHeader")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvoiceHeader.findAll", query = "SELECT i FROM InvoiceHeader i"),
    @NamedQuery(name = "InvoiceHeader.findByInvoiceId", query = "SELECT i FROM InvoiceHeader i WHERE i.invoiceId = :invoiceId"),
    @NamedQuery(name = "InvoiceHeader.findByCompanyId", query = "SELECT i FROM InvoiceHeader i WHERE i.companyId = :companyId"),
    @NamedQuery(name = "InvoiceHeader.findByCustomerId", query = "SELECT i FROM InvoiceHeader i WHERE i.customerId = :customerId"),
    @NamedQuery(name = "InvoiceHeader.findByAmount", query = "SELECT i FROM InvoiceHeader i WHERE i.amount = :amount"),
    @NamedQuery(name = "InvoiceHeader.findByFpa", query = "SELECT i FROM InvoiceHeader i WHERE i.fpa = :fpa"),
    @NamedQuery(name = "InvoiceHeader.findByTaxis", query = "SELECT i FROM InvoiceHeader i WHERE i.taxis = :taxis"),
    @NamedQuery(name = "InvoiceHeader.findByGross", query = "SELECT i FROM InvoiceHeader i WHERE i.gross = :gross"),
    @NamedQuery(name = "InvoiceHeader.findByWithHolding", query = "SELECT i FROM InvoiceHeader i WHERE i.withHolding = :withHolding"),
    @NamedQuery(name = "InvoiceHeader.findByFpaAmount", query = "SELECT i FROM InvoiceHeader i WHERE i.fpaAmount = :fpaAmount"),
    @NamedQuery(name = "InvoiceHeader.findByWithHoldingAmount", query = "SELECT i FROM InvoiceHeader i WHERE i.withHoldingAmount = :withHoldingAmount"),
    @NamedQuery(name = "InvoiceHeader.findByReceivedAmount", query = "SELECT i FROM InvoiceHeader i WHERE i.receivedAmount = :receivedAmount"),
    @NamedQuery(name = "InvoiceHeader.findByInvoiceDate", query = "SELECT i FROM InvoiceHeader i WHERE i.invoiceDate = :invoiceDate"),
    @NamedQuery(name = "InvoiceHeader.findByWithHoldingString", query = "SELECT i FROM InvoiceHeader i WHERE i.withHoldingString = :withHoldingString"),
    @NamedQuery(name = "InvoiceHeader.findByWords", query = "SELECT i FROM InvoiceHeader i WHERE i.words = :words")})
public class InvoiceHeader implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "invoiceId")
    private Integer invoiceId;
    @Basic(optional = false)
    @Column(name = "companyId")
    private int companyId;
    @Basic(optional = false)
    @Column(name = "customerId")
    private int customerId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic(optional = false)
    @Column(name = "fpa")
    private BigDecimal fpa;
    @Basic(optional = false)
    @Column(name = "taxis")
    private BigDecimal taxis;
    @Basic(optional = false)
    @Column(name = "gross")
    private BigDecimal gross;
    @Basic(optional = false)
    @Column(name = "withHolding")
    private BigDecimal withHolding;
    @Basic(optional = false)
    @Column(name = "fpaAmount")
    private BigDecimal fpaAmount;
    @Basic(optional = false)
    @Column(name = "withHoldingAmount")
    private BigDecimal withHoldingAmount;
    @Basic(optional = false)
    @Column(name = "receivedAmount")
    private BigDecimal receivedAmount;
    @Basic(optional = false)
    @Column(name = "invoiceDate")
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;
    @Basic(optional = false)
    @Column(name = "withHoldingString")
    private String withHoldingString;
    @Basic(optional = false)
    @Column(name = "words")
    private String words;
    @Lob
    @Column(name = "invoiceFile")
    private Blob invoiceFile;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceId")
    private Collection<InvoiceDetails> invoiceDetailsCollection;

    @Transient
    private CompanyInfo companyInfo;
    
    @Transient
    private Customer customer;
    
    public InvoiceHeader() {
    }

    public InvoiceHeader(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public InvoiceHeader(Integer invoiceId, int companyId, int customerId, BigDecimal amount, BigDecimal fpa, BigDecimal taxis, BigDecimal gross, BigDecimal withHolding, BigDecimal fpaAmount, BigDecimal withHoldingAmount, BigDecimal receivedAmount, Date invoiceDate, String withHoldingString, String words) {
        this.invoiceId = invoiceId;
        this.companyId = companyId;
        this.customerId = customerId;
        this.amount = amount;
        this.fpa = fpa;
        this.taxis = taxis;
        this.gross = gross;
        this.withHolding = withHolding;
        this.fpaAmount = fpaAmount;
        this.withHoldingAmount = withHoldingAmount;
        this.receivedAmount = receivedAmount;
        this.invoiceDate = invoiceDate;
        this.withHoldingString = withHoldingString;
        this.words = words;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFpa() {
        return fpa;
    }

    public void setFpa(BigDecimal fpa) {
        this.fpa = fpa;
    }

    public BigDecimal getTaxis() {
        return taxis;
    }

    public void setTaxis(BigDecimal taxis) {
        this.taxis = taxis;
    }

    public BigDecimal getGross() {
        return gross;
    }

    public void setGross(BigDecimal gross) {
        this.gross = gross;
    }

    public BigDecimal getWithHolding() {
        return withHolding;
    }

    public void setWithHolding(BigDecimal withHolding) {
        this.withHolding = withHolding;
    }

    public BigDecimal getFpaAmount() {
        return fpaAmount;
    }

    public void setFpaAmount(BigDecimal fpaAmount) {
        this.fpaAmount = fpaAmount;
    }

    public BigDecimal getWithHoldingAmount() {
        return withHoldingAmount;
    }

    public void setWithHoldingAmount(BigDecimal withHoldingAmount) {
        this.withHoldingAmount = withHoldingAmount;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getWithHoldingString() {
        return withHoldingString;
    }

    public void setWithHoldingString(String withHoldingString) {
        this.withHoldingString = withHoldingString;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Blob getInvoiceFile() {
        return invoiceFile;
    }

    public void setInvoiceFile(Blob invoiceFile) {
        this.invoiceFile = invoiceFile;
    }

    @XmlTransient
    public Collection<InvoiceDetails> getInvoiceDetailsCollection() {
        return invoiceDetailsCollection;
    }

    public void setInvoiceDetailsCollection(Collection<InvoiceDetails> invoiceDetailsCollection) {
        this.invoiceDetailsCollection = invoiceDetailsCollection;
    }

    
    public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceId != null ? invoiceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceHeader)) {
            return false;
        }
        InvoiceHeader other = (InvoiceHeader) object;
        if ((this.invoiceId == null && other.invoiceId != null) || (this.invoiceId != null && !this.invoiceId.equals(other.invoiceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aalexandrakis.mycrmliferay.models.InvoiceHeader[ invoiceId=" + invoiceId + " ]";
    }
    
}
