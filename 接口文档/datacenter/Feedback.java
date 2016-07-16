package com.datacenter;

import java.util.Date;

public class Feedback {
	private Integer id;
	private User User;
	private boolean isValid;
	private Date cdate;
	private Date mdate;
	private String content;
	private String contactWay;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return User;
	}
	public void setUser(User user) {
		User = user;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	
}
