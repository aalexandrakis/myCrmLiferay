package com.aalexandrakis.mycrmliferay.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.aalexandrakis.mycrmliferay.daoimpl.CompanyInfoDaoImpl;
import com.aalexandrakis.mycrmliferay.daoimpl.InvoiceDaoImpl;
import com.aalexandrakis.mycrmliferay.models.CompanyInfo;
import com.aalexandrakis.mycrmliferay.models.InvoiceHeader;

@ManagedBean(name="dtSelectCompanyView")
@ViewScoped
public class SelectCompanyView implements Serializable {
	private CompanyInfo company;
	private Integer filterCompanyId;
	private String filterName;
	private String filterAfm;
	private List<CompanyInfo> companies;
	
	public Integer getFilterCompanyId() {
		return filterCompanyId;
	}

	public void setFilterCompanyId(Integer filterCompanyId) {
		this.filterCompanyId = filterCompanyId;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public String getFilterAfm() {
		return filterAfm;
	}

	public void setFilterAfm(String filterAfm) {
		this.filterAfm = filterAfm;
	}

	public CompanyInfo getCompany() {
		return company;
	}

	public void setCompany(CompanyInfo company) {
		this.company = company;
	}

	public List<CompanyInfo> getCompanies() {
		return companies;
	}

	public void setCompanies(List<CompanyInfo> companies) {
		this.companies = companies;
	}
	
	public void selectCompanyFromDialog(CompanyInfo company){
		this.company = company;
	}
				
	public void selectCompany(){
		try {
			this.companies = CompanyInfoDaoImpl.getCompaniesInfo(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
