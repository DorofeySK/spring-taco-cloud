package tacos.interfaces;

import org.springframework.data.repository.CrudRepository;

import tacos.datas.Ingredient;

public interface IIngredientRepository extends CrudRepository<Ingredient, String>{
	
}
