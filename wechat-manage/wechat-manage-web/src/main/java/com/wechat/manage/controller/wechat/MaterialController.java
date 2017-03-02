package com.wechat.manage.controller.wechat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.wechat.vo.ArticleDto;
import com.wechat.manage.pojo.wechat.vo.ArticleListDto;
import com.wechat.manage.pojo.wechat.vo.ErrorDto;
import com.wechat.manage.pojo.wechat.vo.MaterialDto;
import com.wechat.manage.pojo.wechat.vo.MediaDto;
import com.wechat.manage.pojo.wechat.vo.MediaListDto;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.wechat.entity.DataTableParams;
import com.wechat.manage.pojo.wechat.entity.DataTableResult;
import com.wechat.manage.pojo.wechat.entity.MediaLocalUrl;
import com.wechat.manage.mapper.wechat.MediaLocalUrlMapper;
import com.wechat.manage.service.wechat.intf.MaterialService;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.JsonUtil;

@Controller
@RequestMapping(value = "/material")
public class MaterialController extends BaseController {
	@Autowired
	private MaterialService materialService;

	@Autowired
	private UploadController upload;

	@Autowired
	private MediaLocalUrlMapper localUrlMapper;

	@ResponseBody
	@RequestMapping(value = "/getMaterial", method = RequestMethod.POST) // 根据类型获取素材列表
	public Map<String, Object> getMaterial(@RequestBody Map<String, Object> paramMap) {
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		String offset = (String) paramMap.get("offset");
		String count = (String) paramMap.get("count");
		String eventType = (String) paramMap.get("eventType");
		MaterialDto materialList = materialService.getMaterialList(Integer.parseInt(offset),
				Integer.parseInt(count), eventType, curUserInfo);
		if (materialList != null && materialList.getItem() != null
				&& materialList.getItem().size() > 0) {
			paramMap.put("materialList", materialList.getItem());
			paramMap.put("success", "true");
		} else {
			paramMap.put("success", "false");
		}
		return paramMap;
	}

	@RequestMapping("list") // 素材管理初始化列表
	public String listUI(Model model) throws Exception {
		// UserBaseInfodata-src="attr:{src:'+msgData.picUrl+'Dto curUserInfo =
		// getCurUserInfo();
		// MaterialDto materialList = materialService.getMaterialList(0, 10,
		// "image", curUserInfo);
		// List<MediaDto> item = materialList.getItem();
		// for (MediaDto mediaDto : item) {
		// String url = mediaDto.getUrl();
		// System.out.println(url);
		// String[] split = url.split("\\?");
		// mediaDto.setUrl(split[0]);
		// }
		// model.addAttribute("materialList", item);
		return Common.BACKGROUND_PATH + "/wechat/material/list";
		// return Common.BACKGROUND_PATH + "/wechat/material/materialList";
	}

	/**
	 * 重构－图片素材获取
	 * 
	 * @Methods Name getImageMaterial
	 * @Create In 2016年12月20日 By yedong
	 * @param para
	 * @return DataTableResult<ArticleListDto>
	 */
	@ResponseBody
	@RequestMapping("/getImageMaterial")
	public DataTableResult<MediaListDto> getImageMaterial(DataTableParams para) {
		DataTableResult<MediaListDto> page = new DataTableResult<MediaListDto>();
		List<MediaListDto> articleList = new ArrayList<MediaListDto>();
		UserBaseInfoDto curUser = getCurUserInfo();
		MaterialDto materialList = materialService.getMaterialList(para.getiDisplayStart(),
				para.getiDisplayLength(), "image", curUser);
		List<MediaDto> itemlist = materialList.getItem();
		for (MediaDto mediaDto : itemlist) {
			MediaLocalUrl url = new MediaLocalUrl();
			url.setMediaId(mediaDto.getMedia_id());
			url.setStoreCode(curUser.getStoreCode());
			List<MediaLocalUrl> urlList = localUrlMapper.selectListByParam(url);
			if (urlList != null && urlList.size() > 0) {
				mediaDto.setLocalUrl("http://10.6.100.100/wechat/" + urlList.get(0).getLocalUrl());
			}
		}
		MediaListDto media = new MediaListDto();
		for (int i = 0; i < itemlist.size(); i++) {
			if (i == 0 || i % 5 == 0) {
				media.setMedia_1(itemlist.get(i));
			} else if (i % 5 == 1) {
				media.setMedia_2(itemlist.get(i));
			} else if (i % 5 == 2) {
				media.setMedia_3(itemlist.get(i));
			} else if (i % 5 == 3) {
				media.setMedia_4(itemlist.get(i));
			} else if (i % 5 == 4) {
				media.setMedia_5(itemlist.get(i));
			}
			if (media.getMedia_5() != null || i == itemlist.size() - 1) {
				articleList.add(media);
				media = new MediaListDto();
			}
		}
		page.setAaData(articleList);
		page.setiTotalRecords(materialList.getTotal_count());
		page.setiTotalDisplayRecords(materialList.getTotal_count());
		page.setsEcho(para.getsEcho());
		return page;
	}

