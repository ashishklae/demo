package com.ensemble.shifu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="smartphones")
public class smartphone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Length(min=1, max=20)
	private String producer;
	
	@Length(min=1, max=20)
	 private String model;
	
	@Range(min=1, max=15000)
	private double price;
	
	public Integer getId() {
		return id;
	}

	public smartphone update(smartphone sPhone) {
		this.producer = sPhone.producer;
		this.model = sPhone.model;
		this.price = sPhone.price;
		return this;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getProducer() {
		return producer;
	}



	public void setProducer(String producer) {
		this.producer = producer;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	
	
	
	
	@Override
	public String toString() {
		return producer+": "+model+" with price "+price;
	}
}
