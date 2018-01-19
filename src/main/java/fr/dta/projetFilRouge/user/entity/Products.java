package fr.dta.projetFilRouge.user.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.NotBlank;

import fr.dta.projetFilRouge.user.enumeration.Pegi;

@Entity
@SequenceGenerator(name = "seq_versionProducts",sequenceName = "seq_versionProducts", initialValue = 1, allocationSize = 1)
public class Products {

	
	@Id
	@GeneratedValue(generator = "seq_versionProducts")
	private Long id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private Long price;
	
	@NotBlank
	private String gamePublisher;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private Pegi pegi;
	
	@NotBlank
	private String type;
	
	/*
	 * Relation de jointure avec la table Order.
	 * Un produit est présent dans plusieurs commandes différentes.
	 * Une commande peut contenir plusieurs produits différents.
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Order> productOrders = new ArrayList<Order>();

	public Products(String title, Long price, String gamePublisher, Pegi pegi, String type, List<Order> productOrders) {
		this.title = title;
		this.price = price;
		this.gamePublisher = gamePublisher;
		this.pegi = pegi;
		this.type = type;
		this.productOrders = productOrders;
	}
	

	public Products() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getGamePublisher() {
		return gamePublisher;
	}

	public void setGamePublisher(String gamePublisher) {
		this.gamePublisher = gamePublisher;
	}

	public Pegi getPegi() {
		return pegi;
	}

	public void setPegi(Pegi pegi) {
		this.pegi = pegi;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Order> getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(List<Order> productOrders) {
		this.productOrders = productOrders;
	}

	public Long getId() {
		return id;
	}
	
}
