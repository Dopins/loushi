package com.android.loushi.loushi.jsonbean;
import java.io.Serializable;

/**
 * @author  江镇雄
 * @date 创建时间：2016年7月22日 下午4:08:35 
 * @version 1.0.1
 * 改schoolName为school 
 */
public class UserInfoJson implements Serializable {

	private int id;
    private	String nickname;
	private String mobilePhone;
	private String email;
	private String headImgUrl;
	private School school;
	private boolean sex;
	private int messageCount;
	private int userID;
	/**
	 * email :
	 * headImgUrl : http://119.29.187.58:8080/loushi/headImg/default.jpg
	 * id : 47
	 * messageCount : 0
	 * mobilePhone : 13750065622
	 * nickname :
	 * school : {"area":{"city":"11","district":"11","id":1,"province":"广东省","valid":false},"id":1,"name":"华南理工大学","valid":true}
	 * sex : false
	 * userID : 48
	 */

	private BodyBean body;
	/**
	 * body : {"email":"","headImgUrl":"http://119.29.187.58:8080/loushi/headImg/default.jpg","id":47,"messageCount":0,"mobilePhone":"13750065622","nickname":"","school":{"area":{"city":"11","district":"11","id":1,"province":"广东省","valid":false},"id":1,"name":"华南理工大学","valid":true},"sex":false,"userID":48}
	 * code :
	 * return_info :
	 * state : true
	 */

	private String code;
	private String return_info;
	private boolean state;

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
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
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

	public BodyBean getBody() {
		return body;
	}

	public void setBody(BodyBean body) {
		this.body = body;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReturn_info() {
		return return_info;
	}

	public void setReturn_info(String return_info) {
		this.return_info = return_info;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public static class BodyBean implements  Serializable{
		private String email;
		private String headImgUrl;
		private int id;
		private int messageCount;
		private String mobilePhone;
		private String nickname;
		/**
		 * area : {"city":"11","district":"11","id":1,"province":"广东省","valid":false}
		 * id : 1
		 * name : 华南理工大学
		 * valid : true
		 */

		private SchoolBean school;
		private boolean sex;
		private int userID;

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

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getMessageCount() {
			return messageCount;
		}

		public void setMessageCount(int messageCount) {
			this.messageCount = messageCount;
		}

		public String getMobilePhone() {
			return mobilePhone;
		}

		public void setMobilePhone(String mobilePhone) {
			this.mobilePhone = mobilePhone;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public SchoolBean getSchool() {
			return school;
		}

		public void setSchool(SchoolBean school) {
			this.school = school;
		}

		public boolean isSex() {
			return sex;
		}

		public void setSex(boolean sex) {
			this.sex = sex;
		}

		public int getUserID() {
			return userID;
		}

		public void setUserID(int userID) {
			this.userID = userID;
		}

		public static class SchoolBean implements  Serializable{
			/**
			 * city : 11
			 * district : 11
			 * id : 1
			 * province : 广东省
			 * valid : false
			 */

			private AreaBean area;
			private int id;
			private String name;
			private boolean valid;

			public AreaBean getArea() {
				return area;
			}

			public void setArea(AreaBean area) {
				this.area = area;
			}

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public boolean isValid() {
				return valid;
			}

			public void setValid(boolean valid) {
				this.valid = valid;
			}

			public static class AreaBean implements  Serializable{
				private String city;
				private String district;
				private int id;
				private String province;
				private boolean valid;

				public String getCity() {
					return city;
				}

				public void setCity(String city) {
					this.city = city;
				}

				public String getDistrict() {
					return district;
				}

				public void setDistrict(String district) {
					this.district = district;
				}

				public int getId() {
					return id;
				}

				public void setId(int id) {
					this.id = id;
				}

				public String getProvince() {
					return province;
				}

				public void setProvince(String province) {
					this.province = province;
				}

				public boolean isValid() {
					return valid;
				}

				public void setValid(boolean valid) {
					this.valid = valid;
				}
			}
		}
	}
}
