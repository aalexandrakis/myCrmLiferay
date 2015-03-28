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

import com.aalexandrakis.mycrmliferay.daoimpl.SupplierDaoImpl;
import com.aalexandrakis.mycrmliferay.models.Supplier;

@ManagedBean(name="dtSelectSupplierView")
@ViewScoped
public class SelectSupplierView implements Serializable {
	private Supplier supplier;
	private Integer filterSupplierId;
	private String filterName;
	private String filterAfm;
	private List<Supplier> suppliers;
	
	@PostConstruct
	public void init(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        PortletRequest portletRequest = (PortletRequest) externalContext.getRequest();
        PortletSession portletSession = portletRequest.getPortletSession();
        if (portletSession.getAttribute("supplierId", PortletSession.APPLICATION_SCOPE) != null){
        	portletSession.removeAttribute("supplierId", PortletSession.APPLICATION_SCOPE);
        }
	}

	
	public Integer getFilterSupplierId() {
		return filterSupplierId;
	}

	public void setFilterSupplierId(Integer filterSupplierId) {
		this.filterSupplierId = filterSupplierId;
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}
	
	public void selectSupplierFromDialog(Supplier supplier){
		this.supplier = supplier;
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        PortletRequest portletRequest = (PortletRequest) externalContext.getRequest();
        PortletSession portletSession = portletRequest.getPortletSession();
	    portletSession.setAttribute("supplierId", supplier.getSupplierId().toString() ,PortletSession.APPLICATION_SCOPE);
	}
				
	public void selectSupplier(){
		try {
			this.suppliers = SupplierDaoImpl.getSuppliers(null);
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
        if (portletSession.getAttribute("supplierId", PortletSession.APPLICATION_SCOPE) != null){
        	portletSession.removeAttribute("supplierId", PortletSession.APPLICATION_SCOPE);
        }
        supplier = null;

	}
}
