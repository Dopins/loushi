package com.datacenter;

/** 
 * @author  江镇雄
 * @date 创建时间：2016年4月1日 下午9:11:33 
 * @version 1.0 
 */
public class User {

	private int id;
	private String mobilePhone;
	private String password;
	private Userinfo userinfo;
	private Boolean isThird;
	private UserThirdParty userThirdParty;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Userinfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}
	public Boolean getIsThird() {
		return isThird;
	}
	public void setIsThird(Boolean isThird) {
		this.isThird = isThird;
	}
	public UserThirdParty getUserThirdParty() {
		return userThirdParty;
	}
	public void setUserThirdParty(UserThirdParty userThirdParty) {
		this.userThirdParty = userThirdParty;
	}
	
}
