package cn.cs.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.cs.nsfw.user.entity.User;
import cn.cs.nsfw.user.service.UserService;

public class ExcelUtil {
	/**
	 * excel导出
	 * @param userList 用户信息集合
	 * @param outputStream 网站response输出流
	 */
	public static void exportExcel(List<User> userList,ServletOutputStream outputStream){
		try {
			//1、创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			//1.1、创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);
			//1.2、头标题样式
			HSSFCellStyle headStyle = createCellStyle(workbook,(short)16);
			//1.3、列标题样式
			HSSFCellStyle colStyle = createCellStyle(workbook,(short)13);
			//2、创建工作表
			HSSFSheet sheet = workbook.createSheet("用户列表");
			//2.1、加载合并单元格对象
			sheet.addMergedRegion(cellRangeAddress);
			//设置默认列宽
			sheet.setDefaultColumnWidth(20);
			//3、创建行
			//3.1、创建头标题行；并且设置头标题
			HSSFRow headRow = sheet.createRow(0);
			HSSFCell headCell = headRow.createCell(0);
			headCell.setCellStyle(headStyle);
			headCell.setCellValue("用户列表");
			//3.2、创建列标题行；并且设置列标题
			HSSFRow colRow = sheet.createRow(1);
			String[] titles={"用户名","帐号","所属部门","性别","电子邮箱"};
			for(int i=0;i<titles.length;i++){
				HSSFCell cell = colRow.createCell(i);
				cell.setCellStyle(colStyle);
				cell.setCellValue(titles[i]);
			}
			//4、操作单元格；将用户列表写入excel
			if(userList != null){
				for(int j=0;j<userList.size();j++){
					HSSFRow row = sheet.createRow(j+2);
					HSSFCell cell0 = row.createCell(0);
					cell0.setCellValue(userList.get(j).getName());
					HSSFCell cell1 = row.createCell(1);
					cell1.setCellValue(userList.get(j).getAccount());
					HSSFCell cell2 = row.createCell(2);
					cell2.setCellValue(userList.get(j).getDept());
					HSSFCell cell3 = row.createCell(3);
					cell3.setCellValue(userList.get(j).isGender()?"男":"女");
					HSSFCell cell4 = row.createCell(4);
					cell4.setCellValue(userList.get(j).getEmail());
				}
			}
			//5.输出
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建单元格样式
	 * @param workbook 工作簿
	 * @param fontSize 字体大小
	 * @return 单元格样式
	 */
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook,short fontSize){
		HSSFCellStyle style = workbook.createCellStyle();
		//1.2.1居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//1.2.2 字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints(fontSize);
		style.setFont(font);
		return style;
	}

	//导入功能
	public static void importExcel(File userExcel, String userExcelFileName,UserService userService) {
		try {
			//1.读取输入流
			FileInputStream fileInputStream = new FileInputStream(userExcel);
			//2.判断是否为03excel
			boolean is03Excel = userExcelFileName.matches("^.?\\.(?i)((xls))$");
			Workbook workbook = is03Excel? new HSSFWorkbook(fileInputStream):new XSSFWorkbook(fileInputStream);
			//读取工作表
			Sheet sheet = workbook.getSheetAt(0);
			//读取行，从第3行开始
			if(sheet.getPhysicalNumberOfRows() >2){
				//读取单元格
				User user = null;
				for(int k =2;k<sheet.getPhysicalNumberOfRows();k++){
					//读取单元格
					user = new User();
					Row row = sheet.getRow(k);
					//用户名
					user.setName(row.getCell(0).getStringCellValue());
					//帐号
					user.setAccount((row.getCell(1).getStringCellValue()));
					//所属部门
					user.setDept((row.getCell(2).getStringCellValue()));
					//性别
					user.setGender(row.getCell(3).getStringCellValue().equals("男"));
					//手机号 excel会用科学计数法进行处理 或者也有可能是字符串~（看用户设置的习惯）
					String mobile ="";
					try {
						mobile = row.getCell(4).getStringCellValue();
					} catch (Exception e) {
						double dMobile = row.getCell(4).getNumericCellValue();
						mobile = BigDecimal.valueOf(dMobile).toString();
					}
					
					user.setMobile(mobile);
					//电子邮箱
					user.setEmail(row.getCell(5).getStringCellValue());
					//生日 格式问题
					if(row.getCell(6).getDateCellValue() != null){
						user.setBirthday(row.getCell(6).getDateCellValue());
					}
					//默认密码和状态有效
					user.setPassword("123456");
					user.setState(User.USER_STATE_VALID);
					userService.save(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
