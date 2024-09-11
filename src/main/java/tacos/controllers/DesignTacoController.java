package tacos.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.datas.Ingredient;
import tacos.datas.Ingredient.Type;
import tacos.datas.IngredientFactory;
import tacos.datas.Taco;
import tacos.datas.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = IngredientFactory.getDefault();
		final Type types[] = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}
	
	private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream()
			.filter(x->x.getType().equals(type))
			.collect(Collectors.toList());
	}
	
	@ModelAttribute(name = "tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm() {
		return "design";
	}
	
	@PostMapping
	public String processTaco(Taco taco, @ModelAttribute TacoOrder tacoOrder) {
		tacoOrder.addTaco(taco);
		log.info("Processing taco: {}", taco);
		return "redirect:/orders/current";
	}
}
