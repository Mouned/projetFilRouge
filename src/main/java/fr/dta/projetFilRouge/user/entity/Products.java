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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import fr.dta.projetFilRouge.user.enumeration.Pegi;
import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "seq_versionProducts",sequenceName = "seq_versionProducts", initialValue = 1, allocationSize = 1)
@Getter
@Setter
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
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Pegi pegi;
	
	@NotBlank
	private String type;
	
	public Products(String title, Long price, String gamePublisher, Pegi pegi, String type) {
		this.title = title;
		this.price = price;
		this.gamePublisher = gamePublisher;
		this.pegi = pegi;
		this.type = type;
	}

	public Products() {
	}	
}
