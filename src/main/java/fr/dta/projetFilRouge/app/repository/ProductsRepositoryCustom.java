package fr.dta.projetFilRouge.app.repository;

import java.util.List;

import fr.dta.projetFilRouge.user.entity.Products;
import fr.dta.projetFilRouge.user.enumeration.Pegi;

public interface ProductsRepositoryCustom {
	
	public abstract List<Products> findByCriteria(String title, String gamePub, Pegi pegi, Float priceMin, Float priceMax, String type, boolean isAdmin);
	public abstract List<Products> quickFindByCriteria(String gameInfo, boolean isAdmin);
}
