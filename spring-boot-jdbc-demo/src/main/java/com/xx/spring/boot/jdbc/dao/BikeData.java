package com.xx.spring.boot.jdbc.dao;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/*@Data
@RequiredArgsConstructor*/
public class BikeData {
	private  Long id;	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	private  String name;
	//private final String email;
	//private final String phone;
	private  String model;
//	private final String serialNumber;
//	private final BigDecimal purchasePrice;
//	private final Date purchaseDate;
//	private final boolean contact;
	public BikeData(Long id, String name, String model){
		this.id= id; this.name=name; this.model=model;
	}
}
