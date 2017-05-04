package com.wechat.manage.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * http请求
 * @author yfc
 */
public abstract class HttpClient {
	
	private final static Logger logger = LoggerFactory.getLogger(HttpClient.class);
	
	private static final String DEFAULT_CHARSET = "UTF-8";
	private static final String METHOD_POST = "POST";
	private static final String METHOD_GET = "GET";
	private static final int connectTimeout = 10000;
	private static final int readTimeout = 100000;
	private HttpClient() {
	}
	
	/**
	 * post请求
	 * @param url
	 * @param content
	 * @return
	 */
	public static String doPost(String url,String content){
		try {
			return _doPost(url,content.getBytes(DEFAULT_CHARSET),connectTimeout,readTimeout,null,DEFAULT_CHARSET);
		} catch (IOException e) {
			logger.error("接口请求失败:"+url+":"+content,e);
			return "false";
		}
	}
	
	/**
	 * post请求
	 * @param url
	 * @param content
	 * @param heads 头信息
	 * @return
	 */
	public static String doHeadPost(String url,String content,Map<String,String> heads){
		try {
			return _doHeadMapPost(url,content.getBytes(DEFAULT_CHARSET),connectTimeout,readTimeout,null,DEFAULT_CHARSET,heads);
		} catch (IOException e) {
			logger.error("接口请求失败:"+url+":"+content,e);
			return "false";
		}
	}
	
	/**
	 * post请求
	 * @param url
	 * @param object
	 * @return
	 */
	public static String doPost(String url,Object object){
		try {
			String content = JSON.toJSONString(object);
			return _doPost(url,content.getBytes(DEFAULT_CHARSET),connectTimeout,readTimeout,null,DEFAULT_CHARSET);
		} catch (IOException e) {
			logger.error("数据请求失败"+url,e);
			return "false";
		}
	}
	
	/**
	 * post请求
	 * @param url
	 * @param object
	 * @param heads 头信息
	 * @return
	 */
	public static String doHeadPost(String url,Object object,Map<String,String> heads){
		try {
			String content = JSON.toJSONString(object);
			return _doHeadMapPost(url,content.getBytes(DEFAULT_CHARSET),connectTimeout,readTimeout,null,DEFAULT_CHARSET,heads);
		} catch (IOException e) {
			logger.error("数据请求失败:"+url,e);
			return "false";
		}
	}
	/**
	 * post请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url,Map<String,String> params){
		try {
			return _doMapPost(url,params,connectTimeout,readTimeout,DEFAULT_CHARSET);
		} catch (IOException e) {
			logger.error("接口请求失败:"+url,e);
         return "false";
		}
	}
	
	/**
	 * post请求
	 * @param url
	 * @param params
	 * @param
	 * @return
	 */
	public static String doHeadPost(String url,Map<String,String> params,Map<String,String> heads){
		try {
			return _doHeadMapPost(url,params,connectTimeout,readTimeout,DEFAULT_CHARSET,heads);
		} catch (IOException e) {
			logger.error("接口请求失败:"+url,e);
			return "false";
		}
	}
	/**
	 * post请求
	 * @param url
	 * @param content
	 * @param params
	 * @return
	 */
	public static String doPost(String url,String content,Map<String,String> params){
		try {
			return _doPost(url,content.getBytes(DEFAULT_CHARSET),connectTimeout,readTimeout,params,DEFAULT_CHARSET);
		} catch (IOException e) {
			logger.error("接口请求失败:"+url,e);
			return "false";
		}
	}
	
	/**
	 * post请求
	 * @param url
	 * @param object
	 * @param params
	 * @return
	 */
	public static String doPost(String url,Object object,Map<String,String> params){
		try {
			String content = JSON.toJSONString(object);
			return _doPost(url,content.getBytes(DEFAULT_CHARSET),connectTimeout,readTimeout,params,DEFAULT_CHARSET);
		} catch (IOException e) {
			logger.error("接口请求失败:"+url,e);
			return "false";
		}
	}
	
