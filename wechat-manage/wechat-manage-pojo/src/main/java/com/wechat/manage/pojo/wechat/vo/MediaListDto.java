package com.wechat.manage.pojo.wechat.vo;

public class MediaListDto {
	private MediaDto media_1;
	private MediaDto media_2;
	private MediaDto media_3;
	private MediaDto media_4;
	private MediaDto media_5;

	public MediaDto getMedia_1() {
		return media_1;
	}

	public void setMedia_1(MediaDto media_1) {
		this.media_1 = media_1;
	}

	public MediaDto getMedia_2() {
		return media_2;
	}

	public void setMedia_2(MediaDto media_2) {
		this.media_2 = media_2;
	}

	public MediaDto getMedia_3() {
		return media_3;
	}

	public void setMedia_3(MediaDto media_3) {
		this.media_3 = media_3;
	}

	public MediaDto getMedia_4() {
		return media_4;
	}

	public void setMedia_4(MediaDto media_4) {
		this.media_4 = media_4;
	}

	public MediaDto getMedia_5() {
		return media_5;
	}

	public void setMedia_5(MediaDto media_5) {
		this.media_5 = media_5;
	}

	@Override
	public String toString() {
		return "ArticleListDto [media_1=" + media_1 + ", media_2=" + media_2 + ", media_3="
				+ media_3 + ", media_4=" + media_4 + ", media_5=" + media_5 + "]";
	}

}
