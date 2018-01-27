package fr.dta.projetFilRouge.app.controller;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.projetFilRouge.app.service.OrderService;
import fr.dta.projetFilRouge.user.entity.Order;

@RestController
@RequestMapping(value = "/api/orders")
@Transactional
public class OrderController {

	@Autowired
	OrderService orderService;


/////////////////////////////////////////////////////////////////////CREATE ORDER///////////////////////////////////////////////////
	
	@RequestMapping(value = "create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Long create(@RequestBody Order order,
					   @RequestParam("user") long userId,
					   @RequestParam("products[]") List<Long> idProducts) {
		return orderService.createOrder(order, userId, idProducts);
	}

/////////////////////////////////////////////////////////////////////DELETE ORDER///////////////////////////////////////////////////
	
	
	@RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void delete(@RequestParam long id) {
		orderService.deleteOrder(id);
	}

/////////////////////////////////////////////////////////////////////UPDATE ORDER///////////////////////////////////////////////////	
	
	@RequestMapping(value = "update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void update(@RequestBody Order order) {
		orderService.updateOrder(order);
	}


/////////////////////////////////////////////////////////////////////SEARCH ORDER///////////////////////////////////////////////////
	
	@RequestMapping(value = "search/order/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Order> findOrderByUserId(@RequestParam("id") long userId) {
		return orderService.findOrderByUserId(userId);
	}
	
	@RequestMapping(value = "search/order/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Order> findAllOrder() {
		return orderService.findAllOrder();
	}
	
	@RequestMapping(value = "search/order/date", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Order> findAllOrder(@RequestParam LocalDate date) {
		return orderService.findOrderByDate(date);
	}
	
	@RequestMapping(value = "search/order/ordernumber", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Order> findAllOrder(@RequestParam("ordernumber") String orderNumber) {
		return orderService.findOrderByOrderNumber(orderNumber);
	}
	
}
