package com.pilgrimm.core.util.mt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.jdbc.core.JdbcTemplate;



public class ExcelUtil {
	private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;
    /**
     * 读取Excel表格表头的内容
     * @param InputStream
     * @return String 表头内容的数组
     */
    private String[] readExcelTitle(HSSFSheet sheet) {
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            title[i] = getCellFormatValue(row.getCell(i));
        }
        return title;
    }
    
	/**
	 * 获取excel 批注
	 * @param is
	 * @return
	 */
    private String[] readExcelComment(HSSFSheet sheet) {
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
        	if(row.getCell(i).getCellComment()!=null)
        		title[i] = row.getCell(i).getCellComment().getString().getString();
        }
        return title;
    }
	public Object readExcel(InputStream is,ExcelMapper mapper) {
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        String [] Comments=readExcelComment(sheet);
        String [] titles=readExcelTitle(sheet);
        return mapper.extractData(Comments, titles, sheet);
    }
	/**
	 * 读取Excel  返回 List<Map<String,Object>>
	 * 推荐使用
	 * @param is
	 * @return
	 */
	public List<Map<String,Object>> readExcelToMap(InputStream is) {
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		String [] Comments=readExcelComment(sheet);
        int rowCount=sheet.getLastRowNum();//总记录数
       	for(int i=1;i<=rowCount;i++){
       		HSSFRow row=sheet.getRow(i);
       		Map<String,Object> map=new HashMap<String,Object>();
       		for (int j = 0; j < Comments.length; j++) {
       			map.put(Comments[j], row.getCell(j));
       		}
       		rows.add(map);
       	}
       	return rows;
	}
	/**
     * 获取单元格数据内容为字符串类型的数据
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        if (cell == null) {
        	return "";
        }
        switch (cell.getCellType()) {
	        case HSSFCell.CELL_TYPE_STRING:
	            strCell = cell.getStringCellValue();
	            break;
	        case HSSFCell.CELL_TYPE_NUMERIC:
	            strCell = String.valueOf(cell.getNumericCellValue());
	            break;
	        case HSSFCell.CELL_TYPE_BOOLEAN:
	            strCell = String.valueOf(cell.getBooleanCellValue());
	            break;
	        case HSSFCell.CELL_TYPE_BLANK:
	            strCell = "";
	            break;
	        default:
	            strCell = "";
	            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        return strCell;
    }
    /**
     * 获取单元格数据内容为日期类型的数据
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getDateCellValue(HSSFCell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }
	 /**
	  * 导出excel 
	  * @param JdbcTemplate 
	  * @param sql sql 查询脚本
	  * @param excelName excel 导出文件名
	  * @param mappers header映射{ {标题1,字段1,"yyyy-MM-dd"},{标题2,字段2}}
	  * @param response 
	  */
    public static void exportExcelBySql(JdbcTemplate jdbcTemplate,String sql,String excelName,String[][] mappers,HttpServletResponse response){
    	HSSFWorkbook workbook =new HSSFWorkbook();
    	OutputStream out=null;
    	ResultSet rs=null;
    	Connection conn=null;
    	Statement st=null;
    	try {
    		out=response.getOutputStream();
			response.setContentType("APPLICATION/OCTET-STREAM"); 
			excelName = new String(excelName.getBytes("gb2312"), "iso8859-1");
			response.setHeader("Content-Disposition", "attachment; filename="+ excelName);
			HSSFSheet sheet=workbook.createSheet();
				      sheet.setDefaultColumnWidth(20);
			HSSFRow header=sheet.createRow(0);
			header.setHeight((short) 300);//行高
			HSSFPatriarch patr = sheet.createDrawingPatriarch();
			for (int i = 0; i < mappers.length; i++) {
				HSSFCell cell = header.createCell(i);//获取标题
				cell.setCellValue(mappers[i][1].toString());
				HSSFComment comment=patr.createComment(new HSSFClientAnchor(0,0,0,0,(short)3,3,(short)4,8));
				comment.setString(new HSSFRichTextString(mappers[i][0].toString()));
				cell.setCellComment(comment);
			}
			conn=jdbcTemplate.getDataSource().getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
    		int rowNum = 1; 
    		while(rs.next()){
    			HSSFRow row = sheet.createRow(rowNum++); 
    			for (int i = 0; i < mappers.length; i++) {
    				  HSSFCell cell=row.createCell(i);
    				  Object value=rs.getObject(mappers[i][0]);
    				  if(value==null)continue;
    				  String val=value==null?"":value.toString();
    				  if(mappers[i].length>2){
    					  try{
    						  SimpleDateFormat f=new SimpleDateFormat(mappers[i][2]);
    						  val=f.format(f.parse(val));
    					  }catch(Exception e){
    						  System.out.println("不是正确的时间格式:"+"field("+mappers[i][1]+")-value("+val+")"+e.getMessage());
    					  }
    				  }
					cell.setCellValue(val);
				}
    		}
    		workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				 conn.close();
				  out.flush();
				  out.close();
				  workbook.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
    }

     
 
}
