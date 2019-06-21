package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.data.User;
import com.qa.restClient.RestClient;
import com.qa.util.TestUtil;


public class PostApiTest  extends TestBase{
		TestBase testBase;
		RestClient restClient;
		String host;
		String url;
		CloseableHttpResponse closeableHttpResponse;
		
		final static Logger Log = Logger.getLogger(PostApiTest.class);
		
		@BeforeClass
		public void setUp() {
			testBase  = new TestBase();
			host= prop.getProperty("HOST");
			url= host+"/api/users";
		}
		
		@Test
		public void postApiTest() throws ClientProtocolException,IOException{
			restClient= new RestClient();
			//准备请求头信息
			HashMap<String, String> headermap= new HashMap<String, String>();
			headermap.put("Content-Type", "application/json");
			
			//对象转换成json字符串
			User user = new User("Anthony","tester");
			String userJsonString = JSON.toJSONString(user);
			System.out.println(userJsonString);
			
			closeableHttpResponse=restClient.post(url, userJsonString, headermap);
			
			//断言相应json内容中name和pwd是不是期待结果
			String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
			JSONObject responseJson = JSON.parseObject(responseString);
			System.out.println(responseString);
			String name=TestUtil.getValueByJPath(responseJson, "name");
			String pwd =TestUtil.getValueByJPath(responseJson, "pwd");
			
			Assert.assertEquals(name, "Anthony","name is not same");
			Assert.assertEquals(pwd, "tester","pwd is not same");
			
			
		}
}
