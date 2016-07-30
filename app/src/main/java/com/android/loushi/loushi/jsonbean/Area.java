package com.android.loushi.loushi.jsonbean;

/**
 * @author  江镇雄
 * @date 创建时间：2016年7月22日 下午3:51:21 
 * @version 1.0 
 */
public class Area{

	private int id;
	private boolean isValid;
	private String province;
	private String city;
	private String district;

	@Override
	public String toString() {
		return "Area{" +
				"id=" + id +
				", isValid=" + isValid +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", district='" + district + '\'' +
				'}';
	}

	public Area(){
		super();
	}
	
	public Area(int id,boolean isValid,String province,String city,String district){
		this.id = id;
		this.isValid = isValid;
		this.province = province;
		this.city = city;
		this.district = district;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

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
	
}
