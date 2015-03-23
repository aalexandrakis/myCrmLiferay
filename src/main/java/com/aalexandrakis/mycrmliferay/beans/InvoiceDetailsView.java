package com.aalexandrakis.mycrmliferay.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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


	public void calculate(){
		
	}
	
}
