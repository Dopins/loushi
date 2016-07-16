package com.datacenter;

import java.util.Date;

/** 
 * @author  江镇雄
 * @date 创建时间：2016年4月3日 下午8:29:45 
 * @version 1.0 
 */
public class Comment {

	private int id;
	private Date cDate;
	private int userID;
	private Userinfo userinfo;
	private int replyedID;
	private Userinfo replyedInfo;
	
	private byte type;
	private int pid;
	private String pidImgUrl;
	private String content;
	
	
	public Comment() {
		super();
	}
	public Comment(int id, Date cDate, int userID, int replyedID, byte type, int pid, String content) {
		super();
		this.id = id;
		this.cDate = cDate;
		this.userID = userID;
		this.replyedID = replyedID;
		this.type = type;
		this.pid = pid;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getcDate() {
		return cDate;
	}
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public Userinfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}
	public int getReplyedID() {
		return replyedID;
	}
	public void setReplyedID(int replyedID) {
		this.replyedID = replyedID;
	}
	public Userinfo getReplyedInfo() {
		return replyedInfo;
	}
	public void setReplyedInfo(Userinfo replyedInfo) {
		this.replyedInfo = replyedInfo;
	}
	
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPidImgUrl() {
		return pidImgUrl;
	}
	public void setPidImgUrl(String pidImgUrl) {
		this.pidImgUrl = pidImgUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
