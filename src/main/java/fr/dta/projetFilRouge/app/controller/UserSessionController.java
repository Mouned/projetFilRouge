package fr.dta.projetFilRouge.app.controller;

import javax.transaction.Transactional;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/session")
@Transactional
public class UserSessionController {

	@RequestMapping(value = "get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(Authentication authentication) {
		if(authentication != null)
			return (User) authentication.getPrincipal();
		return null;
	}
}
