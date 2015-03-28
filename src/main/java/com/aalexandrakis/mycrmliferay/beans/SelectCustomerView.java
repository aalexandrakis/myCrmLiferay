package com.aalexandrakis.mycrmliferay.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import com.aalexandrakis.mycrmliferay.daoimpl.CustomerDaoImpl;
import com.aalexandrakis.mycrmliferay.models.Customer;

@ManagedBean(name="dtSelectCustomerView")
@ViewScoped
public class SelectCustomerView implements Serializable {
	private Customer customer;
	private Integer filterCustomerId;
	private String filterName;
	private String filterAfm;
	private List<Customer> customers;
	
	@PostConstruct
	public void init(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        PortletRequest portletRequest = (PortletRequest) externalContext.getRequest();
        PortletSession portletSession = portletRequest.getPortletSession();
        if (portletSession.getAttribute("customerId", PortletSession.APPLICATION_SCOPE) != null){
        	portletSession.removeAttribute("customerId", PortletSession.APPLICATION_SCOPE);
        }
	}

	
	public Integer getFilterCustomerId() {
		return filterCustomerId;
	}

	public void setFilterCustomerId(Integer filterCustomerId) {
		this.filterCustomerId = filterCustomerId;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public void selectCustomerFromDialog(Customer customer){
		this.customer = customer;
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        PortletRequest portletRequest = (PortletRequest) externalContext.getRequest();
        PortletSession portletSession = portletRequest.getPortletSession();
	    portletSession.setAttribute("customerId", customer.getCustomerId().toString() ,PortletSession.APPLICATION_SCOPE);
	}
				
	public void selectCustomer(){
		try {
			this.customers = CustomerDaoImpl.getCustomers(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clearSelection(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        PortletRequest portletRequest = (PortletRequest) externalContext.getRequest();
        PortletSession portletSession = portletRequest.getPortletSession();
        if (portletSession.getAttribute("customerId", PortletSession.APPLICATION_SCOPE) != null){
        	portletSession.removeAttribute("customerId", PortletSession.APPLICATION_SCOPE);
        }
        customer = null;

	}
}
