package com.aalexandrakis.mycrmliferay.resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.context.FacesContext;

import com.aalexandrakis.mycrmliferay.daoimpl.InvoiceDaoImpl;

public class InvoiceResource extends Resource {
	Integer invoiceId;
	
	// Public Constants
	public static final String CONTENT_TYPE = "application/pdf";
	public static final String RESOURCE_NAME = "export";

	private String requestPath;

	public InvoiceResource() {
		setLibraryName(InvoiceResourceHandler.LIBRARY_NAME);
		setResourceName(RESOURCE_NAME);
		setContentType(CONTENT_TYPE);
	}
	
	public InvoiceResource(Integer invoiceId){
		this();
		this.invoiceId = invoiceId;
	}
	
	
	@Override
	public InputStream getInputStream() throws IOException {
		System.out.println("In Get Input Stream " + invoiceId);
		try {
			return InvoiceDaoImpl.getInvoice(Integer.valueOf(invoiceId)).getInvoiceFile().getBinaryStream();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getRequestPath() {
		// TODO Auto-generated method stub
		if (requestPath == null) {
			StringBuilder buf = new StringBuilder();
			buf.append(ResourceHandler.RESOURCE_IDENTIFIER);
			buf.append("/");
			buf.append(getResourceName());
			buf.append("?ln=");
			buf.append(getLibraryName());
			buf.append("&");
			buf.append("invoiceId");
			buf.append("=");
			buf.append(invoiceId.toString());
			requestPath = buf.toString();
		}

		return requestPath;
	}

	
	@Override
	public URL getURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean userAgentNeedsUpdate(FacesContext arg0) {
		return true;
	}

	@Override
	public Map<String, String> getResponseHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}
