package com.android.loushi.loushi.jsonbean;

/** 
 * @author  江镇雄
 * @date 创建时间：2016年7月22日 下午4:03:10 
 * @version 1.0 
 */
public class School {

	private int id;
	private boolean isValid;
	private String name;
	private Area area;

	@Override
	public String toString() {
		return "School{" +
				"id=" + id +
				", isValid=" + isValid +
				", name='" + name + '\'' +
				", area=" + area +
				'}';
	}

	public School() {
		super();
	}
	public School(int id, boolean isValid, String name, Area area) {
		super();
		this.id = id;
		this.isValid = isValid;
		this.name = name;
		this.area = area;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	
}
