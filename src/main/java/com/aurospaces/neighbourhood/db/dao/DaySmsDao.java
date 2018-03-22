package com.aurospaces.neighbourhood.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseDaySmsDao;

@Repository(value = "DaySmsDao")
public class DaySmsDao extends BaseDaySmsDao {
	@Autowired CustomConnection custom;
	JdbcTemplate jdbcTempLoc ; 
	
}
