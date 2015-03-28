package com.aalexandrakis.mycrmliferay.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import com.aalexandrakis.mycrmliferay.daoimpl.InvoiceDaoImpl;
import com.aalexandrakis.mycrmliferay.models.InvoiceHeader;

@ManagedBean(name="dtInvoicesView")
@ViewScoped
public class InvoicesView implements Serializable {
	private List<InvoiceHeader> invoices;
	private InvoiceHeader selectedInvoiceHeader;
	private BigDecimal sumAmount;
	private BigDecimal sumFpa;
	private BigDecimal sumGross;
	private Date dateFrom;
	private Date dateTo;
	private String companyId;
	private String customerId;
	
	Date date = new Date();
	SimpleDateFormat df = new SimpleDateFormat();
	SimpleDateFormat old_df = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat new_df = new SimpleDateFormat("yyyy-MM-dd");


	@PostConstruct
	public void init(){
//		System.out.println("init routine");
		df.applyPattern("yyyy");
		try {
			if (dateFrom == null){
					dateFrom = old_df.parse("01/01/" + df.format(date));
			}
			if (dateTo == null){
				dateTo = old_df.parse("31/12/" + df.format(date));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String getDateFromToString(){
		return new_df.format(dateFrom);
	}
	
	public String getDateToToString(){
		return new_df.format(dateTo);
	}
	
	public void searchInvoices(){
		Map<String, Object> parms = new HashMap<String, Object>();
		try {
			parms.put("dateFrom", new_df.format(dateFrom));
			parms.put("dateTo", new_df.format(dateTo));
		} catch (Exception e){
			System.out.println("Could not parse date");
		}
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        PortletRequest portletRequest = (PortletRequest) externalContext.getRequest();
        PortletSession portletSession = portletRequest.getPortletSession();
        
	    companyId = (String)portletSession.getAttribute("companyId",PortletSession.APPLICATION_SCOPE);
	    customerId = (String)portletSession.getAttribute("customerId",PortletSession.APPLICATION_SCOPE);

		if (customerId != null){
			parms.put("customerId", customerId);
		}

		if (companyId != null){
			parms.put("companyId", companyId);
		}
		sumAmount = BigDecimal.ZERO;
		sumFpa = BigDecimal.ZERO;
		sumGross = BigDecimal.ZERO;
		try {
			invoices = InvoiceDaoImpl.getInvoices(parms);
			for (InvoiceHeader invoice : invoices){
				sumAmount = sumAmount.add(invoice.getAmount());
				sumFpa = sumFpa.add(invoice.getFpaAmount());
				sumGross = sumGross.add(invoice.getGross());
			}
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
	
	
	public BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	public BigDecimal getSumFpa() {
		return sumFpa;
	}

	public void setSumFpa(BigDecimal sumFpa) {
		this.sumFpa = sumFpa;
	}

	public BigDecimal getSumGross() {
		return sumGross;
	}

	public void setSumGross(BigDecimal sumGross) {
		this.sumGross = sumGross;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	
}
