package fr.dta.projetFilRouge.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dta.projetFilRouge.app.repository.OrderRepository;
import fr.dta.projetFilRouge.user.entity.Order;
import fr.dta.projetFilRouge.user.entity.Products;
import fr.dta.projetFilRouge.user.entity.User;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductsService productService; 

	public Long createOrder(Order order, Long userId,List<Long> idProducts) {
		
		List<Products> list_Products = new ArrayList<>();
		User user = userService.getUserById(userId);
		
		for(Long productId : idProducts)
			list_Products.add(productService.getById(productId));
		
		order.setUser(user);
		order.setProducts(list_Products);

		orderRepository.saveAndFlush(order);
		if (order != null)
			return order.getId();
		return -1L;
	}

	public void deleteOrder(Long id) {
		orderRepository.delete(id);
	}
	
	public void updateOrder(Order order) {
		orderRepository.updateOrder(order.getId(), order.getTotalPrice(), order.getOrderNumber(),order.getOrderDate());
	}
	
	public List<Order> findOrderByUserId(Long userId) {
		User user = userService.getUserById(userId);
		List<Order> results = orderRepository.findByUser(user);
		
		for(Order o : results)
			Hibernate.initialize(o.getProducts());
		
		return results;
	}
	
	public List<Order> findAllOrder() {
		List<Order> results = orderRepository.findAll();
		for(Order o : results)
			Hibernate.initialize(o.getProducts());
		
		return results;
	}
	
	public List<Order> findOrderByDate(LocalDate date) {
		List<Order> results = orderRepository.findByOrderDate(date);
		for(Order o : results)
			Hibernate.initialize(o.getProducts());
		
		return results;
	}
	
	public List<Products> findProductsByOrderNumber(String orderNumber) {
		List<Order> results = orderRepository.findByOrderNumber(orderNumber);
		for(Order o : results)
			Hibernate.initialize(o.getProducts());
		
		// merge de la liste des produits;
		return results.stream()
					  .flatMap(x -> x.getProducts().stream())
					  .collect(Collectors.toList());
	}
	
}
