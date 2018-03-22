package com.aurospaces.neighbourhood.controller;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.*;
import org.springframework.transaction.support.*;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.aurospaces.neighbourhood.bean.DonorBean;
import com.aurospaces.neighbourhood.bean.UsersBean;
import com.aurospaces.neighbourhood.db.dao.DonorsDao;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class DonorController {
	@Autowired DonorsDao objDonorsDao;
	@Autowired DataSourceTransactionManager transactionManager;
	private Logger logger = Logger.getLogger(DonorController.class);
	@RequestMapping(value = "/DonorHome")
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
			logger.fatal("error in userLogin method in school LoginController class SchemeHome method  ");
			return "login";
			
		}
		return "DonorHome";
	}
	
	@RequestMapping(value = "/AddDonor")
	public String addDonor(@ModelAttribute("packCmd") DonorBean objDonorBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
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
			return "redirect:DonorHome";
			
		}
		return "redirect:DonorHome";
	}
	
	@RequestMapping(value = "/processDonorList", method = RequestMethod.POST)
	public String processDonorList(Model model, @RequestParam("excelfile2007") MultipartFile excelfile,HttpSession session) {	
		TransactionStatus objTransStatus = null;
		TransactionDefinition objTransDef = null;
		boolean isInsert =false;
		DonorBean objDonorBean = null;
		String val ="";
		/*UsersBean userBean = null;
		UsersBean isexist = null;
		String mobileNumber = null;
		 String username = "mopidevi";
         String password = "Mopidevi@123";
         String from = "SSSTMD";
         String requestUrl = null;
         String toAddress = null;*/
		try 
		{
			objTransDef = new DefaultTransactionDefinition();
			objTransStatus = transactionManager.getTransaction(objTransDef);
			int i = 0;
			int count = 0;
			int dupCount = 0;
			// Creates a workbook object from the uploaded excelfile
			Workbook workbook = WorkbookFactory.create(excelfile.getInputStream());
			// Creates a worksheet object representing the first sheet
			Sheet worksheet = workbook.getSheetAt(0);
			// Reads the data in excel file until last row is encountered
				// Creates an object for the UserInfo Model
			int mm =worksheet.getLastRowNum();
			
			while (i <= worksheet.getLastRowNum())
			{
				// Creates an object representing a single row in excel
				Row	 row = worksheet.getRow(i++);
				int i1 = row.getRowNum();
				if(i1>=1)
				{
//					Sets the Read data to the model class
					DonorBean objDonorBean1  =  new DonorBean();
        	
//					System.out.println(row.getCell(0).getStringCellValue());
					
					/*DataFormatter dataFormatter = new DataFormatter();
					String cellStringValue = dataFormatter.formatCellValue(row.getCell(1));
					System.out.println ("Is shows data as show in Excel file" + cellStringValue);*/
		            
//					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
					Date d = null;
					if(row.getCell(1) != null)
					{
						d = row.getCell(1).getDateCellValue();
						SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
							Date date1 = formatter.parse(d.toString());

				Calendar cal = Calendar.getInstance();
				cal.setTime(date1);
				String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
				System.out.println("formatedDate : " + formatedDate);
				objDonorBean1.setDor(formatedDate);
							objDonorBean1.setDor1(date1);
							
							SimpleDateFormat formatter1 = new SimpleDateFormat("dd/M/yyyy");
							Date date11 = formatter1.parse(formatedDate);
						System.out.println(date11);
					}
		           /* if(d !=null)
		            {objDonorBean1.setDor1(d);}*/

//		            String schemeTitle =row.getCell(2).getStringCellValue();
//		        	String schemeTitle =NumberFormat.getInstance().format(row.getCell(2).getNumericCellValue());
		        	String schemeTitle = null;
		        	if(row.getCell(2) != null)
					{
						switch(row.getCell(2).getCellType())
						{
							case Cell.CELL_TYPE_NUMERIC : schemeTitle = ""+row.getCell(2).getNumericCellValue(); break;
							case Cell.CELL_TYPE_STRING : schemeTitle = row.getCell(2).getStringCellValue(); break;
							default :
						}
					}
		        	if(schemeTitle != null)
		        	{
		        		schemeTitle =schemeTitle.replace(".0", "");
		        		objDonorBean1.setSchemeTitle(schemeTitle);
		        	}
		        	
//		            String name = row.getCell(3).getStringCellValue();
		            String name = null;
		        	if(row.getCell(3) != null)
					{
						switch(row.getCell(3).getCellType())
						{
							case Cell.CELL_TYPE_NUMERIC : name = ""+row.getCell(3).getNumericCellValue(); break;
							case Cell.CELL_TYPE_STRING : name = row.getCell(3).getStringCellValue(); break;
							default :
						}
					}
		        	if(name != null)
		        	{objDonorBean1.setName(name);}
		        	
//		        	String address = row.getCell(4).getStringCellValue();
		        	String address = null;
		        	if(row.getCell(4) != null)
					{
						switch(row.getCell(4).getCellType())
						{
							case Cell.CELL_TYPE_NUMERIC : address = ""+row.getCell(4).getNumericCellValue(); break;
							case Cell.CELL_TYPE_STRING : address = row.getCell(4).getStringCellValue(); break;
							default :
						}
					}
		        	if(address != null)
		        	{objDonorBean1.setAddress(address);}
		            
		            
		            /*Cell cellValue = row.getCell(5);
		        	if(cellValue.getCellType() == Cell.CELL_TYPE_NUMERIC)
		        	{
			            String mobile =NumberFormat.getInstance().format(row.getCell(5).getNumericCellValue());
			        	if(mobile != null)
			        	{
			        		mobile =mobile.replace(",", "");
			        		objDonorBean1.setMobile(mobile);
			        	}
		        	}*/
//		        	String mobile =NumberFormat.getInstance().format(row.getCell(5).getNumericCellValue());
		        	String mobile = null;
		        	if(row.getCell(5) != null)
					{
						switch(row.getCell(5).getCellType())
						{
							case Cell.CELL_TYPE_NUMERIC : mobile = ""+row.getCell(5).getNumericCellValue(); break;
							case Cell.CELL_TYPE_STRING : mobile = row.getCell(5).getStringCellValue(); break;
							default :
						}
					}
		        	if(mobile != null)
		        	{
		        		mobile =mobile.replace(".", "");
		        		mobile = mobile.replace("E9","");
		        		objDonorBean1.setMobile(mobile);
		        	}
		        	 
//		        	String datha = row.getCell(6).getStringCellValue();
		        	String datha = null;
		        	if(row.getCell(6) != null)
					{
						switch(row.getCell(6).getCellType())
						{
							case Cell.CELL_TYPE_NUMERIC : datha = ""+row.getCell(6).getNumericCellValue(); break;
							case Cell.CELL_TYPE_STRING : datha = row.getCell(6).getStringCellValue(); break;
							default :
						}
					}
		        	if(datha != null)
		        	{objDonorBean1.setDatha(datha);}
		        	
//		        	String gotram = row.getCell(7).getStringCellValue();
		        	String gotram = null;
		        	if(row.getCell(7) != null)
					{
						switch(row.getCell(7).getCellType())
						{
							case Cell.CELL_TYPE_NUMERIC : gotram = ""+row.getCell(7).getNumericCellValue(); break;
							case Cell.CELL_TYPE_STRING : gotram = row.getCell(7).getStringCellValue(); break;
							default :
						}
					}
		        	if(gotram != null)
		        	{objDonorBean1.setGotram(gotram);}
		        	
//		        	String schemeType =NumberFormat.getInstance().format(row.getCell(8).getNumericCellValue());
		        	String schemeType = null;
		        	if(row.getCell(8) != null)
					{
						switch(row.getCell(8).getCellType())
						{
							case Cell.CELL_TYPE_NUMERIC : schemeType = ""+row.getCell(8).getNumericCellValue(); break;
							case Cell.CELL_TYPE_STRING : schemeType = row.getCell(8).getStringCellValue(); break;
							default :
						}
					}
		        	if(schemeType != null)
		        	{
		        		schemeType = schemeType.replace(".0", "");
		        		objDonorBean1.setSchemeType(schemeType);
		        	}
		        	
//		        	String month =NumberFormat.getInstance().format(row.getCell(9).getNumericCellValue());
		        	String month = null;
		        	if(row.getCell(9) != null)
					{
						switch(row.getCell(9).getCellType())
						{
							case Cell.CELL_TYPE_NUMERIC : month = ""+row.getCell(9).getNumericCellValue(); break;
							case Cell.CELL_TYPE_STRING : month = row.getCell(9).getStringCellValue(); break;
							default :
						}
					}
		        	if(month != null)
		        	{
		        		month = month.replace(".0", "");
		        		objDonorBean1.setMonth(month);
		        	}
		        	
//		        	String day =NumberFormat.getInstance().format(row.getCell(10).getNumericCellValue());
		        	String day = null;
		        	if(row.getCell(10) != null)
					{
						switch(row.getCell(10).getCellType())
						{
							case Cell.CELL_TYPE_NUMERIC : day = ""+row.getCell(10).getNumericCellValue(); break;
							case Cell.CELL_TYPE_STRING : day = row.getCell(10).getStringCellValue(); break;
							default :
						}
					}
		        	if(day != null)
		        	{
		        		day= day.replace(".0", "");
		        		objDonorBean1.setDay(day);
		        	}
		        	
//		        	String amount =NumberFormat.getInstance().format(row.getCell(11).getNumericCellValue());
//		        	double amount =row.getCell(11).getNumericCellValue();

//		        	int value = 0; 
//		        	value = new BigDecimal(amount).setScale(0, RoundingMode.HALF_UP).intValue();
		        	double amount = 0.00;
		        	if(row.getCell(11) != null)
					{
						switch(row.getCell(11).getCellType())
						{
							case Cell.CELL_TYPE_NUMERIC : amount = row.getCell(11).getNumericCellValue(); break;
							default :
						}
					}
		        	if(amount != 0)
		        	{
		        		System.out.println("Donation: "+amount);
		        		objDonorBean1.setAmount1(amount);
		        	}
		        	
//		        	String receiptNo =NumberFormat.getInstance().format(row.getCell(12).getNumericCellValue());
		        	String receiptNo = null;
		        	if(row.getCell(12) != null)
					{
						switch(row.getCell(12).getCellType())
						{
//							case Cell.CELL_TYPE_FORMULA : receiptNo = "FORMULA "; break;
							case Cell.CELL_TYPE_NUMERIC : receiptNo = ""+row.getCell(12).getNumericCellValue(); break;
							case Cell.CELL_TYPE_STRING : receiptNo = row.getCell(12).getStringCellValue(); break;
							default :
						}
					}
		        	if(receiptNo != null)
		        	{
		        		receiptNo = receiptNo.replace(".0", "");
		        		objDonorBean1.setReceiptNo(receiptNo);
		        	}
		        	
//		        	isInsert = objDonorsDao.save(objDonorBean1);
		        	
		        	DonorBean sbean = objDonorsDao.duplicateCheckDonor(objDonorBean1);
		        	if(sbean == null){
		        		isInsert= objDonorsDao.save(objDonorBean1);
		        		count++;
		        	}else{
		        		dupCount++;
		        		isInsert = false;
		        	}
					
				}
				// persist data into database in here
			}
			transactionManager.commit(objTransStatus);
			workbook.close();
				// Creates an object representing a single row in excel
			
			((Closeable) workbook).close();
			if (isInsert) 
			{
				 String s1=String.valueOf(count);
				 session.setAttribute("d_created", s1+" Donor(s) Imported Successfully");
//				 return "redirect:DonorHome.htm?UploadSuccess=Success?no="+s1;
			}
			else 
			{
				String s2=String.valueOf(dupCount);
				session.setAttribute("d_error", s2+" Duplicate Donor Record(s) are Skipped");
//				return "redirect:DonorHome.htm?UploadFail=fail";
			}
		} catch (Exception e) {
			transactionManager.rollback(objTransStatus);
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in Donorcontroller class processDonorList method");
		}
		return "redirect:DonorHome";
	}
	
	@RequestMapping(value = "/getSchemeType")
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
			logger.fatal("error in Controller class DonorController");
			return "login";
			
		}
		return num;
	}
	
	@RequestMapping(value = "/deleteDonor")
	public @ResponseBody List<Map<String, String>> deleteDonor( ModelMap model,HttpServletRequest request)  {
		System.out.println("Scheme controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		String message =null;
		String id = null;
		try{
			id = request.getParameter("id");
			objDonorsDao.delete(Integer.parseInt(id));
			
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
			logger.fatal("error in userLogin method in school Homecontroller class deleteStudent method");
		}

		return listOrderBeans;
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
