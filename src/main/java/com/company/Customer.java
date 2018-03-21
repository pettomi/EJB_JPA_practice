package com.company;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMERS")
@SequenceGenerator(name = "CustomerSequence",
sequenceName = " CUSTOMER_SEQ",
initialValue = 100, allocationSize = 20)
@NamedQueries({
	@NamedQuery(name="Customer.findByName",
				query="select c from Customer c Where c.name=:name")
})
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	 generator = "CustomerSequence")
	String id;
	String name;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
