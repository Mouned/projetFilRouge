package fr.dta.projetFilRouge.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.projetFilRouge.user.entity.Products;


@Transactional
public interface ProductsRepository extends JpaRepository<Products, Long>,PagingAndSortingRepository<Products, Long>{
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// RECHERCHER produit


	List<Products> findByTitle(@Param("title") String title);
	
	List<Products> findByType(@Param("type") String type);
	
	Page<Products> findAll(Pageable pageable);
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// AJOUTER produit
	
}
