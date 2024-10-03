package tacos.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import tacos.datas.Ingredient;
import tacos.datas.IngredientRef;
import tacos.datas.Taco;
import tacos.datas.TacoOrder;
import tacos.interfaces.IIngredientRefRepository;
import tacos.interfaces.IOrderRepository;
import tacos.interfaces.ITacoRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
	private IOrderRepository orderRepo;
	private ITacoRepository tacoRepo;
	private IIngredientRefRepository refRepo;
	
	public OrderController(IOrderRepository orderRepo, ITacoRepository tacoRepo, IIngredientRefRepository refRepo) {
		this.orderRepo = orderRepo;
		this.tacoRepo = tacoRepo;
		this.refRepo = refRepo;
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
		log.info("Processing order: {}", order);
		order.setPlacedAt(new Date());
		orderRepo.save(order);
//		for (Taco taco : order.getTacos()) {
//			taco.setCreateAt(new Date());
//			tacoRepo.save(taco);
//			for (Ingredient ingredient : taco.getIngredients()) {
//				refRepo.save(new IngredientRef(new IngredientRef.Pk(taco.getId(), ingredient.getId())));
//			}
//		}
		sessionStatus.setComplete();
		return "redirect:/";
	}
	
}
