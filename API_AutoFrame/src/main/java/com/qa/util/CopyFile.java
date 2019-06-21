package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {
		public static String path="C:\\Tools\\eclipse-workspace\\API_AutoFrame\\result";
		
		public void copyFiles(String fileCopy,String table) {
			//被复制文件
			File copyFile = new File(fileCopy);
			//指定文件夹路径
			String copiedFolderPath = path;
			//指定文件夹
			File copiedFolder = new File(copiedFolderPath);
			try {
				//判断文件夹是否存在，不存在需要创建，否则无法正常创建该文件夹下的文件
				if (!copiedFolder.exists()) {
					System.out.println("copiedFolder not exists"+copiedFolderPath);
					copiedFolder.mkdirs();
				}
				String copiedFilePath= path+table;
				File copiedFile= new File(copiedFilePath);
				if (!copiedFile.exists()) {
					System.out.println("copiedFile not exists"+ copiedFolderPath);
					copiedFile.createNewFile();
				}
				copyFileContent(copyFile, copiedFile);
			} catch (IOException e) {
				// TODO: handle exception
				System.out.println("error"+e);
			}
		}
		private void copyFileContent(File fromFile,File toFile) throws IOException {
			FileInputStream ins = new FileInputStream(fromFile);
			FileOutputStream out= new FileOutputStream(toFile);
			byte[] b = new byte[1024];
			int n=0;
			while ((n=ins.read(b))!=-1) {
				out.write(b,0,n);
			}
			ins.close();
			out.close();
		}
		
}
