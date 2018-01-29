package fr.dta.projetFilRouge.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import fr.dta.projetFilRouge.app.repository.AbstractRepository;
import fr.dta.projetFilRouge.app.repository.ProductsRepository;
import fr.dta.projetFilRouge.app.repository.ProductsRepositoryCustom;
import fr.dta.projetFilRouge.user.entity.Products;
import fr.dta.projetFilRouge.user.enumeration.Pegi;

@Service
public class ProductsService extends AbstractRepository implements ProductsRepositoryCustom {
	
	@Autowired
	ProductsRepository productsRepository;
	
	public List<Products> getProductsByTitle(String title, boolean isAdmin) {
		List<Products> list = productsRepository.findByTitle(title);
		return isAdmin ? list.stream().sorted(Comparator.comparing(Products::getId)).collect(Collectors.toList()) : this.productsAvalaible(list);
	}
	
	public List<Products> getAllProducts(boolean isAdmin) {
		List<Products> list = productsRepository.findAll();
		return isAdmin ? list.stream().sorted(Comparator.comparing(Products::getId)).collect(Collectors.toList()) : this.productsAvalaible(list);
	}
	
	public List<Products> getProductsByListOfId(List<Long> listId, boolean isAdmin){
		List<Products> list = new ArrayList<>();
		
		for(Long id : listId){
			list.add(productsRepository.findById(id));
		}
		return isAdmin ? list.stream().sorted(Comparator.comparing(Products::getId)).collect(Collectors.toList()) : this.productsAvalaible(list);
	}
	
    public Page<Products> findPaginated(int page, int sizeElement) {
        return productsRepository.findAll(new PageRequest(page,sizeElement));
    }
    
    public Products createProduct(Products p) {
    	return productsRepository.saveAndFlush(p);
    }
    
    public void deleteProduct(Products p) {
    	productsRepository.delete(p);
    	this.deleteImage(p.getId());
    	
    }
    
    public Products getById(long id, boolean isAdmin) {
    	Products p = productsRepository.findById(id);
    	return (isAdmin ? p : (p.getAvailable() ? p : null));
    }
    
    public void updateProducts(Products p) {
    	productsRepository.saveAndFlush(p);
    }
    
    public void updateById(MultipartFile file, long id) {
      	String url = file.getOriginalFilename()+""+new Timestamp(System.currentTimeMillis());
      	
     	Products p = this.getById(id, true);
     	p.setUrl(url);
     	productsRepository.saveAndFlush(p);
    }   
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// CREATE FILE
    public boolean store(long id, MultipartFile file) {
        File convFile = new File( ".\\src\\main\\webapp\\images\\" + id + "\\"  + file.getOriginalFilename());
        System.out.println("Création du fichier : " + convFile.getAbsolutePath() + " : En cours");
        try {
        	convFile.getParentFile().mkdirs();//creer dossier manquant
			convFile.createNewFile();
	        FileOutputStream fos = new FileOutputStream(convFile); 
	        fos.write(file.getBytes());
	        fos.close();
	        System.out.println("Création du fichier : " + convFile.getAbsolutePath()  + " : OK");
	        return true;
		} catch (IOException e) {
			System.err.println("Erreur de suavegarde de fichier : " + e.getMessage());
			return false;
		} 
    }
    
    public boolean deleteImage(long id) {
    	
    	File folderToDelete = new File(".\\src\\main\\webapp\\images\\"+id+"\\");
    	
    	String[] listFile = folderToDelete.list();
    	File currentFile;
    	
    	if(listFile==null)return false;
    	
    	for(String filename : listFile) {
    		currentFile = new File(folderToDelete.getPath(),filename);
    	    currentFile.delete();
    	}
    	if(folderToDelete.list().length == 0)
    		return folderToDelete.delete();
    	return false;
    }
    
    
	@Override
	public List<Products> findByCriteria(String title, String gamePub, Pegi pegi, Float priceMin, Float priceMax, String type, boolean isAdmin) {
		Criteria crit = getSession().createCriteria(Products.class);
		if (!StringUtils.isEmpty(title)) {
			crit.add(Restrictions.like("title", "%"+title+"%").ignoreCase());
		}
		if (!StringUtils.isEmpty(gamePub)) {
			crit.add(Restrictions.like("gamePublisher", "%"+gamePub+"%").ignoreCase());
		}
		if (pegi != null) {
			crit.add(Restrictions.eq("pegi", pegi));
		}
		if (priceMin != null && priceMax != null) {
			crit.add(Restrictions.between("price", priceMin, priceMax));
		}
		if (!StringUtils.isEmpty(type)) {
			crit.add(Restrictions.like("type", "%"+type+"%").ignoreCase());
		}
		if (!isAdmin) {
			crit.add(Restrictions.eq("available", true));
		}
		List<Products> searchResult = crit.list(); 
		return searchResult.stream().sorted(Comparator.comparing(Products::getId)).collect(Collectors.toList());
	}

	@Override
	public List<Products> quickFindByCriteria(String gameInfo, boolean isAdmin) {
		Criteria crity = getSession().createCriteria(Products.class);
		if (!StringUtils.isEmpty(gameInfo)) {
			crity.add(Restrictions.disjunction()
					.add(Restrictions.like("title", "%"+gameInfo+"%").ignoreCase())
					.add(Restrictions.like("gamePublisher", "%"+gameInfo+"%").ignoreCase())
					.add(Restrictions.like("type", "%"+gameInfo+"%").ignoreCase()));
		}
		if (!isAdmin) {
			crity.add(Restrictions.eq("available", "true"));
		}
//		if (!StringUtils.isEmpty(gamePub)) {
//			crity.add(Restrictions.like("gamePublisher", "%"+gamePub+"%").ignoreCase());
//		}
//		if (!StringUtils.isEmpty(type)) {
//			crity.add(Restrictions.like("type", "%"+type+"%").ignoreCase());
//		}
		List<Products> searchResult = crity.list();
		return searchResult.stream().sorted(Comparator.comparing(Products::getId)).collect(Collectors.toList());
	}
	
	public void disableProducts(List<Long> productsId) {
		for (Long id : productsId) {
			disableOrEnableProduct(id);
		}
	}
	
	public void disableOrEnableProduct(Long id) {
		Products p = productsRepository.findById(id);
		p.setAvailable(p.getAvailable() ? false : true);
		productsRepository.saveAndFlush(p);
	}
	
	public List<Products> productsAvalaible(List<Products> list){
		if(list != null) {
			return list.stream()
					   .filter(product -> product.getAvailable())
					   .sorted(Comparator.comparing(Products::getId))
					   .collect(Collectors.toList());
		}
		return new ArrayList<Products>();
	}
}
