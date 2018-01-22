package fr.dta.projetFilRouge.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dta.projetFilRouge.app.repository.ProductsRepository;
import fr.dta.projetFilRouge.user.entity.Products;

@Service
public class ProductsService {

	
	@Autowired
	ProductsRepository searchRepository;
	
	public List<Products> getProductsByTitle(String title) {
		List<Products> list = searchRepository.findByTitle(title);
		return list;
	}
	
	public List<Products> getAllProducts() {
		List<Products> list = searchRepository.findAll();
		return list;
	}
}
