package com.myhome.controller.helloworld;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myhome.domain.Company;
import com.myhome.domain.UserDetails;
import com.myhome.form.WelcomeForm;
import com.myhome.service.Avion;
import com.myhome.service.ICompanyService;
import com.myhome.service.ObjSession;

@Controller
public class HelloWorld {

	@Autowired
	private Avion avion;

	@Autowired
	private ObjSession objS;

	@Autowired
	private UserDetails userDetails;

	@Autowired
	private ICompanyService companyService;

	@RequestMapping("/")
	public @ResponseBody String home() {
		System.out.println("objsession=" + objS);

		return "SpringMVCHelloWorld.home()";
	}

	@RequestMapping(value = "/welcome.html", method = RequestMethod.GET)
	public String helloWorld(ModelMap model, HttpSession session) {
		System.out.println("Helloworld");
		System.out.println("objsession=" + objS);
		System.out.println(avion.test());
		model.addAttribute("message", "avion");
		session.setAttribute("avion", avion);
		return "welcome";
	}

	@RequestMapping("/catchexception")
	public String catchexception(ModelMap model, HttpSession session) {

		int a = 1;
		if (a == 1)
			throw new NullPointerException("test exception handler");
		return "welcome";
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		ModelAndView m = new ModelAndView("error");
		m.addObject("message", e.getMessage());
		return m;
	}

	// @RequestMapping(name = "/hello", method = RequestMethod.GET)
	// public String welcomeMessageParam(final ModelMap model,
	// @RequestParam(value = "message") final String message) {
	//
	// model.addAttribute("message", "Welcome (param) " + message);
	// return "welcome";
	// }

	@RequestMapping(value = "/companyListJSON", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Company> getCompanyListJSON() {
		System.out.println("getCompanyListJSON");
		return companyService.findAllCompanies();
	}

	@RequestMapping("/companyList")
	public String getCompanyList(ModelMap model, HttpSession session) {
		System.out.println("getCompanyList");
		List<Company> companyList = companyService.findAllCompanies();
		model.addAttribute("companyList", companyList);
		return "welcome";
	}

	@RequestMapping("/companyListModelView")
	public ModelAndView getCompanyListModelView(HttpSession session) {
		System.out.println("companyListModelView");
		List<Company> companyList = companyService.findAllCompanies();
		ModelAndView model = new ModelAndView("welcome");
		model.addObject("companyList", companyList);
		return model;
	}

	/**
	 * lien /perso avec au moins tmp en parametre
	 */
	@RequestMapping(value = "/perso", method = RequestMethod.GET, params = "tmp")
	public @ResponseBody String persoTmp(@RequestParam("tmp") String tmp) {
		System.out.println("objsession=" + objS);

		return "SpringMVCHelloWorld.perso(@RequestParam(tmp) String tmp) : "
				+ tmp + "  - " + avion.test();
	}

	/**
	 * tous les autres liens perso avec ou sans parametres
	 */
	@RequestMapping("/perso")
	public @ResponseBody String perso(WebRequest webRequest, HttpSession session) {
		System.out.println("objsession=" + objS);

		System.out.println(webRequest.getParameterMap().keySet());
		return "SpringMVCHelloWorld.perso() - " + avion.test();
	}

	@RequestMapping("/somepage")
	public String someAction(@ModelAttribute("welcomeform") WelcomeForm data,
			HttpSession session) {

		System.out.println("name=" + data.getName());

		if (data != null && data.getName() != null && !data.getName().isEmpty()) {
			session.setAttribute("name", data.getName());
		}

		return "redirect:welcome";
	}

	/**
	 * URI parameter
	 */
	@RequestMapping(value = "/URITemplate/{ownerId}", method = RequestMethod.GET)
	public @ResponseBody String uriTemplate(@PathVariable String ownerId,
			Model model) {
		return "uriTemplate : \"" + ownerId + "\"";
	}

	/**
	 * FILE DOWNLOAD
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// get absolute path of the application
		ServletContext context = request.getServletContext();
		String appPath = context.getRealPath("/WEB-INF/");
		System.out.println("appPath = " + appPath);

		// construct the complete absolute path of the file
		String fullPath = appPath + "/test.txt";
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		// get MIME type of the file
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[4096];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();
	}

	/**
	 * GET JSON
	 */
	@RequestMapping(value = "/springcontent", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody UserDetails getUser() {
		System.out.println("SpringMVCHelloWorld.getUser()");
		UserDetails userDetails = new UserDetails();
		userDetails.setUserName("Praveen");
		userDetails.setEmailId("praveen@gmail.com");
		return userDetails;
	}

	/**
	 * GET
	 */
	@RequestMapping(value = "/springcontent2", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String getStr() {
		System.out.println("SpringMVCHelloWorld.getStr()");
		return "FROM getStr()";
	}

	/**
	 * GET
	 */
	@RequestMapping(value = "/springcontent3", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<String> getList() {
		System.out.println("SpringMVCHelloWorld.getList()");
		List<String> list = new ArrayList<String>();

		list.add("num1");
		list.add("num2");
		list.add("num3");

		return list;
	}

	/**
	 * GET
	 */
	@RequestMapping(value = "/springcontent4", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Integer> getMap() {
		System.out.println("SpringMVCHelloWorld.getMap()");
		Map<String, Integer> map = new HashMap<String, Integer>();

		map.put("num1", 1);
		map.put("num2", 2);
		map.put("num3", 3);

		return map;
	}

	/**
	 * POST JSON
	 */
	@RequestMapping(value = "/savecompany_json", method = RequestMethod.POST)
	public @ResponseBody String saveCompany_JSON(@RequestBody Company company) {
		System.out.println("SpringMVCHelloWorld.saveCompany_JSON()");
		String str = "The company name: " + company.getName()
				+ ", Employees count: " + company.getEmployees()
				+ ", Headoffice: " + company.getHeadoffice();
		// System.out.println(str);
		return str;
	}

	/**
	 * POST
	 */
	@RequestMapping(value = "/savecompany", method = RequestMethod.POST)
	public @ResponseBody Company saveCompany(@RequestParam("name") String name,
			@RequestParam("employees") long employees,
			@RequestParam("headoffice") String headoffice) {
		System.out.println("SpringMVCHelloWorld.saveCompany()");
		String str = "The company data (name: " + name + ", employees: "
				+ String.valueOf(employees) + ", headoffice: " + headoffice
				+ ") is saved";
		// System.out.println(str);

		Company c = new Company(name + "___", employees + 100, "___"
				+ headoffice);

		return c;
	}
}
