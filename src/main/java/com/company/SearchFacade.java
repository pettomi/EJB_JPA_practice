package com.company;


import java.util.List;
import javax.ejb.Remote;

@Remote
public interface SearchFacade {

	List<String> customerSearch(String customerName);
	public void createCustomer(String name);
}
