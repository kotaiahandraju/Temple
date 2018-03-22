package com.aurospaces.neighbourhood.db.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.DonorBean;
import com.aurospaces.neighbourhood.bean.ReportsBean;
import com.aurospaces.neighbourhood.bean.UsersBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseDonorsDao;
import com.aurospaces.neighbourhood.db.callback.RowValueCallbackHandler;

@Repository(value = "DonorsDao")
public class DonorsDao extends BaseDonorsDao {
	@Autowired CustomConnection custom;
	JdbcTemplate jdbcTempLoc ;
	 public List<Map<String, String>> getAllDonors(String id){
		 jdbcTempLoc = custom.getJdbcTemplate();
			StringBuffer buffer = new StringBuffer();
			 buffer.append("select d.id,d.name as donorName,DATE_FORMAT( Date(d.regdate),'%d-%M-%Y') as doreg,d.datha,d.amount,d.address,d.gotram, d.mobile,d.otherinformation,d.email,d.receiptno,m.name as monthname,da.name as dayname,st.name as schemetipe,s.name as schemetitle from donor d,scheme s,schemetype st,months m,days da where "
						+" d.schemetitle= s.id and st.id =d.schemetype and m.id = d.month and da.id = d.day and Month(d.created_time) in (month(now()),month(now())+1) " );
			 
							 if(id != null &&  !id.equals("3") ){
							 buffer.append(" and st.id='"+id+"'");
							 }
			 String sql =buffer.toString();
			System.out.println(sql);
			RowValueCallbackHandler handler = 
					new RowValueCallbackHandler(new String[] { "id","donorName","doreg","datha","amount","address","gotram", "mobile","monthname","dayname","otherinformation", "email", "receiptno","schemetipe","schemetitle"});
			jdbcTempLoc.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
	 
	 public List<Map<String, String>> getDonors(String id){
		 jdbcTempLoc = custom.getJdbcTemplate();
			StringBuffer buffer = new StringBuffer();
			 buffer.append("select d.id ,d.name as donorName,DATE_FORMAT( Date(d.regdate),'%d-%M-%Y') as doreg,d.datha,d.amount,d.address,d.gotram, d.mobile,d.otherinformation,d.email,d.receiptno,m.name as monthname,da.name as dayname,st.name as schemetipe,s.name as schemetitle,d.schemetype as schemetypeId ,d.month as monthId,d.day as dayId,d.schemetitle as schemetitleId from donor d,scheme s,schemetype st,months m,days da where "
						+" d.schemetitle= s.id and st.id =d.schemetype and m.id = d.month and da.id = d.day order by d.created_time limit 300" );
			 
							 /*if(id != null &&  !id.equals("3") ){
							 buffer.append(" and st.id='"+id+"'");
							 }*/
			 String sql =buffer.toString();
			System.out.println(sql);
			RowValueCallbackHandler handler = 
					new RowValueCallbackHandler(new String[] { "id","donorName","doreg","datha","amount","address","gotram", "mobile","monthname","dayname","otherinformation", "email", "receiptno","schemetipe","schemetitle","schemetypeId","monthId","dayId","schemetitleId"});
			jdbcTempLoc.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
	 
	 public List<Map<String, String>> getDateDonors(String id){
		 jdbcTempLoc = custom.getJdbcTemplate();
			StringBuffer buffer = new StringBuffer();
			 buffer.append("select d.id ,d.name as donorName,DATE_FORMAT( Date(d.regdate),'%d-%M-%Y') as doreg,d.datha,d.amount,d.address,d.gotram, d.mobile,d.otherinformation,d.email,d.receiptno,m.name as monthname,da.name as dayname,st.name as schemetipe,s.name as schemetitle from donor d,scheme s,schemetype st,months m,days da where "
						+" d.schemetitle= s.id and st.id =d.schemetype and m.id = d.month and da.id = d.day and st.id=1 order by d.created_time limit 300" );
			 
			 String sql =buffer.toString();
			System.out.println(sql);
			RowValueCallbackHandler handler = 
					new RowValueCallbackHandler(new String[] { "id","donorName","doreg","datha","amount","address","gotram", "mobile","monthname","dayname","otherinformation", "email", "receiptno","schemetipe","schemetitle"});
			jdbcTempLoc.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
	 
	 public List<Map<String, String>> getThidiDonors(String id){
		 jdbcTempLoc = custom.getJdbcTemplate();
			StringBuffer buffer = new StringBuffer();
			 buffer.append("select d.id ,d.name as donorName,DATE_FORMAT( Date(d.regdate),'%d-%M-%Y') as doreg,d.datha,d.amount,d.address,d.gotram, d.mobile,d.otherinformation,d.email,d.receiptno,m.name as monthname,da.name as dayname,st.name as schemetipe,s.name as schemetitle from donor d,scheme s,schemetype st,months m,days da where "
						+" d.schemetitle= s.id and st.id =d.schemetype and m.id = d.month and da.id = d.day and st.id=2 order by d.created_time limit 300" );
			 
			 String sql =buffer.toString();
			System.out.println(sql);
			RowValueCallbackHandler handler = 
					new RowValueCallbackHandler(new String[] { "id","donorName","doreg","datha","amount","address","gotram", "mobile","monthname","dayname","otherinformation", "email", "receiptno","schemetipe","schemetitle"});
			jdbcTempLoc.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
	 
	 /*public List<Map<String, String>> getDateWiseDonors(String schemeTitle,String frommonth,String tomonth,String day){
			StringBuffer buffer = new StringBuffer();
			buffer.append("select d.id ,d.name as donorName,DATE_FORMAT( Date(d.regdate),'%d-%M-%Y') as doreg,d.datha,d.amount,d.address,d.gotram, d.mobile,d.otherinformation,d.email,d.receiptno,m.name as monthname,da.name as dayname,st.name as schemetipe,s.name as schemetitle from donor d,scheme s,schemetype st,months m,days da where "
						+" d.schemetitle = s.id and d.schemetype = st.id and d.month = m.id and d.day = da.id and d.schemetype=1 " );
			
							 if(schemeTitle != null ){
							 buffer.append(" and s.id='"+schemeTitle+"'");
							 }
							 
//							 if(frommonth != null ){
//								 buffer.append(" and m.id='"+frommonth+"'");
//							 }
//							 
//							 if(tomonth != null ){
//								 buffer.append(" and m.id='"+tomonth+"'");
//							 }
							 
							 if(frommonth != null && tomonth != null){
								 buffer.append(" and  m.id BETWEEN '"+frommonth+"' and '"+tomonth+"' ");
							 }
							 
							 if(day != null ){
								 buffer.append(" and da.id='"+day+"' ");
							 }
			 String sql =buffer.toString();
			System.out.println(sql);
			RowValueCallbackHandler handler = 
					new RowValueCallbackHandler(new String[] { "id","donorName","doreg","datha","amount","address","gotram", "mobile","monthname","dayname","otherinformation", "email", "receiptno","schemetipe","schemetitle"});
			jdbcTempLoc.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}*/
	 
		 public List<Map<String, String>> getDateWiseDonors(ReportsBean objReportsBean)
		 {
			 jdbcTempLoc = custom.getJdbcTemplate();
			StringBuffer buffer = new StringBuffer();
			buffer.append("select d.id ,d.name as donorName,DATE_FORMAT( Date(d.regdate),'%d-%M-%Y') as doreg,d.datha,d.amount,d.address,d.gotram, d.mobile,d.otherinformation,d.email,d.receiptno,m.name as monthname,da.name as dayname,st.name as schemetipe,s.name as schemetitle from donor d,scheme s,schemetype st,months m,days da where "
						+" d.schemetitle = s.id and d.schemetype = st.id and d.month = m.id and d.day = da.id and d.schemetype=1 " );
			 
			if (StringUtils.isNotBlank(objReportsBean.getSchemeTitle())) {
				buffer.append("  and s.id='"+objReportsBean.getSchemeTitle()+"' " );
			}
			
//			if (StringUtils.isNotBlank(objReportsBean.getFrommonth())) {
//				buffer.append("  and m.id='"+objReportsBean.getFrommonth()+"' " );
//			}
//			
//			if (StringUtils.isNotBlank(objReportsBean.getTomonth())) {
//				buffer.append("  and m.id='"+objReportsBean.getTomonth()+"' " );
//			}
			
			if (StringUtils.isNotBlank(objReportsBean.getFrommonth()) && StringUtils.isNotBlank(objReportsBean.getTomonth()) ) {
				buffer.append("  and  m.id BETWEEN '"+objReportsBean.getFrommonth() +"' AND '"+objReportsBean.getTomonth()+"' " );
			}
			
			if (StringUtils.isNotBlank(objReportsBean.getDay())) {
				buffer.append("  and da.id='"+objReportsBean.getDay()+"' " );
			}
			
			buffer.append(" order by d.created_time");
							 
			String sql =buffer.toString();
			System.out.println(sql);
			RowValueCallbackHandler handler = 
					new RowValueCallbackHandler(new String[] { "id","donorName","doreg","datha","amount","address","gotram", "mobile","monthname","dayname","otherinformation", "email", "receiptno","schemetipe","schemetitle"});
			jdbcTempLoc.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		 }
		 
		 
		 public List<Map<String, String>> getThidiWiseDonors(ReportsBean objReportsBean)
		 {
			 jdbcTempLoc = custom.getJdbcTemplate();
			StringBuffer buffer = new StringBuffer();
			buffer.append("select d.id ,d.name as donorName,DATE_FORMAT( Date(d.regdate),'%d-%M-%Y') as doreg,d.datha,d.amount,d.address,d.gotram, d.mobile,d.otherinformation,d.email,d.receiptno,m.name as monthname,da.name as dayname,st.name as schemetipe,s.name as schemetitle from donor d,scheme s,schemetype st,months m,days da where "
						+" d.schemetitle = s.id and d.schemetype = st.id and d.month = m.id and d.day = da.id and d.schemetype=2 " );
			 
			if (StringUtils.isNotBlank(objReportsBean.getSchemeTitle())) {
				buffer.append("  and s.id='"+objReportsBean.getSchemeTitle()+"' " );
			}
			
//			if (StringUtils.isNotBlank(objReportsBean.getFrommonth())) {
//				buffer.append("  and m.id='"+objReportsBean.getFrommonth()+"' " );
//			}
//			
//			if (StringUtils.isNotBlank(objReportsBean.getTomonth())) {
//				buffer.append("  and m.id='"+objReportsBean.getTomonth()+"' " );
//			}
			
			if (StringUtils.isNotBlank(objReportsBean.getFrommonth()) && StringUtils.isNotBlank(objReportsBean.getTomonth()) ) {
				buffer.append("  and  m.id BETWEEN '"+objReportsBean.getFrommonth() +"' AND '"+objReportsBean.getTomonth()+"' " );
			}
			
			if (StringUtils.isNotBlank(objReportsBean.getDay())) {
				buffer.append("  and da.id='"+objReportsBean.getDay()+"' " );
			}
			
			buffer.append(" order by d.created_time");
							 
			String sql =buffer.toString();
			System.out.println(sql);
			RowValueCallbackHandler handler = 
					new RowValueCallbackHandler(new String[] { "id","donorName","doreg","datha","amount","address","gotram", "mobile","monthname","dayname","otherinformation", "email", "receiptno","schemetipe","schemetitle"});
			jdbcTempLoc.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		 }
		 
	 
	 public DonorBean testParentId(int id){
		 jdbcTempLoc = custom.getJdbcTemplate();
		 String sql = "SELECT id,GetParentIDByID(id) as schemeid,GetAncestry(id) as allSchemeIds FROM scheme where id= "+id;
			List<DonorBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(DonorBean.class));
			System.out.println(sql);
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 public List<DonorBean> populate(String sql ){
		 jdbcTempLoc = custom.getJdbcTemplate();
				List<DonorBean> retlist = jdbcTempLoc.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(DonorBean.class));
					return retlist;
		 }
	 public DonorBean getByName(String  name){
		 jdbcTempLoc = custom.getJdbcTemplate();
		 String sql = "SELECT * FROM donor where name= '"+name+"'";
			List<DonorBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(DonorBean.class));
			System.out.println(sql);
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 
	 public DonorBean getById(int id){
		 jdbcTempLoc = custom.getJdbcTemplate();
//		 String sql = "SELECT * FROM donor where id= '"+id+"'";
		 String sql = "select d.id,d.name,d.mobile,s.name as schemetitle,m.name as month,da.name as day,st.name as schemetype from donor d,scheme s,schemetype st,months m,days da where d.id="+id+" and s.id = d.schemetitle and st.id = d.schemetype and m.id = d.month and da.id = d.day";
			List<DonorBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(DonorBean.class));
			System.out.println(sql);
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 public List<Map<String, String>> getDonors(){
		 jdbcTempLoc = custom.getJdbcTemplate();
			String sql =" select id,name from donor ";

			//System.out.println(sql);
			RowValueCallbackHandler handler = new RowValueCallbackHandler(new String[] { "id","name","doreg","datha","amount","address","poojaType","month","day","thidi","phno","gotram","otherInfo"});
			jdbcTempLoc.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
			
		 public String getSchemeType(int id) {
			 jdbcTempLoc = custom.getJdbcTemplate();
			    String sql = "select type from scheme where id=?";

			    String streetName = (String) jdbcTempLoc.queryForObject(
			            sql, new Object[] { id }, String.class);

			    return streetName;
			}

		public DonorBean duplicateCheckDonor(DonorBean objDonorBean1) {
			jdbcTempLoc = custom.getJdbcTemplate();
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("SELECT * from donor where ");
			
			if (objDonorBean1.getDor1() != null || !objDonorBean1.getDor1().equals(null)) {
				buffer.append(" regdate=STR_TO_DATE('"+objDonorBean1.getDor()+"', '%e/%c/%Y') and " );
			}
			if (StringUtils.isNotBlank(objDonorBean1.getSchemeTitle())) {
				buffer.append(" schemetitle='"+objDonorBean1.getSchemeTitle()+"' " );
			}
			if (StringUtils.isNotBlank(objDonorBean1.getName())) {
				buffer.append(" and name='"+objDonorBean1.getName()+"' " );
			}
			if (StringUtils.isNotBlank(objDonorBean1.getGotram())) {
				buffer.append(" and gotram='"+objDonorBean1.getGotram()+"' " );
			}
			if (StringUtils.isNotBlank(objDonorBean1.getDatha())) {
				buffer.append(" and datha='"+objDonorBean1.getDatha()+"' " );
			}
			if (StringUtils.isNotBlank(objDonorBean1.getSchemeType())) {
				buffer.append(" and schemetype='"+objDonorBean1.getSchemeType()+"' " );
			}
			if (StringUtils.isNotBlank(objDonorBean1.getMonth())) {
				buffer.append(" and month='"+objDonorBean1.getMonth()+"' " );
			}
			if (StringUtils.isNotBlank(objDonorBean1.getDay())) {
				buffer.append(" and day='"+objDonorBean1.getDay()+"' " );
			}
			if (StringUtils.isNotBlank(objDonorBean1.getAddress())) {
				buffer.append(" and address='"+objDonorBean1.getAddress()+"' " );
			}
			if (StringUtils.isNotBlank(objDonorBean1.getMobile())) {
				buffer.append(" and mobile='"+objDonorBean1.getMobile()+"' " );
			}
			if (objDonorBean1.getAmount1() != 0) {
				buffer.append(" and amount='"+objDonorBean1.getAmount1()+"' " );
			}
			
			String sql =buffer.toString();
			System.out.println(sql);
			/*String sql = "SELECT * from donor where "
			+" regdate= Date('"+objDonorBean1.getDor1()+"') and schemetitle='"+objDonorBean1.getSchemeTitle()+"'"
			+" and name='"+objDonorBean1.getName()+"' and gotram='"+objDonorBean1.getGotram()+"' and datha='"+objDonorBean1.getDatha()+"' "
			+" and schemetype='"+objDonorBean1.getSchemeType()+"' and month='"+objDonorBean1.getMonth()+"' and day='"+objDonorBean1.getDay()+"' "
			+" and address='"+objDonorBean1.getAddress()+"' and mobile='"+objDonorBean1.getMobile()+"' and amount='"+objDonorBean1.getAmount1()+"' "
			+" and receiptno='"+objDonorBean1.getReceiptNo()+"' ";*/
			List<DonorBean> retlist = jdbcTempLoc.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(DonorBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
		 
		public List<DonorBean> getSMSforDoners(Date date) {
			 jdbcTempLoc=custom.getJdbcTemplate();
				String sql = "select d.id as donorId, d.name as  name,s.name as schemeTitle,m.name as month,da.name as day,d.mobile as mobile from donor d ,months m,days da,scheme s  where d.schemetitle=s.id  and d.day =da.id and d.month=m.id and d.schemetype=1 and d.month = month(DATE_ADD('"+date +"' , INTERVAL 7 DAY)) and  d.day= Day(DATE_ADD('"+date +"' , INTERVAL 7 DAY)) ";
				List<DonorBean> retlist = jdbcTempLoc.query(sql,
				new Object[]{},
				ParameterizedBeanPropertyRowMapper.newInstance(DonorBean.class));
				if(retlist.size() > 0)
					return retlist;
				return null;
			}
}
