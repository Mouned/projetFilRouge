package fr.dta.projetFilRouge.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dta.projetFilRouge.app.repository.ProductsRepository;
import fr.dta.projetFilRouge.user.entity.Products;

@Service
public class ProductsService {

	
	@Autowired
	ProductsRepository productsRepository;
	
	
	public List<Products> getProductsByTitle(String title) {
		List<Products> list = productsRepository.findByTitle(title);
		return list;
	}
	
	public List<Products> getAllProducts() {
		List<Products> list = productsRepository.findAll();
		return list;
	}
	
    public Page<Products> findPaginated(int page, int sizeElement) {
        return productsRepository.findAll(new PageRequest(page,sizeElement));
    }
    
    public void createProduct(Products p) {
    	productsRepository.saveAndFlush(p);
    }
    
    public void deleteProduct(Products p) {
    	productsRepository.delete(p);
    }
}
