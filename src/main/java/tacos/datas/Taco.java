package tacos.datas;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import org.springframework.data.cassandra.core.mapping.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table("tacos")
public class Taco {
	@PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
	private UUID id = Uuids.timeBased();
	
	@PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
	private Date createAt = new Date();
		
	@NotNull
	@Size(min=5, message="Name must not be less than 5 char")
	private String name;
	
	@NotNull
	@Size(min=1, message="You must choose more than 1 ingredient")
	@Column("ingredients")
	private List<IngredientUDT> ingredients;

	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(TacoUDRUtils.toIngredientUDT(ingredient));
	}
}
