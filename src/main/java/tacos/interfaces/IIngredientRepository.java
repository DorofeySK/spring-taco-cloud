package tacos.interfaces;

import java.util.Optional;

import tacos.datas.Ingredient;

public interface IIngredientRepository {
	Iterable<Ingredient> findAll();
	Optional<Ingredient> findById(String id);
	Ingredient save(Ingredient ingredient);
}
