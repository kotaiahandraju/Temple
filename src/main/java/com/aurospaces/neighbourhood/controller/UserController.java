package com.aurospaces.neighbourhood.controller;

import java.io.IOException;
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
import com.aurospaces.neighbourhood.bean.SchemeBean;
import com.aurospaces.neighbourhood.bean.UsersBean;
import com.aurospaces.neighbourhood.db.dao.DonorsDao;
import com.aurospaces.neighbourhood.db.dao.SchemeDao;
import com.aurospaces.neighbourhood.db.dao.UsersDao;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class UserController {
@Autowired SchemeDao objSchemeDao;
@Autowired UsersDao objUsersDao;
@Autowired DonorsDao objDonorsDao;

	private Logger logger = Logger.getLogger(UserController.class);
	@RequestMapping(value = "/UserHome")
	public String userHome(@ModelAttribute("packCmd") DonorBean objDonorBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		UsersBean objUserBean = null;
		String sJson=null;
		try{
			
			listOrderBeans = objDonorsDao.getAllDonors(null);
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
			logger.fatal("error in UserController class");
			return "redirect:UserHome";
			
		}
		return "userdashbord";
	}
	
	@RequestMapping(value = "/userSearchSchemeType")
	public @ResponseBody List<Map<String, String>> searchSchemeType(ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
//		System.out.println("Home page...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			String id = request.getParameter("id");
			if(id != null ){
				listOrderBeans =objDonorsDao.getAllDonors(id);
			}
			
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
			logger.fatal("error in userLogin method in school SchemeController class SchemeHome method  ");
			
		}
		return listOrderBeans;
	}
	
	@ModelAttribute("schemetype")
	public Map<Integer, String> populateschemetype() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,name from schemetype order by name asc";
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
