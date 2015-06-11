package com.myhome.controller.greeting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/redirect")
public class RedirectController {

	// ------------ pont vers une action tierce ( def dans
	// ResponsesController-----------------------
	@RequestMapping(value = "/a10", method = RequestMethod.GET)
	public String a10() {

		return "redirect:greeting/a01";
	}
}
