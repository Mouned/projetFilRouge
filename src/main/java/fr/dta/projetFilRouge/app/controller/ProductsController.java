package fr.dta.projetFilRouge.app.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.projetFilRouge.app.service.ProductsService;
import fr.dta.projetFilRouge.user.entity.Products;

@RestController
@RequestMapping(value = "/api")
@Transactional
public class ProductsController {

	
	@Autowired
	ProductsService searchService;
	
	@RequestMapping(path = "public/product/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Products> getProductsByTitle(@RequestParam("title") String title) {
		List<Products> list = searchService.getProductsByTitle(title);
		return list;
	}
}
