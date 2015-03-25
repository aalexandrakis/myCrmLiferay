/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aalexandrakis.mycrmliferay.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Basic(optional = false)
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
    @OneToMany(mappedBy = "invoiceId")
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

	public void addNewLine(){
		InvoiceDetails invoiceLine = new InvoiceDetails();
		if (invoiceDetailsCollection == null){
			invoiceDetailsCollection = new ArrayList<InvoiceDetails>();
		}
		invoiceLine.setLineId(invoiceDetailsCollection.size()+1);
		this.invoiceDetailsCollection.add(invoiceLine);
	}
	
	public void removeLine(Integer lineId){
		for (InvoiceDetails invoiceLine : invoiceDetailsCollection){
			if (invoiceLine.getLineId() == lineId){
				invoiceDetailsCollection.remove(invoiceLine);
				break;
			}
		}
		lineId = 0;
		for (InvoiceDetails invoiceLine : invoiceDetailsCollection){
			lineId++;
			invoiceLine.setLineId(lineId);
		}
	}
	
	public void calculate(){
		this.taxis = BigDecimal.ZERO;
		this.amount = BigDecimal.ZERO;
		this.fpaAmount = BigDecimal.ZERO;
		this.gross = BigDecimal.ZERO;
		this.withHoldingAmount = BigDecimal.ZERO;
		this.receivedAmount = BigDecimal.ZERO;
		this.withHoldingString = null;
		if (invoiceDetailsCollection != null){
			for (InvoiceDetails line : invoiceDetailsCollection){
				if (line.getNet() != null){
					this.amount = this.amount.add(line.getNet());
				}
			}
			
			this.fpaAmount = (this.amount.multiply(this.fpa)).divide(new BigDecimal("100"));
			
			this.gross = this.amount.add(this.fpaAmount);
			
			if (this.withHolding != null && this.withHolding != BigDecimal.ZERO){
				this.withHoldingAmount = this.amount.multiply(this.withHolding).divide(new BigDecimal("100"));
				this.receivedAmount = this.amount.subtract(this.withHoldingAmount).add(this.fpaAmount);
				this.withHoldingString = "Έγινε παρακράτηση " + this.withHolding.toString() + "% = " +
									  this.withHoldingAmount.toString() + " Euro. Τελικό εισπρακτέο " + this.receivedAmount + " Euro";
			}
			
			this.words = grossInWords();
		}
		
	}
	
	public String grossInWords(){
		Map<String, String> map = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;

		{
			put("11", "Εκατόν ");
			put("12", "Διακόσιες ");
			put("13", "Τριακόσιες ");
			put("14", "Τετρακόσιες ");
			put("15", "Πεντακόσιες ");
			put("16", "Εξακόσιες ");
			put("17", "Εφτακόσιες ");
			put("18", "Οχτακόσιες ");
			put("19", "Ενιακόσιες ");
			
			put("21", "Δέκα ");
			put("22", "Είκοσι ");
			put("23", "Τριάντα ");
			put("24", "Σαράντα ");
			put("25", "Πενήντα ");
			put("26", "Εξήντα ");
			put("27", "Εβδομήντα ");
			put("28", "Ογδόντα ");
			put("29", "Ενεννήντα ");
			
			put("2131", "Έντεκα ");
			put("2132", "Δωδεκα ");

			put("31", "Μια ");
			put("32", "Δύο ");
			put("33", "Τρεις ");
			put("34", "Τέσσερις ");
			put("35", "Πέντε ");
			put("36", "Εξι ");
			put("37", "Εφτά ");
			put("38", "Οχτώ ");
			put("39", "Εννέα ");
			
			put("41", "Εκατόν ");
			put("42", "Διακόσια ");
			put("43", "Τριακόσια ");
			put("44", "Τετρακόσια ");
			put("45", "Πεντακόσια ");
			put("46", "Εξακόσια ");
			put("47", "Εφτακόσια ");
			put("48", "Οχτακόσια ");
			put("49", "Ενιακόσια ");
			
			put("51", "Δέκα ");
			put("52", "Είκοσι ");
			put("53", "Τριάντα ");
			put("54", "Σαράντα ");
			put("55", "Πενήντα ");
			put("56", "Εξήντα ");
			put("57", "Εβδομήντα ");
			put("58", "Ογδόντα ");
			put("59", "Ενεννήντα ");
			
			put("5161", "Έντεκα ");
			put("5162", "Δωδεκα ");
			
			put("61", "Ένα ");
			put("62", "Δύο ");
			put("63", "Τρία ");
			put("64", "Τέσσερα ");
			put("65", "Πέντε ");
			put("66", "Έξι ");
			put("67", "Εφτά ");
			put("68", "Οχτώ ");
			put("69", "Εννέα ");
			
			put("81", "Δέκα ");
			put("82", "Έικοσι ");
			put("83", "Τριάντα ");
			put("84", "Σαρράντα ");
			put("85", "Πενήντα ");
			put("86", "Εξήντα ");
			put("87", "Εβδομήντα ");
			put("88", "Ογδόντα ");
			put("89", "Ενεννήντα ");
			
			put("8191", "Έντεκα ");
			put("8192", "Δώδεκα ");
			
			put("91", "Ένα ");
			put("92", "Δύο ");
			put("93", "Τρία ");
			put("94", "Τέσσερα ");
			put("95", "Πέντε ");
			put("96", "Έξι ");
			put("97", "Εφτά ");
			put("98", "Οχτώ ");
			put("99", "Εννέα ");
			
		}};
		
		List<String> grossArray = new ArrayList<String>();
		String grossInWords = "";
		int length =  this.gross.toString().length();
		int i = 1;
		for (i = 1 ; i <= 9 - length; i++){
			grossArray.add("0");
		}
		for(char character : this.gross.toString().toCharArray()){
			i++;
			grossArray.add(String.valueOf(character));
		}
		
		//thousands
		if (!grossArray.get(0).equals("0") || !grossArray.get(1).equals("0") || !grossArray.get(2).equals("0")){
			grossInWords += map.get("1" + grossArray.get(0)) != null ? map.get("1" + grossArray.get(0)) : "" ;
			if (grossArray.get(0).equals("0") && grossArray.get(1).equals("0") && grossArray.get(2).equals("1")){
				grossInWords += "Χίλια ";
			} else if (grossArray.get(1).equals("1") && (grossArray.get(2).equals("1") || grossArray.get(2).equals("2")) ){
				grossInWords += map.get("21" + "3" + grossArray.get(2));
				grossInWords += "Χιλιάδες , ";
			} else {
				grossInWords += map.get("2" + grossArray.get(1)) != null ? map.get("2" + grossArray.get(1)) : "";
				grossInWords += map.get("3" + grossArray.get(2)) != null ? map.get("3" + grossArray.get(2)) : "";
				grossInWords += "Χιλιάδες , ";
			}
		}
		
		
		//hundreds
		if (!grossArray.get(3).equals("0") || !grossArray.get(4).equals("0") || !grossArray.get(5).equals("0")){
			grossInWords += map.get("4" + grossArray.get(3)) != null ? map.get("4" + grossArray.get(3)) : "";
			if (grossArray.get(4).equals("1") && (grossArray.get(5).equals("1") || grossArray.get(5).equals("2")) ){
				grossInWords += map.get("51" + "6" + grossArray.get(5));
			} else {
				grossInWords += map.get("5" + grossArray.get(4)) != null ? map.get("5" + grossArray.get(4)) : "";
				grossInWords += map.get("6" + grossArray.get(5)) != null ? map.get("6" + grossArray.get(5)) : "";
			}
			grossInWords += "Ευρώ";
		}
		
		if (grossArray.get(6).equals(".") && !this.gross.toString().endsWith(".00")){
			grossInWords += " και ";
			//cents
			if (grossArray.get(7).equals("1") && (grossArray.get(8).equals("1") || grossArray.get(8).equals("2")) ){
				grossInWords += map.get("81" + "9" + grossArray.get(8));
			} else {
				grossInWords += map.get("8" + grossArray.get(7)) != null ? map.get("8" + grossArray.get(7)) : "";
				grossInWords += map.get("9" + grossArray.get(8)) != null ? map.get("9" + grossArray.get(8)) : "";
			}
			grossInWords += "λεπτά.";
		} else {
			grossInWords += ".";
		}
		
		
//		System.out.println(grossInWords);
		
		return grossInWords;
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
