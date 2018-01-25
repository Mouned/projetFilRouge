package fr.dta.projetFilRouge.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.dta.projetFilRouge.user.entity.Order;
import fr.dta.projetFilRouge.user.entity.User;

public interface OrderRepository extends JpaRepository<Order, Long>{

	@Modifying
	@Query("update Order o set o.totalPrice = :totalPrice, o.orderNumber = :orderNumber, o.orderDate = :orderDate where o.id = :id")
	public void updateOrder(@Param("id") Long id,
							@Param("totalPrice")Float totalPrice, 
							@Param("orderNumber") String orderNumber,
							@Param("orderDate") LocalDate orderDate);
	
	public List<Order> findByUser(User user);
	
	public List<Order> findByOrderDate(LocalDate date);
	
	public List<Order> findByOrderNumber(String orderNumber);
}
