package com.aalexandrakis.mycrmliferay.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the outcomeDetails database table.
 * 
 */
@Entity
@Table(name="outcomeDetails")
@NamedQuery(name="OutcomeDetail.findAll", query="SELECT o FROM OutcomeDetail o")
public class OutcomeDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

//	@Column
//	private Integer outcomeId;
	
	@Column(nullable=false, length=300)
	private String description;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal fpa;

	@Column(nullable=false)
	private int lineId;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal net;

	//bi-directional many-to-one association to OutcomeHeader
	@ManyToOne
	@JoinColumn(name="outcomeId", nullable=false)
	private OutcomeHeader outcomeHeader;

	public OutcomeDetail() {
	}

	public OutcomeDetail(Integer lineId) {
		this.lineId = lineId;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getFpa() {
		return this.fpa;
	}

	public void setFpa(BigDecimal fpa) {
		this.fpa = fpa;
	}

	public int getLineId() {
		return this.lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public BigDecimal getNet() {
		return this.net;
	}

	public void setNet(BigDecimal net) {
		this.net = net;
	}

	public OutcomeHeader getOutcomeHeader() {
		return this.outcomeHeader;
	}

	public void setOutcomeHeader(OutcomeHeader outcomeHeader) {
		this.outcomeHeader = outcomeHeader;
	}

//	public Integer getOutcomeId() {
//		return outcomeId;
//	}
//
//	public void setOutcomeId(Integer outcomeId) {
//		this.outcomeId = outcomeId;
//	}

}