package cn.intsmaze.jbdc.mysql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

import cn.intsmaze.jbdc.mysql.utils.JdbcUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
* 读取配置文件的sql语句，解析出使用的表名，然后获取该表的的字段名，字段类型，字段注释的信息写入excel中。
* @author YangLiu
* @date 2018年2月5日
* @version 1.0
 */
public class TableDescToPoi {


	//读取xml配置文件中的所有sql语句，解析出from关键字前面的表名，返回将xml中使用的表名返回
    public static  Set readFileByLines(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader reader = null;

        String[] filelist = file.list();
        Set set=new java.util.HashSet();
        for (int i = 0; i < filelist.length; i++) {
            File readfile = new File(fileName + "\\" + filelist[i]);
            BufferedWriter writer = null;
            reader = new BufferedReader(new FileReader(readfile));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
            	String[] arr=tempString.split(" ");
            	for(int j=0;j<arr.length;j++)
            	{
            		if((arr[j]+" ").indexOf("from ")>0)
            		{
            			set.add(arr[j+1]);
            		}
            	}
            }
        }
        return set;
    }

	public static void main(String[] args) throws IOException {
		Set set=TableDescToPoi.readFileByLines("D:/aiyouwei");

        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("表结构");

		Iterator it=set.iterator();
		int number=0;
		while(it.hasNext())
		{
			try {
				number=writeTableDesc((String) it.next(),sheet,number);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    FileOutputStream outputStream=new FileOutputStream("D:/table-result.xls");
        workbook.write(outputStream);
        outputStream.close();
	}


	public static int writeTableDesc(String tableName,HSSFSheet sheet,int number) throws SQLException, IOException
	{
		Connection conn = JdbcUtils.getInstance().getConnection();
		DatabaseMetaData m_DBMetaData = conn.getMetaData();

		String columnName;
		String columnType;
		String remarkName;
		ResultSet colRet = m_DBMetaData.getColumns(null,"%", tableName,"%");

	    HSSFRow row=sheet.createRow(number);
        HSSFCell cell=row.createCell(1);
        cell.setCellValue(tableName);
		while(colRet.next()) {
			number=number+1;
			columnName = colRet.getString("COLUMN_NAME");
			columnType = colRet.getString("TYPE_NAME");
			remarkName=colRet.getString("REMARKS");
	        HSSFRow row1=sheet.createRow(number);
	        HSSFCell cell1=row1.createCell(1);
	        cell1.setCellValue(columnName);
	        HSSFCell cell2=row1.createCell(2);
	        cell2.setCellValue(columnType);
	        HSSFCell cell3=row1.createCell(3);
	        cell3.setCellValue(remarkName);
		}
		number=number+3;
		return number;
	}
}
