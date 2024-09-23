package tacos.interfaces;

import tacos.datas.TacoOrder;

public interface IOrderRepository {
	TacoOrder save(TacoOrder order);
}
