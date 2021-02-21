package com.uob.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customerdetails")

public class CustomerDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	
	@Column(name="customer_id")
    private String customerId;
	
	@Column(name="customer_name")
    private String customerName;
	
	@Column(name="customer_acctno")
    private String customerAccNo;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAccNo() {
		return customerAccNo;
	}

	public void setCustomerAccNo(String customerAccNo) {
		this.customerAccNo = customerAccNo;
	}
	
	
}
