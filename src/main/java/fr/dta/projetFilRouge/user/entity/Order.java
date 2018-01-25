package fr.dta.projetFilRouge.user.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "seq_versionOrder",sequenceName = "seq_versionOrder", initialValue = 1, allocationSize = 1)
@Getter
@Setter
@Table(name = "order_")
public class Order implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1909969806549750380L;

	@Id
	@GeneratedValue(generator = "seq_versionOrder")
	private Long id;
	
	@NotNull
	private Float totalPrice;
	
	@NotBlank
	private String orderNumber;
	
	@NotNull
	@JsonDeserialize(using=LocalDateDeserializer.class)
	@JsonSerialize(using=LocalDateSerializer.class)	
	private LocalDate orderDate;
	
	/*
	 * Relation de jointure avec la table User.
	 * Un client possède une liste de commandes.
	 * 1 commande est associée à un unique client.
	 */
	@ManyToOne
	private User user;
	
	/*
	 * Relation de jointure avec la table Order.
	 * Un produit est présent dans plusieurs commandes différentes.
	 * Une commande peut contenir plusieurs produits différents.
	 */
	@ManyToMany
	private List<Products> products = new ArrayList<>();

	public Order() {
		super();
	}

	public Order(Float totalPrice, String orderNumber, LocalDate orderDate, User user, List<Products> products) {
		super();
		this.totalPrice = totalPrice;
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.user = user;
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", totalPrice=" + totalPrice + ", orderNumber=" + orderNumber + ", orderDate="
				+ orderDate + ", user=" + user + ", products=" + products + "]";
	}
}
