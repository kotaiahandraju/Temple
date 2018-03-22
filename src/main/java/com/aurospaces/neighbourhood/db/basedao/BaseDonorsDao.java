
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

import com.aurospaces.neighbourhood.bean.DonorBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseDonorsDao{

	@Autowired CustomConnection custom;
	JdbcTemplate jdbcTempLoc ; 
 
	public final String INSERT_SQL = "INSERT INTO donor(created_time,updated_time,regdate,schemetitle,name,gotram,datha,schemetype,month,day,otherinformation,address,mobile,email,amount,receiptno) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
	
	@Transactional
	public boolean save(final DonorBean objDonorsBean) 
	{
		 jdbcTempLoc=custom.getJdbcTemplate();
	if(objDonorsBean.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTempLoc.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(objDonorsBean.getCreatedTime() == null)
					{
					objDonorsBean.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(objDonorsBean.getCreatedTime().getTime()); 
							
					if(objDonorsBean.getUpdatedTime() == null)
					{
					objDonorsBean.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(objDonorsBean.getUpdatedTime().getTime());
					java.sql.Timestamp dor = 
						new java.sql.Timestamp(objDonorsBean.getDor1().getTime());
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
					
	ps.setTimestamp(1, createdTime);
	ps.setTimestamp(2, updatedTime);
	ps.setTimestamp(3, dor);
	ps.setString(4, objDonorsBean.getSchemeTitle());
	ps.setString(5, objDonorsBean.getName());
	ps.setString(6, objDonorsBean.getGotram());
	ps.setString(7, objDonorsBean.getDatha());
	ps.setString(8, objDonorsBean.getSchemeType());
	ps.setString(9, objDonorsBean.getMonth());
	ps.setString(10, objDonorsBean.getDay());
	ps.setString(11, objDonorsBean.getOtherInformation());
	ps.setString(12, objDonorsBean.getAddress());
	ps.setString(13, objDonorsBean.getMobile());
	ps.setString(14, objDonorsBean.getEmail());
	ps.setDouble(15, objDonorsBean.getAmount1());
	ps.setString(16, objDonorsBean.getReceiptNo());
	

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				objDonorsBean.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE donor set regdate=?, schemetitle=?, name =?, gotram=?, datha=?, schemetype = ?, month=?, day=?,"
					+ "otherinformation=?, address=?, mobile=?, email=?, amount=?, receiptno=?  where id = ? ";
	
			jdbcTempLoc.update(sql, new Object[]{objDonorsBean.getDor1(),objDonorsBean.getSchemeTitle(),objDonorsBean.getName(),objDonorsBean.getGotram(),
					objDonorsBean.getDatha(),objDonorsBean.getSchemeType(),objDonorsBean.getMonth(),objDonorsBean.getDay(),
					objDonorsBean.getOtherInformation(),objDonorsBean.getAddress(),objDonorsBean.getMobile(),
					objDonorsBean.getEmail(),objDonorsBean.getAmount1(),objDonorsBean.getReceiptNo(),objDonorsBean.getId()});
		}
	return true;
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTempLoc=custom.getJdbcTemplate();
			String sql = "DELETE FROM donor WHERE id=?";
			jdbcTempLoc.update(sql, new Object[]{id});
		}
		

	 public DonorBean getById(int id) {
		 jdbcTempLoc=custom.getJdbcTemplate();
			String sql = "SELECT * from donor where id = ? ";
			List<DonorBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(DonorBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

}
