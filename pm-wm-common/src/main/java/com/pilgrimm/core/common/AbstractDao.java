package com.pilgrimm.core.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;

/**
 * Abstract jdbc dao template In order to assist JDBC processing.
 * @param <T>
 */
public abstract class AbstractDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	@Autowired
	public void setNamedParameterJdbcTemplate(JdbcTemplate jdbcTemplate) {
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate);
	}

	protected <T> T queryForObject(String sql, Map<String, Object> paramMap,
			Class<T> requiredType) {
		return namedParameterJdbcTemplate.queryForObject(sql, paramMap,
				requiredType);
	}
	
	protected <T> T queryForObject(String sql, SqlParameterSource paramSource,
			Class<T> requiredType) {
		return namedParameterJdbcTemplate.queryForObject(sql, paramSource,
				requiredType);
	}

	protected <T> T queryForObject(String sql, Map<String, Object> paramMap,
			RowMapper<T> rowMapper) {
		return namedParameterJdbcTemplate.queryForObject(sql, paramMap,
				rowMapper);
	}

	protected <T> T queryForObject(String sql, SqlParameterSource paramSource,
			RowMapper<T> rowMapper) {
		return namedParameterJdbcTemplate.queryForObject(sql, paramSource,
				rowMapper);
	}
	
	protected Map<String, Object> queryForMap(String sql,
			Map<String, Object> paramMap) {
		try {
			return namedParameterJdbcTemplate.queryForMap(sql, paramMap);
		} catch (EmptyResultDataAccessException e) {
			return new HashMap<String,Object>();
		}
	}

	protected Map<String, Object> queryForMap(String sql,
			SqlParameterSource paramSource) {
		return namedParameterJdbcTemplate.queryForMap(sql, paramSource);
	}
	
	protected long queryForTotal(String sql, Map<String, Object> paramMap) {
		StringBuffer bufferSql = new StringBuffer();
		bufferSql.append("select count(*) from (");
		bufferSql.append(sql);
		bufferSql.append(") c");
		return namedParameterJdbcTemplate.queryForObject(bufferSql.toString(),
				paramMap, Integer.class);
	}

	protected long queryForTotal(String sql, SqlParameterSource paramSource) {
		StringBuffer bufferSql = new StringBuffer();
		bufferSql.append("select count(*) from (");
		bufferSql.append(sql);
		bufferSql.append(") c");
		return namedParameterJdbcTemplate.queryForObject(bufferSql.toString(),
				paramSource, Integer.class);
	}
	
	protected Map<String, Object> queryForPageMap(
			PageSqlEntry pageSqlEntry, Map<String, Object> paramMap,
			PageResultGenerator<?> pageResultGenerator) {
		return pageResultGenerator.pageMap(pageSqlEntry, paramMap);
	}

	protected List<Map<String, Object>> queryForList(String sql,
			Map<String, Object> paramMap) {
		return namedParameterJdbcTemplate.queryForList(sql, paramMap);
	}

	protected List<Map<String, Object>> queryForList(String sql,
			SqlParameterSource paramSource) {
		return namedParameterJdbcTemplate.queryForList(sql, paramSource);
	}

	protected int update(String sql, Map<String, Object> paramMap) {
		return namedParameterJdbcTemplate.update(sql, paramMap);
	}

	protected int update(String sql, SqlParameterSource paramSource) {
		return namedParameterJdbcTemplate.update(sql, paramSource);
	}
	
	protected int update(String sql, Map<String, Object> paramMap,
			KeyHolder generatedKeyHolder) {
		return namedParameterJdbcTemplate.update(sql,
				new MapSqlParameterSource(paramMap), generatedKeyHolder);
	}

	protected int update(String sql, SqlParameterSource paramSource,
			KeyHolder generatedKeyHolder) {
		return namedParameterJdbcTemplate.update(sql, paramSource,
				generatedKeyHolder);
	}
	
	protected int update(String sql, Map<String, Object> paramMap,
			KeyHolder generatedKeyHolder, String[] keyColumnNames) {
		return namedParameterJdbcTemplate.update(sql,
				new MapSqlParameterSource(paramMap), generatedKeyHolder,
				keyColumnNames);
	}

	protected int update(String sql, SqlParameterSource paramSource,
			KeyHolder generatedKeyHolder, String[] keyColumnNames) {
		return namedParameterJdbcTemplate.update(sql, paramSource,
				generatedKeyHolder, keyColumnNames);
	}

	protected int[] batchUpdate(String sql, Map<String, Object>[] batchArgs) {
		return namedParameterJdbcTemplate.batchUpdate(sql, batchArgs);
	}

	protected int[] batchUpdate(String sql, SqlParameterSource[] batchArgs) {
		return namedParameterJdbcTemplate.batchUpdate(sql, batchArgs);
	}

}
