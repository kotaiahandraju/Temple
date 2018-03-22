package com.aurospaces.neighbourhood.db.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.UsersBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseUsersDao;

@Repository(value = "UsersDao")
public class UsersDao extends BaseUsersDao {
	@Autowired CustomConnection custom;
	JdbcTemplate jdbcTempLoc ;
	 public UsersBean getUserExist(UsersBean objUserBean) {
		 jdbcTempLoc=custom.getJdbcTemplate();
			String sql = "SELECT * from users where binary(name) = '"+objUserBean.getName() +"' and binary(password) = '"+objUserBean.getPassword()+"' and rollId = '"+objUserBean.getRollId()+"'";
			List<UsersBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(UsersBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}	
	 public List<UsersBean> getLastWeekDays() {
		 jdbcTempLoc=custom.getJdbcTemplate();
			String sql = " SELECT CURDATE() AS day UNION SELECT CURDATE() - INTERVAL 1 DAY UNION SELECT CURDATE() - INTERVAL 2 DAY  UNION SELECT CURDATE() - INTERVAL 3 DAY  UNION SELECT CURDATE() - INTERVAL 4 DAY   UNION SELECT CURDATE() - INTERVAL 5 DAY  UNION SELECT CURDATE() - INTERVAL 6 DAY "  ;
			List<UsersBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(UsersBean.class));
			if(retlist.size() > 0)
				return retlist;
			return null;
		}	
	 public UsersBean todaySMSSendOrNOt(Date date) {
		 jdbcTempLoc=custom.getJdbcTemplate();
		 
			String sql = "select date as day from day_message where Date(date) = Date('"+date +"')  ";
			List<UsersBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(UsersBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}	
	
	 }
