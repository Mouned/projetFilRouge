package fr.dta.projetFilRouge.user.entity;

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

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "seq_versionOrder",sequenceName = "seq_versionOrder", initialValue = 1, allocationSize = 1)
@Getter
@Setter
@Table(name = "order_")
public class Order {

	
	@Id
	@GeneratedValue(generator = "seq_versionOrder")
	private Long id;
	
	@NotNull
	private Long totalPrice;
	
	@NotBlank
	private String orderNumber;
	
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

	public Order(Long totalPrice, String orderNumber, User userOrdered, List<Products> orderProducts) {
		this.totalPrice = totalPrice;
		this.orderNumber = orderNumber;
		this.user = userOrdered;
		this.products = orderProducts;
	}

	public Order() {
		super();
	}
}
