package com.pilgrimm.core.common;

import java.util.Map;

public interface PageResultGenerator<T> {
	
	Map<String, Object> pageMap(PageSqlEntry pageSqlEntry, Map<String, Object> paramMap);
	
	PageResult<T> pageResult(PageSqlEntry pageSqlEntry, Map<String, Object> paramMap);

}
