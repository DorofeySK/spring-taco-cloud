package tacos.datas;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Table("ingredientref")
@AllArgsConstructor
public class IngredientRef implements Persistable<IngredientRef.Pk>{
	@Id
	@Embedded(onEmpty=OnEmpty.USE_EMPTY)
	private final Pk pk;
	
	public record Pk(Long taco, String ingredient) {}

	@Override
	public Pk getId() {
		return pk;
	}

	@Override
	public boolean isNew() {
		return true;
	}
}
