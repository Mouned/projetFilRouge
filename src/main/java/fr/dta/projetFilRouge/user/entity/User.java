package fr.dta.projetFilRouge.user.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;



@Entity
@SequenceGenerator(name = "seq_versionUser",sequenceName = "seq_versionUser", initialValue = 1, allocationSize = 1)
@Getter
@Setter
@Table(name = "member")
public class User implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -675152126074975436L;

	@Id
	@GeneratedValue(generator = "seq_versionUser")
	private Long id;
	
	@NotBlank
	private String lastname;
	
	@NotBlank
	private String firstname;
	
	@NotBlank
	private String login;
	
	@NotBlank
	private String password;
	
	@NotNull
	private LocalDate birthDate;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String phoneNumber;

	/*
	 * Relation de jointure avec la table Order.
	 * Un client possède une liste de commandes.
	 * 1 commande est associée à un unique client.
	 */
	@OneToMany(mappedBy = "user")
	private List<Order> orders = new ArrayList<>();	
	

	public User(String lastname, String firstname, String login, String password, LocalDate birthDate, String email,
			String phoneNumber, List<Order> listClientPrefere) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.login = login;
		this.password = password;
		this.birthDate = birthDate;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.orders = listClientPrefere;
	}

	public User() {
	}	
}
