package com.aalexandrakis.mycrmliferay.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.aalexandrakis.mycrmliferay.daoimpl.InvoiceDaoImpl;
import com.aalexandrakis.mycrmliferay.models.InvoiceHeader;

@ManagedBean(name="dtInvoicesView")
@ViewScoped
public class InvoicesView implements Serializable {
	private List<InvoiceHeader> invoices;
	private InvoiceHeader selectedInvoiceHeader;
	
	
	@PostConstruct
	public void init(){
		System.out.println("init routine");
		try {
			invoices = InvoiceDaoImpl.getInvoices(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<InvoiceHeader> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<InvoiceHeader> invoices) {
		this.invoices = invoices;
	}

	public InvoiceHeader getSelectedInvoiceHeader() {
		return selectedInvoiceHeader;
	}

	public void setSelectedInvoiceHeader(InvoiceHeader selectedInvoiceHeader) {
		this.selectedInvoiceHeader = selectedInvoiceHeader;
	}
	
	public void saveInvoice(){
		try {
			InvoiceDaoImpl.saveInvoice(selectedInvoiceHeader);
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public InvoiceHeader addInvoiceHeader(){
		this.selectedInvoiceHeader = new InvoiceHeader();
		return selectedInvoiceHeader;
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
