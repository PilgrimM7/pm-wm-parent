package com.pilgrimm.core.common;

public class MysqlPageSqlEntry implements PageSqlEntry {
	
	private String sql;
	
	public MysqlPageSqlEntry(String sql) {
		this.sql = sql;
	}

	@Override
	public String getSql() {
		return sql;
	}
	
	@Override
	public String getPageSql(int pageNumber, int pageSize) {
		StringBuffer sb = new StringBuffer(sql);
		sb.append(" LIMIT " + (pageNumber - 1) * pageSize + ", " 
				+ pageSize);
		return sb.toString();
	}

	@Override
	public String getPageSql(PageParam pageParam) {
		StringBuffer sb = new StringBuffer(sql);
		sb.append(" LIMIT " + (pageParam.getPageNumber() - 1) * pageParam.getPageSize() + ", " 
				+ pageParam.getPageSize());
		return sb.toString();
	}

}
