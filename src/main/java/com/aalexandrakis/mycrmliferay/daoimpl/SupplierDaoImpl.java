package com.aalexandrakis.mycrmliferay.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.aalexandrakis.mycrmliferay.models.Supplier;
import com.aalexandrakis.mycrmliferay.util.HibernateUtil;

public class SupplierDaoImpl {
	
	public static void saveSupplier(Supplier supplier) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(supplier);
		session.getTransaction().commit();
		session.close();
	}


	public static void updateSupplier(Supplier supplier) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(supplier);
		session.getTransaction().commit();
		session.close();
	}

	public static Supplier getSupplier(Integer supplierId) throws Exception{
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Supplier supplier = (Supplier) session.get(Supplier.class, supplierId);
		session.getTransaction().commit();
		session.close();
		return supplier;
	}

	@SuppressWarnings("unchecked")
	public static List<Supplier> getSuppliers(Map<String, String> parms) throws Exception{
		String query = "from Supplier";
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
		List<Supplier> suppliers = new ArrayList<Supplier>();
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		suppliers = session.createQuery(query).list();
		session.getTransaction().commit();
		session.close();
		
		return suppliers;
	}
}
