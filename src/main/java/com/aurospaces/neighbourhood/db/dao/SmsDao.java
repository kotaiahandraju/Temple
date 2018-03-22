package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.SmsBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseSmsDao;

@Repository(value = "SmsDao")
public class SmsDao extends BaseSmsDao {
	@Autowired CustomConnection custom;
	JdbcTemplate jdbcTempLoc ; 
	 public SmsBean getMessages(SmsBean objSmsBean) {
		 jdbcTempLoc=custom.getJdbcTemplate();
			String sql = "SELECT * from messages where donorid = '"+objSmsBean.getDonorId() +"'";
			List<SmsBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(SmsBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
	 }
	 
	 public SmsBean getById(int id){
		 jdbcTempLoc=custom.getJdbcTemplate();
		 String sql = "SELECT id,message FROM messages where id= '"+id+"'";
			List<SmsBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(SmsBean.class));
			System.out.println(sql);
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
	}
}
