package com.pilgrimm.core.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pilgrimm.core.common.AbstractDao;

public class DefaultPageResultGenerator<T> implements PageResultGenerator<T> {
	
	private AbstractDao dao;
	
	public DefaultPageResultGenerator(AbstractDao dao) {
		this.dao = dao;
	}

	@Override
	public Map<String, Object> pageMap(PageSqlEntry pageSqlEntry, Map<String, Object> paramMap) {
		if (paramMap.get("pageNumber") == null) {
			throw new NullPointerException("pageNumber");
		}
		if (paramMap.get("pageSize") == null) {
			throw new NullPointerException("pageSize");
		}
		
		int pageNumber = Integer.parseInt(paramMap.get("pageNumber").toString());
		int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
		
		long total = dao.queryForTotal(pageSqlEntry.getSql(), paramMap);
		List<Map<String, Object>> rows = dao.queryForList(
				pageSqlEntry.getPageSql(pageNumber, pageSize), paramMap);
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("total", total);
		pageMap.put("rows", rows);
		return pageMap;
	}

	@Override
	public PageResult<T> pageResult(PageSqlEntry pageSqlEntry, Map<String, Object> paramMap) {
		if (paramMap.get("pageNumber") == null) {
			throw new NullPointerException("pageNumber");
		}
		if (paramMap.get("pageSize") == null) {
			throw new NullPointerException("pageSize");
		}
		
		PageParam pageParam = new PageParam();
		pageParam.setPageNumber(Integer.parseInt(paramMap.get("pageNumber").toString()));
		pageParam.setPageSize(Integer.parseInt(paramMap.get("pageSize").toString()));
		
		long total = dao.queryForTotal(pageSqlEntry.getSql(), paramMap);
		List<Map<String, Object>> rows = dao.queryForList(
				pageSqlEntry.getPageSql(pageParam), paramMap);
		
		PageResult<T> pageResult = new PageResult<T>();
		pageResult.setTotal(total);
//		pageResult.setRows(rows);
		return pageResult;
	}

}
