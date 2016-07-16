package com.datacenter;

/** 
 * @author  江镇雄
 * @date 创建时间：2016年4月1日 下午10:12:35 
 * @version 1.0 
 */
public class Image {

	private int id;
	private int sort;
	private String url;
	private byte type;
	private int pid;
	
	public Image() {
		super();
	}
	public Image(int id, int sort, String url, byte type, int pid) {
		super();
		this.id = id;
		this.sort = sort;
		this.url = url;
		this.type = type;
		this.pid = pid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	
}
