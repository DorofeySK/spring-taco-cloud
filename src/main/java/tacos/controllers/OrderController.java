package tacos.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import tacos.datas.TacoOrder;
import tacos.interfaces.IOrderRepository;

import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
	private IOrderRepository orderRepo;
	
	public OrderController(IOrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
		if (errors.hasErrors() == true) {
			return "orderForm";
		}
		
		order.setPlacedAt(new Date());
		orderRepo.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}
	
}
