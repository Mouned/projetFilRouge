package fr.dta.projetFilRouge.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dta.projetFilRouge.user.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
