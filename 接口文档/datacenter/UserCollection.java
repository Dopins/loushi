package com.datacenter;

/** 
 * @author  江镇雄
 * @date 创建时间：2016年4月3日 下午8:23:38 
 * @version 1.0 
 */
public class UserCollection {

	private int id;
	private byte type;
	private int pid;
	private int userID;
	
	private Scene scene;
	private Topic topic;
	private Strategy strategy;
	private Goods goods;
	
	public UserCollection() {
		// TODO Auto-generated constructor stub
	}
	
	public UserCollection(int id, byte type, int pid, int userID) {
		super();
		this.id = id;
		this.type = type;
		this.pid = pid;
		this.userID = userID;
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public Strategy getStrategy() {
		return strategy;
	}
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
}
