package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {
		public List readExcel(File file) {
			
			try {
				//创建输入流，读取Excel
				InputStream is = new FileInputStream(file.getAbsolutePath());
				//jxl提供的workbook类
				Workbook wb = Workbook.getWorkbook(is);
				//Excel的页签数量
				int sheet_size = wb.getNumberOfSheets();
				for (int index = 0; index < sheet_size; index++) {
					List<List> outerList = new ArrayList<List>();
					Sheet sheet = wb.getSheet(index);
					//sheet.getRows()返回该页的总行数
					for (int i = 0; i < sheet.getRows(); i++) {
						List innerList = new ArrayList();
						//sheet.getColumns()返回该页的总列数
						for (int j = 0; j < sheet.getColumns(); j++) {
							String cellinfo = sheet.getCell(j, i).getContents();
							if (cellinfo.isEmpty()) {
								continue;
							}
							innerList.add(cellinfo);
						}
						outerList.add(innerList);
						System.out.println(outerList);
					}
					return outerList;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (BiffException e) {
				// TODO: handle exception
				e.printStackTrace();
			}catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
			
		}
}
