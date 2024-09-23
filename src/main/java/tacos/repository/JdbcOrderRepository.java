package tacos.repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tacos.datas.Ingredient;
import tacos.datas.Taco;
import tacos.datas.TacoOrder;
import tacos.interfaces.IOrderRepository;

@Repository
public class JdbcOrderRepository implements IOrderRepository {
	private JdbcOperations jdbcOperations;
	public JdbcOrderRepository(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}
	
	@Override
	@Transactional
	public TacoOrder save(TacoOrder order) {
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
			"insert into tacoorder ("
			+ "deliveryName, deliveryStreet, deliveryCity, "
			+ "deliveryState, deliveryZip, ccNumber, ccExpiration, "
			+ "ccCVV, placedAt) values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
			Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
			Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
		);
		pscf.setReturnGeneratedKeys(true);
		order.setPlacedAt(new Date());
		PreparedStatementCreator creator = pscf.newPreparedStatementCreator(
			Arrays.asList(order.getDeliveryName(), order.getDeliveryStreet(),
			order.getDeliveryCity(), order.getDeliveryState(), order.getDeliveryZip(),
			order.getCcNumber(), order.getCcExpiration(), order.getCcCVV(),
			order.getPlacedAt())
		);
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(creator, keyHolder);
		Long orderId = keyHolder.getKey().longValue();
		order.setId(orderId);
		List<Taco> tacos = order.getTacos();
		int seq = 0;
		for (Taco taco : tacos) {
			saveTaco(orderId, seq++, taco);
		}
		return order;
	}
	
	private Long saveTaco(Long orderId, int orderSeq, Taco taco) {
		taco.setCreateAt(new Date());
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
			"insert into taco "
			+ "(name, tacoOrderId, tacoOrderKey, createdAt) "
			+ "values (?, ?, ?, ?)",
			Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.TIMESTAMP
		);
		pscf.setReturnGeneratedKeys(true);
		PreparedStatementCreator creaetor = pscf.newPreparedStatementCreator(
			Arrays.asList(taco.getName(), orderId, orderSeq, taco.getCreateAt())
		);
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(creaetor, keyHolder);
		Long tacoId = keyHolder.getKey().longValue();
		taco.setId(tacoId);
		saveIngredientRefs(tacoId, taco.getIngredients());
		return tacoId;
	}
	
	private void saveIngredientRefs(Long tacoId, List<Ingredient> ingredients) {
		long seq = 1;
		for (Ingredient ingredient : ingredients) {
			jdbcOperations.update(
				"insert into ingredientref (ingredientId, tacoId, tacoKey) "
				+ "values (?, ?, ?)",
				ingredient.getId(), tacoId, seq++
			);
		}
	}
}
