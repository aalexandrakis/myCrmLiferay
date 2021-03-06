package com.aalexandrakis.mycrmliferay.portlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.faces.GenericFacesPortlet;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.commons.io.IOUtils;
import org.springframework.ui.ModelMap;

import com.aalexandrakis.mycrmliferay.commons.Methods;
import com.aalexandrakis.mycrmliferay.daoimpl.InvoiceDaoImpl;
import com.aalexandrakis.mycrmliferay.models.InvoiceHeader;

public class InvoicesPortlet extends GenericFacesPortlet {

	SimpleDateFormat eur_df = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat iso_df = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException, IOException {
		
		if (resourceRequest.getResourceID() != null && resourceRequest.getResourceID().length() > 0){
			if (resourceRequest.getResourceID().equals("invoiceResource")){
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
			} else if(resourceRequest.getResourceID().equals("printInvoiceList")){
				try {
					resourceResponse.setContentType("application/pdf");
					ModelMap parms = new ModelMap();
					parms.put("REPORT_CONNECTION", Methods.getConnection(System.getenv("MYCRM_DB_USERNAME"),System.getenv("MYCRM_DB_PASSWORD")));
				    parms.put("dateFrom", iso_df.parse(resourceRequest.getParameter("dateFrom")));
				    parms.put("dateTo", iso_df.parse(resourceRequest.getParameter("dateTo")));
				    JasperReport jasperReport = JasperCompileManager.compileReport(this.getPortletContext().getRealPath("/WEB-INF/reports/incomesPdf.jrxml"));
		            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parms, Methods.getConnection(System.getenv("MYCRM_DB_USERNAME"),System.getenv("MYCRM_DB_PASSWORD")));
		            OutputStream os = resourceResponse.getPortletOutputStream();
		            JasperExportManager.exportReportToPdfStream(jasperPrint, os);
		            os.close();
				} catch (Exception e){
					e.printStackTrace();
				}
			} else if(resourceRequest.getResourceID().equals("downloadFiles")){
				try {
					resourceResponse.setContentType("application/zip");
					resourceResponse.setProperty("Content-Disposition","inline; filename=incomes.zip;");
					OutputStream outputStream = resourceResponse.getPortletOutputStream();
					Map<String, Object> parms = new HashMap<String, Object>();
					parms.put("dateFrom", resourceRequest.getParameter("dateFrom"));
				    parms.put("dateTo", resourceRequest.getParameter("dateTo"));
					System.out.println("Could not parse date");

					List<InvoiceHeader> invoices  = InvoiceDaoImpl.getInvoices(parms);
					ZipOutputStream zip = new ZipOutputStream(outputStream);
					for (InvoiceHeader invoiceObject : invoices){
						zip.putNextEntry(new ZipEntry("invoiceNo" + invoiceObject.getInvoiceId().toString() + ".pdf"));
						byte[] b = new byte[1024];
						int len;
						InputStream inputStream = invoiceObject.getInvoiceFile().getBinaryStream();
						while((len = inputStream.read(b)) != -1){
							zip.write(b, 0, len);
						}
						zip.closeEntry();
					}
					zip.flush();
					zip.close();

					outputStream.flush();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		} else {
			super.serveResource(resourceRequest, resourceResponse);
		}
	}
	
}
