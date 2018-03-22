/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *
 * @author Tomi
 */
@Stateless(name = "SearchFacadeBean")
public class SearchFacadeBean implements SearchFacade{
	//List<Customer> customers;
	
	@PersistenceContext
	 private EntityManager em;
	
	public SearchFacadeBean() {
	}

	@Override
	public List<String> customerSearch(String customerName) {
		List<String> ls = new ArrayList<String>();
		try {
			ls=em.createNamedQuery("Customer.findByName", Customer.class)
					.setParameter("name", customerName)
					.getResultList()
					.stream()
					.map(Customer::getName)
					.collect(Collectors.toList());
		} catch (Exception e) {
			System.err.println("SQL ERROR: "+  e.getMessage());
		}
		//System.out.println(ls.toString());
		return ls;
	}
	
	@Override
	public void createCustomer(String name) {
		 final Customer cust = new Customer();
		 cust.setName(name);
		 em.persist(cust);
		 }

	@PostConstruct
	public void initializeCountryWineList() {
		
	}

	@PreDestroy
	public void destroyWineList() {
	}

	@AroundInvoke
	public Object TimerLog(InvocationContext ctx) throws Exception {
		String beanClassName = ctx.getClass().getName();
		String businessMethodName = ctx.getMethod().getName();
		String target = beanClassName + "." + businessMethodName;
		long startTime = System.currentTimeMillis();
		System.out.println("Invoking " + target);
		try {
			return ctx.proceed();
		} finally {
			System.out.println("Exiting" + target);
			long totalTime = System.currentTimeMillis() - startTime;
			System.out.println("Business method" + businessMethodName + "in" + beanClassName + "takes" + totalTime
					+ "ms to execute");
		}
	}
	
}
