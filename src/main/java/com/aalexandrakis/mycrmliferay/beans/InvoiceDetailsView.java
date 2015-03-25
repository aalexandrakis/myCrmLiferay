package com.aalexandrakis.mycrmliferay.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import org.omg.CORBA.Request;

import com.aalexandrakis.mycrmliferay.models.InvoiceHeader;
import com.liferay.faces.portal.bean.Liferay;

@ManagedBean(name="dtInvoiceView")
@ViewScoped
public class InvoiceDetailsView implements Serializable {
	private InvoiceHeader invoice;
	
	@PostConstruct
	public void init(){
		invoice = new InvoiceHeader();
	}

	public InvoiceHeader getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceHeader invoice) {
		this.invoice = invoice;
	}
	
	public void save(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        PortletRequest portletRequest = (PortletRequest) externalContext.getRequest();
        PortletSession portletSession = portletRequest.getPortletSession();
	    String companyId = (String)portletSession.getAttribute("companyId",PortletSession.APPLICATION_SCOPE);
	    System.out.println("Company " + companyId);
	    String customerId = (String)portletSession.getAttribute("customerId",PortletSession.APPLICATION_SCOPE);
	    System.out.println("Customer " + customerId);

	}
}
