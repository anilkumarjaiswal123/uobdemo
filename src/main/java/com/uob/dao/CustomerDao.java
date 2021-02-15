package com.uob.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uob.Entity.Customer;

@Transactional
@Repository
public class CustomerDao implements ICustomerDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomers() {
		
		String hql = "FROM Customer as cust ORDER BY cust.customerId";
		
		return (List<Customer>) entityManager.createQuery(hql).getResultList();
	}	

}
