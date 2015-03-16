package com.aalexandrakis.mycrmliferay.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.aalexandrakis.mycrmliferay.models.Customer;
import com.aalexandrakis.mycrmliferay.util.HibernateUtil;

public class CustomerDaoImpl {
	
	public static void saveCustomer(Customer customer) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(customer);
		session.getTransaction().commit();
		session.close();
	}


	public static void updateCustomer(Customer customer) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(customer);
		session.getTransaction().commit();
		session.close();
	}

	public static Customer getCustomer(Integer customerId) throws Exception{
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Customer customer = (Customer) session.get(Customer.class, customerId);
		session.getTransaction().commit();
		session.close();
		return customer;
	}

	@SuppressWarnings("unchecked")
	public static List<Customer> getCustomers(Map<String, String> parms) throws Exception{
		String query = "from Customer";
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
		List<Customer> customers = new ArrayList<Customer>();
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		customers = session.createQuery(query).list();
		session.getTransaction().commit();
		session.close();
		
		return customers;
	}
}
