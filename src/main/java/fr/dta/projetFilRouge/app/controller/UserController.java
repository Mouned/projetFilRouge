package fr.dta.projetFilRouge.app.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.projetFilRouge.app.service.UserService;
import fr.dta.projetFilRouge.user.entity.User;

@RestController
@RequestMapping(value = "/api/users")
@Transactional
public class UserController {

	@Autowired
	UserService userService;

	@CrossOrigin
	@RequestMapping(value = "search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserById(@RequestParam("id") Long id) {
		User user = userService.getUserById(id);
		return user;
	}
	
	@CrossOrigin
	@RequestMapping(value = "all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUser() {
		List<User> users = userService.getAllUser();
		return users;
	}
	
	@CrossOrigin
	@RequestMapping(value = "create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public Long createUser(@RequestBody User user) {
		
		System.out.println(user);
		Long userId = userService.createUser(user);
		
		return userId;
	}
	
	@CrossOrigin
	@RequestMapping(value = "get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserByLogin(@RequestParam("login") String login) {
		
		return userService.findByLogin(login);
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser(@RequestBody User user) {
		userService.update(user);
	}
}