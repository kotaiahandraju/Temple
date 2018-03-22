package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.SchemeBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseSchemeDao;
import com.aurospaces.neighbourhood.db.callback.RowValueCallbackHandler;

@Repository(value = "SchemeDao")
public class SchemeDao extends BaseSchemeDao {
	@Autowired CustomConnection custom;
	JdbcTemplate jdbcTempLoc ; 
	 public List<Map<String, String>> getAllSchemes(){
		 jdbcTempLoc=custom.getJdbcTemplate();
			String sql ="select s.id, s.name, st.id as schemetype, st.name as date from scheme s, schemetype st where s.type=st.id order by created_time limit 300";

			//System.out.println(sql);
			RowValueCallbackHandler handler = new RowValueCallbackHandler(new String[] { "id","name","schemetype","date"});
			jdbcTempLoc.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
	 public SchemeBean testParentId(int id){
		 jdbcTempLoc=custom.getJdbcTemplate();
		 String sql = "SELECT id,GetParentIDByID(id) as schemeid,GetAncestry(id) as allSchemeIds FROM scheme where id= "+id;
			List<SchemeBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(SchemeBean.class));
			System.out.println(sql);
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 public List<SchemeBean> populate(String sql ){
		 jdbcTempLoc=custom.getJdbcTemplate();
		 
				List<SchemeBean> retlist = jdbcTempLoc.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(SchemeBean.class));
					return retlist;
		 }
	 public SchemeBean getByName(String  name){
		 jdbcTempLoc=custom.getJdbcTemplate();
		 String sql = "SELECT * FROM scheme where name= '"+name+"'";
			List<SchemeBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(SchemeBean.class));
			System.out.println(sql);
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 
	 public SchemeBean getById(int id){
		 jdbcTempLoc=custom.getJdbcTemplate();
		 String sql = "SELECT id,name FROM scheme where id= '"+id+"'";
			List<SchemeBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(SchemeBean.class));
			System.out.println(sql);
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 public List<Map<String, String>> getSchemes(){
		 jdbcTempLoc=custom.getJdbcTemplate();
			String sql =" select id,name from scheme " ;

			//System.out.println(sql);
			RowValueCallbackHandler handler = new RowValueCallbackHandler(new String[] { "id","name"});
			jdbcTempLoc.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
	 
	 // Get Thidi Name
	 /*public List<Map<String, String>> getAllThidis(){
		 jdbcTempLoc=custom.getJdbcTemplate();
			String sql ="select id as tid,name as thidiname,schemetype from days where schemetype=2 limit 300";

			//System.out.println(sql);
			RowValueCallbackHandler handler = new RowValueCallbackHandler(new String[] { "tid","thidiname","schemetype"});
			jdbcTempLoc.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
	 
	 public SchemeBean getByTname(String  name){
		 jdbcTempLoc=custom.getJdbcTemplate();
		 String sql = "SELECT id,name FROM days where name='"+name+"' and schemetype=2";
			List<SchemeBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(SchemeBean.class));
			System.out.println(sql);
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}*/
	 
}
