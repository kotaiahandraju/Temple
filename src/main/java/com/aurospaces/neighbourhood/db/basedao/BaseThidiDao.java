
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

import com.aurospaces.neighbourhood.bean.ThidiBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseThidiDao{

	@Autowired CustomConnection custom;
	JdbcTemplate jdbcTempLoc ; 
 
	public final String INSERT_SQL = "INSERT INTO days(name,schemetype) values (?, ?)";

	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public boolean save(final ThidiBean objThidiBean) 
	{
		 jdbcTempLoc=custom.getJdbcTemplate();
	if(objThidiBean.getTid() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTempLoc.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setString(1, objThidiBean.getTname());
	ps.setInt(2, 2);
							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				objThidiBean.setTid(unId.intValue());
		}
		else
		{
			String sql = "UPDATE days set name = ?  where id = ? ";
			jdbcTempLoc.update(sql, new Object[]{objThidiBean.getTname(),objThidiBean.getTid()});
		}
	return true;
	}
		
		@Transactional
		public void delete(int tid) {
			 jdbcTempLoc=custom.getJdbcTemplate();
			String sql = "DELETE FROM days WHERE id=? and schemetype=2";
			jdbcTempLoc.update(sql, new Object[]{tid});
		}
		

	 public ThidiBean getByTid(int tid) {
		 jdbcTempLoc=custom.getJdbcTemplate();
			String sql = "SELECT * from days where id = ? and schemetype=2";
			List<ThidiBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{tid},
			ParameterizedBeanPropertyRowMapper.newInstance(ThidiBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

}
