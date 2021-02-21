package com.uob.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="customer_token")
/*
 * @Data
 * 
 * @Setter
 * 
 * @Getter
 */
public class Token implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="token_id")
    private String tokenId;
	
	@Column(name="token_no")
    private String tokenNumber;
	
	@Column(name="customer_accno")
    private String customerAcctNo;
	
	@Column(name="token_status")
    private String tokenStatus;
	
	@Column(name="service_type")
    private String serviceType;
	
	
	@Column(name="customer_feedback")
    private String feedback;
	
	
	//private String message;

	public String getTokenNumber() {
		return tokenNumber;
	}

	public void setTokenNumber(String tokenNumber) {
		this.tokenNumber = tokenNumber;
	}

	public String getCustomerAcctNo() {
		return customerAcctNo;
	}

	public void setCustomerAcctNo(String customerAcctNo) {
		this.customerAcctNo = customerAcctNo;
	}

	public String getTokenStatus() {
		return tokenStatus;
	}

	public void setTokenStatus(String tokenStatus) {
		this.tokenStatus = tokenStatus;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	/*
	 * public String getMessage() { return message; }
	 * 
	 * public void setMessage(String message) { this.message = message; }
	 */	
}
