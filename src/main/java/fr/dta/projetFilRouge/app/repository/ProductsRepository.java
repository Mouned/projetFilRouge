package fr.dta.projetFilRouge.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.projetFilRouge.user.entity.Products;
import fr.dta.projetFilRouge.user.enumeration.Pegi;


@Transactional
public interface ProductsRepository extends JpaRepository<Products, Long>,PagingAndSortingRepository<Products, Long>{
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// RECHERCHER produit


	List<Products> findByTitle(@Param("title") String title);
	
	List<Products> findByType(@Param("type") String type);
	
	Page<Products> findAll(Pageable pageable);
	
	Products findById(long id);
	
	@Modifying
	@Query("update Products p set p.gamePublisher = :gamePublisher, p.pegi = :pegi, p.price = :price, p.title = :title, p.type = :type, p.url = :url where p.id = :id")
	void updateById(@Param("gamePublisher") String game_publisher, 
			@Param("pegi") Pegi pegi, 
			@Param("price") float price, 
			@Param("title") String title, 
			@Param("type") String type, 
			@Param("url") String url, 
			@Param("id") long id);
}
