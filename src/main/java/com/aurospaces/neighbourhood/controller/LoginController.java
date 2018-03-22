package com.aurospaces.neighbourhood.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.DonorBean;
import com.aurospaces.neighbourhood.bean.SmsBean;
import com.aurospaces.neighbourhood.bean.UsersBean;
import com.aurospaces.neighbourhood.db.dao.DaySmsDao;
import com.aurospaces.neighbourhood.db.dao.DonorsDao;
import com.aurospaces.neighbourhood.db.dao.SchemeDao;
import com.aurospaces.neighbourhood.db.dao.SmsDao;
import com.aurospaces.neighbourhood.db.dao.UsersDao;
import com.aurospaces.neighbourhood.util.SendAttachmentInEmail;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class LoginController {
	@Autowired
	ServletContext objContext;
	@Autowired
	SchemeDao objSchemeDao;
	@Autowired
	UsersDao objUsersDao;
	@Autowired
	DonorsDao objDonorsDao;
	@Autowired
	SmsDao objSmsDao;
	@Autowired DaySmsDao daySmsDao;
	private Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/Login")
	public String adminLogin(ModelMap model, HttpServletRequest request, HttpSession session)
			throws JsonGenerationException, JsonMappingException, IOException {
		// System.out.println("AdminHome page...");
		String username = "mopidevi ";
		String password = "Mopidevi@123";
		String from = "SSSTMD";
		String requestUrl = null;
		String mobileNumber = null;
		SmsBean objSmsBean = null;
		try {
			UsersBean objuserBean = (UsersBean) session.getAttribute("cacheUserBean");
			if (objuserBean != null) {
				if (Integer.parseInt(objuserBean.getRollId()) == 1) {
					return "redirect:Home.htm";
				}
				if (Integer.parseInt(objuserBean.getRollId()) == 2) {
					return "redirect:UserHome.htm";
				}
			}
			List<UsersBean> listBean = objUsersDao.getLastWeekDays();
			for (UsersBean userBean : listBean) {
  System.out.println(userBean.getDay());
				UsersBean objUserBean = objUsersDao.todaySMSSendOrNOt(userBean.getDay());
				if (objUserBean == null) {
					UsersBean saveUserBean = new UsersBean();
							saveUserBean.setDay(userBean.getDay());
					saveUserBean.setStatus("1");
					List<DonorBean> listDonorBean = objDonorsDao.getSMSforDoners(userBean.getDay());
					if (listDonorBean != null) {
						for (DonorBean donerBean : listDonorBean) {
							System.out.println(userBean.getDay());
							String message = "Dear #1, Your occasion of #2 is during #3 #4 #5.";
							String dumessage = message;
							
							
							Date m = new Date();
						    Calendar cal = Calendar.getInstance();  
						    cal.setTime(m);  
						    cal.add(Calendar.DATE, 7); // 7 is the days you want to add or subtract   
						    m = cal.getTime();   
						    System.out.println("mmm"+m);
						    String thisYear = new SimpleDateFormat("yyyy").format(m);
						    
							dumessage = dumessage.replaceAll("#1", donerBean.getName());
							dumessage = dumessage.replaceAll("#2", donerBean.getSchemeTitle());
							dumessage = dumessage.replaceAll("#3", donerBean.getMonth());
							dumessage = dumessage.replaceAll("#4", donerBean.getDay());
							dumessage = dumessage.replaceAll("#5", thisYear);

							mobileNumber = donerBean.getMobile();

							if (StringUtils.isNotBlank(mobileNumber)) {
								requestUrl = "http://182.18.160.225/index.php/api/bulk-sms?username="
										+ URLEncoder.encode(username, "UTF-8") + "&password="
										+ URLEncoder.encode(password, "UTF-8") + "&from=" + from + "&to="
										+ URLEncoder.encode(mobileNumber, "UTF-8") + "&message="
										+ URLEncoder.encode(dumessage, "UTF-8") + "&sms_type=2";
								URL url = new URL(requestUrl);
								HttpURLConnection uc = (HttpURLConnection) url.openConnection();
								System.out.println(uc.getResponseMessage());
								uc.disconnect();
								objSmsBean = new SmsBean();
								objSmsBean.setMessage(dumessage);
								objSmsBean.setDonorId(String.valueOf(donerBean.getDonorId()));
								if (userBean != null) {
									objSmsBean.setSenderId(String.valueOf(userBean.getId()));
								}
								objSmsDao.save(objSmsBean);
								daySmsDao.save(saveUserBean);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in LoginController class adminLogin method");
			return "login";
		}
		return "login";
	}

	@RequestMapping(value = "/LoginCheck")
	public String loginCheck(@ModelAttribute("packCmd") UsersBean objUsersBean, ModelMap model,
			HttpServletRequest request, HttpSession session)
					throws JsonGenerationException, JsonMappingException, IOException {
		// System.out.println("LoginCheck page...");

		UsersBean userBean = null;
		UsersBean objuserBean = null;
		try {

			if (StringUtils.isBlank(objUsersBean.getName()) || StringUtils.isBlank(objUsersBean.getPassword())) {
				session.setAttribute("error", "Please Enter Login Credentials");
				return "redirect:Login";
			}
			if (objUsersBean != null) {
				UsersBean list = objUsersDao.getUserExist(objUsersBean);
				if (list != null) {
					session.setAttribute("cacheUserBean", list);
					session.setAttribute("name", list.getName());
					session.setAttribute("rolId", list.getRollId());
					if (list.getRollId().equals("1")) {
						session.setAttribute("login", "Login Successfully");
						return "redirect:Home.htm";
					}
					if (list.getRollId().equals("2")) {
						session.setAttribute("userLogin", "Login Successfully");
						return "redirect:UserHome.htm";
					}
				} else {
					session.setAttribute("error", "Invalid Login Credentials");
					return "redirect:Login";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in loginCheck method in LoginController class");
			return "login";

		}
		return "login";
	}

	@RequestMapping(value = "/Logout")
	public String adminLogout(ModelMap model, HttpServletRequest request, HttpSession objSession,
			HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("logout page...");
		try {

			HttpSession session1 = request.getSession(false);
			if (session1 != null) {
				session1.removeAttribute("cacheUserBean");
				session1.removeAttribute("name");
				session1.removeAttribute("rolId");
				session1.invalidate();
				// return "redirect:Login";

				// response.setHeader("Cache-Control",
				// "no-cache,no-store,must-revalidate"); // HTTP 1.0
				// response.setHeader("Pragma", "no-cache"); // HTTP 1.0
				// response.setDateHeader("Expires", -1); // prevents caching at
				// the proxy server
				// String baseUrl = MiscUtils.getBaseUrl(request);
				// System.out.println(baseUrl);
				// response.sendRedirect(baseUrl+"/Login.htm" );
				// response.sendRedirect(request.getContextPath() + "/Login");
				return "redirect:Login";
			}
			/*
			 * if(objSession.getAttribute("userbean") != null ||
			 * objSession.getAttribute("userbean") != "") {
			 * objSession.setAttribute("userbean", null);
			 * objSession.invalidate(); return "redirect:Login"; }
			 */

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in Logincontroller class logout method");
			return "redirect:Login";
		}
		return "login";
	}

	@RequestMapping(value = "/Home")
	public String home(@ModelAttribute("packCmd") DonorBean objDonorBean, ModelMap model, HttpServletRequest request,
			HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		// System.out.println("Home page...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		UsersBean objUserBean = null;
		// SchemeBean objSchemeBean = null;
		String sJson = null;
		try {

			listOrderBeans = objDonorsDao.getAllDonors(null);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school SchemeController class SchemeHome method  ");
			return "redirect:Home";

		}
		return "Home";
	}

	@RequestMapping(value = "/searchSchemeType")
	public @ResponseBody List<Map<String, String>> searchSchemeType(ModelMap model, HttpServletRequest request,
			HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		try {
			String id = request.getParameter("id");
			if (id != null) {
				listOrderBeans = objDonorsDao.getAllDonors(id);
			}

			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in LoginController class searchSchemeType method");

		}
		return listOrderBeans;
	}

	@RequestMapping(value = "/sendSms")
	public @ResponseBody String sendSms(ModelMap model, HttpServletRequest request, HttpSession session) {
		// System.out.println("Home page...");
		String username = "mopidevi ";
		String password = "Mopidevi@123";
		String from = "SSSTMD";
		String message = request.getParameter("message");
		String donorId = request.getParameter("donorId");
		String[] array = donorId.split(",");
		int donorIdint = 0;
		DonorBean objDonor = null;
		String requestUrl = null;
		String mobileNumber = null;
		String message1 = "";
		String dumessage = null;
		int count = 0;
		SmsBean objSmsBean = null;
		JSONObject json = new JSONObject();
		UsersBean userBean = (UsersBean) session.getAttribute("cacheUserBean");

		try {
			System.out.println("-------------");
			for (int i = 0; i < array.length; i++) {
				objDonor = new DonorBean();
				donorIdint = Integer.parseInt(array[i]);
				objDonor = objDonorsDao.getById(donorIdint);
				String thisYear = new SimpleDateFormat("yyyy").format(new Date());

				dumessage = message.replaceAll("#1", objDonor.getName());
				dumessage = dumessage.replaceAll("#2", objDonor.getSchemeTitle());
				dumessage = dumessage.replaceAll("#3", objDonor.getMonth());
				dumessage = dumessage.replaceAll("#4", objDonor.getDay());
				dumessage = dumessage.replaceAll("#5", thisYear);

				System.out.println(dumessage);

				mobileNumber = objDonor.getMobile();

				if (StringUtils.isNotBlank(mobileNumber)) {
					requestUrl = "http://182.18.160.225/index.php/api/bulk-sms?username="
							+ URLEncoder.encode(username, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8")
							+ "&from=" + from + "&to=" + URLEncoder.encode(mobileNumber, "UTF-8") + "&message="
							+ URLEncoder.encode(dumessage, "UTF-8") + "&sms_type=2";
					URL url = new URL(requestUrl);
					HttpURLConnection uc = (HttpURLConnection) url.openConnection();
					System.out.println(uc.getResponseMessage());
					uc.disconnect();
					objSmsBean = new SmsBean();
					objSmsBean.setMessage(dumessage);
					objSmsBean.setDonorId(array[i]);
					if (userBean != null) {
						objSmsBean.setSenderId(String.valueOf(userBean.getId()));
					}
					objSmsDao.save(objSmsBean);
					session.setAttribute("sms", "Successfully SMS has been Sended");
					count++;
					json.put("message", "Successfully SMS has been Sent");
					message1 = "Successfully SMS has been Sent";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in sendSms method in LoginController class");
			return "sms failed";

		}
		return String.valueOf(json);
	}

	@RequestMapping(value = "/ChangePassword")
	public String changePassword(@ModelAttribute("packCmd") UsersBean objUsersBean, ModelMap model,
			HttpServletRequest request, HttpSession session)
					throws JsonGenerationException, JsonMappingException, IOException {
		try {

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println(e);
			logger.error(e);
			logger.fatal("error in changePassword method in Logincontroller");
			return "login";

		}
		return "ChangePassword";
	}

	@RequestMapping(value = "/adminChangePassword")
	public String adminChangePassword(ModelMap model, HttpServletRequest request, HttpSession session)
			throws JsonGenerationException, JsonMappingException, IOException {
		UsersBean userBean = null;
		UsersBean objuserBean = null;
		String oldPassword = null;
		String password = null;
		String userPassword = null;
		String retypePassword = null;
		try {

			userBean = new UsersBean();
			oldPassword = request.getParameter("oldPassword");
			password = request.getParameter("password");
			retypePassword = request.getParameter("retypePassword");
			if (StringUtils.isNotBlank(retypePassword) && StringUtils.isNotBlank(password)) {
				userBean = (UsersBean) session.getAttribute("cacheUserBean");
				userPassword = userBean.getPassword();
				if (!userPassword.equals(oldPassword)) {
					session.setAttribute("invalid", "Invalid Current Password");
					return "redirect:ChangePassword";
				}
				if (password.equals(retypePassword) && userPassword.equals(oldPassword)) {
					userBean.setPassword(password);
					objUsersDao.save(userBean);
					session.setAttribute("changed", "Successfully Password Changed");
					return "redirect:ChangePassword";
				} else {
					session.setAttribute("mismatch", "Password and Confirm Password mismatched");
					return "redirect:ChangePassword";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school parent controll class parentChangePassword method  ");
			session.setAttribute("message", "fail login");
		}
		return "redirect:ChangePassword";
	}

	@RequestMapping(value = "/oldPasswordCheck")
	public @ResponseBody String oldPasswordCheck(ModelMap model, HttpServletRequest request, HttpSession session)
			throws JsonGenerationException, JsonMappingException, IOException {
		UsersBean userBean = null;
		UsersBean objuserBean = null;
		String oldPassword = null;
		String password = null;

		try {
			oldPassword = request.getParameter("oldPassword");
			userBean = (UsersBean) session.getAttribute("cacheUserBean");
			password = userBean.getPassword();
			if (!password.equals(oldPassword)) {

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class HomePage1 method  ");
			session.setAttribute("message", "fail login");
		}
		return "redirect:ChangePassword";
	}

	@ModelAttribute("schemetype")
	public Map<Integer, String> populateschemetype() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,name from schemetype order by name asc";
			List<DonorBean> list = objDonorsDao.populate(sSql);
			for (DonorBean bean : list) {
				statesMap.put(bean.getId(), bean.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}

	@RequestMapping(value = "/backupData")
	public String backUpdata(HttpServletResponse response, HttpServletRequest request, HttpSession objSession)
			throws JsonGenerationException, JsonMappingException, IOException {
		String sTomcatRootPath = null;
		try {
			ServletContext sc = request.getServletContext();
			// String propertiespath =
			// sc.getRealPath("/Resources/DataBase.properties");

			String propertiespath = objContext.getRealPath("/Resources" + File.separator + "DataBase.properties");
			System.out.println("File path is: " + propertiespath);

			FileInputStream input = new FileInputStream(propertiespath);
			Properties prop = new Properties();
			// load a properties file
			prop.load(input);
			String usermail = prop.getProperty("usermail");
			String to = prop.getProperty("to");
			String mailpassword = prop.getProperty("mailpassword");
			String port = prop.getProperty("port");
			String userName = prop.getProperty("userName");
			String password = prop.getProperty("password");
			String dbname = prop.getProperty("db");
			String dbport = prop.getProperty("dbport");

			// Date d =new Date();
			// String date = d.getDate()+"-"+d.getMonth()+"-"+d.getYear();

			// Properties prop = new Properties();
			// String propertiespath = objContext.getRealPath("Resources"
			// + File.separator + "DataBase.properties");
			// FileInputStream input = new FileInputStream(propertiespath);
			// // load a properties file
			// prop.load(input);
			// String couponcode = prop.getProperty("usermail");

			/*
			 * ByteArrayOutputStream outByteStream = new
			 * ByteArrayOutputStream(); hwb.write(outByteStream);
			 */
			byte[] outArray = SendAttachmentInEmail.getData("localhost", dbport, userName, password, dbname).getBytes();
			response.setContentType("application/ms-excel");
			response.setContentLength(outArray.length);
			response.setHeader("Expires:", "0"); // eliminates browser caching
			response.setHeader("Content-Disposition", "attachment; filename=" + new java.util.Date() + ".sql");
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();

			byte[] data = SendAttachmentInEmail.getData("localhost", dbport, userName, password, dbname).getBytes();
			File filedst = new File("templefile.sql");
			FileOutputStream dest = new FileOutputStream(filedst);
			dest.write(data);
			dest.close();
			SendAttachmentInEmail.send(to, usermail, mailpassword, port);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in LoginController class backUpdata method");
		}
		return "redirect:Home";

	}

}
