package com.aalexandrakis.mycrmliferay.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the suppliers database table.
 * 
 */
@Entity
@Table(name="suppliers")
@NamedQuery(name="Supplier.findAll", query="SELECT s FROM Supplier s")
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, name="supplierId")
	private Integer supplierId;

	@Column(nullable=false, length=50, name="supplierAddress")
	private String supplierAddress;

	@Column(nullable=false, length=9, name="supplierAfm")
	private String supplierAfm;

	@Column(nullable=false, length=150, name="supplierBusDesc")
	private String supplierBusDesc;

	@Column(nullable=false, length=50, name="supplierDoy")
	private String supplierDoy;

	@Column(nullable=false, length=50, name="supplierName")
	private String supplierName;

	@Column(nullable=false, length=10, name="supplierPhone")
	private String supplierPhone;

	public Supplier() {
	}

	public Integer getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierAddress() {
		return this.supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierAfm() {
		return this.supplierAfm;
	}

	public void setSupplierAfm(String supplierAfm) {
		this.supplierAfm = supplierAfm;
	}

	public String getSupplierBusDesc() {
		return this.supplierBusDesc;
	}

	public void setSupplierBusDesc(String supplierBusDesc) {
		this.supplierBusDesc = supplierBusDesc;
	}

	public String getSupplierDoy() {
		return this.supplierDoy;
	}

	public void setSupplierDoy(String supplierDoy) {
		this.supplierDoy = supplierDoy;
	}

	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierPhone() {
		return this.supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

}