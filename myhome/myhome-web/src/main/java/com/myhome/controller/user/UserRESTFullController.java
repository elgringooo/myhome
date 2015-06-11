package com.myhome.controller.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.myhome.domain.User;
import com.myhome.domain.UserSearch;

/**
 * CRUD
 * 
 * @author Thales
 *
 */

@Controller
@RequestMapping("/user")
public class UserRESTFullController {

	private Set<User> syncal = Collections
			.synchronizedSet(new java.util.HashSet<User>());

	// /////////////////////
	// CRUD
	// /////////////////////

	// http://localhost:8181/springmvcsamples/user/getList
	@RequestMapping(value = "/getList", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Set<User> getList() {
		return syncal;

	}

	// http://localhost:8181/springmvcsamples/user/create
	// {"id":1,"nom":"test1","age":35}
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody User p) {
		synchronized (syncal) {
			syncal.add(p);

		}
	}

	// http://localhost:8181/springmvcsamples/user/update/test1
	// {"id":1,"nom":"test1","age":99}
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody User p) {
		synchronized (syncal) {
			for (User user : syncal) {
				if (user.getId() == id) {
					user.setAge(p.getAge());
					user.setId(p.getId());
				}
			}
		}
	}

	// http://localhost:8181/springmvcsamples/user/delete/test1
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE
			+ ";charset=UTF-8")
	public @ResponseBody String delete(@PathVariable Integer id) {
		User found = null;
		synchronized (syncal) {

			for (User user : syncal) {
				if (user.getId() == id) {
					found = user;
					break;
				}
			}

			if (found != null) {
				syncal.remove(found);
				return "found";
			}

		}

		return "Nothing";
	}

	/**
	 * GET XML. http://localhost:8181/springmvcsamples/getUserListXML
	 */
	@RequestMapping(value = "/getUserListXML", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody UserSearch getUserListXML() {
		return buildUserSearchResult();
	}

	/**
	 * GET JSON
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUserListJSON", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserSearch getUserListJSON() {
		return buildUserSearchResult();
	}

	private UserSearch buildUserSearchResult() {
		List<User> plist = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			plist.add(new User(i, "test" + 1, i * 5));
		}
		UserSearch result = new UserSearch();
		result.setResult(plist);
		return result;
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getUser() {
		return new User(1, "test" + 1, 35);
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User saveUser(@RequestBody User p) {
		return p;
	}
}