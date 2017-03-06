package com.wechat.manage.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Admin
 *
 */
public class RequestAndResponse {
	
	private static Logger log = LoggerFactory.getLogger(RequestAndResponse.class);

	/**
	 * 获取流数据
	 * 
	 * @param request
	 *            请求对象
	 * @return
	 * @throws Exception
	 */
	public static String getData(HttpServletRequest request) throws Exception {
		InputStream stream = null;
		try {
			stream = request.getInputStream();
			Reader reader = new InputStreamReader(request.getInputStream(),
					"UTF-8");
			StringBuilder response = new StringBuilder();
			final char[] buff = new char[1024];
			int read = 0;
			while ((read = reader.read(buff)) > 0) {
				response.append(buff, 0, read);
			}

			return response.toString();
		} catch (Exception e) {
			log.error(e.toString(), e);
			throw new Exception("数据获取失败");
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					log.error(e.toString(), e);
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 回写数据
	 * @param response
	 * @param resultString   回写的数据
	 */
	public static void responseWrite(HttpServletResponse response, String resultString) {
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			writer.write(resultString);
		} catch (Exception e) {
			log.error(e.toString(), e);
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}
	
	public static void mqResponseWrite(HttpServletResponse response, String resultString) {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(resultString);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.toString(), e);
		}
	}
}
