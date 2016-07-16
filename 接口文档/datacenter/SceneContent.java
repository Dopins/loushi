package com.datacenter;

/** 
 * @author  江镇雄
 * @date 创建时间：2016年4月1日 下午9:33:08 
 * @version 1.0 
 */
public class SceneContent {

	private int id;
	private int sort;
	private byte type;
	private String content;
	private int sceneID;
	private Scene scene;
	
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
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSceneID() {
		return sceneID;
	}
	public void setSceneID(int sceneID) {
		this.sceneID = sceneID;
	}
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
}
