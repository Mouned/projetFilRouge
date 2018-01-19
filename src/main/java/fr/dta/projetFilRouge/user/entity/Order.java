package fr.dta.projetFilRouge.user.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@SequenceGenerator(name = "seq_versionOrder",sequenceName = "seq_versionOrder", initialValue = 1, allocationSize = 1)
public class Order {

	
	@Id
	@GeneratedValue(generator = "seq_versionOrder")
	private Long id;
	
	@NotBlank
	private Long totalPrice;
	
	@NotBlank
	private String orderNumber;
	
	/*
	 * Relation de jointure avec la table User.
	 * Un client possède une liste de commandes.
	 * 1 commande est associée à un unique client.
	 */
	@ManyToOne
	private User userOrdered;
	
	/*
	 * Relation de jointure avec la table Order.
	 * Un produit est présent dans plusieurs commandes différentes.
	 * Une commande peut contenir plusieurs produits différents.
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Products> orderProducts = new ArrayList<Products>();

	public Order(Long totalPrice, String orderNumber, User userOrdered, List<Products> orderProducts) {
		this.totalPrice = totalPrice;
		this.orderNumber = orderNumber;
		this.userOrdered = userOrdered;
		this.orderProducts = orderProducts;
	}

	public Order() {
		super();
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public User getUserOrdered() {
		return userOrdered;
	}

	public void setUserOrdered(User userOrdered) {
		this.userOrdered = userOrdered;
	}

	public List<Products> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<Products> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public Long getId() {
		return id;
	}
	
}
