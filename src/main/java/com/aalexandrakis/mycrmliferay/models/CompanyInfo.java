package com.aalexandrakis.mycrmliferay.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="companyInfo")
public class CompanyInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="companyId")
	private Integer companyId;
	@Column(name="name")
	@NotEmpty
	@NotNull
	private String name;
	@NotNull @NotEmpty @Column(name="busDesc")
	private String busDesc;
	@NotNull @NotEmpty @Column(name="address")
	private String address;
	@NotNull @NotEmpty @Size(min=9, max=9) @Column(name="afm")
	private String afm;
	@NotNull @NotEmpty @Column(name="doy")
	private String doy;
	@NotNull @NotEmpty @Column(name="mobilePhone")
	private String mobilePhone;
	@NotNull @NotEmpty @Column(name="workPhone")
	private String workPhone;
	@NotNull @NotEmpty @Email @Column(name="email")
	private String email;
	
	public CompanyInfo() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusDesc() {
		return busDesc;
	}

	public void setBusDesc(String busDesc) {
		this.busDesc = busDesc;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAfm() {
		return afm;
	}

	public void setAfm(String afm) {
		this.afm = afm;
	}

	public String getDoy() {
		return doy;
	}

	public void setDoy(String doy) {
		this.doy = doy;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
