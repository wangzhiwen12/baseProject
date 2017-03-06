package com.wechat.manage.service.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.Charsets;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * ftp工具类
 * 
 * @author ghost
 *
 */
public class FTPUtils {
	private static Logger logger = Logger.getLogger(FTPUtils.class);
	private static FTPUtils ftp;
	private static String filePath = "/wshop/page/";
	private static String host = "10.6.100.100";
	private static int port = 21;
	private static String username = "images";
	private static String password = "123456";

	protected FTPUtils() {
	}

	public static FTPUtils getInstance() {
		if (ftp == null) {
			ftp = new FTPUtils();
		}
		return ftp;
	}

	/**
	 * 获取ftp的客户端
	 * 
	 * @return
	 */
	private FTPClient getClient(String host, int port, String username, String password) throws Exception {
		FTPClient ftpClient = new FTPClient();
		ftpClient.setCharset(Charsets.UTF_8);
		ftpClient.connect(host, port);
		ftpClient.login(username, password);
		// 设置上传文件类型为二进制
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		return ftpClient;
	}

	/**
	 * 退出登陆，关闭链接
	 * 
	 * @param client
	 *            FTP客户端实例
	 */
	private void logout(FTPClient client) {
		if (client != null) {
			try {
				client.logout();
			} catch (IOException e) {
				logger.error(e.toString(), e);
			} finally {
				if (client.isConnected()) {
					try {
						client.disconnect();
					} catch (IOException e) {
						logger.error(e.toString(), e);
					}
				}
			}
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param filePath
	 *            上传的文件路径
	 * @param fileName
	 *            上传的文件名称
	 * @param input
	 *            输入流
	 * @return 上传结果
	 */
	public boolean uploadFile(String host, int port, String username, String password, String filePath, String fileName,
			InputStream input) {
		boolean result = false;
		FTPClient ftpClient = null;
		try {
			ftpClient = getClient(host, port, username, password);
			ftpClient.enterLocalPassiveMode();
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return result;
			}
			// 切换到上传目录
			ftpClient.changeWorkingDirectory(filePath);
			// 上传文件
			if (!ftpClient.storeFile(fileName, input)) {
				return result;
			}
			input.close();
			result = true;
		} catch (Exception e) {
			logger.error(e.toString(), e);
			result = false;
		} finally {
			logout(ftpClient);
		}
		return result;
	}

	/**
	 * 删除单个文件
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean delete(String filePath, String host, int port, String username, String password) {
		boolean result = false;
		FTPClient client = null;
		try {
			client = getClient(host, port, username, password);
			int reply = client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				client.disconnect();
				return result;
			}
			client.deleteFile(filePath);
			result = true;
		} catch (Exception e) {
			logger.error(e.toString(), e);
			result = false;
		} finally {
			logout(client);
		}
		return result;
	}

	/**
	 * 读取文件
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public String readFile(String fileName) throws Exception {
		InputStream ins = null;
		StringBuilder builder = null;
		FTPClient ftpClient = getClient(host, port, username, password);
		 ftpClient.enterLocalPassiveMode();
		try {
			// 从服务器上读取指定的文件
			ins = ftpClient.retrieveFileStream(filePath+fileName);
			if (ins != null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
				String line;
				builder = new StringBuilder(150);
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				reader.close();
				if (ins != null) {
					ins.close();
				}
			}
			// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
			ftpClient.getReply();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	/**
	 * 复制文件
	 * @param oldNme
	 * @param newName
	 * @return
	 * @throws Exception
	 */
	public boolean copyFile(String oldNme,String newName) throws Exception{
		boolean copyFalg=false;
		FTPClient	ftpClient=getClient(host, port, username, password);
		 ftpClient.enterLocalPassiveMode();
		InputStream ins = null;
		ins = ftpClient.retrieveFileStream(filePath+oldNme);
        // 复制文件时掉用了retrieveFileStream方法
        // 调用完之后必须调用completePendingCommand释放,否则FTP会断开连接
        if (!ftpClient.completePendingCommand()) {
            copyFalg = false;
            return copyFalg;
        }
        // 如果读取的文件流不为空则复制文件
        if (ins != null) {
            copyFalg = ftpClient.storeFile(filePath+newName, ins);
            // 关闭文件流
            ins.close();
            if (!copyFalg) {
                return copyFalg;
            }
        }
		return copyFalg;
	}

	public static void main(String[] args) {
		// 上传
//		 InputStream input = new ByteArrayInputStream("<input value='123'/>".getBytes());
//		 FTPUtils util = FTPUtils.getInstance();
//		 boolean result = util.uploadFile(host, port, username, password,
//		 filePath, "5.html", input);
//		 System.out.println(result);
		// 删除
		// FTPUtils util = FTPUtils.getInstance();
		// boolean result = util.delete("/wshop/page/abc.html",
		// "10.6.100.100",21,"images","123456");
		// System.out.println(result);
		// 查询
//		FTPUtils util = FTPUtils.getInstance();
//		String result = null;
//		try {
//			result = util.readFile("test.html");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(result);
		//复制
		FTPUtils util = FTPUtils.getInstance();
		boolean result = false;
		
		try {
			result = util.copyFile("1.html", "6.html");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
	}
}
