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

import com.aurospaces.neighbourhood.bean.ThidiBean;
import com.aurospaces.neighbourhood.db.dao.ThidiDao;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class ThidiController {
@Autowired ThidiDao objThidiDao;

	private Logger logger = Logger.getLogger(ThidiController.class);
	
	@RequestMapping(value = "/ThidiHome")
	public String thidiHome(@ModelAttribute("packCmd") ThidiBean objThidiBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
//		System.out.println("ThidiHome page...");
		List<Map<String, String>> listOrderBeans = null;
		List<Map<String, String>> listOrderBeans1 = null;
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			
			listOrderBeans1 = objThidiDao.getAllThidis();
			if(listOrderBeans1 != null && listOrderBeans1.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans1);
				  request.setAttribute("allOrders2", sJson);
				 // System.out.println(sJson); 
			}else{
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans1);
				  request.setAttribute("allOrders2", "''");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in ThidiController class thidiHome method  ");
			return "thidi";
			
		}
		return "ThidiHome";
	}
	
	@RequestMapping(value = "/AddThidi")
	public String addThidi(@ModelAttribute("packCmd") ThidiBean objThidiBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("AddThidi page...");
		List<Map<String, String>> listOrderBeans1 = null;
		ObjectMapper objectMapper = null;
		ThidiBean tb = null;
		String sJson=null;
		int id = 0;
		ThidiBean thidi= null;
		String allParentIds = null;
		try{

			ThidiBean thidiBean = objThidiDao.getByTname(objThidiBean.getTname());
		int dummyId =0;
		if(thidiBean != null){
			dummyId = thidiBean.getTid();
		}
		if(objThidiBean.getTid() != 0)
		{
			 id = objThidiBean.getTid();
			if(id == dummyId || thidiBean == null )
			{
				objThidiDao.save(objThidiBean);
				session.setAttribute("tupdated", "Festival/Thidi Updated Successfully");
				System.out.println("thidi updated");
			}
			else
			{
				session.setAttribute("terror", "Record Already Exists");
				System.out.println("duplicate thidi");
			}
		}
		if(objThidiBean.getTid() == 0 && thidiBean == null)
		{
			objThidiDao.save(objThidiBean);
			session.setAttribute("tcreated", "Festival/Thidi Created Successfully");
			System.out.println("thidi inserted");
		}
		if(objThidiBean.getTid() == 0 && thidiBean != null)
		{
			session.setAttribute("terror", "Record Already Exists");
			System.out.println("duplicate thidi");
		}

//				objThidiDao.save(objThidiBean);
		listOrderBeans1 = objThidiDao.getAllThidis();
		if(listOrderBeans1 != null && listOrderBeans1.size() > 0) {
			  objectMapper = new ObjectMapper(); 
			  sJson =objectMapper.writeValueAsString(listOrderBeans1);
			  request.setAttribute("allOrders2", sJson);
			 // System.out.println(sJson); 
		}else{
			  objectMapper = new ObjectMapper(); 
			  sJson =objectMapper.writeValueAsString(listOrderBeans1);
			  request.setAttribute("allOrders2", "''");
		}
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in ThidiController class addThidi method");
			return "redirect:ThidiHome";
			
		}
		return "redirect:ThidiHome";
	}
	
	@RequestMapping(value = "/deleteThidi")
	public @ResponseBody List<Map<String, String>> deleteThidi( ModelMap model,HttpServletRequest request)  {
		System.out.println("Thidi controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		String message =null;
		String tid = null;
		try{
			tid = request.getParameter("tid");
			objThidiDao.delete(Integer.parseInt(tid));
			
				listOrderBeans = objThidiDao.getAllThidis();
				if(listOrderBeans != null && listOrderBeans.size() > 0) {
					  objectMapper = new ObjectMapper(); 
					  sJson =objectMapper.writeValueAsString(listOrderBeans);
					  request.setAttribute("allOrders2", sJson);
					 // System.out.println(sJson); 
				}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in ThidiController class deleteThidi method");
		}

		return listOrderBeans;
	}
	
}
