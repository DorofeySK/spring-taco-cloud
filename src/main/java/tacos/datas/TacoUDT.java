package tacos.datas;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import java.util.List;
import lombok.Data;

@Data
@UserDefinedType("taco")
public class TacoUDT {
	private final String name;
	private final List<IngredientUDT> ingredients;
}
