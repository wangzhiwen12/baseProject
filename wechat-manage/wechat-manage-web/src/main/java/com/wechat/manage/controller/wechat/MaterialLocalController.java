package com.wechat.manage.controller.wechat;

import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.wechat.entity.Material;
import com.wechat.manage.pojo.wechat.vo.*;
import com.wechat.manage.service.util.PropertiesUtils;
import com.wechat.manage.service.wechat.intf.MaterialLocalService;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.JsonUtil;
import com.wechat.manage.vo.DataTableParams;
import com.wechat.manage.vo.DataTableResult;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("materialLocal")
public class MaterialLocalController extends BaseController {
	private static Logger logger = Logger.getLogger(MaterialLocalController.class);

	private FTPClient ftp;
	@Autowired
	private MaterialLocalService materialService;

	@ResponseBody
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public Map<String, Object> imageUpload(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException, IOException {
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("success", "error");
		if (file != null) {// 判断上传的文件是否为空
			String path = null;// 文件路径
			String type = null;// 文件类型
			String fileName = file.getOriginalFilename();// 文件原名称
			System.out.print("上传的文件原名称:" + fileName);
			// 判断文件类型
			type = fileName.indexOf(".") != -1
					? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {// 判断文件类型是否为空
				if (!"PNG".equals(type.toUpperCase()) && !"JPG".equals(type.toUpperCase())) {
					System.out.print("不是我们想要的文件类型,请按要求重新上传");
					paramMap.put("errMsg", "不是我们想要的文件类型,请按要求重新上传");
					return paramMap;
				}
				// 项目在容器中实际发布运行的根路径
				// String realPath =
				// request.getSession().getServletContext().getRealPath("/");
				String realPath = System.getProperty("user.dir") + "/";
				// 自定义的文件名称
				String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
				// 设置存放图片文件的路径
				path = realPath + /* System.getProperty("file.separator")+ */trueFileName;
				System.out.print("存放图片文件的路径:" + path);
				// 转存文件到指定的路径
				file.transferTo(new File(path));
				System.out.print("文件成功上传到指定目录下");
				String url = materialService.imageInsert(path, "image", curUserInfo);
				String localUrl = null;
				if (url != null) {
					try {
						String ftpPath = PropertiesUtils.findPropertiesKey("ftp.path");
						String ftpAddr = PropertiesUtils.findPropertiesKey("ftp.addr");
						String ftpPort = PropertiesUtils.findPropertiesKey("ftp.port");
						String ftpUserName = PropertiesUtils.findPropertiesKey("ftp.username");
						String ftpPwd = PropertiesUtils.findPropertiesKey("ftp.password");
						localUrl = "http://" + ftpAddr + "/" + ftpPath + "/" + trueFileName;
						System.out.print("ftp----------" + ftpPath + ftpAddr + ftpPort + ftpUserName
								+ ftpPwd);
						connect(ftpPath, ftpAddr, Integer.parseInt(ftpPort), ftpUserName, ftpPwd);
						File file1 = new File(path);
						upload(file1);
					} catch (Exception e) {
						e.printStackTrace();
					}
					paramMap.put("success", "success");
					paramMap.put("url", url);
					paramMap.put("localUrl", localUrl);
					return paramMap;
				} else {
					paramMap.put("errorMsg", "已达到上限");
				}
			} else {
				System.out.print("文件类型为空");
				paramMap.put("errorMsg", "文件类型为空");
				return paramMap;
			}
		} else {
			System.out.print("没有找到相对应的文件");
			paramMap.put("errorMsg", "没有找到相对应的文件");
			return paramMap;
		}
		return paramMap;
	}

	// 图片素材上传 1.上传到项目服务器2.上传到微信与ftp服务器3.微信返回上传成功后存入数据库
	@ResponseBody
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public Map<String, Object> fileUpload(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException, IOException {
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("success", "error");
		if (file != null) {// 判断上传的文件是否为空
			String path = null;// 文件路径
			String type = null;// 文件类型
			String fileName = file.getOriginalFilename();// 文件原名称
			String fileType = request.getParameter("fileType");
			System.out.print("上传的文件原名称:" + fileName);
			// 判断文件类型
			type = fileName.indexOf(".") != -1
					? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {// 判断文件类型是否为空
				if (fileType.equals("image")) {
					if (!"PNG".equals(type.toUpperCase()) && !"JPG".equals(type.toUpperCase())) {
						System.out.print("不是我们想要的文件类型,请按要求重新上传");
						paramMap.put("errMsg", "不是我们想要的文件类型,请按要求重新上传");
						return paramMap;
					}
				}
				String realPath = System.getProperty("user.dir") + "\\";
				// 自定义的文件名称
				String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
				// 设置存放图片文件的路径
				path = realPath + trueFileName;
				System.out.print("存放图片文件的路径:" + path);
				// 转存文件到指定的路径
				file.transferTo(new File(path));
				System.out.print("文件成功上传到指定目录下");
				MediaDto material = materialService.imageMaterialInsert(path, fileType,
						curUserInfo);// 上传到微信
				System.out.print("material-----------" + material);
				if (material != null && !material.equals("")) {
					// 上传到ftp服务器
					try {
						String ftpPath = PropertiesUtils.findPropertiesKey("ftp.path");
						String ftpAddr = PropertiesUtils.findPropertiesKey("ftp.addr");
						String ftpPort = PropertiesUtils.findPropertiesKey("ftp.port");
						String ftpUserName = PropertiesUtils.findPropertiesKey("ftp.username");
						String ftpPwd = PropertiesUtils.findPropertiesKey("ftp.password");
						System.out.print("ftp----------" + ftpPath + ftpAddr + ftpPort + ftpUserName
								+ ftpPwd);
						connect(ftpPath, ftpAddr, Integer.parseInt(ftpPort), ftpUserName, ftpPwd);
						File file1 = new File(path);
						upload(file1);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// 存入数据库
					try {
						Material record = new Material();
						record.setMaterialType("image");
						record.setMediaId(material.getMedia_id());
						record.setPicUrl(material.getUrl());
						record.setLocalUrl(trueFileName);
						record.setStoreCode(curUserInfo.getStoreCode());
						record.setImageName(trueFileName);
						materialService.insert(record);
						paramMap.put("success", "success");
						paramMap.put("material", record);
						return paramMap;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return paramMap;
			} else {
				System.out.print("文件类型为空");
				paramMap.put("errMsg", "文件类型为空");
				return paramMap;
			}
		} else {
			System.out.print("没有找到相对应的文件");
			paramMap.put("errMsg", "没有找到相对应的文件");
			return paramMap;
		}
	}

	// 数据格式转化（微信－>本地）
	public List<Material> wechatToLocal(MediaDto media, String type) throws Exception {
		List<Material> materials = new ArrayList<Material>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date upDate = sdf.parse(media.getUpdate_time());
		if (type.equals("image")) {
			Material material = new Material();
			material.setMaterialType("image");
			material.setImageName(media.getName());
			material.setMediaId(media.getMedia_id());
			material.setUpdateTime(upDate);
			material.setPicUrl(media.getUrl());
			materials.add(material);
		} else if (type.equals("news")) {
			List<ArticleDto> news_item = media.getContent().getNews_item();
			for (ArticleDto article : news_item) {
				Material material = new Material();
				material.setMaterialType("news");
				material.setMediaId(media.getMedia_id());
				material.setContent(article.getContent());
				material.setAuthor(article.getAuthor());
				material.setContentSoureUrl(article.getContent_source_url());
				material.setDigest(article.getDigest());
				material.setShowCoverPic(Integer.parseInt(article.getShow_cover_pic()));
				material.setThumbMediaId(article.getThumb_media_id());
				material.setTitle(article.getTitle());
				material.setUpdateTime(upDate);
				material.setLocalUrl(article.getLocalUrl());
				materials.add(material);
			}
		} else {
			System.out.print("暂不提供转换");
		}
		return materials;
	}

	// 数据格式转化（本地－>微信）

	// 图文素材上传1.上传值微信2.获取图片url3.存入数据库
	@ResponseBody
	@RequestMapping(value = "/articleAdd", produces = "text/plain;charset=UTF-8")
	public String articleAdd(ArticleDto article) {
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		List<ArticleDto> artList = new ArrayList<ArticleDto>();
		artList.add(article);
		ArticleRe articleRe = materialService.articleInsert(artList, curUserInfo);
		if (articleRe != null) {
			Material material = new Material();
			material.setLocalContent(article.getLocalContent());
			material.setStoreCode(curUserInfo.getStoreCode());
			material.setAuthor(article.getAuthor());
			material.setContent(article.getContent());
			material.setContentSoureUrl(article.getContent_source_url());
			material.setDigest(article.getDigest());
			material.setLocalUrl(article.getLocalUrl());
			material.setMaterialType("news");
			material.setMediaId(articleRe.getMedia_id());
			material.setShowCoverPic(Integer.parseInt(article.getShow_cover_pic()));
			material.setThumbMediaId(article.getThumb_media_id());
			material.setTitle(article.getTitle());
			ContentDto media = materialService.getMaterialByMediaId(material.getMediaId(),
					curUserInfo);
			if (media.getNews_item() != null && media.getNews_item().size() > 0) {
				String url = media.getNews_item().get(0).getUrl();
				material.setPicUrl(url);
				material.setThumbUrl(media.getNews_item().get(0).getThumb_url());
			}
			materialService.insert(material);
			return "success";
		} else {
			return "error";
		}
	}

	// 图片素材分页查询－类型
	@ResponseBody
	@RequestMapping("/getImageList")
	public DataTableResult<MaterialListDto> getImageList(DataTableParams para) {
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		DataTableResult<MaterialListDto> page = materialService.getMaterialList(
				para.getiDisplayStart(), para.getiDisplayLength(), "image", curUserInfo);
		return page;
	}

	// 图文素材分页查询－类型
	@ResponseBody
	@RequestMapping("/getNewsList")
	public DataTableResult<MaterialListDto> getNewsList(DataTableParams para, String type) {
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		DataTableResult<MaterialListDto> page = materialService.getMaterialList(
				para.getiDisplayStart(), para.getiDisplayLength(), "news", curUserInfo);
		return page;
	}

	// 根据名称查询图片素材
	@ResponseBody
	@RequestMapping("/getMaterialByName")
	public Material getMaterialByName(String imageName) {

		return null;
	}

	// 素材删除
	@ResponseBody
	@RequestMapping("/del")
	public String del(String mediaId) {
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		String materialDelete = materialService.materialDelete(mediaId, curUserInfo);
		ErrorDto error = JsonUtil.getDTO(materialDelete, ErrorDto.class);
		if (error.getErrcode().equals("0")) {
			return "success";
		} else {
			return "error";
		}
	}

	// 瀑布式显示素材
	@ResponseBody
	@RequestMapping("/getMaterialList")
	public List<Material> getMaterialList(String start, String limit, String material_type) {
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", Integer.parseInt(start));
		paramMap.put("limit", Integer.parseInt(limit));
		paramMap.put("materialType", material_type);
		paramMap.put("storeCode", curUserInfo.getStoreCode());
		List<Material> selectList = materialService.selectList(paramMap);
		return selectList;
	}
	// 数据初始化（同步）

	/**
	 * 
	 * @param path
	 *            上传到ftp服务器哪个路径下
	 * @param addr
	 *            地址
	 * @param port
	 *            端口号
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws Exception
	 */
	private boolean connect(String path, String addr, int port, String username, String password)
			throws Exception {
		boolean result = false;
		ftp = new FTPClient();
		int reply;
		ftp.connect(addr, port);
		ftp.login(username, password);
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			return result;
		}
		ftp.changeWorkingDirectory(path);
		result = true;
		return result;
	}

	/**
	 * 
	 * @param file
	 *            上传的文件或文件夹
	 * @throws Exception
	 */
	private void upload(File file) throws Exception {
		if (file.isDirectory()) {
			ftp.makeDirectory(file.getName());
			ftp.changeWorkingDirectory(file.getName());
			String[] files = file.list();
			for (int i = 0; i < files.length; i++) {
				File file1 = new File(file.getPath() + "\\" + files[i]);
				if (file1.isDirectory()) {
					upload(file1);
					ftp.changeToParentDirectory();
				} else {
					File file2 = new File(file.getPath() + "\\" + files[i]);
					FileInputStream input = new FileInputStream(file2);
					ftp.storeFile(file2.getName(), input);
					input.close();
				}
			}
		} else {
			File file2 = new File(file.getPath());
			FileInputStream input = new FileInputStream(file2);
			ftp.storeFile(file2.getName(), input);
			input.close();
		}
	}

	@RequestMapping("imageInsert") // 弹出图片素材添加页面
	public String imageAdd(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/wechat/material/imageInsert";
	}

	@RequestMapping("imgUrlAdd") // 弹出图文添加页面
	public String imgUrlAdd(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/wechat/material/imgUrlAdd";
	}

	@RequestMapping("newsInsert") // 弹出图文添加页面
	public String newsAdd(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/wechat/material/newsInsert";
	}

	@RequestMapping("imageList") // 弹出图片素材添加页面
	public String imageList(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String type = request.getParameter("type");
		model.addAttribute("type", type);
		return Common.BACKGROUND_PATH + "/wechat/material/imageList2";
	}

	@RequestMapping("newsList") // 弹出图文添加页面
	public String newsList(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/wechat/material/newsList2";
	}
}
