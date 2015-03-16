/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aalexandrakis.mycrmliferay.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author aalexandrakis
 */
@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId")
    private Integer customerId;
    @NotNull @NotEmpty @Column(name = "customerName")
    private String customerName;
    @NotNull @NotEmpty @Column(name = "customerBusDesc")
    private String customerBusDesc;
    @NotNull @NotEmpty @Column(name = "customerDoy")
    private String customerDoy;
    @NotNull @NotEmpty @Column(name = "customerAddress")
    private String customerAddress;
    @NotNull @NotEmpty @Column(name = "customerAfm")
    private String customerAfm;
    @NotNull @NotEmpty @Column(name = "customerPhone")
    private String customerPhone;

    public Customer() {
    }

    public Customer(Integer customerId) {
        this.customerId = customerId;
    }

//    public Customer(Integer customerId, String customerName, String customerBusDesc, String customerDoy, String customerAddress, String customerAfm, String customerPhone) {
//        this.customerId = customerId;
//        this.customerName = customerName;
//        this.customerBusDesc = customerBusDesc;
//        this.customerDoy = customerDoy;
//        this.customerAddress = customerAddress;
//        this.customerAfm = customerAfm;
//        this.customerPhone = customerPhone;
//    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerBusDesc() {
        return customerBusDesc;
    }

    public void setCustomerBusDesc(String customerBusDesc) {
        this.customerBusDesc = customerBusDesc;
    }

    public String getCustomerDoy() {
        return customerDoy;
    }

    public void setCustomerDoy(String customerDoy) {
        this.customerDoy = customerDoy;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerAfm() {
        return customerAfm;
    }

    public void setCustomerAfm(String customerAfm) {
        this.customerAfm = customerAfm;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    
    public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aalexandrakis.mycrmliferay.models.Customer[ customerId=" + customerId + " ]";
    }
    
}
