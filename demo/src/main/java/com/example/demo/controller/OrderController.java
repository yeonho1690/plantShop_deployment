package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.OrderFormDto;
import com.example.demo.model.Address;
import com.example.demo.model.Faq;
import com.example.demo.model.Notice;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Controller
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrderRepository orderRepository;

//	@GetMapping("/orders")
//	public ResponseEntity<Order> getOrder(){
//		List<Order> orders = orderRepository.findAll();
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

//	@PostMapping("/orders")
//	public ResponseEntity<Order> pushOrder(@RequestBody OrderFormDto dto, String username){
//		Order neworder = new Order();
//		neworder.setUsername(username);
//		neworder.setOName(dto.getOName());
//		neworder.setOAddress(dto.getOAddress());
//		neworder.setOPhone(dto.getOPhone());
//		neworder.setOPost(dto.getOPost());
//		neworder.setOPayment(dto.getOPayment());
//		neworder.setODate(dto.getODate());
//		
//		orderRepository.save(neworder);
//		
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

	@GetMapping("/orders/list/{username}")
	public ResponseEntity<List<Order>> orderList(@RequestParam(required = false) String oname,
			@PathVariable("username") String username) {
		try {
			List<Order> order = new ArrayList<Order>();

			orderRepository.findAllByUsername(username).forEach(order::add);
			if (order.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/addorder")
	public ResponseEntity<Order> createFaq(@RequestBody Order order) {

		try { 

			Order _order = this.orderRepository.save(new Order(order.getPid(),order.getPname(), order.getPprice(),order.getOcount(),order.getOtotal(),order.getPimg1(), LocalDate.now(), order.getUsername()));
			return new ResponseEntity<>(_order, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/orders/list")
	public ResponseEntity<List<Order>> list(@RequestParam(required = false) String pname) {
		try {
			List<Order> order = new ArrayList<Order>();
			if (pname == null)
				orderRepository.findAll().forEach(order::add);
			else
				orderRepository.findByPnameContaining(pname).forEach(order::add);
			if (order.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/order/modify/{oid}")
	public ResponseEntity<Order> orderDetail(@PathVariable("oid") Long oid){
		Optional<Order> order = orderRepository.findById(oid);
		if(order.isPresent()) {
			order.get().setOstatus(order.get().getOstatus());
			order.get().setOid(order.get().getOid());
			order.get().setPid(order.get().getPid());
			order.get().setPimg1(order.get().getPimg1());
			order.get().setPprice(order.get().getPprice());
			order.get().setPname(order.get().getPname());
			return new ResponseEntity<>(order.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/order/modify/{oid}")
	public ResponseEntity<Order> noticeModify(@PathVariable("oid") Long oid, @RequestBody Order order) {
		
			Optional<Order> orderData = orderRepository.findById(oid);
			if(orderData.isPresent()) {
				Order newOrder = orderData.get();
				newOrder.setOstatus(order.getOstatus());
				newOrder.setPid(order.getPid());
				newOrder.setPname(order.getPname());
				newOrder.setPprice(order.getPprice());
				newOrder.setOtotal(order.getOtotal());
				newOrder.setPimg1(order.getPimg1());
				return new ResponseEntity<>(orderRepository.save(newOrder), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		
	}
	
}