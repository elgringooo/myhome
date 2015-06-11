package com.myhome.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	/**
	 * Example param localhost:8181/springmvcsamples/home?name=gerald
	 * 
	 * */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(ModelMap model,
			@RequestParam(value = "name", required = false) String name) {
		System.out.println("Home");
		model.addAttribute("message", "Welcome to your home (param) " + name);
		return "home";
	}

	/**
	 * Example path and param
	 * 
	 * localhost:8181/springmvcsamples/home/ventabren?name=gerald
	 * */

	@RequestMapping(value = "/home/{path}", method = RequestMethod.GET)
	public String homePath(ModelMap model, @PathVariable String path,
			@RequestParam(value = "name", required = false) String name) {
		System.out.println("Home");
		model.addAttribute("message", "Welcome to your path  " + path + " "
				+ name);
		return "home";
	}

}
