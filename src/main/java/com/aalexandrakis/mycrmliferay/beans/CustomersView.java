package com.aalexandrakis.mycrmliferay.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.aalexandrakis.mycrmliferay.daoimpl.CustomerDaoImpl;
import com.aalexandrakis.mycrmliferay.models.Customer;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;

@ManagedBean(name="dtCustomersView")
@ViewScoped
public class CustomersView implements Serializable {
	private List<Customer> customers;
	private Customer selectedCustomer;
	
	
	@PostConstruct
	public void init(){
		System.out.println("init routine");
		try {
			customers = CustomerDaoImpl.getCustomers(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}
	
	public void saveCustomer(){
		try {
			if (selectedCustomer.getCustomerId() != null){
				CustomerDaoImpl.updateCustomer(selectedCustomer);
			} else {
				CustomerDaoImpl.saveCustomer(selectedCustomer);
			}
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Customer addCustomer(){
		this.selectedCustomer = new Customer();
		return selectedCustomer;
	}
//	 public void onRowSelect(SelectEvent event) {
//	        FacesMessage msg = new FacesMessage("Company Selected", ((CompanyInfo) event.getObject()).getCompanyId().toString());
//	        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
// 
//    public void onRowUnselect(UnselectEvent event) {
//        FacesMessage msg = new FacesMessage("Company Unselected", ((CompanyInfo) event.getObject()).getCompanyId().toString());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
	
}
