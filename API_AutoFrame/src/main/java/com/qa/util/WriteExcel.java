package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;



public class WriteExcel {
		private static final String EXCEL_XLS="xls";
		private static final String EXCEL_XLSX="xlsx";
		public static void main(String[] args) {
			
		}
		public static void writeExcel(String response,int rowcount ,int cloumnCount,String finalXlsxPath) {
			OutputStream out = null;
			System.out.println(response);
			try {
				//读取excel文档
				File finalXlsFile = new File(finalXlsxPath);
				Workbook workbook =getWorkbok(finalXlsFile);
				//sheet对应一个工作页
				Sheet sheet = workbook.getSheetAt(0);
				out = new FileOutputStream(finalXlsFile);
				workbook.write(out);
				//往excel中写入新数据
				//创建一行
				Row row = sheet.getRow(rowcount);
				//在一行内循环
				Cell first = row.createCell(cloumnCount);
				first.setCellValue(response);
				//创建文件输入出流，准备输出电子表格
				out= new FileOutputStream(finalXlsxPath);
				workbook.write(out);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if (out!=null) {
						out.flush();
						out.close();
					}
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			System.out.println("数据导出成功");
		}
		
		public static Workbook getWorkbok(File file)throws IOException {
			Workbook wb =null;
			FileInputStream in = new FileInputStream(file);
			if (file.getName().endsWith(EXCEL_XLS)) {
				wb= new HSSFWorkbook(in);
			}else if (file.getName().endsWith(EXCEL_XLSX)) {
				wb = new HSSFWorkbook(in);
			}
			return wb;
		}
}
