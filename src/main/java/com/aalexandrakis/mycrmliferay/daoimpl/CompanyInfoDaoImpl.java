package com.aalexandrakis.mycrmliferay.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.aalexandrakis.mycrmliferay.models.CompanyInfo;
import com.aalexandrakis.mycrmliferay.util.HibernateUtil;

public class CompanyInfoDaoImpl {
	
	public static void saveCompanyInfo(CompanyInfo companyInfo) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(companyInfo);
		session.getTransaction().commit();
		session.close();
	}


	public static void updateCompanyInfo(CompanyInfo companyInfo) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(companyInfo);
		session.getTransaction().commit();
		session.close();
	}

	public static CompanyInfo getCompanyInfo(Integer companyId) throws Exception{
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		CompanyInfo companyInfo = (CompanyInfo) session.get(CompanyInfo.class, companyId);
		session.getTransaction().commit();
		session.close();
		return companyInfo;
	}

	@SuppressWarnings("unchecked")
	public static List<CompanyInfo> getCompaniesInfo(Map<String, String> parms) throws Exception{
		String query = "from CompanyInfo";
		if (parms != null){
			int counter = 0;
			for (String key : parms.keySet()){
				if (counter == 0){
					query += " where ";
				} else {
					query += " and ";
				}
				query += key + " like '" + parms.get(key) + "%'";
				counter++;
			}
		}
		List<CompanyInfo> companiesInfo = new ArrayList<CompanyInfo>();
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		companiesInfo = session.createQuery(query).list();
		session.getTransaction().commit();
		session.close();
		
		return companiesInfo;
	}
}
