package com.xx.spring.boot.jdbc.dao;

public interface BikeDao {
	Iterable<BikeData> findAll();
	BikeData findOne(String id);
}
