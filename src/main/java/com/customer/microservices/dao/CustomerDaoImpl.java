package com.customer.microservices.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.microservices.exception.CustomerException;
import com.customer.microservices.model.Customer;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDaoIF {

	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SessionFactory sessionFactory;

	// @PersistenceContext
	// private EntityManager em;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomers() throws CustomerException {
		Session session = null;
		List<Customer> customerList = null;
		try {
			session = getSession();
			customerList = session.createQuery("from Customer").list();
			return customerList;
		} catch (Exception ex) {
			throw ex;
		} finally {
//			if (session != null) {
//				session.close();
//			}
		}
		
	}

	@Override
	public Object getCustomer(Long customerId) throws CustomerException {
		Session session = null;
		Object customer = null;
		try {
			session = getSession();
			customer = session.get(Customer.class,customerId);
			return customer;
		} catch (Exception ex) {
			throw ex;
		} finally {
//			if (session != null) {
//				session.close();
//			}
		}
		
	}

	@Override
	
	public Object saveCustomer(Customer customer) throws CustomerException {
		Session session = null;
		//Transaction transaction = null;
		Long id =0L;
		try {
			session = getSession();
			//transaction = session.beginTransaction();
			id = (Long)session.save(customer);
			//transaction.commit();
			return getCustomer(id);
		} catch (Exception ex) {
			throw ex;
		} finally {
//			if (session != null) {
//				session.close();
//			}
		}
		
	}

	@Override
	public Object updateCustomer(Customer customer) throws CustomerException {
		Session session = null;
		try {
			session = getSession();
			session.update(customer);
			return getCustomer(customer.getId());
		} catch (Exception ex) {
			throw ex;
		} finally {
//			if (session != null) {
//				session.close();
//			}
		}
		
	}

	@Override
	public Object deleteCustomer(Long customerId) throws CustomerException {
		Session session = null;
		Object customer=null;
		try {
			customer = getCustomer(customerId);
			session = getSession();
			session.delete(customer);
			return customer;
		} catch (Exception ex) {
			throw ex;
		} finally {
//			if (session != null) {
//				session.close();
//			}
		}
		
	}

	@Override
	public Object getCustomerByEmail(String emailId) throws CustomerException {

		Session session = null;
		Object customer = null;
		try {
			session = getSession();
			customer = session.createQuery("from Customer where email=:email").setParameter("email", emailId);
			return customer;
		} catch (Exception ex) {
			throw ex;
		} finally {
//			if (session != null) {
//				session.close();
//			}
		}
		
	}

	@Override
	public Object getCustomerByName(String name) throws CustomerException {
		Session session = null;
		Object customer = null;

		try {
			session = getSession();
			customer = session.createQuery("from Customer where name=:name").setParameter("name", name);
			return customer;
		} catch (Exception ex) {
			throw ex;
		} finally {
//			if (session != null) {
//				session.close();
//			}
		}
		
	}

}
