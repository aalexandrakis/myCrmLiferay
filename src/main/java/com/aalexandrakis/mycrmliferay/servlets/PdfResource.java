package com.aalexandrakis.mycrmliferay.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.Resource;
import javax.faces.context.FacesContext;

import com.aalexandrakis.mycrmliferay.daoimpl.InvoiceDaoImpl;
import com.aalexandrakis.mycrmliferay.models.InvoiceHeader;

public class PdfResource extends Resource {
	InvoiceHeader invoice;
	
	public PdfResource(InvoiceHeader invoice) {
		this.invoice = invoice;
	}
	@Override
	public InputStream getInputStream() throws IOException{
		try {
			return invoice.getInvoiceFile().getBinaryStream();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getRequestPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getResponseHeaders() {
		// TODO Auto-generated method stub
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("content", "application/pdf");
		return headers;
	}

	@Override
	public URL getURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean userAgentNeedsUpdate(FacesContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
