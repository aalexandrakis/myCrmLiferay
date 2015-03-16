package com.aalexandrakis.mycrmliferay.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.aalexandrakis.mycrmliferay.daoimpl.SupplierDaoImpl;
import com.aalexandrakis.mycrmliferay.models.Supplier;

@ManagedBean(name="dtSuppliersView")
@ViewScoped
public class SuppliersView implements Serializable {
	private List<Supplier> suppliers;
	private Supplier selectedSupplier;
	
	
	@PostConstruct
	public void init(){
		System.out.println("init routine");
		try {
			suppliers = SupplierDaoImpl.getSuppliers(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public Supplier getSelectedSupplier() {
		return selectedSupplier;
	}

	public void setSelectedSupplier(Supplier selectedSupplier) {
		this.selectedSupplier = selectedSupplier;
	}
	
	public void saveSupplier(){
		try {
			if (selectedSupplier.getSupplierId() != null){
				SupplierDaoImpl.updateSupplier(selectedSupplier);
			} else {
				SupplierDaoImpl.saveSupplier(selectedSupplier);
			}
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Supplier addSupplier(){
		this.selectedSupplier = new Supplier();
		return selectedSupplier;
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
