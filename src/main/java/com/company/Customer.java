package com.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="customers")
@SequenceGenerator(name = "CustomerSequence",
sequenceName = "customer_seq",
initialValue = 100)
@NamedQueries({
	@NamedQuery(name="Customer.findByName",
				query="select c from Customer c Where c.name=:name"),
	@NamedQuery(name="Customer.findAll",
				query="select c from Customer c")
})
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	 generator = "CustomerSequence")
	@Column(name="id")
	private Integer id;
	@Column(name="name")
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
