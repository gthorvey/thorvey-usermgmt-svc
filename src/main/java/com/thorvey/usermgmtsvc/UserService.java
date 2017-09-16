package com.thorvey.usermgmtsvc;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/UserService")
public class UserService {
	
	@Autowired
	private UserRepository userDao;
	
	private static final String SUCCESS_RESULT = "success";
	
	private static final String FAILURE_RESULT = "failure";

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Collection<User> getUsers() {
		return userDao.findAll();
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody User getUser(@PathVariable("id") int userid) {
		return userDao.findOne(Long.valueOf(userid));
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody String createUser(@RequestBody String jsonString) throws IOException {
		User user = new User();
		ObjectMapper mapper = new ObjectMapper();
		user = mapper.readValue(jsonString, User.class);

		User result = userDao.save(user);
		if (result != null) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public String updateUser(@RequestBody String jsonString) throws IOException {
		User user = new User();
		ObjectMapper mapper = new ObjectMapper();
		user = mapper.readValue(jsonString, User.class);
		User result = userDao.save(user);
		if (result != null) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public String deleteUser(@PathVariable("id") int userid) {
		userDao.delete(Long.valueOf(userid));
		if (!userDao.exists(Long.valueOf(userid))) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@RequestMapping(value = "/users", method = RequestMethod.OPTIONS, produces = "application/json")
	public String getSupportedOperations() {
		return "GET, PUT, POST, DELETE";
	}

}