package com.wechat.manage.mapper.wshopnav;

import java.util.List;

import com.wechat.manage.pojo.wshopnav.entity.TPageEdit;

public interface TPageEditMapper {
	 
	void  insertTpageEdit(TPageEdit tpEdit);

	List<TPageEdit> selectById(String id);

	void update(TPageEdit pageEdit);

	void deleteWpage(String id);
}
