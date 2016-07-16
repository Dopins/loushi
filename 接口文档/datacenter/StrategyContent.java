package com.datacenter;

/** 
 * @author  江镇雄
 * @date 创建时间：2016年4月1日 下午9:43:26 
 * @version 1.0 
 */
public class StrategyContent {

	private int id;
	private int sort;
	private byte type;
	private String content;
	private int strategyID;
	private Strategy strategy;
	
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
	public int getStrategyID() {
		return strategyID;
	}
	public void setStrategyID(int strategyID) {
		this.strategyID = strategyID;
	}
	public Strategy getStrategy() {
		return strategy;
	}
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
}
