package com.wechat.manage.service.wechat.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wechat.manage.pojo.wechat.vo.ArticleDto;
import com.wechat.manage.pojo.wechat.vo.ArticleRe;
import com.wechat.manage.pojo.wechat.vo.ContentDto;
import com.wechat.manage.pojo.wechat.vo.MaterialListDto;
import com.wechat.manage.pojo.wechat.vo.MediaDto;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.wechat.entity.DataTableResult;
import com.wechat.manage.pojo.wechat.entity.Material;
import com.wechat.manage.mapper.wechat.MaterialMapper;
import com.wechat.manage.service.wechat.intf.MaterialLocalService;
import com.wechat.manage.utils.HttpUtils;
import com.wechat.manage.utils.JsonUtil;
import com.wechat.manage.service.util.PropertiesUtils;
import com.wechat.manage.service.util.WechatUtil;

@Service
public class MaterialLocalServiceImpl implements MaterialLocalService {
	@Autowired
	private WechatUtil tokenUtil;
	@Autowired
	private MaterialMapper materialMapper;

	private static Logger logger = Logger.getLogger(MaterialLocalServiceImpl.class);

	public int insert(Material record) {
		return materialMapper.insertSelective(record);
	}

	public List<Material> selectList(Map<String, Object> paramMap) {
		List<Material> materialList = materialMapper.selectPageListByParam(paramMap);
		String ftpPath = PropertiesUtils.findPropertiesKey("ftp.path");
		String ftpAddr = PropertiesUtils.findPropertiesKey("ftp.addr");
		for (Material material : materialList) {
			material.setLocalUrl(
					"http://" + ftpAddr + "/" + ftpPath + "/" + material.getLocalUrl());
		}
		return materialList;
	}

	/**
	 * 图片永久素材上传
	 * 
	 * @Methods Name materialInsert
	 * @Create In 2016年11月24日 By yedong
	 * @param filePath
	 * @param type
	 * @return Media
	 */
	public MediaDto imageMaterialInsert(String filePath, String type, UserBaseInfoDto curUserInfo) {
		logger.info("start-imageMaterialInsert,param ,filePath" + filePath + "type " + type);
		String access_token = tokenUtil.getAccessToken(curUserInfo.getAppId(),
				curUserInfo.getAppSecret());
		String reString = "";
		String[] cmds = { "curl", "-F", "media=@" + filePath,
				"https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="
						+ access_token + "&type=" + type };
		ProcessBuilder pb = new ProcessBuilder(cmds);
		pb.redirectErrorStream(true);
		Process p;
		try {
			p = pb.start();
			BufferedReader br = null;
			String line = null;
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = br.readLine()) != null) {
				reString = line;
			}
			MediaDto media = JsonUtil.getJacksonDTO(reString, MediaDto.class);
			br.close();
			return media;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回url的图片上传
	 * 
	 * @param path
	 * @param param
	 *            buffer/media
	 * @return
	 */
	public String imageInsert(String path, String param, UserBaseInfoDto curUserInfo) {
		logger.info("start-imageInsert,param ,path" + path);
		String reString = null;
		String access_token = tokenUtil.getAccessToken(curUserInfo.getAppId(),
				curUserInfo.getAppSecret());
		String[] cmds = { "curl", "-F", "" + param + "=@" + path,
				"https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + access_token };
		ProcessBuilder pb = new ProcessBuilder(cmds);
		pb.redirectErrorStream(true);
		Process p;
		try {
			p = pb.start();
			BufferedReader br = null;
			String line = null;
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = br.readLine()) != null) {
				reString = line;
			}
			MediaDto media = JsonUtil.getJacksonDTO(reString, MediaDto.class);
			br.close();
			return media.getUrl();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Map<String, Object> articles = new HashMap<String, Object>();
		articles.put("articles",
				"<img name=\"pic\" src=\"http://mmbiz.qpic.cn/mmbiz_jpg/LViaibuiaT88eewEhOiaDvgXS8AIYmabVzZcXuchHa3mwPNA9PNULic1Sgya3wqbj65LLqMpN3hm68edsr9lX8Swoaw/0\" alt=\"http://10.6.100.100/wechat/148300306544406.jpg\"><br>asdfasdfsfasdfasdf<br><br><img name=\"pic\" src=\"http://mmbiz.qpic.cn/mmbiz_jpg/LViaibuiaT88eewEhOiaDvgXS8AIYmabVzZcuJ5F38ibicektickXhTk3XZ0NvBic19PY61pNPia5sD0KltDYB1mtChVq3w/0\" alt=\"http://10.6.100.100/wechat/148300315667505.jpg\">");
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(articles);
		System.out.println(json);
	}

