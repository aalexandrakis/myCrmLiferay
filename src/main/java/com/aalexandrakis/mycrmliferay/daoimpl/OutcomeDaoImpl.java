package com.aalexandrakis.mycrmliferay.daoimpl;

import java.io.InputStream;
import java.sql.Blob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.aalexandrakis.mycrmliferay.models.CompanyInfo;
import com.aalexandrakis.mycrmliferay.models.OutcomeDetail;
import com.aalexandrakis.mycrmliferay.models.OutcomeHeader;
import com.aalexandrakis.mycrmliferay.models.Supplier;
import com.aalexandrakis.mycrmliferay.util.HibernateUtil;

public class OutcomeDaoImpl {
	public static final DateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	public static OutcomeHeader saveOutcome(OutcomeHeader outcome, InputStream file, String fileName, String contentType) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		try{
			byte [] fileInBytes = IOUtils.toByteArray(file);
			Blob blob = Hibernate.getLobCreator(session).createBlob(fileInBytes);
			if (fileInBytes.length > 0){
	            outcome.setFileName(fileName);
	            outcome.setOutcomeFile(blob);
	            outcome.setFileType(contentType);
			} else {
				OutcomeHeader outcomeOld = getOutcome(outcome.getOutcomeId());
				outcome.setFileName(outcomeOld.getFileName());
	            outcome.setOutcomeFile(outcomeOld.getOutcomeFile());
	            outcome.setFileType(outcomeOld.getFileType());
			}			
			session.beginTransaction();
			session.saveOrUpdate(outcome);
			Integer outcomeId = outcome.getOutcomeId();
//			for (OutcomeDetail outcomeLine : outcome.getOutcomeDetails()){
//				outcomeLine.setOutcomeId(outcomeId);
//				session.saveOrUpdate(outcomeLine);
//			}
			session.getTransaction().commit();
			return outcome;
		} catch (Exception e){
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public static OutcomeHeader saveOutcome(OutcomeHeader outcome) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(outcome);
			Integer outcomeId = outcome.getOutcomeId();
//			for (OutcomeDetail outcomeLine : outcome.getOutcomeDetails()){
//				outcomeLine.setOutcomeId(outcomeId);
//				session.saveOrUpdate(outcomeLine);
//			}
			session.getTransaction().commit();
			return outcome;
		} catch (Exception e){
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<OutcomeHeader> getOutcomes(Map<String, Object> parms){
		String query = "from OutcomeHeader";
		if (parms != null){
			int i = 0;
			for (String key : parms.keySet()){
				if (key.endsWith("Id")){
					if (i==0){
						query += " where ";
					} else {
						query += " and ";
					}
					query += key + " = " + parms.get(key);
					i++;
				}
			}
			if (parms.containsKey("dateFrom") && parms.containsKey("dateTo")){
				if (i==0){
					query += " where ";
				} else {
					query += " and ";
				}
				query += "outcomeDate between '" + parms.get("dateFrom") + "' and '" + parms.get("dateTo") + "'";
			}
		}
		System.out.println(query);
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<OutcomeHeader> outcomeList = session.createQuery(query).list();
		
		for (OutcomeHeader outcome : outcomeList){
			outcome.setSupplier((Supplier) session.get(Supplier.class, outcome.getSupplierId()));
			outcome.setCompanyInfo((CompanyInfo) session.get(CompanyInfo.class, outcome.getCompanyId()));
		}
		
		session.getTransaction().commit();
		session.close();
		return outcomeList;
	}
	
	public static OutcomeHeader getOutcome(Integer outcomeId) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			OutcomeHeader outcome = (OutcomeHeader) session.get(OutcomeHeader.class, outcomeId);
			outcome.setCompanyInfo((CompanyInfo) session.get(CompanyInfo.class, outcome.getCompanyId()));
			outcome.setSupplier((Supplier) session.get(Supplier.class, outcome.getSupplierId()));
			return outcome;
		} catch (Exception e){
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
}
