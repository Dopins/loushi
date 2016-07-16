package com.datacenter;

/** 
 * @author  江镇雄
 * @date 创建时间：2016年4月1日 下午9:31:51 
 * @version 1.0 
 */
public class SceneGroup {

	private int id;
	private String name;
	private int sceneNum;
	
	public SceneGroup() {
		super();
	}
	
	public SceneGroup(int id, String name, int sceneNum) {
		super();
		this.id = id;
		this.name = name;
		this.sceneNum = sceneNum;
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
	public int getSceneNum() {
		return sceneNum;
	}
	public void setSceneNum(int sceneNum) {
		this.sceneNum = sceneNum;
	}
	
}
