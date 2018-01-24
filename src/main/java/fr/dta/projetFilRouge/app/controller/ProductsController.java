package fr.dta.projetFilRouge.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.dta.projetFilRouge.app.service.ProductsService;
import fr.dta.projetFilRouge.user.entity.Products;
import fr.dta.projetFilRouge.user.enumeration.Pegi;

@RestController
@RequestMapping(value = "/api/products")
@Transactional
public class ProductsController {

	
	@Autowired
	ProductsService productsService;

//	private final int sizeElement = 12;
	
	@CrossOrigin
	@RequestMapping(value = "search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Products> getProductsByTitle(@RequestParam("title") String title) {
		List<Products> list = productsService.getProductsByTitle(title);
		return list;
	}

	@CrossOrigin
	@RequestMapping(value = "advanced-search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Products> getProductsByCriteria(
			@RequestParam(value="title", required=false) String title, 
			@RequestParam(value="gamePublisher", required=false) String gamePublisher, 
			@RequestParam(value="pegi", required=false) String pegi,
			@RequestParam(value="priceMin", required=false, defaultValue="0") Float priceMin,
			@RequestParam(value="priceMax", required=false, defaultValue="1000") Float priceMax,
			@RequestParam(value="type", required=false) String type) {
		
		Pegi searchPegi = null;
		
		if(pegi != null) 
			searchPegi = Pegi.valueByCode(pegi);
		
		List<Products> list = productsService.findByCriteria(title, gamePublisher, searchPegi, priceMin, priceMax, type);
		return list;
	}
	
	@CrossOrigin
	@RequestMapping(value = "all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Products> getAllProducts() {
		
		List<Products> products = productsService.getAllProducts();

		return products;
	}
	
//	@CrossOrigin
//	@RequestMapping(path = "public/products", params = {"page"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public Page<Products> getAllProductsByPage(@RequestParam("page") int page) {
//		
//		Page<Products> resultPage = productsService.findPaginated(page,sizeElement);
//
//		return resultPage;
//	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// PEGI
	@RequestMapping(value = "pegi", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pegi> getPegis() {
		List<Pegi> pegis = new ArrayList<>();
		Collections.addAll(pegis, Pegi.values());
		return pegis;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// CREATE PRODUCT
	
	@CrossOrigin
	@RequestMapping(value = "create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Products createProduct(@RequestBody Products p)
	{
		System.out.println(p);
			
		return productsService.createProduct(p);
			
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// UPLOAD PRODUCT	
	
	@RequestMapping(value = "upload/{id}", method = RequestMethod.POST/*, consumes = "multipart/form-data"*/)
	public void upload(@PathVariable long id, @RequestParam MultipartFile file)
	{
		System.out.println("test");
		if(file.getContentType().equals("image/jpeg") 
				|| file.getContentType().equals("image/png") 
				|| file.getContentType().equals("image/tiff") 
				|| file.getContentType().equals("image/bmp") 
				|| file.getContentType().equals("image/gif")) 
		{
			System.out.println("Fichier accepté.");
			
			
			System.out.println(file.getContentType().equals("image/jpeg"));
			productsService.store(id, file);
		}else {
			System.out.println("Fichier refusé.");
		}
	}	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// DELETE PRODUCT

	
	@CrossOrigin
	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public void deleteProduct(@PathVariable Long id) {
		Products p = productsService.getById(id);
		
		if(p != null) {
			productsService.deleteProduct(p);
		}
		
    }
}
