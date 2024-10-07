package tacos.datas;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "ingredientref")
public class IngredientRef {
	@Column(name = "taco_id")
	private final Long tacoId;
	@Column(name = "ingredient_id")
	private final String ingredientId;
}
