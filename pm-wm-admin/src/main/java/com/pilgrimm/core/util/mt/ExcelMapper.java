package com.pilgrimm.core.util.mt;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public abstract class ExcelMapper {

	/**
	 * 
	 * @param Comments 批注
	 * @param titles 标题
	 * @param sheet Excel工作簿
	 * @return
	 */
	public abstract Object extractData(String [] Comments,String [] titles,HSSFSheet sheet);
}
