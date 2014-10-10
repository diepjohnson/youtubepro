package com.ecode.YouTubePro.model;

public class ChannelModel {
	String urlThumb;
	int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getUrlThumb() {
		return urlThumb;
	}
	public void setUrlThumb(String urlThumb) {
		this.urlThumb = urlThumb;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	String title;
	String id;
}