	/**
	 * 重构－图文素材获取
	 * 
	 * @Methods Name getNewsMaterial
	 * @Create In 2016年12月20日 By yedong
	 * @param para
	 * @return DataTableResult<ArticleListDto>
	 */
	@ResponseBody
	@RequestMapping("/getNewsMaterial")
	public DataTableResult<ArticleListDto> getNewsMaterial(DataTableParams para) {
		DataTableResult<ArticleListDto> page = new DataTableResult<ArticleListDto>();
		List<ArticleListDto> articleList = new ArrayList<ArticleListDto>();
		UserBaseInfoDto curUser = getCurUserInfo();
		MaterialDto materialList = materialService.getMaterialList(para.getiDisplayStart(),
				para.getiDisplayLength(), "news", curUser);
		List<MediaDto> itemlist = materialList.getItem();// 取mid
		ArticleListDto article = new ArticleListDto();

		for (int i = 0; i < itemlist.size(); i++) {
			List<ArticleDto> content = itemlist.get(i).getContent().getNews_item();
			for (ArticleDto articlDto : content) {
				MediaLocalUrl url = new MediaLocalUrl();
				url.setMediaId(articlDto.getThumb_media_id());
				url.setStoreCode(curUser.getStoreCode());
				List<MediaLocalUrl> urlList = localUrlMapper.selectListByParam(url);
				if (urlList != null && urlList.size() > 0) {
					articlDto.setLocalUrl(
							"http://10.6.100.100/wechat/" + urlList.get(0).getLocalUrl());
				}
			}
			content.get(0).setMedia_id(itemlist.get(i).getMedia_id());
			content.get(0).setUpdate_time(itemlist.get(i).getUpdate_time());
			content.get(0).setContent(content.get(0).getContent().substring(0, 20));
			if (i == 0 || i % 5 == 0) {
				article.setArticle_1(content.get(0));
			} else if (i % 5 == 1) {
				article.setArticle_2(content.get(0));
			} else if (i % 5 == 2) {
				article.setArticle_3(content.get(0));
			} else if (i % 5 == 3) {
				article.setArticle_4(content.get(0));
			} else if (i % 5 == 4) {
				article.setArticle_5(content.get(0));
			}
			if (article.getArticle_5() != null || i == itemlist.size() - 1) {
				articleList.add(article);
				article = new ArticleListDto();
			}
		}
		page.setAaData(articleList);
		page.setiTotalRecords(materialList.getTotal_count());
		page.setiTotalDisplayRecords(materialList.getTotal_count());
		page.setsEcho(para.getsEcho());
		return page;
	}

	@RequestMapping("imageList") // 显示图片素材列表
	public String imageList(Model model) throws Exception {
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		MaterialDto materialList = materialService.getMaterialList(0, 100, "image", curUserInfo);
		List<MediaDto> item = materialList.getItem();
		for (MediaDto mediaDto : item) {
			MediaLocalUrl url = new MediaLocalUrl();
			url.setMediaId(mediaDto.getMedia_id());
			url.setStoreCode(curUserInfo.getStoreCode());
			List<MediaLocalUrl> urlList = localUrlMapper.selectListByParam(url);
			if (urlList != null && urlList.size() > 0) {
				mediaDto.setLocalUrl("http://10.6.100.100/wechat/" + urlList.get(0).getLocalUrl());
			}
		}
		model.addAttribute("materialList", item);
		return Common.BACKGROUND_PATH + "/wechat/material/imageList";
	}

	@RequestMapping("imageAdd") // 弹出图片素材添加页面
	public String imageAdd(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/wechat/material/imageAdd";
	}

	@RequestMapping("newsAdd") // 弹出图文添加页面
	public String newsAdd(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/wechat/material/newsAdd";
	}

	@ResponseBody
	@RequestMapping("imageAdd/upload") // 图片素材上传
	public Map<String, Object> upload(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			return upload.fileUpload(file, request, response);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 图文上传
	 * 
	 * @Methods Name articleAdd
	 * @Create In 2016年12月21日 By yedong
	 * @param article
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping("/articleAdd")
	public Map<String, Object> articleAdd(ArticleDto article) {
		UserBaseInfoDto curUserInfo = getCurUserInfo();
		List<ArticleDto> artList = new ArrayList<ArticleDto>();
		artList.add(article);
		Map<String, Object> articleInsert = materialService.articleInsert(artList, curUserInfo);
		return articleInsert;
	}

	@ResponseBody
	@RequestMapping("/del") // 根据mid删除素材
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

}
