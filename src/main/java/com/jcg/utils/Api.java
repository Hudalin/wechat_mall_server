package com.jcg.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

public class Api {
	
	/**
	 *  LOG
	 */
	private static Logger logger = Logger.getLogger(Api.class);
	
	// private static final String IP=Server.URLHEAD;
	private static final String GETIP() {
		Util util = new Util();
		Properties properties = util.readRcErpURL("db.properties");
		String ip = properties.getProperty("ip");
		Integer port = 0;
		if(StringUtils.isNotEmpty(properties.getProperty("port"))){
			port = Integer.parseInt(properties.getProperty("port"));
		}
		String url = "http://" + ip + ":" + port + "/";
		return url;
	}

	private static String modifyJson(String json)
			throws UnsupportedEncodingException {
		byte[] bt = json.getBytes();
		String jsond = new String(bt, "utf-8");
		JSONObject jsons = JSONObject.fromObject(jsond);
		System.out.println(jsons.toString());
		if (jsons.getInt("code") == 2) {
			jsons.put("status", 2);
			jsons.put("url", "/login.jsp");
		}
		return jsons.toString();
	}

	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = GETIP() + url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			
			// 设置通用的请求属性
			connection.setConnectTimeout(10000);
//			connection.setRequestProperty("accept", "*/*");
//			connection.setRequestProperty("connection", "Keep-Alive");
//			connection.setRequestProperty("user-agent",
//					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setReadTimeout(10000);
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
			return "{\"code\":5,\"msg\":\"服务器连接失败\"}";
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		try {
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"code\":5,\"msg\":\"服务器连接失败\"}";
		}
	}
	
	public static String sendGetTest(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			// String urlNameString = url + "?" + param;
			// String urlNameString = "http://jdm.jcgcom.com:10002/manage/dev/operate/getParams?operateAccount=root&agencyId=1&sn=04%3A5f%3Aa7%3A5a%3A3f%3A6c&type=0";
			String urlNameString = url+"?"+param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
			return "{\"code\":5,\"msg\":\"服务器连接失败\"}";
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		try {
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"code\":5,\"msg\":\"服务器连接失败\"}";
		}
	}

	public static String sendPostTest(String url, String param) {
		// String urls = GETIP() + url;
		String urls = url;
		DefaultHttpClient httpClient = getHttpClient(6000000, 6000000);
		StringBuffer result = new StringBuffer();
		HttpPost post = new HttpPost(urls);
		StringEntity params = null;
		params = new StringEntity(param, Charset.forName("UTF-8"));
		post.setEntity(params);
		HttpResponse response = null;
		try {
			response = httpClient.execute(post);
		} catch (ClientProtocolException e) {
			result.append("{\"code\":5,\"msg\":\"服务器连接失败\"}");
			e.printStackTrace();
		} catch (IOException e) {
			result.append("{\"code\":5,\"msg\":\"服务器连接失败\"}");
			e.printStackTrace();
		}
		
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		} catch (IllegalStateException e) {
			result.append("{\"code\":5,\"msg\":\"服务器连接失败\"}");
			e.printStackTrace();
		} catch (IOException e) {
			result.append("{\"code\":5,\"msg\":\"服务器连接失败\"}");
			e.printStackTrace();
		}
		
		String line = "";
		try {
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			result.append("{\"code\":5,\"msg\":\"服务器连接失败\"}");
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
	public static String sendPost(String url, String param) {
		String urls = GETIP() + url;
		DefaultHttpClient httpClient = getHttpClient(6000000, 6000000);
		StringBuffer result = new StringBuffer();
		HttpPost post = new HttpPost(urls);
		StringEntity params = null;
		params = new StringEntity(param, Charset.forName("UTF-8"));
		post.setEntity(params);
		HttpResponse response = null;
		try {
			response = httpClient.execute(post);
		} catch (ClientProtocolException e) {
			result.append("{\"code\":5,\"msg\":\"服务器连接失败\"}");
			e.printStackTrace();
		} catch (IOException e) {
			result.append("{\"code\":5,\"msg\":\"服务器连接失败\"}");
			e.printStackTrace();
		}

		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		} catch (IllegalStateException e) {
			result.append("{\"code\":5,\"msg\":\"服务器连接失败\"}");
			e.printStackTrace();
		} catch (IOException e) {
			result.append("{\"code\":5,\"msg\":\"服务器连接失败\"}");
			e.printStackTrace();
		}

		String line = "";
		try {
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			result.append("{\"code\":5,\"msg\":\"服务器连接失败\"}");
			e.printStackTrace();
		}

		return result.toString();
	}

	public static String doHttpPost(String xmlInfo, String URL) {
		System.out.println("发起的数据:" + xmlInfo);
		byte[] xmlData = xmlInfo.getBytes();
		InputStream instr = null;
		java.io.ByteArrayOutputStream out = null;
		try {
			String urls = GETIP() + URL;
			URL url = new URL(urls);
			URLConnection urlCon = url.openConnection();
			urlCon.setDoOutput(true);
			urlCon.setDoInput(true);
			urlCon.setUseCaches(false);
			urlCon.setRequestProperty("Content-Type", "text/xml");
			urlCon.setRequestProperty("Content-length",
					String.valueOf(xmlData.length));
			System.out.println(String.valueOf(xmlData.length));
			DataOutputStream printout = new DataOutputStream(
					urlCon.getOutputStream());
			printout.write(xmlData);
			printout.flush();
			printout.close();
			instr = urlCon.getInputStream();
			byte[] bis = IOUtils.toByteArray(instr);
			String ResponseString = new String(bis, "UTF-8");
			if ((ResponseString == null) || ("".equals(ResponseString.trim()))) {
				System.out.println("返回空");
			}
			System.out.println("返回数据为:" + ResponseString);
			// return ResponseString;
			return modifyJson(ResponseString);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				return modifyJson("{}");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
				return "{\"code\":5,\"msg\":\"error\"}";
			}
		} finally {
			try {
				out.close();
				instr.close();
			} catch (Exception ex) {
				try {
					return modifyJson("{}");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
					return "{\"code\":5,\"msg\":\"error\"}";
				}
			}
		}
	}

	public static String sendPost6(String url, String parme) {
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				5000);// 数据传输时间
		String urls = GETIP() + url;
		HttpPost httppost = new HttpPost(urls);
		String strResult = "";

		try {
			httppost.addHeader("Content-type", "application/json; charset=utf-8");
			httppost.setHeader("Accept", "application/json");
			httppost.setEntity(new StringEntity(parme, Charset.forName("UTF-8")));

			HttpResponse response = httpclient.execute(httppost);
			if (response.getStatusLine().getStatusCode() == 200) {
				/* 读返回数据 */
				strResult = EntityUtils.toString(response.getEntity());

			} else {
				String err = response.getStatusLine().getStatusCode() + "";
				err += "发送失败:" + err;
				System.out.println(err);
				strResult = "{\"code\":5,\"msg\":\"服务器连接失败\"}";
			}
		} catch (ClientProtocolException e) {
			strResult = "{\"code\":5,\"msg\":\"服务器连接失败\"}";
			e.printStackTrace();
		} catch (IOException e) {
			strResult = "{\"code\":5,\"msg\":\"服务器连接失败\"}";
			e.printStackTrace();
		}

		return strResult;
	}

	private static DefaultHttpClient getHttpClient(int rTimeOut, int sTimeOut) {
		BasicHttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, rTimeOut);
		HttpConnectionParams.setSoTimeout(httpParams, sTimeOut);
		DefaultHttpClient client = new DefaultHttpClient(httpParams);
		return client;
	}
	
	/**
	 * 暂时用于无登录状态的Get请求
	 * @param url
	 * @param param
	 * @return
	 */
	public static String sendGetDev(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = GETIPDEV() + url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			
			// 设置通用的请求属性
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
			return "{\"code\":5,\"msg\":\"服务器连接失败\"}";
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		try {
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"code\":5,\"msg\":\"服务器连接失败\"}";
		}
	}
	
	/**
	 * 暂时用于无登录状态的Post请求
	 * @param url
	 * @param param
	 * @return
	 */
	public static String sendPostDev(String url, String param) {
		String urls = GETIPDEV() + url;
		DefaultHttpClient httpClient = getHttpClient(6000000, 6000000);
		StringBuffer result = new StringBuffer();
		HttpPost post = new HttpPost(urls);
		StringEntity params = null;
		params = new StringEntity(param, Charset.forName("UTF-8"));
		post.setEntity(params);
		HttpResponse response = null;
		try {
			response = httpClient.execute(post);
		} catch (ClientProtocolException e) {
			result.append("{\"code\":5,\"msg\":\"服务器连接失败\"}");
			e.printStackTrace();
		} catch (IOException e) {
			result.append("{\"code\":5,\"msg\":\"服务器连接失败\"}");
			e.printStackTrace();
		}

		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		} catch (IllegalStateException e) {
			result.append("{\"code\":5,\"msg\":\"服务器连接失败\"}");
			e.printStackTrace();
		} catch (IOException e) {
			result.append("{\"code\":5,\"msg\":\"服务器连接失败\"}");
			e.printStackTrace();
		}

		String line = "";
		try {
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			result.append("{\"code\":5,\"msg\":\"服务器连接失败\"}");
			e.printStackTrace();
		}

		return result.toString();
	}
	// private static final String IP=Server.URLHEAD;
	private static final String GETIPDEV() {
		Util util = new Util();
		Properties properties = util.readRcErpURL("db.properties");
		String ip = properties.getProperty("ipDev");
		Integer port = Integer.parseInt(properties.getProperty("portDev"));
		String url = "http://" + ip + ":" + port + "/";
		return url;
	}
	
    /**
     * 接收HttpPost请求
     * 
     * @param request
     * @return 成功:字符串
     */
    public static String jsonPostReception(HttpServletRequest request) {
    	String result = "";
    	try {
			// 获取并解密数据
			Map<String, String[]> params = request.getParameterMap();
			for (String key : params.keySet()) {
				String[] values = params.get(key);
				for (int i = 0; i < values.length; i++) {
					String value = values[i];
					result += key + "=" + (value==""?"":"\"}&");
				}
			}
			logger.info("login result A:"+ result);
			// 去掉最后一个空格
			result = result.substring(0, result.length() - 1);
			logger.info("login result B:"+ result);
    	} catch (Exception e) {
    		System.out.println("发送 POST 请求出现异常！"+e);
    		e.printStackTrace();
    	}
		return result;
    }
}
