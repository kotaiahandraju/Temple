
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
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseSmsDao{

	@Autowired CustomConnection custom;
	JdbcTemplate jdbcTempLoc ; 

 
	public final String INSERT_SQL = "INSERT INTO messages(created_time,updated_time,donorid,senderid,message,notificationid) values (?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public boolean save(final SmsBean objSmsBean) 
	{
		jdbcTempLoc=custom.getJdbcTemplate();
	if(objSmsBean.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTempLoc.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
						
						if(objSmsBean.getCreatedTime() == null)
						{
						objSmsBean.setCreatedTime( new Date());
						}
						java.sql.Timestamp createdTime = 
							new java.sql.Timestamp(objSmsBean.getCreatedTime().getTime()); 
								
						if(objSmsBean.getUpdatedTime() == null)
						{
						objSmsBean.setUpdatedTime( new Date());
						}
						java.sql.Timestamp updatedTime = 
							new java.sql.Timestamp(objSmsBean.getUpdatedTime().getTime()); 
	
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});

	ps.setTimestamp(1, createdTime);
	ps.setTimestamp(2, updatedTime);
	ps.setString(3, objSmsBean.getDonorId());
	ps.setString(4, objSmsBean.getSenderId());
	ps.setString(5, objSmsBean.getMessage());
	ps.setString(6, objSmsBean.getNotificationId());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				objSmsBean.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE messages  set donorid = ? ,senderid = ? ,message = ?, notificationid= ?  where id = ? ";
	
			jdbcTempLoc.update(sql, new Object[]{objSmsBean.getDonorId(),objSmsBean.getSenderId(),objSmsBean.getMessage(),objSmsBean.getNotificationId(),objSmsBean.getId()});
		}
	return true;
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTempLoc=custom.getJdbcTemplate();
			String sql = "DELETE FROM messages WHERE id=?";
			jdbcTempLoc.update(sql, new Object[]{id});
		}
		

	 public SmsBean getById(int id) {
		 jdbcTempLoc=custom.getJdbcTemplate();
			String sql = "SELECT * from messages where id = ? ";
			List< SmsBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(SmsBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

}
