
package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.SmsBean;
import com.aurospaces.neighbourhood.bean.UsersBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseDaySmsDao{

	@Autowired CustomConnection custom;
	JdbcTemplate jdbcTempLoc ; 

 
	public final String INSERT_SQL = "INSERT INTO day_message(date,status) values (?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public boolean save(final UsersBean objUserBean) 
	{
		jdbcTempLoc=custom.getJdbcTemplate();
	if(objUserBean.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTempLoc.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
						
					
						
						if(objUserBean.getDay() == null)
						{
						objUserBean.setDay( new Date());
						}
						java.sql.Timestamp date = 
								new java.sql.Timestamp(objUserBean.getDay().getTime()); 
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});

	ps.setTimestamp(1, date);
	ps.setString(2, objUserBean.getStatus());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				objUserBean.setId(unId.intValue());
				

		}
		
	return true;
	}
		
		
}
