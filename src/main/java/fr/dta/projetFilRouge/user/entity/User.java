package fr.dta.projetFilRouge.user.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Getter;
import lombok.Setter;



@Entity
@SequenceGenerator(name = "seq_versionUser",sequenceName = "seq_versionUser", initialValue = 1, allocationSize = 1)
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
	//@Temporal(TemporalType.DATE)
	@JsonDeserialize(using=LocalDateDeserializer.class)
	@JsonSerialize(using=LocalDateSerializer.class)	
	private LocalDate birthDate;
	
	@NotBlank
	@Column(unique = true)
	private String email;
	
	@NotBlank
	private String phoneNumber;

	private boolean isAdmin;

	/*
	 * Relation de jointure avec la table Order.
	 * Un client possède une liste de commandes.
	 * 1 commande est associée à un unique client.
	 */
//	@OneToMany(mappedBy = "user")
//	@JsonIgnore
//	private List<Order> orders = new ArrayList<>();	
	

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

//	public List<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}

	@Override
	public String toString() {
		return "User [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + ", login=" + login
				+ ", password=" + password + ", birthDate=" + birthDate + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", isAdmin=" + isAdmin + /*", orders=" + orders +*/"]";
	}	
	
	
}
