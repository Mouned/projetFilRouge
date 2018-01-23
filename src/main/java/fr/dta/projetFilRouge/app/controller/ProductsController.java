package fr.dta.projetFilRouge.app.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@CrossOrigin
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public void createProduct(
			@RequestParam("game_publisher") String game_publisher, 
			@RequestParam("pegi") String pegi, 
			@RequestParam("price") float price,
			@RequestParam("title") String title,
			@RequestParam("type") String type) 
	{
		
		Products p = new Products();
		p.setGamePublisher(game_publisher);
		p.setPegi(Pegi.valueOf(pegi));
		p.setPrice(price);
		p.setTitle(title);
		p.setType(type);
		
		productsService.createProduct(p);
	}
	
	@RequestMapping(value = "upload/{id}", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") long id ) {
		productsService.store(file);
        return "Message : OK";
    }

}
