package com.packtpub.springmvc.pojo;

import java.util.Date;

public class NewEventPojo {
	private String description;
	private Date exdate;
	private String picture;
	private int resid;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getExdate() {
		return exdate;
	}
	public void setExdate(Date exdate) {
		this.exdate = exdate;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getResid() {
		return resid;
	}
	public void setResid(int resid) {
		this.resid = resid;
	}
	
	
}
