package cn.cs.nsfw.info.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Info implements Serializable {
	
	private String infoId;
	private String title;
	private String type;
	private String source;
	private String content;
	private String state;
	private String memo;
	private String creator;
	private Timestamp createTime;
	
	//状态常量
	public static String INFO_STATE_PUBLIC = "1";//发布
	public static String INFO_STATE_STOP = "0";//停用
	
	public Info() {
		
	}
	
	public Info(String infoId, String title, String type, String source,
			String content, String state, String memo, String creator,
			Timestamp createTime) {
		super();
		this.infoId = infoId;
		this.title = title;
		this.type = type;
		this.source = source;
		this.content = content;
		this.state = state;
		this.memo = memo;
		this.creator = creator;
		this.createTime = createTime;
	}

	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
}
