package com.datacenter;

import java.util.List;

/** 
 * @author  江镇雄
 * @date 创建时间：2016年4月1日 下午10:07:47 
 * @version 1.0 
 */
public class Goods {

	private int id;
	private String name;
	private double price;
	private String introduction;
	private String url;//导流链接
	private List<Image> images; 

	private int collectionNum;
	private Boolean collected;
	private int forwordNum;
	private int commentNum;
	
	public Goods() {
		super();
	}
	public Goods(int id, String name, double price, String introduction, String url, Boolean collected) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.introduction = introduction;
		this.url = url;
		this.collected = collected;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
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
	
}
