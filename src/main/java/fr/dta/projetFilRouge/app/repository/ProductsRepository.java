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
	@Transactional
	@Query("update Products p set p.gamePublisher = ?1, p.pegi = ?2, p.price = ?3, p.title = ?4, p.type = ?5, p.url = ?6 where p.id = ?7")
	void updateById(String game_publisher, Pegi pegi, float price, String title, String type, String url, long id);
}
