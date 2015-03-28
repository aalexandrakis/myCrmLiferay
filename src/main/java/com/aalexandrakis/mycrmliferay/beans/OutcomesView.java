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

import com.aalexandrakis.mycrmliferay.daoimpl.OutcomeDaoImpl;
import com.aalexandrakis.mycrmliferay.models.OutcomeHeader;

@ManagedBean(name="dtOutcomesView")
@ViewScoped
public class OutcomesView implements Serializable {
	private List<OutcomeHeader> outcomes;
	private OutcomeHeader selectedOutcomeHeader;
	private BigDecimal sumAmount;
	private BigDecimal sumFpa;
	private BigDecimal sumGross;
	private Date dateFrom;
	private Date dateTo;
	private String companyId;
	private String supplierId;
	
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
	
	public void searchOutcomes(){
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
	    supplierId = (String)portletSession.getAttribute("supplierId",PortletSession.APPLICATION_SCOPE);

		if (supplierId != null){
			parms.put("supplierId", supplierId);
		}

		if (companyId != null){
			parms.put("companyId", companyId);
		}
		sumAmount = BigDecimal.ZERO;
		sumFpa = BigDecimal.ZERO;
		sumGross = BigDecimal.ZERO;
		try {
			outcomes = OutcomeDaoImpl.getOutcomes(parms);
			for (OutcomeHeader outcome : outcomes){
				sumAmount = sumAmount.add(outcome.getAmount());
				sumFpa = sumFpa.add(outcome.getFpaAmount());
				sumGross = sumGross.add(outcome.getGross());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public List<OutcomeHeader> getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(List<OutcomeHeader> outcomes) {
		this.outcomes = outcomes;
	}

	public OutcomeHeader getSelectedOutcomeHeader() {
		return selectedOutcomeHeader;
	}

	public void setSelectedOutcomeHeader(OutcomeHeader selectedOutcomeHeader) {
		this.selectedOutcomeHeader = selectedOutcomeHeader;
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

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	
}
