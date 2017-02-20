package cn.cs.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestPOI2Excel {
	
	@Test
	//07--XSSF 03--HSSF
	public void testWrite03Excel() throws Exception{
		//1.创建工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(); 
		//2.创建工作表
		XSSFSheet sheet = workbook.createSheet("hello work");//工作表名
		//3.创建行
		XSSFRow row = sheet.createRow(3);
		//4.创建单元格
		XSSFCell cell = row.createCell(3);
		cell.setCellValue("嗨!");
		//输出到硬盘中
		FileOutputStream outputStream = new FileOutputStream("E:\\c\\SSH_csTax\\csTax\\test\\测试.xlsx");
		//把excel输出到具体位置
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	@Test
	public void testRead03Excel() throws Exception{
		//从硬盘中读出
		FileInputStream inputStream = new FileInputStream("E:\\c\\SSH_csTax\\csTax\\test\\测试.xlsx");
		//1.创建工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream); 
		//2.创建工作表
		XSSFSheet sheet = workbook.getSheetAt(0);//工作表名
		//3.创建行
		XSSFRow row = sheet.getRow(3);
		//4.创建单元格
		XSSFCell cell = row.getCell(3);
		System.out.println("第四行第四列单元格内容："+cell.getStringCellValue());
		
		workbook.close();
		inputStream.close();
	}
	
	@Test
	public void testRead03And07Excel() throws Exception{
		//从硬盘中读出
		String fileName = "E:\\c\\SSH_csTax\\csTax\\test\\测试.xls";
		FileInputStream inputStream = new FileInputStream(fileName);
		String expandName = fileName.substring(fileName.lastIndexOf("."));
//		if(".xlsx".equals(expandName))
//			is03Excel = false;
//		else if(".xls".equals(expandName))
//			is03Excel = true;
//		else
//			throw new RuntimeException("文件不是Excle格式");
		//正则表达式
		if(fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
			boolean is03Excel = fileName.matches("^.+\\.(?i)((xls))$");
		//1.创建工作簿
		Workbook workbook = is03Excel == true? new HSSFWorkbook(inputStream): new XSSFWorkbook(inputStream); 
		//2.创建工作表
		Sheet sheet = workbook.getSheetAt(0);//工作表名
		//3.创建行
		Row row = sheet.getRow(3);
		//4.创建单元格
		Cell cell = row.getCell(3);
		System.out.println("第四行第四列单元格内容："+cell.getStringCellValue());
		
		workbook.close();
		}
		inputStream.close();
	}
}
