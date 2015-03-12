package com.aalexandrakis.mycrmliferay.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.aalexandrakis.mycrmliferay.daoimpl.CompanyInfoDaoImpl;
import com.aalexandrakis.mycrmliferay.models.CompanyInfo;

@ManagedBean(name="dtCompaniesView")
@ViewScoped
public class CompaniesView implements Serializable {
	private List<CompanyInfo> companies;
	private CompanyInfo selectedCompany;
	
	@PostConstruct
	public void init(){
		try {
			companies = CompanyInfoDaoImpl.getCompaniesInfo(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<CompanyInfo> getCompanies() {
		return companies;
	}

	public void setCompanies(List<CompanyInfo> companies) {
		this.companies = companies;
	}

	public CompanyInfo getSelectedCompany() {
//		System.out.println("Selected company " + selectedCompany.getName());
		return selectedCompany;
	}

	public void setSelectedCompany(CompanyInfo selectedCompany) {
		System.out.println("Selected company " + selectedCompany.getName());
		this.selectedCompany = selectedCompany;
	}
	
	public void saveCompany(){
		System.out.println(" Company name :" + selectedCompany.getName());
		try {
			if (selectedCompany.getCompanyId() != null){
				CompanyInfoDaoImpl.updateCompanyInfo(selectedCompany);
			} else {
				CompanyInfoDaoImpl.saveCompanyInfo(selectedCompany);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	 public void onRowSelect(SelectEvent event) {
//	        FacesMessage msg = new FacesMessage("Company Selected", ((CompanyInfo) event.getObject()).getCompanyId().toString());
//	        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
// 
//    public void onRowUnselect(UnselectEvent event) {
//        FacesMessage msg = new FacesMessage("Company Unselected", ((CompanyInfo) event.getObject()).getCompanyId().toString());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
	
}
