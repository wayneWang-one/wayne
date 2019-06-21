package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.qa.restClient.Execut;
import com.qa.restClient.RestClient;
import com.qa.util.CopyFile;
import com.qa.util.WriteExcel;

public class DemoTest extends Execut {
		@Test
		public void test() throws ClientProtocolException,IOException {
			String datapath ="C:\\Tools\\eclipse-workspace\\API_AutoFrame\\apidata\\1.xls";
			Execut execut = new Execut();
			//excel写入
			WriteExcel write = new WriteExcel();
			//excel复制
			CopyFile copy= new CopyFile();
			copy.copyFiles(datapath, "\\result.xls");
			String copyPath = copy.path;
			RestClient restClient= new RestClient();
			File file =new File(datapath);
			List response = execut.selectmethod(file);
			List excelList = read(file);
			System.out.println(response.size());
			
			for (int i = 1; i < response.size(); i++) {
				List list = (List) excelList.get(i);
				//响应码正确值，String转integer
				int liststatuscode = Integer.valueOf((String) list.get(7));
				//请求方法
				String type = (String) list.get(4);
				CloseableHttpResponse res = (CloseableHttpResponse) response.get(i);
				int statusCode = restClient.getStatusCode(res);
				if (res.getEntity()!=null) {
					String responseString = EntityUtils.toString(res.getEntity(),"utf-8");
					write.writeExcel(responseString, i, 10, copy.path+"\\result.xls");
				}
				if (liststatuscode== statusCode) {
					write.writeExcel(String.valueOf(statusCode)+"pass",i, 8, copy.path+"\\result.xls");
					System.out.println(type+"请求，"+"返回值正确");
				}else {
					write.writeExcel(String.valueOf(statusCode)+"pass",i, 8, copy.path+"\\result.xls");
					System.out.println(type+"请求，"+"返回值错误");
				}
			}
		}
}
