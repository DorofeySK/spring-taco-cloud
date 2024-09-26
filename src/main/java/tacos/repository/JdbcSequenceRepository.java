package tacos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.datas.Ingredient;
import tacos.interfaces.ISequenceRepository;

@Repository
public class JdbcSequenceRepository implements ISequenceRepository {
	private JdbcTemplate jdbcTemplate;
	public JdbcSequenceRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Long getNextTacoSequence() {
		return jdbcTemplate.query(
			"select nextval('tacoSeq')",
			this::mapRowToLong
		).get(0);
	}

	@Override
	public Long getNextTacoOrderSequence() {
		return jdbcTemplate.query(
			"select nextval('tacoOrderSeq')",
			this::mapRowToLong
		).get(0);
	}
	
	private Long mapRowToLong(ResultSet row, int rowNum) throws SQLException {
		return row.getLong("nextval");
	}
}
