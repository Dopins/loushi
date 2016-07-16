package com.datacenter;

/** 
 * @author  江镇雄 E-mail: 1046694715@qq.com
 * @date 创建时间：2016年4月1日 下午9:05:40 
 * @version 1.0 
 */
public class DataPackage<T> {

	private Boolean state;
	private String code;
	private T body;
	private String return_info;
	
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	public String getReturn_info() {
		return return_info;
	}
	public void setReturn_info(String return_info) {
		this.return_info = return_info;
	}
	
	
	
}
