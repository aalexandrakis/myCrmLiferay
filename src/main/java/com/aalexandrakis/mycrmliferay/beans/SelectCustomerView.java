package com.aalexandrakis.mycrmliferay.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.aalexandrakis.mycrmliferay.daoimpl.CustomerDaoImpl;
import com.aalexandrakis.mycrmliferay.models.Customer;

@ManagedBean(name="dtSelectCustomerView")
@ViewScoped
public class SelectCustomerView implements Serializable {
	private Customer customer;
	private Integer filterCustomerId;
	private String filterName;
	private String filterAfm;
	private List<Customer> customers;
	
	public Integer getFilterCustomerId() {
		return filterCustomerId;
	}

	public void setFilterCustomerId(Integer filterCustomerId) {
		this.filterCustomerId = filterCustomerId;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public String getFilterAfm() {
		return filterAfm;
	}

	public void setFilterAfm(String filterAfm) {
		this.filterAfm = filterAfm;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public void selectCompanyFromDialog(Customer customer){
		this.customer = customer;
	}
				
	public void selectCustomer(){
		try {
			this.customers = CustomerDaoImpl.getCustomers(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
