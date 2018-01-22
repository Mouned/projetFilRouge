package fr.dta.projetFilRouge.app.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import fr.dta.projetFilRouge.app.repository.*;
import fr.dta.projetFilRouge.user.entity.Products;
import fr.dta.projetFilRouge.user.enumeration.Pegi;

@Service
public class ProductsService extends AbstractRepository implements ProductsRepositoryCustom {
	
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
    
    public List<Products> findByCriteria() {
    	return null;
    }

	@Override
	public List<Products> findByCriteria(String title, String gamePub, Pegi pegi, Float priceMin, Float priceMax, String type) {
		Criteria crit = getSession().createCriteria(Products.class);
		if (!StringUtils.isEmpty(title)) {
			crit.add(Restrictions.like("title", "%"+title+"%"));
		}
		if (!StringUtils.isEmpty(gamePub)) {
			crit.add(Restrictions.like("gamePublisher", "%"+gamePub+"%"));
		}
		if (pegi != null) {
			crit.add(Restrictions.eq("pegi", pegi));
		}
		if (priceMin != null && priceMax != null) {
			crit.add(Restrictions.between("price", priceMin, priceMax));
		}
		if (!StringUtils.isEmpty(type)) {
			crit.add(Restrictions.like("type", "%"+type+"%"));
		}
		List<Products> searchResult = crit.list(); 
		return searchResult;
	}
}
