package com.datacenter;

import java.util.Date;

/** 
 * @author  江镇雄
 * @date 创建时间：2016年4月3日 下午8:37:56 
 * @version 1.0 
 */
public class UserMessage {

	private int id;
	private Date cDate;
	private int commentID;
	private Comment comment;
	
	
	public UserMessage() {
		super();
	}
	public UserMessage(int id, Date cDate, int commentID) {
		super();
		this.id = id;
		this.cDate = cDate;
		this.commentID = commentID;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getcDate() {
		return cDate;
	}
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
}
