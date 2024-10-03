package tacos.datas;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table("taco")
public class Taco implements Persistable<Long>{
	@Id
	private Long id;
	@Column("createdat")
	private Date createAt = new Date();
	@Column("tacoorderid")
	private Long tacoOrderId;
	
	@NotNull
	@Size(min=5, message="Name must not be less than 5 char")
	@Column("name")
	private String name;
	
	@MappedCollection(idColumn = "tacoid", keyColumn = "ingredientid")
	private List<IngredientRef> ingredientsRefs;
	
	@NotNull
	@Size(min=1, message="You must choose more than 1 ingredient")
	private List<Ingredient> ingredients;

	@Override
	public boolean isNew() {
		return true;
	}
}
