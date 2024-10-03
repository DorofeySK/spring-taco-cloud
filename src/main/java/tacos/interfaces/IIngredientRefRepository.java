package tacos.interfaces;

import org.springframework.data.repository.CrudRepository;

import tacos.datas.IngredientRef;

public interface IIngredientRefRepository extends CrudRepository<IngredientRef, IngredientRef.Pk>{

}
