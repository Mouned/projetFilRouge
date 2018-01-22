package fr.dta.projetFilRouge.app.service;

import java.util.List;

import org.hibernate.Hibernate;
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
	
	public User getUserById(Long id) {
		User u = userRepository.findById(id);
		
		//Hibernate.initialize(u.getOrders());
		
		return u;
	}
    
    public User findByEmail(String email) {
    	return userRepository.findByEmail(email);
    }
    
    public Long createUser(User user) {
    	userRepository.saveAndFlush(user);
    	if(user!=null)return user.getId();
    	return -1L;
    }
}
