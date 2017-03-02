package com.wechat.manage.pojo.wechat.vo;

import java.util.ArrayList;
import java.util.List;

public class ContentDto {
	private List<ArticleDto> news_item = new ArrayList<ArticleDto>();
	private String create_time;
	private String update_time;

	public List<ArticleDto> getNews_item() {
		return news_item;
	}

	public void setNews_item(List<ArticleDto> news_item) {
		this.news_item = news_item;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	@Override
	public String toString() {
		return "ContentDto [news_item=" + news_item + ", create_time=" + create_time
				+ ", update_time=" + update_time + "]";
	}

}
