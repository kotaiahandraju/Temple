package com.aurospaces.neighbourhood.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.DonorBean;
import com.aurospaces.neighbourhood.bean.UsersBean;
import com.aurospaces.neighbourhood.db.dao.DonorsDao;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class UserDonorController {
	@Autowired DonorsDao objDonorsDao;
	private Logger logger = Logger.getLogger(UserDonorController.class);
	@RequestMapping(value = "/UserDonor")
	public String userDonor(@ModelAttribute("packCmd") DonorBean objDonorBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		UsersBean objUserBean = null;
		String sJson=null;
		try{
			listOrderBeans = objDonorsDao.getDonors(null);
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}else{
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", "''");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school UserDonorController class");
			return "login";
			
		}
		return "userdonors";
	}
	
	@RequestMapping(value = "/AddUserDonor")
	public String addUserDonor(@ModelAttribute("packCmd") DonorBean objDonorBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		String sJson=null;
		String mobileNumber = null;
		String requestUrl = null; 
		String username = "mopidevi ";
		String password = "Mopidevi@123";
		String from = "SSSTMD";
		try{
			String name = objDonorBean.getName(); 
			byte[] bytes = name.getBytes(StandardCharsets.ISO_8859_1);
			name = new String(bytes, StandardCharsets.UTF_8);
			objDonorBean.setName(name);
			
			String gotram = objDonorBean.getGotram(); 
			byte[] bytes1 = gotram.getBytes(StandardCharsets.ISO_8859_1);
			gotram = new String(bytes1, StandardCharsets.UTF_8);
			objDonorBean.setGotram(gotram);
			
			String address = objDonorBean.getAddress(); 
			byte[] bytes2 = address.getBytes(StandardCharsets.ISO_8859_1);
			address = new String(bytes2, StandardCharsets.UTF_8);
			objDonorBean.setAddress(address);
			
			String information = objDonorBean.getOtherInformation(); 
			byte[] bytes3 = information.getBytes(StandardCharsets.ISO_8859_1);
			information = new String(bytes3, StandardCharsets.UTF_8);
			objDonorBean.setOtherInformation(information);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
			if(StringUtils.isNotBlank(objDonorBean.getDor())){
				Date date1 = formatter.parse(objDonorBean.getDor());
				objDonorBean.setDor1(date1);
			}
			if(StringUtils.isNotBlank(objDonorBean.getAmount())){
				double amount = Double.parseDouble(objDonorBean.getAmount());
				objDonorBean.setAmount1(amount);
			}
			if(objDonorBean.getId() != 0){
				session.setAttribute("d_updated", "Donor Updated Successfully");
			}else{
				session.setAttribute("d_created", "Donor Created Successfully");
			}
			boolean isInsert =  objDonorsDao.save(objDonorBean);
			if(isInsert)
        	{
				String  message = "Dear "+objDonorBean.getName()+",Thank you for Registering with us.\nSri Subrahmanyeswara Swamy Temple, Mopidevi.";
//				String message = "Dear "+objDonorBean.getName()+",\n Thanks for Registering with us.";
        		mobileNumber = objDonorBean.getMobile();
//		String message = "Dear Parent your son, Results in Foundation grand Test-1 Conducted on .\nYour Login details,\n Username: "+objStudentBean1.getFatherName()+"\n Password: 12345";
        		if(StringUtils.isNotBlank(mobileNumber)){
        			requestUrl  = "http://182.18.160.225/index.php/api/bulk-sms?username="+URLEncoder.encode(username, "UTF-8")+"&password="+ URLEncoder.encode(password, "UTF-8")+"&from="+from+"&to="+URLEncoder.encode(mobileNumber, "UTF-8")+"&message="+URLEncoder.encode(message, "UTF-8")+"&sms_type=2";
        			URL url = new URL(requestUrl);
        			HttpURLConnection uc = (HttpURLConnection)url.openConnection();
        			System.out.println(uc.getResponseMessage());
        			uc.disconnect();
        			System.out.println(message);
        		}
//		toAddress=  objMarksBean.getEmail();
        		
//        		toAddress = "nitunchinta@gmail.com";
//				if(StringUtils.isNotBlank(toAddress)){
//					MailSender.sendEmailWithAttachment(toAddress, "Regarding Test/Exam Results",message,null);
//				}
//				else{ System.out.println("No Email-ID"); }
        		
        	}
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in addDonor method in DonorController class");
			return "redirect:UserDonor";
			
		}
		return "redirect:UserDonor";
	}
	
	@RequestMapping(value = "/getSchemeType1")
	public @ResponseBody String getSchemeType(ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		String sJson=null;
		String schemeTitle = null;
		String num =null;
		try{
			schemeTitle = request.getParameter("schemeTitle");
			 num = objDonorsDao.getSchemeType(Integer.parseInt(schemeTitle));
			System.out.println(num);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in class UserDonorController");
			return "login";
			
		}
		return num;
	}
	
	@RequestMapping(value = "/DonorList")
	public String adminLogin(@ModelAttribute("packCmd") DonorBean objDonorBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		UsersBean objUserBean = null;
		String sJson=null;
		try{
			listOrderBeans = objDonorsDao.getDonors(null);
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper();
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson);
			}else{
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", "''");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school UserDonorController class SchemeHome method  ");
			return "login";
			
		}
		return "donorslist";
	}
	
	@ModelAttribute("schemeTitle")
	public Map<Integer, String> populateScheme() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,name from scheme order by name asc";
			List<DonorBean> list= objDonorsDao.populate(sSql);
			for(DonorBean bean: list){
				statesMap.put(bean.getId(), bean.getName());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
	@ModelAttribute("telugumasalu")
	public Map<Integer, String> populatetelugumasalu() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,name from months where schemetype=2";
			List<DonorBean> list= objDonorsDao.populate(sSql);
			for(DonorBean bean: list){
				statesMap.put(bean.getId(), bean.getName());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}

	@ModelAttribute("telugudays")
	public Map<Integer, String> populatetelugudays() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,name from days where schemetype =2";
			System.out.println(sSql);
			List<DonorBean> list= objDonorsDao.populate(sSql);
			for(DonorBean bean: list){
				statesMap.put(bean.getId(), bean.getName());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
	@ModelAttribute("englishmonths")
	public Map<Integer, String> populateteenglishmonths() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,name from months where schemetype=1";
			List<DonorBean> list= objDonorsDao.populate(sSql);
			for(DonorBean bean: list){
				statesMap.put(bean.getId(), bean.getName());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
	@ModelAttribute("englishdays")
	public Map<Integer, String> populateenglishdays() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,name from days where schemetype =1";
			List<DonorBean> list= objDonorsDao.populate(sSql);
			for(DonorBean bean: list){
				statesMap.put(bean.getId(), bean.getName());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}

	@ModelAttribute("schemetype")
	public Map<Integer, String> populateschemetype() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,name from schemetype where id not in(3) order by name asc";
			List<DonorBean> list= objDonorsDao.populate(sSql);
			for(DonorBean bean: list){
				statesMap.put(bean.getId(), bean.getName());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}

	
}
