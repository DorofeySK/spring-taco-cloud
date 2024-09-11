package tacos.datas;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConventer implements Converter<String, Ingredient> {

	private Map<String, Ingredient> ingredientMap = new HashMap<>();
	
	public IngredientByIdConventer() {
		for (Ingredient ingredient : IngredientFactory.getDefault()) {
			ingredientMap.put(ingredient.getId(), ingredient);
		}
	}
	
	@Override
	public Ingredient convert(String source) {
		return ingredientMap.get(source);
	}

}
