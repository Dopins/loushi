package com.datacenter;

/** 
 * @author  江镇雄
 * @date 创建时间：2016年4月1日 下午9:36:05 
 * @version 1.0 
 */
public class Scene {

	private int id;
	private String name;
	private String label;
	private int groupID;
	private SceneGroup sceneGroup;
	
	private int collectionNum;
	private Boolean collected;
	private int forwordNum;
	private int commentNum;
	private String imgUrl;
	
	public Scene() {
		super();
	}
	
	public Scene(int id, String name, String label, int groupID, Boolean collected, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.label = label;
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public SceneGroup getSceneGroup() {
		return sceneGroup;
	}
	public void setSceneGroup(SceneGroup sceneGroup) {
		this.sceneGroup = sceneGroup;
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
