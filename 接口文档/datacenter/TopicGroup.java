package com.datacenter;

/** 
 * @author  江镇雄
 * @date 创建时间：2016年4月1日 下午9:58:41 
 * @version 1.0 
 */
public class TopicGroup {

	private int id;
	private String name;
	private int topicNum;
	private String imgUrl;
	
	public TopicGroup() {
		super();
	}
	public TopicGroup(int id, String name, int topicNum, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.topicNum = topicNum;
		this.imgUrl = imgUrl;
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
	public int getTopicNum() {
		return topicNum;
	}
	public void setTopicNum(int topicNum) {
		this.topicNum = topicNum;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