	/**
	 * post请求
	 * @param url
	 * @param content
	 * @param params
	 * @param charset
	 * @return
	 */
	public static String doPost(String url,String content,Map<String,String> params,String charset){
		try {
			return _doPost(url,content.getBytes(charset),connectTimeout,readTimeout,params,charset);
		} catch (IOException e) {
			logger.error("接口请求失败:"+url,e);
			return "false";
		}
	}
	
	
	/**
     * 将JSON封装到body中传输
     *
     * @param url
     * @param json
     * @param timeout
     * @return
     * @throws Exception
     */
    public static String postBody(String url, String json, int timeout) {
        String result = null;
        try {
            //4.3写法
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json; charset=utf-8");
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout)
                    .setConnectTimeout(timeout).build();//设置请求和传输超时时间
            httpPost.setConfig(requestConfig);
            if (json != null) {
                StringEntity paramEntity = new StringEntity(json, "utf8");
                paramEntity.setContentType("application/json");
                paramEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                httpPost.setEntity(paramEntity);
            }
            HttpResponse response = httpClient.execute(httpPost);//执行请求
            Integer statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                result = EntityUtils.toString(response.getEntity());
            } else {
                throw new Exception(statusCode.toString());
            }
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }
	
	
	
	
	/**
	 * post请求
	 * @param url
	 * @param content
	 * @param params
	 * @param charset
	 * @param connectTimeout
	 * @param readTimeout
	 * @return
	 */
	public static String doPost(String url,String content,Map<String,String> params,String charset
			,int connectTimeout,int readTimeout){
		try {
			return _doPost(url,content.getBytes(charset),connectTimeout,readTimeout,params,charset);
		} catch (IOException e) {
			logger.error("接口请求失败:"+url,e);
			return "false";
		}
	}
	
	/**
	 * 针对营销，会员中心返回数据格式化
	 * @param result
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String format(String result){
		Map<String,Object> resultmap =(Map<String,Object>)JSON.parse(result);
		if(!resultmap.get("returncode").equals("0")){
			return "false";
		}
		return result;
	}
	/**
	 * 针对营销，会员中心返回为空数据数据格式化为Null
	 * @param result
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String formatToNull(String result){
		Map<String,Object> resultmap =(Map<String,Object>)JSON.parse(result);
		if(!resultmap.get("returncode").equals("0")){
			resultmap.put("data", null);
			result = JSON.toJSONString(resultmap);
		}
		return result;
	}
	/**
	 * 
	 * @param url
	 * @param params
	 * @param connectTimeout
	 * @param readTimeout
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	private static String _doMapPost(String url, Map<String,String> params, int connectTimeout, int readTimeout,
			String charset) throws IOException {
		String query = buildQuery(params, charset);
		byte[] content = {};
		if (query != null) {
			content = query.getBytes(charset);
		}
		return _doMapPost(url,content,connectTimeout,readTimeout,null,charset);
	}
	
	/**
	 * 
	 * @param url
	 * @param params
	 * @param connectTimeout
	 * @param readTimeout
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	private static String _doHeadMapPost(String url, Map<String,String> params, int connectTimeout, int readTimeout,
			String charset,Map<String,String> heads) throws IOException {
		String query = buildQuery(params, charset);
		byte[] content = {};
		if (query != null) {
			content = query.getBytes(charset);
		}
		return _doHeadMapPost(url,content,connectTimeout,readTimeout,null,charset,heads);
	}
	
	/**
	 * 
	 * @param url
	 * @param content
	 * @param connectTimeout
	 * @param readTimeout
	 * @param params
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	private static String _doMapPost(String url, byte[] content, int connectTimeout, int readTimeout,
			Map<String, String> params,String charset) throws IOException {
		String ctype = "application/x-www-form-urlencoded;charset=" + charset;
		HttpURLConnection conn = null;
		OutputStream out = null;
		String rsp = null;
		try {
			try {
				conn = getConnection(new URL(url), METHOD_POST, ctype, null);
				conn.setConnectTimeout(connectTimeout);
				conn.setReadTimeout(readTimeout);
			} catch (IOException e) {
				throw e;
			}
			if(content!=null){
				try {
					out = conn.getOutputStream();
					out.write(content);
				} catch (IOException e) {
					throw e;
				}
			}
			rsp = getResponseAsString(conn);
		} finally {
			if (out != null) {
				out.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}

		return rsp;
	}
	
	
	/**
	 * 
	 * @param url
	 * @param content
	 * @param connectTimeout
	 * @param readTimeout
	 * @param params
	 * @param charset
	 * @param headMap 头参数
	 * @return
	 * @throws IOException
	 */
	private static String _doHeadMapPost(String url, byte[] content, int connectTimeout, int readTimeout,
			Map<String, String> params,String charset,Map<String,String> headMap) throws IOException {
		String ctype = "application/x-www-form-urlencoded;charset=" + charset;
		HttpURLConnection conn = null;
		OutputStream out = null;
		String rsp = null;
		try {
			try {
				conn = getConnection(new URL(url), METHOD_POST, ctype, headMap);
				conn.setConnectTimeout(connectTimeout);
				conn.setReadTimeout(readTimeout);
			} catch (IOException e) {
				throw e;
			}
			if(content!=null){
				try {
					out = conn.getOutputStream();
					out.write(content);
				} catch (IOException e) {
					throw e;
				}
			}
			rsp = getResponseAsString(conn);
		} finally {
			if (out != null) {
				out.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}

		return rsp;
	}
	
	private static String _doPost(String url, byte[] content, int connectTimeout, int readTimeout,
			Map<String, String> params,String charset) throws IOException {
		String ctype = "application/x-www-form-urlencoded;charset=" + charset;
		HttpURLConnection conn = null;
		OutputStream out = null;
		String rsp = null;
		String query = buildQuery(params, charset);
		try {
			try {
				conn = getConnection(buildGetUrl(url, query), METHOD_POST, ctype, null);
				conn.setConnectTimeout(connectTimeout);
				conn.setReadTimeout(readTimeout);
			} catch (IOException e) {
				throw e;
			}
			if(content!=null){
				try {
					out = conn.getOutputStream();
					out.write(content);
				} catch (IOException e) {
					throw e;
				}
			}
			rsp = getResponseAsString(conn);
		} finally {
			if (out != null) {
				out.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}

		return rsp;
	}
	
	/**
	 * get请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doGet(String url,Map<String,String> params){
		try {
			return doGet(url,params,DEFAULT_CHARSET,connectTimeout,readTimeout);
		} catch (IOException e) {
			return "false";
		}
	}
	
	/**
	 * get请求
	 * @param url
	 * @param params
	 * @param charset
	 * @return
	 */
	public static String doGet(String url,Map<String,String> params,String charset){
		try {
			return doGet(url,params,charset,connectTimeout,readTimeout);
		} catch (IOException e) {
			return "false";
		}
	}
	
	/**
	 * 执行HTTP GET请求。
	 * 
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param charset 字符集，如UTF-8, GBK, GB2312
	 * @return 响应字符串
	 * @throws IOException
	 */
	public static String doGet(String url, Map<String, String> params, String charset
			,int connectTimeout,int readTimeout) throws IOException {
		HttpURLConnection conn = null;
		String rsp = null;

		try {
			String ctype = "application/x-www-form-urlencoded;charset=" + charset;
			String query = buildQuery(params, charset);
			try {
				conn = getConnection(buildGetUrl(url, query), METHOD_GET, ctype, null);
				conn.setConnectTimeout(connectTimeout);
				conn.setReadTimeout(readTimeout);
			} catch (IOException e) {
				throw e;
			}

			try {
				rsp = getResponseAsString(conn);
			} catch (IOException e) {
				throw e;
			}

		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return rsp;
	}
	
	private static URL buildGetUrl(String strUrl, String query) throws IOException {
		URL url = new URL(strUrl);
		if (StringUtils.isEmpty(query)) {
			return url;
		}

		if (StringUtils.isEmpty(url.getQuery())) {
			if (strUrl.endsWith("?")) {
				strUrl = strUrl + query;
			} else {
				strUrl = strUrl + "?" + query;
			}
		} else {
			if (strUrl.endsWith("&")) {
				strUrl = strUrl + query;
			} else {
				strUrl = strUrl + "&" + query;
			}
		}

		return new URL(strUrl);
	}
	
	public static String buildQuery(Map<String, String> params, String charset) throws IOException {
		if (params == null || params.isEmpty()) {
			return null;
		}

		StringBuilder query = new StringBuilder();
		Set<Entry<String, String>> entries = params.entrySet();
		boolean hasParam = false;

		for (Entry<String, String> entry : entries) {
			String name = entry.getKey();
			String value = entry.getValue();
			// 忽略参数名或参数值为空的参数
			if (StringUtils.isNotBlank(name)&&StringUtils.isNotBlank(value)) {
				if (hasParam) {
					query.append("&");
				} else {
					hasParam = true;
				}

				query.append(name).append("=").append(URLEncoder.encode(value, charset));
			}
		}

		return query.toString();
	}
	
	private static class DefaultTrustManager implements X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
	}
	
	private static HttpURLConnection getConnection(URL url, String method, String ctype, Map<String, String> headerMap) throws IOException {
		HttpURLConnection conn = null;
		if ("https".equals(url.getProtocol())) {
			SSLContext ctx = null;
			try {
				ctx = SSLContext.getInstance("TLS");
				ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
			} catch (Exception e) {
				throw new IOException(e);
			}
			HttpsURLConnection connHttps = (HttpsURLConnection) url.openConnection();
			connHttps.setSSLSocketFactory(ctx.getSocketFactory());
			connHttps.setHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;// 默认都认证通过
				}
			});
			conn = connHttps;
		} else {
			conn = (HttpURLConnection) url.openConnection();
		}

		conn.setRequestMethod(method);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("User-Agent", "api-java");
		conn.setRequestProperty("Content-Type", ctype);
		if (headerMap != null) {
			for (Entry<String, String> entry : headerMap.entrySet()) {
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}
		return conn;
	}
	
	
	protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
		String charset = getResponseCharset(conn.getContentType());
		InputStream es = conn.getErrorStream();
		if (es == null) {
			return getStreamAsString(conn.getInputStream(), charset);
		} else {
			String msg = getStreamAsString(es, charset);
			if (StringUtils.isEmpty(msg)) {
				throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
			} else {
				throw new IOException(msg);
			}
		}
	}
	
	private static String getResponseCharset(String ctype) {
		String charset = DEFAULT_CHARSET;
		if (!StringUtils.isEmpty(ctype)) {
			String[] params = ctype.split(";");
			for (String param : params) {
				param = param.trim();
				if (param.startsWith("charset")) {
					String[] pair = param.split("=", 2);
					if (pair.length == 2) {
						if (!StringUtils.isEmpty(pair[1])) {
							charset = pair[1].trim();
						}
					}
					break;
				}
			}
		}
		return charset;
	}
	
	
	private static String getStreamAsString(InputStream stream, String charset) throws IOException {
		try {
			Reader reader = new InputStreamReader(stream, charset);
			StringBuilder response = new StringBuilder();

			final char[] buff = new char[1024];
			int read = 0;
			while ((read = reader.read(buff)) > 0) {
				response.append(buff, 0, read);
			}

			return response.toString();
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}
	
	

}
