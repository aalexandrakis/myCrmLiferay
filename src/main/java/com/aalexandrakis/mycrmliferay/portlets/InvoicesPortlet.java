package com.aalexandrakis.mycrmliferay.portlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.faces.GenericFacesPortlet;

import org.apache.commons.io.IOUtils;

import com.aalexandrakis.mycrmliferay.daoimpl.InvoiceDaoImpl;
import com.aalexandrakis.mycrmliferay.models.InvoiceHeader;

public class InvoicesPortlet extends GenericFacesPortlet {
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException, IOException {

		if (resourceRequest.getResourceID() != null && resourceRequest.getResourceID().length() > 0){
			try {
				InvoiceHeader invoice = InvoiceDaoImpl.getInvoice(Integer.valueOf(resourceRequest.getParameter("fileID")));
				resourceResponse.setContentType("application/pdf");
				OutputStream os = resourceResponse.getPortletOutputStream();
				IOUtils.copy(invoice.getInvoiceFile().getBinaryStream(), os);
				os.flush();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.serveResource(resourceRequest, resourceResponse);
		}
	}
	
}
