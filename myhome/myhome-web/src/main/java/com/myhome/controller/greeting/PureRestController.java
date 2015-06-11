package com.myhome.controller.greeting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myhome.domain.Greeting;
import com.myhome.domain.User;

@RestController
@RequestMapping("/greeting")
public class PureRestController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/")
	public Greeting greeting(
			@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template,
				name));
	}

	@RequestMapping("/list")
	public List<Greeting> greetingList() {

		List<Greeting> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Greeting g = new Greeting(i, "content" + i);
			list.add(g);
		}

		return list;
	}

	// ----------------------- simple text ISO ------------------------
	@RequestMapping(value = "/a01", method = RequestMethod.GET)
	public String test1() {
		return "Greetings from Spring Boot!";
	}

	// ----------------------- caractères accentués - UTF8
	// ------------------------
	@RequestMapping(value = "/a02", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String test2() {
		return "caractères accentués : éèàôûî";
	}

	// ----------------------- text/xml ------------------------
	@RequestMapping(value = "/a03", method = RequestMethod.GET, produces = "text/xml;charset=UTF-8")
	public String test3() {
		String greeting = "<greetings><greeting>Greetings from Spring Boot!</greeting></greetings>";
		return greeting;
	}

	// ----------------------- produire du jSON ------------------------
	@RequestMapping(value = "/a04", method = RequestMethod.GET)
	public Map<String, Object> a04() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("1", "un");
		map.put("2", new int[] { 4, 5 });
		return map;
	}

	// ----------------------- produire du jSON - 2 ------------------------
	@RequestMapping(value = "/a05", method = RequestMethod.GET)
	public User a05() {
		return new User(1, "carole", 45);
	}

	// ----------------------- génération complète de la réponse
	// ------------------------
	@RequestMapping(value = "/a13")
	public void a13(HttpServletResponse response) throws IOException {
		response.setStatus(666);
		response.addHeader("header1", "qq chose");
		response.addHeader("Content-Type", "text/html;charset=UTF-8");
		String greeting = "<h1>Greetings from Spring Boot!</h1>";
		response.getWriter().write(greeting);
	}

}