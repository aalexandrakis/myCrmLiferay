package com.aalexandrakis.mycrmliferay.beans;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.springframework.ui.ModelMap;

import com.aalexandrakis.mycrmliferay.commons.Methods;
import com.aalexandrakis.mycrmliferay.daoimpl.InvoiceDaoImpl;
import com.aalexandrakis.mycrmliferay.models.InvoiceHeader;

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
	    if (companyId == null){
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Λάθος", "Παρακαλώ επιλέξτε εταιρεία"));
	    }
	    if (customerId == null){
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Λάθος", "Παρακαλώ επιλέξτε πελάτη"));
	    }
	    if (this.invoice.getInvoiceDate() == null){
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Λάθος", "Παρακαλώ επιλέξτε ημερομηνία"));
	    }
	    if (this.invoice.getFpa() == null){
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Λάθος", "Παρακαλώ επιλέξτε ποσοστό Φ.Π.Α"));
	    }
	    if (this.invoice.getWithHolding() == null){
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Λάθος", "Παρακαλώ επιλέξτε ποσοστό παρακράτησης"));
	    }
	    
	    if (facesContext.getMessageList().isEmpty()){
	    	invoice.calculate();
	    	invoice.setCompanyId(Integer.valueOf(companyId));
	    	invoice.setCustomerId(Integer.valueOf(customerId));
	    	try {
				InvoiceDaoImpl.saveInvoice(invoice);
				ModelMap parms = new ModelMap();
			    parms.put("invoiceId", invoice.getInvoiceId().toString());
		        
			    JasperReport jasperReport = JasperCompileManager.compileReport(externalContext.getRealPath("/WEB-INF/reports/invoicePdf.jrxml"));
	            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parms, Methods.getConnection(System.getenv("MYCRM_DB_USERNAME"),System.getenv("MYCRM_DB_PASSWORD")));
	            ByteArrayOutputStream os = new ByteArrayOutputStream();
	            JasperExportManager.exportReportToPdfStream(jasperPrint, os);
	            os.close();
				InvoiceDaoImpl.saveInvoice(invoice, os);
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Συγχαριτήρια", "Η έκδοση τιμολογίου ολοκήρώθηκε με επιτυχία."));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Παρουσιάστηκε σφάλμα", e.getMessage()));
			}
	    }
	}
}
