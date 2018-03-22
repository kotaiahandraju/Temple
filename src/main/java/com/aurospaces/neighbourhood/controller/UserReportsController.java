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

import com.aurospaces.neighbourhood.bean.*;
import com.aurospaces.neighbourhood.db.dao.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class UserReportsController {
@Autowired DonorsDao objDonorsDao;
@Autowired UsersDao objUsersDao;

	private Logger logger = Logger.getLogger(UserReportsController.class);
	@RequestMapping(value = "/UserDateWiseReport")
	public String dateWiseReport(@ModelAttribute("packCmd") ReportsBean objReportsBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		UsersBean objUserBean = null;
		String sJson=null;
		try{
			listOrderBeans = objDonorsDao.getDateDonors(null);
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
			logger.fatal("error in UserReportsController class");
			return "UserDateWiseReport";
			
		}
	return "userDateReports";
	}
	
	@RequestMapping(value = "/UserThidiWiseReport")
	public String thidiWiseReport(@ModelAttribute("packCmd") ReportsBean objReportsBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
//		System.out.println("Reports page...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		UsersBean objUserBean = null;
		String sJson=null;
		try{
			listOrderBeans = objDonorsDao.getThidiDonors(null);
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
			logger.fatal("error in UserReportsController class");
			return "UserThidiWiseReport";
			
		}
	return "userThidiReports";
	}
	
	
	@RequestMapping(value = "/userSearchDateWiseReports")
	public @ResponseBody List<Map<String, String>> searchDateWiseReports(@ModelAttribute("packCmd") ReportsBean objReportsBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			String schemeTitle = request.getParameter("schemeTitle");
			String frommonth = request.getParameter("frommonth");
			String tomonth = request.getParameter("tomonth");
			String day = request.getParameter("day");
			
			if(schemeTitle != null || frommonth != null || tomonth != null || day != null){
//				listOrderBeans =objDonorsDao.getDateWiseDonors(schemeTitle, frommonth, tomonth, day);
				listOrderBeans =objDonorsDao.getDateWiseDonors(objReportsBean);
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
			logger.fatal("error in UserSearchDateWiseReport() method in  UserReportController class");
			
		}
		return listOrderBeans;
	}
	
	
	@RequestMapping(value = "/userSearchThidiWiseReports")
	public @ResponseBody List<Map<String, String>> searchThidiWiseReports(@ModelAttribute("packCmd") ReportsBean objReportsBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			String schemeTitle = request.getParameter("schemeTitle");
			String frommonth = request.getParameter("frommonth");
			String tomonth = request.getParameter("tomonth");
			String day = request.getParameter("day");
			
			if(schemeTitle != null || frommonth != null || tomonth != null || day != null){
//				listOrderBeans =objDonorsDao.getThidiWiseDonors(schemeTitle, frommonth, tomonth, day);
				listOrderBeans =objDonorsDao.getThidiWiseDonors(objReportsBean);
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
			logger.fatal("error in UserSearchDateWiseReport() method in  UserReportController class");
			
		}
		return listOrderBeans;
	}
	
	
	@ModelAttribute("schemetitle")
	public Map<Integer, String> populateschemetype() {
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
	
	@ModelAttribute("telugumasaluDesc")
	public Map<Integer, String> populatetelugumasaluDesc() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,name from months where schemetype=2 order by id desc";
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
	
	@ModelAttribute("englishmonthsDesc")
	public Map<Integer, String> populateteenglishmonthsDesc() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,name from months where schemetype=1 order by id desc";
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
}
		

