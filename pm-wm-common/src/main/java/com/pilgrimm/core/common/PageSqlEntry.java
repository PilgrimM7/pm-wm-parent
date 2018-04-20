package com.pilgrimm.core.common;

public interface PageSqlEntry {
	
	String getSql();
	
	String getPageSql(int pageNumber, int pageSize);
	
	String getPageSql(PageParam pageParam);

}
