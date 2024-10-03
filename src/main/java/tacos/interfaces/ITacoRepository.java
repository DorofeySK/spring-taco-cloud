package tacos.interfaces;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import tacos.datas.Taco;

public interface ITacoRepository extends CrudRepository<Taco, Long>{
	@Query("select nextval('tacoSeq')")
	Long getNextTacoId();
}
