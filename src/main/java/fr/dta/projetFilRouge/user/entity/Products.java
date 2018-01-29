package fr.dta.projetFilRouge.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

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
	
	@NotNull
	//@Column(precision=10, scale=2)
	private Float price;
	
	@NotBlank
	private String gamePublisher;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Pegi pegi;
	
	@NotBlank
	private String type;
	
	@Column
	private String url;
	
	@NotNull
	private Boolean available;
	
	public Products(String title, Float price, String gamePublisher, Pegi pegi, String type) {
		this.title = title;
		this.price = price;
		this.gamePublisher = gamePublisher;
		this.pegi = pegi;
		this.type = type;
	}

	public Products() {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", title=" + title + ", price=" + price + ", gamePublisher=" + gamePublisher
				+ ", pegi=" + pegi + ", type=" + type + ", url=" + url + "]";
	}
	
}
