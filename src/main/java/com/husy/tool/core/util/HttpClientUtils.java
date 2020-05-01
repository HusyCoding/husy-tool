package com.husy.tool.core.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * @description: HttpClient 工具类
 * @author: husy
 * @date 2020/4/7
 */
public class HttpClientUtils {
	public static void main(String[] args) {
		try {
			String url="http://192.168.1.16:18095/dcrole/bas-data-source/login";
			Map<String,String> params = new HashMap<>(2);
			params.put("dcEntId","cjl");
			params.put("isMStation","1");
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("dcEntId","1");
//			jsonObject.put("isMStation",1);

			Map<String,Object> result = httpPost(url,params,null,null);
			System.out.println("响应结果"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 编码格式。发送编码格式统一用UTF-8
	 */
	private static final String CHARSET = "UTF-8";

	/**
	 * 设置连接超时时间，单位毫秒。
	 */
	private static final int CONNECT_TIMEOUT = 6000;

	/**
	 * 设置请求超时时间，单位毫秒
	 */
	private static final int CONNECT_REQUEST_TIMEOUT = 6000;
	/**
	 * 设置请求获取数据的超时时间(即响应时间)，单位毫秒。
	 */
	private static final int SOCKET_TIMEOUT = 6000;


	public static Map<String,Object> httpJson(String url, String paramsJson, Map<String, String> headers, String charset) throws IOException {
		Map<String, Object> resultMap = new HashMap<>(2);
		resultMap.put("code", null);
		resultMap.put("result", null);

		if (charset == null || "".equals(charset)) {
			charset = CHARSET;
		}
		//1、创建httpclient对象
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			//2、创建post方式请求对象
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(getConfig());

			//3、装填参数,给httpPost设置JSON格式的参数
			StringEntity requestEntity = new StringEntity(paramsJson,charset);
			httpPost.setEntity(requestEntity);

			//4、设置header信息，指定报文头【Content-type】、【User-Agent】
			initHeader(httpPost, headers);
			httpPost.setHeader("Content-type", "application/json;charset="+charset);

			//5、执行请求操作，并拿到结果（同步阻塞）
			try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
				resultMap = getResult(response, charset);
			}
		} catch (Exception e) {
			throw e;
		}
		return resultMap;
	}

	public static Map<String, Object> httpGet(String url, Map<String, String> params, Map<String, String> headers, String charset) throws Exception {
		Map<String, Object> resultMap = new HashMap<>(2);
		resultMap.put("code", null);
		resultMap.put("result", null);

		if (charset == null || "".equals(charset)) {
			charset = CHARSET;
		}

		//1、创建httpclient对象
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			//2、装填 URI 对象
			URIBuilder builder = new URIBuilder(url);

			//3、装填参数
			List<NameValuePair> nvps = dealParams(params);
			if (params != null) {
				// 设置参数到请求对象中
				builder.setParameters(nvps);
			}
			// 4、创建get方式请求对象
			URI uri = builder.build();
			HttpGet httpGet = new HttpGet(uri);

			//5、设置header信息，指定报文头【Content-type】、【User-Agent】
			initHeader(httpGet, headers);

			//6、执行请求操作，并拿到结果（同步阻塞）
			try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
				resultMap = getResult(response, charset);
			}
		} catch (Exception e) {
			throw e;
		}
		return resultMap;
	}

	public static Map<String, Object> httpPost(String url, Map<String, String> params, Map<String, String> headers, String charset) throws Exception {
		Map<String, Object> resultMap = new HashMap<>(2);
		resultMap.put("code", null);
		resultMap.put("result", null);

		if (charset == null || "".equals(charset)) {
			charset = CHARSET;
		}
		//1、创建httpclient对象
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			//2、创建post方式请求对象
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(getConfig());

			//3、装填参数
			List<NameValuePair> nvps = dealParams(params);
			if (params != null) {
				// 设置参数到请求对象中
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
			}

			//4、设置header信息，指定报文头【Content-type】、【User-Agent】
			initHeader(httpPost, headers);

			//5、执行请求操作，并拿到结果（同步阻塞）
			try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
				resultMap = getResult(response, charset);
			}
		} catch (Exception e) {
			throw e;
		}
		return resultMap;
	}

	private static void initHeader(HttpRequestBase httpRequest, Map<String, String> headers) {
		httpRequest.setHeader("Content-type", "application/json;charset=utf8");
		httpRequest.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		httpRequest.setHeader("Connection", "keep-alive");
		httpRequest.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
		httpRequest.setHeader("Accept-Encoding", "gzip, deflate, br");
		if (headers != null && headers.size() > 0) {
			Set<Map.Entry<String, String>> entrySet = headers.entrySet();
			for (Map.Entry<String, String> entry : entrySet) {
				// 设置到请求头到HttpRequestBase对象中
				httpRequest.setHeader(entry.getKey(), entry.getValue());
			}
		}
	}

	private static List<NameValuePair> dealParams(Map<String, String> params) {
		if (params != null) {
			// 将参数放入键值对象类NameValuePair中,再放入集合中
			List<NameValuePair> nvps = new ArrayList<>();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			return nvps;
		}
		return null;
	}

	private static Map<String, Object> getResult(CloseableHttpResponse response, String charset) throws IOException {
		Map<String, Object> resultMap = new HashMap<>(2);
		//6、获取结果实体
		resultMap.put("code", response.getStatusLine().getStatusCode());
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			//按指定编码转换结果实体为String类型
			resultMap.put("result", EntityUtils.toString(entity, charset));
			// 关闭HttpEntity是的流
			EntityUtils.consume(entity);
		}
		return resultMap;
	}

	private static RequestConfig getConfig() {
		// 配置信息
		return RequestConfig.custom()
				// 设置连接超时时间(单位毫秒)
				.setConnectTimeout(CONNECT_TIMEOUT)
				// 设置请求超时时间(单位毫秒)
				.setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT)
				// socket读写超时时间(单位毫秒)
				.setSocketTimeout(SOCKET_TIMEOUT)
				// 设置是否允许重定向(默认为true)
				.setRedirectsEnabled(true).build();
	}


}
