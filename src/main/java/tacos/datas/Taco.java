package tacos.datas;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "taco")
public class Taco {
	@Id
	@GeneratedValue(generator = "tacoseq")
	@SequenceGenerator(name = "tacoseq", allocationSize = 1)
	private Long id;
	
	@Column(name = "createat")
	private Date createAt = new Date();
	
	@Column(name = "name")
	@NotNull
	@Size(min=5, message="Name must not be less than 5 char")
	private String name;
	
	@NotNull
	@Size(min=1, message="You must choose more than 1 ingredient")
	@ManyToMany()
	private List<Ingredient> ingredients;

	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}
}
