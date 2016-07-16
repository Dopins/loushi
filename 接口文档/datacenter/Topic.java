package com.datacenter;

/** 
 * @author  江镇雄
 * @date 创建时间：2016年4月1日 下午9:59:03 
 * @version 1.0 
 */
public class Topic {

	private int id;
	private String name;
	private int groupID;
	private TopicGroup topicGroup;

	private int collectionNum;
	private Boolean collected;
	private int forwordNum;
	private int commentNum;
	private String imgUrl;
	
	public Topic() {
		super();
	}
	public Topic(int id, String name, int groupID, Boolean collected, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.groupID = groupID;
		this.collected = collected;
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
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public TopicGroup getTopicGroup() {
		return topicGroup;
	}
	public void setTopicGroup(TopicGroup topicGroup) {
		this.topicGroup = topicGroup;
	}
	
	public int getCollectionNum() {
		return collectionNum;
	}
	public void setCollectionNum(int collectionNum) {
		this.collectionNum = collectionNum;
	}
	public Boolean getCollected() {
		return collected;
	}
	public void setCollected(Boolean collected) {
		this.collected = collected;
	}
	public int getForwordNum() {
		return forwordNum;
	}
	public void setForwordNum(int forwordNum) {
		this.forwordNum = forwordNum;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
