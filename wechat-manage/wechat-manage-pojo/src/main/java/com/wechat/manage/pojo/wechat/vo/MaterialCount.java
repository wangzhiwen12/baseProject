package com.wechat.manage.pojo.wechat.vo;

public class MaterialCount {
	private int voice_count;
	private int video_count;
	private int image_count;
	private int news_count;

	public int getVoice_count() {
		return voice_count;
	}

	public void setVoice_count(int voice_count) {
		this.voice_count = voice_count;
	}

	public int getVideo_count() {
		return video_count;
	}

	public void setVideo_count(int video_count) {
		this.video_count = video_count;
	}

	public int getImage_count() {
		return image_count;
	}

	public void setImage_count(int image_count) {
		this.image_count = image_count;
	}

	public int getNews_count() {
		return news_count;
	}

	public void setNews_count(int news_count) {
		this.news_count = news_count;
	}

	@Override
	public String toString() {
		return "MaterialCount [voice_count=" + voice_count + ", video_count=" + video_count
				+ ", image_count=" + image_count + ", news_count=" + news_count + "]";
	}

}
