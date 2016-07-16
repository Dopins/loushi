package com.datacenter;

/** 
 * @author  江镇雄
 * @date 创建时间：2016年4月1日 下午9:05:34 
 * @version 1.0 
 */
public class Userinfo {

	private int id;
    private	String nickname;
	private String mobilePhone;
	private String email;
	private String headImgUrl;
	private String schoolName;
	private boolean sex;
	private int messageCount;
	private int userID;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public int getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
}
