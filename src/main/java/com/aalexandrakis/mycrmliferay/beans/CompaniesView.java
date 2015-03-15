package com.aalexandrakis.mycrmliferay.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.PortletResponse;
import javax.portlet.WindowStateException;

import com.aalexandrakis.mycrmliferay.daoimpl.CompanyInfoDaoImpl;
import com.aalexandrakis.mycrmliferay.models.CompanyInfo;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.util.PortalUtil;

@ManagedBean(name="dtCompaniesView")
@ViewScoped
public class CompaniesView implements Serializable {
	private List<CompanyInfo> companies;
	private CompanyInfo selectedCompany;
	private LiferayPortletURL portletRenderURL;
	
	
	@PostConstruct
	public void init(){
		System.out.println("init routine");
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
	
	
	public LiferayPortletURL getPortletRenderURL() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		        ExternalContext externalContext = facesContext.getExternalContext();
		        PortletResponse portletResponse = (PortletResponse) externalContext.getResponse();
		
		        LiferayPortletResponse liferayPortletResponse =
		            PortalUtil.getLiferayPortletResponse(portletResponse);
		
		        LiferayPortletURL liferayPortletURL =  liferayPortletResponse.createLiferayPortletURL(
		           "company_WAR_myCrmLiferayportlet", "Company");
		
		        try {
					liferayPortletURL.setWindowState(LiferayWindowState.MAXIMIZED);
				} catch (WindowStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		        return liferayPortletURL;
	}

	public void setPortletRenderURL(LiferayPortletURL portletRenderURL) {
		this.portletRenderURL = portletRenderURL;
	}

	public void saveCompany(){
		System.out.println(" Company name :" + selectedCompany.getName());
		try {
			if (selectedCompany.getCompanyId() != null){
				System.out.println(" Update company  :" + selectedCompany.getName());
				CompanyInfoDaoImpl.updateCompanyInfo(selectedCompany);
			} else {
				System.out.println(" New company  :" + selectedCompany.getName());
				CompanyInfoDaoImpl.saveCompanyInfo(selectedCompany);
			}
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public CompanyInfo addCompany(){
		this.selectedCompany = new CompanyInfo();
		return selectedCompany;
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
