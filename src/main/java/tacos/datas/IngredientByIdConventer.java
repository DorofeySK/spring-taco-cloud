package tacos.datas;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.interfaces.IIngredientRepository;

@Component
public class IngredientByIdConventer implements Converter<String, Ingredient> {

	private IIngredientRepository ingredientRepo;
	
	@Autowired
	public IngredientByIdConventer(IIngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	@Override
	public Ingredient convert(String source) {
		return ingredientRepo.findById(source).orElse(null);
	}

}
