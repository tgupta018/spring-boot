package com.xx.spring.boot.jdbc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xx.spring.boot.jdbc.config.DBHrConfig;
import com.xx.spring.boot.jdbc.dao.BikeDao;
import com.xx.spring.boot.jdbc.dao.BikeData;

@Repository
public class BikeDataRepository implements BikeDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(BikeDataRepository.class);
	
	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private DBHrConfig dbHRConfig;
	
//	@Autowired
//	public BikeDataRepository(JdbcTemplate jdbc) {
//	LOGGER.info(".........................BikeDataRepository.............................................."+jdbc.getDataSource().toString());
//		this.jdbc = jdbc.setDataSource(dataSource);;
//	}
	
	@Autowired
	public BikeDataRepository(DBHrConfig dbHRConfig, JdbcTemplate jdbc) {
	LOGGER.info(".........................BikeDataRepository.............................................."+jdbc.getDataSource().toString());
	    jdbc.setDataSource(dbHRConfig.dbHRDatasource());
		this.jdbc = jdbc;
	}

	@Override
	public Iterable<BikeData> findAll() {
		return jdbc.query("select id, name, model from Bike", this::mapRowToBikeData);
	}

	@Override
	public BikeData findOne(String id) {
		return jdbc.queryForObject("select id, name, model from Bike where id=?", this::mapRowToBikeData, id);
	}

	private BikeData mapRowToBikeData(ResultSet rs, int rowNum) throws SQLException {
		return new BikeData(rs.getLong("id"), rs.getString("name"), rs.getString("model"));
	}

//	@Override
//	public BikeData findOne(String id) {
//		return jdbc.queryForObject("select id, name, type from BikeData where id=?", new RowMapper<BikeData>() {
//			public BikeData mapRow(ResultSet rs, int rowNum) throws SQLException {
//				return new BikeData(rs.getString("id"), rs.getString("name"),
//						BikeData.Type.valueOf(rs.getString("type")));
//			};
//		}, id);
//	}

}
