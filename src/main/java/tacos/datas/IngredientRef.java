package tacos.datas;

import lombok.Data;

@Data
public class IngredientRef {
	private final Long tacoId;
	private final String ingredientId;
}
