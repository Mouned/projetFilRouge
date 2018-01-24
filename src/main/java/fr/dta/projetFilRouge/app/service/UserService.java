package fr.dta.projetFilRouge.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.dta.projetFilRouge.app.repository.UserRepository;
import fr.dta.projetFilRouge.user.entity.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> getAllUser() {
		List<User> list = userRepository.findAll();
		return list;
	}

	public User getUserById(Long id) {
		return userRepository.findById(id);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Long createUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userRepository.saveAndFlush(user);
		if (user != null)
			return user.getId();
		return -1L;
	}
	
	public User findByLogin(String login) {
		return userRepository.findByLogin(login);
	}
}
