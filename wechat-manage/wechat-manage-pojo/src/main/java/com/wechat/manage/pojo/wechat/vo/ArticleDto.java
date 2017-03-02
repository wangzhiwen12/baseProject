package com.wechat.manage.pojo.wechat.vo;

public class ArticleDto {
	// 图文素材DTO
	private String media_id;// mid
	private String title;// 标题
	private String thumb_media_id;// 图文消息的封面图片素材id（必须是永久mediaID）
	private String author;// 作者
	private String digest;// 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
	private String show_cover_pic;// 是否显示封面，0 为false，即不显示，1 为true，即显示
	private String content;// 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
	private String content_source_url;// 图文消息的原文地址，即点击“阅读原文”后的URL
	private String url;
	private String update_time;
	private String localUrl;
	private String thumb_url;
	private String localContent;

	public String getThumb_url() {
		return thumb_url;
	}

	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}

	public String getLocalUrl() {
		return localUrl;
	}

	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumb_media_id() {
		return thumb_media_id;
	}

	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getShow_cover_pic() {
		return show_cover_pic;
	}

	public void setShow_cover_pic(String show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent_source_url() {
		return content_source_url;
	}

	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getLocalContent() {
		return localContent;
	}

	public void setLocalContent(String localContent) {
		this.localContent = localContent;
	}

	@Override
	public String toString() {
		return "ArticleDto [media_id=" + media_id + ", title=" + title + ", thumb_media_id="
				+ thumb_media_id + ", author=" + author + ", digest=" + digest + ", show_cover_pic="
				+ show_cover_pic + ", content=" + content + ", content_source_url="
				+ content_source_url + ", url=" + url + ", update_time=" + update_time
				+ ", localUrl=" + localUrl + ", thumb_url=" + thumb_url + ", localContent="
				+ localContent + "]";
	}

}
