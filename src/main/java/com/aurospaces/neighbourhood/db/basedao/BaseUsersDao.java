
package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.UsersBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseUsersDao{

	@Autowired CustomConnection custom;
	JdbcTemplate jdbcTempLoc ; 
 
	public final String INSERT_SQL = "INSERT INTO users(name,password,rollId) values (?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public boolean save(final UsersBean obiUserBean) 
	{
		 jdbcTempLoc=custom.getJdbcTemplate();
	if(obiUserBean.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTempLoc.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});


	ps.setString(3, obiUserBean.getName());
	ps.setString(4, obiUserBean.getPassword());
	ps.setString(5, obiUserBean.getRollId());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				obiUserBean.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE users  set name = ? ,password = ?   where id = ? ";
	
			jdbcTempLoc.update(sql, new Object[]{obiUserBean.getName(),obiUserBean.getPassword(),obiUserBean.getId()});
		}
	return true;
	}
		
		@Transactional
		public void delete(int id) {
			 jdbcTempLoc=custom.getJdbcTemplate();
			String sql = "DELETE FROM users WHERE id=?";
			jdbcTempLoc.update(sql, new Object[]{id});
		}
		

	 public UsersBean getById(int id) {
		 jdbcTempLoc=custom.getJdbcTemplate();
			String sql = "SELECT * from scheme where id = ? ";
			List< UsersBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(UsersBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

}
