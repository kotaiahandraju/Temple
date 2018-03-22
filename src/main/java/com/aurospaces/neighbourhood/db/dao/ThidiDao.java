package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.ThidiBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseThidiDao;
import com.aurospaces.neighbourhood.db.callback.RowValueCallbackHandler;

@Repository(value = "ThidiDao")
public class ThidiDao extends BaseThidiDao {
	@Autowired CustomConnection custom;
	JdbcTemplate jdbcTempLoc ; 
	 
	public List<Map<String, String>> getAllThidis(){
		 jdbcTempLoc=custom.getJdbcTemplate();
			String sql ="select id as tid,name as thidiname,schemetype from days where schemetype=2";

			//System.out.println(sql);
			RowValueCallbackHandler handler = new RowValueCallbackHandler(new String[] { "tid","thidiname","schemetype"});
			jdbcTempLoc.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
	
	 public ThidiBean testParentId(int id){
		 jdbcTempLoc=custom.getJdbcTemplate();
		 String sql = "SELECT id,GetParentIDByID(id) as tid,GetAncestry(id) as allThidiIds FROM days where id= "+id;
			List<ThidiBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(ThidiBean.class));
			System.out.println(sql);
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 public List<ThidiBean> populate(String sql ){
		 jdbcTempLoc=custom.getJdbcTemplate();
		 
				List<ThidiBean> retlist = jdbcTempLoc.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(ThidiBean.class));
					return retlist;
		 }
	 
	 public ThidiBean getByTid(int id){
		 jdbcTempLoc=custom.getJdbcTemplate();
		 String sql = "SELECT id,name FROM days where id= '"+id+"' and schemetype=2";
			List<ThidiBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(ThidiBean.class));
			System.out.println(sql);
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 
	 public ThidiBean getByTname(String name){
		 jdbcTempLoc=custom.getJdbcTemplate();
		 String sql = "SELECT id as tid,name FROM days where name='"+name+"' and schemetype=2";
			List<ThidiBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(ThidiBean.class));
			System.out.println(sql);
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 
}
