package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.restClient.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
		TestBase testBase;
		String host;
		String url;
		RestClient restClient;
		CloseableHttpResponse closeableHttpResponse;
		
		final static Logger Log= Logger.getLogger(GetAPITest.class);
		
		@BeforeClass
		public void setUp() {
			testBase = new TestBase();
			host= prop.getProperty("HOST");
			url= host+"/api/users?page=2";
		}
		
		@Test
		public void getApiTest() throws ClientProtocolException ,IOException {
			Log.info("开始执行用例");
			restClient = new RestClient();
			closeableHttpResponse = restClient.get(url);
			
			//断言状态码是否是200
			//int statusCode= restClient.getStatusCode(closeableHttpResponse);
			//Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200,"response status code is not 200");
			
			JSONObject responseJson= restClient.getResponseJson(closeableHttpResponse);
			System.out.println("respon json from api"+ responseJson);
			
			//json内容解析
			String s = TestUtil.getValueByJPath(responseJson, "data[0]/first_name");
			Log.info("执行json解析，解析内容为"+s);
			Assert.assertEquals(s, "Eve","first name is eve");
			Log.info("用例执行结束...");
			
		}
}