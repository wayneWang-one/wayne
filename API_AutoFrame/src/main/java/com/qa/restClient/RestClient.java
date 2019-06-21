package com.qa.restClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class RestClient {
		final static Logger Log= Logger.getLogger(RestClient.class);
		/***
		 * 不带请求头的get方法封装
		 * @param url
		 * @return
		 * @throws ClientProtocolException
		 * @throws IOException
		 */
		public CloseableHttpResponse get(String url) throws ClientProtocolException ,IOException{
			//创建一个可关闭的HttpClient对象
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//创建一个HttpGet的请求对象
			HttpGet httpget= new HttpGet(url);
			//执行请求
			Log.info("开始发送get请求");
			CloseableHttpResponse httpResponse = httpClient.execute(httpget);
			Log.info("发送请求成功，开始得到响应对象");
			return httpResponse;
		}
		/***
		 * 带请求头信息的get方法
		 * @param url
		 * @param headermap
		 * @return
		 * @throws ClientProtocolException
		 * @throws IOException
		 */
		public CloseableHttpResponse get(String url,HashMap<String, String> headermap) throws ClientProtocolException ,IOException{
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			//加载请求头到httpget对象
			for (Map.Entry<String, String> entry : headermap.entrySet()) {
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
			//执行请求
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			Log.info("开始发送带请求头的get请求");
			return httpResponse;
		}
		/***
		 * 封装post方法
		 * @param url
		 * @param entityString
		 * @param headermap
		 * @return
		 * @throws ClientProtocolException
		 * @throws IOException
		 */
		public CloseableHttpResponse post(String url,String entityString,HashMap<String, String> headermap) throws ClientProtocolException,IOException{
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			//设置payload
			httpPost.setEntity(new StringEntity(entityString));
			
			for (Map.Entry<String, String> entry : headermap.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			Log.info("开始发送Post请求");
			return httpResponse;
		}
		/***
		 * 封装put方法，参数和post一样
		 * @param url
		 * @param entityString
		 * @param headermap
		 * @return
		 * @throws ClientProtocolException
		 * @throws IOException
		 */
		public CloseableHttpResponse put(String url,String entityString,HashMap<String, String> headermap) throws ClientProtocolException,IOException{
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPut httpPut = new HttpPut(url);
			//设置payload
			httpPut.setEntity(new StringEntity(entityString));
			
			for (Map.Entry<String, String> entry : headermap.entrySet()) {
				httpPut.addHeader(entry.getKey(), entry.getValue());
			}
			CloseableHttpResponse httpResponse = httpClient.execute(httpPut);
			Log.info("开始发送put请求");
			return httpResponse;
		}
		/***
		 * 封装delete方法，参数和get一样
		 * @param url
		 * @return
		 * @throws ClientProtocolException
		 * @throws IOException
		 */
		public CloseableHttpResponse delete(String url) throws ClientProtocolException ,IOException{
			//创建一个可关闭的HttpClient对象
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//创建一个HttpGet的请求对象
			HttpDelete httpDelete= new HttpDelete(url);
			//执行请求
			Log.info("开始发送delete请求");
			CloseableHttpResponse httpResponse = httpClient.execute(httpDelete);
			Log.info("发送请求成功，开始得到响应对象");
			return httpResponse;
		}
		/***
		 * 获取相应状态码，常用来和TestBase中定义的状态码常量去测试断言
		 * @param response
		 * @return 返回int类型状态码
		 */
		public int getStatusCode (CloseableHttpResponse response) {
			int statusCode= response.getStatusLine().getStatusCode();
			Log.info("解析，得到响应码"+statusCode);
			return statusCode;
		}
		
		public JSONObject getResponseJson(CloseableHttpResponse response) throws ClientProtocolException,IOException {
			Log.info("得到相应对象的String格式");
			String responseString = EntityUtils.toString(response.getEntity(),"utf-8");
			JSONObject responseJson = JSON.parseObject(responseString);
			Log.info("返回相应内容的json格式");
			return responseJson;
		}
		
}
