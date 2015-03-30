package com.aalexandrakis.mycrmliferay.beans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.aalexandrakis.mycrmliferay.daoimpl.OutcomeDaoImpl;
import com.aalexandrakis.mycrmliferay.models.OutcomeHeader;

@ManagedBean(name="dtOutcomeView")
@ViewScoped
public class OutcomeDetailsView implements Serializable {
	private OutcomeHeader outcome;
	private UploadedFile file;
	
	
	@PostConstruct
	public void init(){
		outcome = new OutcomeHeader();
	}

	public OutcomeHeader getOutcome() {
		return outcome;
	}

	public void setOutcome(OutcomeHeader outcome) {
		this.outcome = outcome;
	}
	
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void save(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        PortletRequest portletRequest = (PortletRequest) externalContext.getRequest();
        PortletSession portletSession = portletRequest.getPortletSession();
	    String companyId = (String)portletSession.getAttribute("companyId",PortletSession.APPLICATION_SCOPE);
	    String supplierId = (String)portletSession.getAttribute("supplierId",PortletSession.APPLICATION_SCOPE);
	    if (companyId == null){
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Λάθος", "Παρακαλώ επιλέξτε εταιρεία"));
	    }
	    if (supplierId == null){
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Λάθος", "Παρακαλώ επιλέξτε προμηθευτή"));
	    }
	    if (this.outcome.getOutcomeDate() == null){
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Λάθος", "Παρακαλώ επιλέξτε ημερομηνία"));
	    }
	    if (this.outcome.getOutcomeNumber() == null){
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Λάθος", "Παρακαλώ δώστε αριθμο παραστατικού"));
	    }
	   
	    if (facesContext.getMessageList().isEmpty()){
	    	outcome.calculate();
	    	outcome.setCompanyId(Integer.valueOf(companyId));
	    	outcome.setSupplierId(Integer.valueOf(supplierId));
	    	
	    	try {
				OutcomeDaoImpl.saveOutcome(outcome);
//				ModelMap parms = new ModelMap();
//			    parms.put("outcomeId", outcome.getOutcomeId().toString());
//		        
//			    JasperReport jasperReport = JasperCompileManager.compileReport(externalContext.getRealPath("/WEB-INF/reports/outcomePdf.jrxml"));
//	            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parms, Methods.getConnection(System.getenv("MYCRM_DB_USERNAME"),System.getenv("MYCRM_DB_PASSWORD")));
//	            ByteArrayOutputStream os = new ByteArrayOutputStream();
//	            JasperExportManager.exportReportToPdfStream(jasperPrint, os);
//	            os.close();
//				OutcomeDaoImpl.saveOutcome(outcome, os);
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Συγχαριτήρια", "Η έκδοση τιμολογίου ολοκήρώθηκε με επιτυχία."));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Παρουσιάστηκε σφάλμα", e.getMessage()));
			}
	    }
	    
	}
	
	public void fileUpload(FileUploadEvent event) {
		System.out.println("in file upload");
        outcome.setFileName(event.getFile().getFileName());
        outcome.setFileType(event.getFile().getContentType());
		
        byte[] fileInBytes;
		try {
			fileInBytes = IOUtils.toByteArray(event.getFile().getInputstream());
			outcome.setOutcomeFile(new SerialBlob(fileInBytes));
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}
