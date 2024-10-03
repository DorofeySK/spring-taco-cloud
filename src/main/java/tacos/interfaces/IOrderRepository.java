package tacos.interfaces;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import tacos.datas.TacoOrder;

public interface IOrderRepository extends CrudRepository<TacoOrder, Long> {
	@Query("select nextval('tacoorderSeq')")
	Long getNextTacoOrderId();
}
