package fr.dta.projetFilRouge.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import fr.dta.projetFilRouge.user.enumeration.Pegi;
import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "seq_versionProducts",sequenceName = "seq_versionProducts", initialValue = 1, allocationSize = 1)
@Getter
@Setter
public class Products implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1009609691074522512L;

	@Id
	@GeneratedValue(generator = "seq_versionProducts")
	private Long id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	//@Column(precision=10, scale=2)
	private Float price;
	
	@NotBlank
	private String gamePublisher;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Pegi pegi;
	
	@NotBlank
	private String type;
	
	public Products(String title, Float price, String gamePublisher, Pegi pegi, String type) {
		this.title = title;
		this.price = price;
		this.gamePublisher = gamePublisher;
		this.pegi = pegi;
		this.type = type;
	}

	public Products() {
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", title=" + title + ", price=" + price + ", gamePublisher=" + gamePublisher
				+ ", pegi=" + pegi + ", type=" + type + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
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
}
