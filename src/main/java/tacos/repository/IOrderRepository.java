package tacos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tacos.datas.TacoOrder;

public interface IOrderRepository extends CrudRepository<TacoOrder, Long>{
	List<TacoOrder> findByDeliveryZip(String deliveryZip);
	List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
}
