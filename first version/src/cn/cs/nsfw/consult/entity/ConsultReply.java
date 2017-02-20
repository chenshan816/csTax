package cn.cs.nsfw.consult.entity;

import java.sql.Timestamp;



/**
 * TConsultReply entity. @author MyEclipse Persistence Tools
 */

public class ConsultReply implements java.io.Serializable {

	// Fields

	private String replyId;
	private Consult consult;
	private String replyer;
	private String replyDept;
	private Timestamp replyTime;
	private String replyContent;

	// Constructors

	/** default constructor */
	public ConsultReply() {
	}

	/** minimal constructor */
	public ConsultReply(Consult consult) {
		this.consult = consult;
	}

	/** full constructor */
	public ConsultReply(Consult consult, String replyer, String replyDept,
			Timestamp replyTime, String replyContent) {
		this.consult = consult;
		this.replyer = replyer;
		this.replyDept = replyDept;
		this.replyTime = replyTime;
		this.replyContent = replyContent;
	}

	// Property accessors

	public String getReplyId() {
		return this.replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public Consult getConsult() {
		return this.consult;
	}

	public void setConsult(Consult consult) {
		this.consult = consult;
	}

	public String getReplyer() {
		return this.replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}

	public String getReplyDept() {
		return this.replyDept;
	}

	public void setReplyDept(String replyDept) {
		this.replyDept = replyDept;
	}

	public Timestamp getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}

	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

}