	/**
	 * 图文永久素材上传
	 * 
	 * @Methods Name articleInsert
	 * @Create In 2016年11月28日 By yedong
	 * @param artList
	 * @return String
	 */
	public ArticleRe articleInsert(List<ArticleDto> artList, UserBaseInfoDto curUserInfo) {
		logger.info("start-uploadPhoto,param ,artList" + artList);
		String access_token = tokenUtil.getAccessToken(curUserInfo.getAppId(),
				curUserInfo.getAppSecret());
		String articleUrl = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token="
				+ access_token;
		Map<String, Object> articles = new HashMap<String, Object>();
		articles.put("articles", artList);
		String media = null;
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(articles);
		try {
			media = HttpUtils.doPost(articleUrl, json);
		} catch (Exception e) {
			media = null;
		}
		ArticleRe dto = JsonUtil.getDTO(media, ArticleRe.class);
		if (dto.getErrcode() != null) {
			return null;
		} else {
			return dto;
		}
	}

	/**
	 * 素材分页查询
	 * 
	 * @Methods Name getMaterialList
	 * @Create In 2016年12月26日 By yedong
	 * @param offset
	 * @param count
	 * @param type
	 * @param curUserInfo
	 * @return DataTableResult<MaterialListDto>
	 */
	public DataTableResult<MaterialListDto> getMaterialList(int offset, int count, String type,
			UserBaseInfoDto curUserInfo) {
		DataTableResult<MaterialListDto> page = new DataTableResult<MaterialListDto>();
		List<MaterialListDto> materialList = new ArrayList<MaterialListDto>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", offset);
		paramMap.put("limit", count);
		paramMap.put("storeCode", curUserInfo.getStoreCode());
		paramMap.put("materialType", type);
		List<Material> material = materialMapper.selectPageListByParam(paramMap);
		MaterialListDto media = new MaterialListDto();
		String ftpPath = PropertiesUtils.findPropertiesKey("ftp.path");
		String ftpAddr = PropertiesUtils.findPropertiesKey("ftp.addr");
		for (int i = 0; i < material.size(); i++) {
			material.get(i).setLocalUrl(
					"http://" + ftpAddr + "/" + ftpPath + "/" + material.get(i).getLocalUrl());
			if (i == 0 || i % 5 == 0) {
				media.setMaterial_1(material.get(i));
			} else if (i % 5 == 1) {
				media.setMaterial_2(material.get(i));
			} else if (i % 5 == 2) {
				media.setMaterial_3(material.get(i));
			} else if (i % 5 == 3) {
				media.setMaterial_4(material.get(i));
			} else if (i % 5 == 4) {
				media.setMaterial_5(material.get(i));
			}
			if (media.getMaterial_5() != null || i == material.size() - 1) {
				materialList.add(media);
				media = new MaterialListDto();
			}
		}
		paramMap.put("start", null);
		paramMap.put("limit", null);
		Integer materCount = materialMapper.getCountByParam(paramMap);
		page.setAaData(materialList);
		page.setiTotalRecords(materCount);
		page.setiTotalDisplayRecords(materCount);
		page.setsEcho(page.getsEcho());
		return page;
	}

	/**
	 * 删除永久素材
	 * 
	 * @Methods Name materialDelete
	 * @Create In 2016年11月24日 By yedong
	 * @param mediaId
	 * @return String
	 */
	public String materialDelete(String mediaId, UserBaseInfoDto curUserInfo) {
		logger.info("start-materialDelete,param ,mediaId " + mediaId);
		String access_token = tokenUtil.getAccessToken(curUserInfo.getAppId(),
				curUserInfo.getAppSecret());
		String delMateriaPath = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token="
				+ access_token;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("media_id", mediaId);
		String reString = HttpUtils.doPost(delMateriaPath, JsonUtil.getJSONString(paramMap));
		List<Material> list = materialMapper.selectListByParam(paramMap);
		materialMapper.deleteByPrimaryKey(list.get(0).getSid());
		logger.info(reString);
		return reString;
	}

	/**
	 * 根据mediaId获取素材信息
	 * 
	 * @Methods Name getMaterialByMediaId
	 * @Create In 2016年12月6日 By yedong
	 * @param mediaId
	 * @return Map<String,Object>
	 */
	public ContentDto getMaterialByMediaId(String mediaId, UserBaseInfoDto curUserInfo) {
		String access_token = tokenUtil.getAccessToken(curUserInfo.getAppId(),
				curUserInfo.getAppSecret());
		String getCountPath = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token="
				+ access_token;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("media_id", mediaId);
		String doPost = HttpUtils.doPost(getCountPath, JsonUtil.getJSONString(map));
		ContentDto material = JsonUtil.getDTO(doPost, ContentDto.class);
		return material;
	}

	public List<Material> selectList(Material material) {
		return materialMapper.selectListByParam(material);
	}
}
