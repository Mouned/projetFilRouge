package fr.dta.projetFilRouge.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dta.projetFilRouge.app.repository.UserRepository;
import fr.dta.projetFilRouge.user.entity.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUser() {
		List<User> list = userRepository.findAll();
		return list;
	}
    
    public void createProduct(User user) {
    	userRepository.saveAndFlush(user);
    }
}
