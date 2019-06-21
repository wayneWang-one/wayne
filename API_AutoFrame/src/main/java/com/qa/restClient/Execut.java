package com.qa.restClient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.qa.base.TestBase;
import com.qa.util.ReadExcel;

import net.sf.json.JSONObject;

public class Execut extends RestClient {
		String host;
		static String url;
		static RestClient restClient;
		static CloseableHttpResponse closeableHttpResponse;
		final static Logger Log = Logger.getLogger(Execut.class);
		
		//读取excel测试案例
		public List read(File file) {
			ReadExcel obj = new ReadExcel();
			List excelList = obj.readExcel(file);
			return excelList;
		}
		
		public List selectmethod(File file) throws ClientProtocolException,IOException {
			TestBase tb = new TestBase();
			restClient = new RestClient();
			List resList = new ArrayList();
			List excelList = read(file);
			for (int i = 0; i < excelList.size(); i++) {
				List list= (List) excelList.get(i);
				url = tb.prop.getProperty("HOST")+list.get(3);
				if (list.get(4).equals("get")) {
					Log.info("调用get方法");
					closeableHttpResponse = restClient.get(url);
				}else if (list.get(4).equals("post")) {
					Log.info("调用post方法");
					String data= (String) list.get(6);
					//String 转为json对象
					JSONObject object = JSONObject.fromObject(data);
					//json转json字符串
					String userJsonString = JSON.toJSONString(object);
					Log.info("开始执行用例");
					//准备请求头信息
					HashMap<String, String> headermap = new HashMap<String, String>();
					headermap.put("Content-Type", "application/json");
					closeableHttpResponse = restClient.post(url, userJsonString, headermap);
				}else if (list.get(4).equals("put")) {
					Log.info("调用put方法");
					String data = (String) list.get(6);
					//String 转为json对象
					JSONObject object = JSONObject.fromObject(data);
					//json转json字符串
					String userJsonString = JSON.toJSONString(object);
					Log.info("开始执行用例");
					//准备请求头信息
					HashMap<String, String> headermap = new HashMap<String, String>();
					headermap.put("Content-Type", "application/json");
					//调用 put
					closeableHttpResponse = restClient.put(url, userJsonString, headermap);
				}else if (list.get(4).equals("delete")) {
					Log.info("调用delete方法");
					closeableHttpResponse = restClient.delete(url);
				}
				resList.add(closeableHttpResponse);
			}
			return resList;
		}
}
