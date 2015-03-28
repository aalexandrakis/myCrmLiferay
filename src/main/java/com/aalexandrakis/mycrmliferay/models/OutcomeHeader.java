package com.aalexandrakis.mycrmliferay.models;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the outcomeHeader database table.
 * 
 */
@Entity
@Table(name="outcomeHeader")
@NamedQuery(name="OutcomeHeader.findAll", query="SELECT o FROM OutcomeHeader o")
public class OutcomeHeader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer outcomeId;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal amount;

	@Column(nullable=false)
	private int companyId;

	@Lob
	@Column(nullable=false)
	private String fileName;

	@Lob
	@Column(nullable=false)
	private String fileType;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal fpaAmount;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal gross;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date outcomeDate;

	@Lob
	@Column(nullable=false)
	private Blob outcomeFile;

	@Column(nullable=false, length=15)
	private String outcomeNumber;

	@Column(nullable=false)
	private int supplierId;

	//bi-directional many-to-one association to OutcomeDetail
	@OneToMany(mappedBy="outcomeHeader")
	private List<OutcomeDetail> outcomeDetails;

	@Transient
	private CompanyInfo companyInfo;
	
	@Transient
	private Supplier supplier;
	
	public OutcomeHeader() {
	}

	public Integer getOutcomeId() {
		return this.outcomeId;
	}

	public void setOutcomeId(Integer outcomeId) {
		this.outcomeId = outcomeId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public BigDecimal getFpaAmount() {
		return this.fpaAmount;
	}

	public void setFpaAmount(BigDecimal fpaAmount) {
		this.fpaAmount = fpaAmount;
	}

	public BigDecimal getGross() {
		return this.gross;
	}

	public void setGross(BigDecimal gross) {
		this.gross = gross;
	}

	public Date getOutcomeDate() {
		return this.outcomeDate;
	}

	public void setOutcomeDate(Date outcomeDate) {
		this.outcomeDate = outcomeDate;
	}

	public Blob getOutcomeFile() {
		return this.outcomeFile;
	}

	public void setOutcomeFile(Blob outcomeFile) {
		this.outcomeFile = outcomeFile;
	}

	public String getOutcomeNumber() {
		return this.outcomeNumber;
	}

	public void setOutcomeNumber(String outcomeNumber) {
		this.outcomeNumber = outcomeNumber;
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public List<OutcomeDetail> getOutcomeDetails() {
		return this.outcomeDetails;
	}

	public void setOutcomeDetails(List<OutcomeDetail> outcomeDetails) {
		this.outcomeDetails = outcomeDetails;
	}

	public OutcomeDetail addOutcomeDetail(OutcomeDetail outcomeDetail) {
		getOutcomeDetails().add(outcomeDetail);
		outcomeDetail.setOutcomeHeader(this);

		return outcomeDetail;
	}

	public OutcomeDetail removeOutcomeDetail(OutcomeDetail outcomeDetail) {
		getOutcomeDetails().remove(outcomeDetail);
		outcomeDetail.setOutcomeHeader(null);

		return outcomeDetail;
	}

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	

}