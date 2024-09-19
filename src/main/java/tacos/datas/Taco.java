package tacos.datas;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Taco {
	private Long id;
	private Date createAt = new Date();
	
	@NotNull
	@Size(min=5, message="Name must not be less than 5 char")
	private String name;
	
	@NotNull
	@Size(min=1, message="You must choose more than 1 ingredient")
	private List<Ingredient> ingredients;
}
