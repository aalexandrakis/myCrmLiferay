package com.aalexandrakis.mycrmliferay.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the payments database table.
 * 
 */
@Entity
@Table(name="payments")
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private String paymentId;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal amount;

	@Column(nullable=false)
	private int companyId;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date paymentDate;

	@Lob
	private byte[] paymentFile;

	@Column(nullable=false, length=100)
	private String paymentFileName;

	@Column(nullable=false, length=100)
	private String paymentFileType;

	@Column(nullable=false)
	private int supplierId;

	public Payment() {
	}

	public String getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
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

	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public byte[] getPaymentFile() {
		return this.paymentFile;
	}

	public void setPaymentFile(byte[] paymentFile) {
		this.paymentFile = paymentFile;
	}

	public String getPaymentFileName() {
		return this.paymentFileName;
	}

	public void setPaymentFileName(String paymentFileName) {
		this.paymentFileName = paymentFileName;
	}

	public String getPaymentFileType() {
		return this.paymentFileType;
	}

	public void setPaymentFileType(String paymentFileType) {
		this.paymentFileType = paymentFileType;
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

}