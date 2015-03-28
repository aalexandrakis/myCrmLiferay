package com.aalexandrakis.mycrmliferay.beans;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

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
	
	@PostConstruct
	public void init(){
		System.out.println("init routine");
		sumAmount = BigDecimal.ZERO;
		sumFpa = BigDecimal.ZERO;
		sumGross = BigDecimal.ZERO;
		try {
			invoices = InvoiceDaoImpl.getInvoices(null);
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
	
	public void showPdf(ActionEvent actionEvent){
		System.out.println("In Action Event");
		HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/pdf");
//		response.setContentLength((int) selectedInvoiceHeader.getInvoiceFile().length());
		try {
			ServletOutputStream out = response.getOutputStream();
			IOUtils.copy(selectedInvoiceHeader.getInvoiceFile().getBinaryStream(), out);
			out.flush();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
