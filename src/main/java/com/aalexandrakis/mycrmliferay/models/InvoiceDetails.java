/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aalexandrakis.mycrmliferay.models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aalexandrakis
 */
@Entity
@Table(name = "invoiceDetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvoiceDetails.findAll", query = "SELECT i FROM InvoiceDetails i"),
    @NamedQuery(name = "InvoiceDetails.findById", query = "SELECT i FROM InvoiceDetails i WHERE i.id = :id"),
    @NamedQuery(name = "InvoiceDetails.findByDescription", query = "SELECT i FROM InvoiceDetails i WHERE i.description = :description"),
    @NamedQuery(name = "InvoiceDetails.findByNet", query = "SELECT i FROM InvoiceDetails i WHERE i.net = :net"),
    @NamedQuery(name = "InvoiceDetails.findByLineId", query = "SELECT i FROM InvoiceDetails i WHERE i.lineId = :lineId")})
public class InvoiceDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "net")
    private BigDecimal net;
    @Basic(optional = false)
    @Column(name = "lineId")
    private int lineId;
    @JoinColumn(name = "invoiceId", referencedColumnName = "invoiceId")
    @ManyToOne(optional = false)
    private InvoiceHeader invoiceId;

    public InvoiceDetails() {
    }

    public InvoiceDetails(Long id) {
        this.id = id;
    }

    public InvoiceDetails(Long id, String description, BigDecimal net, int lineId) {
        this.id = id;
        this.description = description;
        this.net = net;
        this.lineId = lineId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getNet() {
        return net;
    }

    public void setNet(BigDecimal net) {
        this.net = net;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public InvoiceHeader getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(InvoiceHeader invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceDetails)) {
            return false;
        }
        InvoiceDetails other = (InvoiceDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aalexandrakis.mycrmliferay.models.InvoiceDetails[ id=" + id + " ]";
    }
    
}
