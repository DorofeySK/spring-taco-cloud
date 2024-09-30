package tacos.interfaces;

import org.springframework.data.repository.CrudRepository;

import tacos.datas.TacoOrder;

public interface IOrderRepository extends CrudRepository<TacoOrder, Long> {

}
