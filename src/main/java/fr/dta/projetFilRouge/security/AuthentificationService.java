package fr.dta.projetFilRouge.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import fr.dta.projetFilRouge.app.repository.UserRepository;
import fr.dta.projetFilRouge.user.entity.User;


@Component
public class AuthentificationService implements UserDetailsService {
    @Autowired
    private UserRepository utilisateurRepository;
    @Override
    public UserDetails loadUserByUsername(final String username) {
        User user = utilisateurRepository.findByEmail(username);
        if (user != null) {
            List<GrantedAuthority> rules = this.getUserCredentials(user);
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), rules);
        }
        throw new UsernameNotFoundException("username.not.found");
    }
    
    public List<GrantedAuthority> getUserCredentials(User user) {
        
        List<GrantedAuthority> rules = new ArrayList<>();
        
        rules.add(new SimpleGrantedAuthority("CUSTOMER"));
        
        if (user.isAdmin()) {
        	rules.add(new SimpleGrantedAuthority("ADMIN"));
        }
        
        return rules;
    }
}

