
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

import com.aurospaces.neighbourhood.bean.SchemeBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseSchemeDao{

	@Autowired CustomConnection custom;
	JdbcTemplate jdbcTempLoc ; 
 
	public final String INSERT_SQL = "INSERT INTO scheme(created_time,updated_time,name,type) values (?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public boolean save(final SchemeBean objDepartmentBean) 
	{
		 jdbcTempLoc=custom.getJdbcTemplate();
	if(objDepartmentBean.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTempLoc.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(objDepartmentBean.getCreatedTime() == null)
					{
					objDepartmentBean.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(objDepartmentBean.getCreatedTime().getTime()); 
							
					if(objDepartmentBean.getUpdatedTime() == null)
					{
					objDepartmentBean.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(objDepartmentBean.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
	ps.setTimestamp(2, updatedTime);
	ps.setString(3, objDepartmentBean.getName());
	ps.setInt(4, objDepartmentBean.getDate());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				objDepartmentBean.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE scheme  set name = ? ,type = ?  where id = ? ";
	
			jdbcTempLoc.update(sql, new Object[]{objDepartmentBean.getName(),objDepartmentBean.getDate(),objDepartmentBean.getId()});
		}
	return true;
	}
		
		@Transactional
		public void delete(int id) {
			 jdbcTempLoc=custom.getJdbcTemplate();
			String sql = "DELETE FROM scheme WHERE id=?";
			jdbcTempLoc.update(sql, new Object[]{id});
		}
		

	 public SchemeBean getById(int id) {
		 jdbcTempLoc=custom.getJdbcTemplate();
			String sql = "SELECT * from scheme where id = ? ";
			List<SchemeBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(SchemeBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

}
