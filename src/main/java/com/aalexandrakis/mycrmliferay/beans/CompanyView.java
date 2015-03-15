package com.aalexandrakis.mycrmliferay.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import com.aalexandrakis.mycrmliferay.daoimpl.CompanyInfoDaoImpl;
import com.aalexandrakis.mycrmliferay.models.CompanyInfo;

@ManagedBean(name="dtCompanyView")
@SessionScoped
public class CompanyView implements Serializable {

	private CompanyInfo company;
	
	@PostConstruct
	public void init(){
		try {
			company = CompanyInfoDaoImpl.getCompanyInfo(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CompanyInfo getCompany() {
		return company;
	}

	public void setCompany(CompanyInfo company) {
		this.company = company;
	}

	public void saveCompanyA(){
		System.out.println(" Company name :" + company.getName());
		try {
			if (company.getCompanyId() != null){
				CompanyInfoDaoImpl.updateCompanyInfo(company);
			} else {
				CompanyInfoDaoImpl.saveCompanyInfo(company);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
