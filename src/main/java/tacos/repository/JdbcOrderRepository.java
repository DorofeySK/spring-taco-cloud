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
	private JdbcSequenceRepository seqRepository;
	
	public JdbcOrderRepository(JdbcOperations jdbcOperations, JdbcSequenceRepository seqRepository) {
		this.jdbcOperations = jdbcOperations;
		this.seqRepository = seqRepository;
	}
	
	@Override
	@Transactional
	public TacoOrder save(TacoOrder order) {
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
			"insert into tacoorder ("
			+ "id, deliveryName, deliveryStreet, deliveryCity, "
			+ "deliveryState, deliveryZip, ccNumber, ccExpiration, "
			+ "ccCVV, placedAt) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
			Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
			Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
		);
		order.setId(seqRepository.getNextTacoOrderSequence());
		order.setPlacedAt(new Date());
		PreparedStatementCreator creator = pscf.newPreparedStatementCreator(
			Arrays.asList(order.getId(), order.getDeliveryName(), order.getDeliveryStreet(),
			order.getDeliveryCity(), order.getDeliveryState(), order.getDeliveryZip(),
			order.getCcNumber(), order.getCcExpiration(), order.getCcCVV(),
			order.getPlacedAt())
		);
		
		jdbcOperations.update(creator);
		List<Taco> tacos = order.getTacos();
		int seq = 0;
		for (Taco taco : tacos) {
			saveTaco(order.getId(), seq++, taco);
		}
		return order;
	}
	
	private Long saveTaco(Long orderId, int orderSeq, Taco taco) {
		taco.setId(seqRepository.getNextTacoSequence());
		taco.setCreateAt(new Date());
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
			"insert into taco "
			+ "(id, name, tacoOrderId, tacoOrderKey, createdAt) "
			+ "values (?, ?, ?, ?, ?)",
			Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.TIMESTAMP
		);
		PreparedStatementCreator creaetor = pscf.newPreparedStatementCreator(
			Arrays.asList(taco.getId(), taco.getName(), orderId, orderSeq, taco.getCreateAt())
		);
		jdbcOperations.update(creaetor);
		saveIngredientRefs(taco.getId(), taco.getIngredients());
		return taco.getId();
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
