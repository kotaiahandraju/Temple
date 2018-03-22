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

import com.aurospaces.neighbourhood.bean.SchemeBean;
import com.aurospaces.neighbourhood.db.dao.SchemeDao;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class SchemeController {
@Autowired SchemeDao objSchemeDao;

	private Logger logger = Logger.getLogger(SchemeController.class);
	
	
	@RequestMapping(value = "/SchemeHome")
	public String SchemeHome(@ModelAttribute("packCmd") SchemeBean objSchemeBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
//		System.out.println("SchemeHome page...");
		List<Map<String, String>> listOrderBeans = null;
		List<Map<String, String>> listOrderBeans1 = null;
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			listOrderBeans = objSchemeDao.getAllSchemes();
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
			
			/*listOrderBeans1 = objSchemeDao.getAllThidis();
			if(listOrderBeans1 != null && listOrderBeans1.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans1);
				  request.setAttribute("allOrders2", sJson);
				 // System.out.println(sJson); 
			}else{
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans1);
				  request.setAttribute("allOrders2", "''");
			}*/
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school SchemeController class SchemeHome method  ");
			return "scheme";
			
		}
		return "SchemeHome";
	}
	@RequestMapping(value = "/AddScheme")
	public String updateScheme(@ModelAttribute("packCmd") SchemeBean objSchemeBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("SchemeHome page...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		SchemeBean sb = null;
		String sJson=null;
		int id = 0;
		SchemeBean scheme= null;
		String allParentIds = null;
		try{

		SchemeBean schemeBean = objSchemeDao.getByName(objSchemeBean.getName());
		int dummyId =0;
		if(schemeBean != null){
			dummyId = schemeBean.getId();
		}
		if(objSchemeBean.getId() != 0)
		{
			 id = objSchemeBean.getId();
			if(id == dummyId || schemeBean == null )
			{
				objSchemeDao.save(objSchemeBean);
				session.setAttribute("updated", "Scheme Updated Successfully");
				System.out.println("scheme updated");
			}
			else
			{
				session.setAttribute("error", "Record Already Exists");
				System.out.println("duplicate scheme");
			}
		}
		if(objSchemeBean.getId() == 0 && schemeBean == null)
		{
			objSchemeDao.save(objSchemeBean);
			session.setAttribute("created", "Scheme Created Successfully");
			System.out.println("scheme inserted");
		}
		if(objSchemeBean.getId() == 0 && schemeBean != null)
		{
			session.setAttribute("error", "Record Already Exists");
			System.out.println("duplicate scheme");
		}

//				objSchemeDao.save(objSchemeBean);
			
			listOrderBeans = objSchemeDao.getAllSchemes();
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
			return "redirect:SchemeHome";
			
		}
		return "redirect:SchemeHome";
	}
	
	@RequestMapping(value = "/deleteScheme")
	public @ResponseBody List<Map<String, String>> deleteScheme( ModelMap model,HttpServletRequest request)  {
		System.out.println("Scheme controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		String message =null;
		String id = null;
		try{
			id = request.getParameter("id");
			objSchemeDao.delete(Integer.parseInt(id));
			
				listOrderBeans = objSchemeDao.getAllSchemes();
				if(listOrderBeans != null && listOrderBeans.size() > 0) {
					  objectMapper = new ObjectMapper(); 
					  sJson =objectMapper.writeValueAsString(listOrderBeans);
					  request.setAttribute("allOrders1", sJson);
					 // System.out.println(sJson); 
				}
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class deleteStudent method");
		}

		return listOrderBeans;
	}
	
	@ModelAttribute("Scheme")
	public Map<Integer, String> populateScheme() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,name from scheme order by name asc";
			List<SchemeBean> list= objSchemeDao.populate(sSql);
			for(SchemeBean bean: list){
				statesMap.put(bean.getId(), bean.getName());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
}
