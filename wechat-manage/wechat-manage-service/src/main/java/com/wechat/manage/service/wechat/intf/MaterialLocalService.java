package com.wechat.manage.service.wechat.intf;

import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.wechat.entity.Material;
import com.wechat.manage.pojo.wechat.vo.*;
import com.wechat.manage.vo.DataTableResult;

import java.util.List;
import java.util.Map;

public interface MaterialLocalService {
	public List<Material> selectList(Material material);

	public String imageInsert(String path, String param, UserBaseInfoDto curUserInfo);

	public List<Material> selectList(Map<String, Object> paramMap);

	public ContentDto getMaterialByMediaId(String mediaId, UserBaseInfoDto curUserInfo);

	/**
	 * 删除永久素材
	 * 
	 * @Methods Name materialDelete
	 * @Create In 2016年11月24日 By yedong
	 * @param mediaId
	 * @return String
	 */
	public String materialDelete(String mediaId, UserBaseInfoDto curUserInfo);

	public int insert(Material record);

	/**
	 * 图片永久素材上传
	 * 
	 * @Methods Name materialInsert
	 * @Create In 2016年11月24日 By yedong
	 * @param filePath
	 * @param type
	 * @return Media
	 */
	public MediaDto imageMaterialInsert(String filePath, String type, UserBaseInfoDto curUserInfo);

	/**
	 * 图文永久素材上传
	 * 
	 * @Methods Name articleInsert
	 * @Create In 2016年11月28日 By yedong
	 * @param artList
	 * @return String
	 */
	public ArticleRe articleInsert(List<ArticleDto> artList, UserBaseInfoDto curUserInfo);

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
															UserBaseInfoDto curUserInfo);
}
