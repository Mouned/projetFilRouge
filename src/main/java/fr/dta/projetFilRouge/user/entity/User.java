package fr.dta.projetFilRouge.user.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@SequenceGenerator(name = "seq_versionUser",sequenceName = "seq_versionUser", initialValue = 1, allocationSize = 1)
public class User {

	
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
	
	@NotBlank
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
	@OneToMany(mappedBy = "userOrdered")
	private List<Order> listClientPrefere = new ArrayList<Order>();	
	

	public User(String lastname, String firstname, String login, String password, LocalDate birthDate, String email,
			String phoneNumber, List<Order> listClientPrefere) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.login = login;
		this.password = password;
		this.birthDate = birthDate;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.listClientPrefere = listClientPrefere;
	}

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public List<Order> getListClientPrefere() {
		return listClientPrefere;
	}


	public void setListClientPrefere(List<Order> listClientPrefere) {
		this.listClientPrefere = listClientPrefere;
	}		
}
