package com.pilgrimm.wm.study.jadk.http;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpRequestDemo {

	public static String doPost(String url, Map<String, Object> paramMap, Map<String, Object> fileMap) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		String result = "";
		// 创建httpClient实例
		httpClient = HttpClients.createDefault();
		// 创建httpPost远程连接实例
		HttpPost httpPost = new HttpPost(url);
		// 配置请求参数实例
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
				.setConnectionRequestTimeout(35000)// 设置连接请求超时时间
				.setSocketTimeout(60000)// 设置读取数据连接超时时间
				.build();
		// 为httpPost实例设置配置
		httpPost.setConfig(requestConfig);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setCharset(Charset.forName("UTF-8")); // 设置请求的编码格式
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE); // 设置浏览器兼容模式
		
		// 封装post请求参数
		if (null != paramMap && paramMap.size() > 0) {
			// 通过map集成entrySet方法获取entity
			Set<Entry<String, Object>> entrySet = paramMap.entrySet();
			// 循环遍历，获取迭代器
			Iterator<Entry<String, Object>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> mapEntry = iterator.next();
				builder.addTextBody(mapEntry.getKey(), mapEntry.getValue().toString()); // 设置请求参数
			}
		}
		
		// 封装post请求文件
		if (null != fileMap && fileMap.size() > 0) {
			// 通过map集成entrySet方法获取entity
			Set<Entry<String, Object>> entrySet = fileMap.entrySet();
			// 循环遍历，获取迭代器
			Iterator<Entry<String, Object>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> mapEntry = iterator.next();
				builder.addBinaryBody(mapEntry.getKey(), (File) mapEntry.getValue());
			}
		}
		
		HttpEntity requestEntity = builder.build();// 生成 HTTP POST 实体  	
		httpPost.setEntity(requestEntity);
		try {
			// httpClient对象执行post请求,并返回响应参数对象
			httpResponse = httpClient.execute(httpPost);
			// 从响应对象中获取响应内容
			HttpEntity responseEntity = httpResponse.getEntity();
			result = EntityUtils.toString(responseEntity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != httpResponse) {
				try {
					httpResponse.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
//		String url = "http://127.0.0.1:8080/pm-wm-study/uploadFile";
		String url = "http://127.0.0.1:8080/pm-wm-admin/func/upload/upload";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("param_1", "XX");
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		File file1 = new File("F:\\temp\\htmlToPdf.pdf");
		File file2 = new File("F:\\temp\\走廊监控设备中标通知书.png");
		fileMap.put("file_1", file1);
		fileMap.put("file_2", file2);
		doPost(url, paramMap, fileMap);
	}
}
