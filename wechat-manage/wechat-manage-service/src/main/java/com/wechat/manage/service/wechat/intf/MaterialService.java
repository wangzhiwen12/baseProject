package com.wechat.manage.service.wechat.intf;

import com.wechat.manage.pojo.wechat.vo.ArticleDto;
import com.wechat.manage.pojo.wechat.vo.MaterialDto;
import com.wechat.manage.pojo.wechat.vo.MediaDto;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;

import java.util.List;
import java.util.Map;

public interface MaterialService {
	public MediaDto materialInsert(String filePath, String type, String title, String introduction,
								   UserBaseInfoDto curUserInfo);

	public Map<String, Object> articleInsert(List<ArticleDto> artList, UserBaseInfoDto curUserInfo);

	public String imageInsert(String path, String param, UserBaseInfoDto curUserInfo);

	public MaterialDto getMaterialList(int start, int limit, String type,
									   UserBaseInfoDto curUserInfo);

	public Map<String, Object> getMaterialByMediaId(String mediaId);

	public String materialDelete(String mediaId, UserBaseInfoDto curUserInfo);

	public int getMaterialCountByType(String type);
}
