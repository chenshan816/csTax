package cn.cs.nsfw.consult.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * TConsult entity. @author MyEclipse Persistence Tools
 */

public class Consult implements java.io.Serializable {

	// Fields

	private String consId;
	private ConsultReply consultReply;
	private String consTitle;
	private String consContent;
	private Timestamp consTime;
	private String state;
	private String consDept;
	private String consName;
	private String consMobile;

	//状态
	public static String CONSULT_STATE_NEW="0";
	public static String CONSULT_STATE_DONE="1";
	public static String CONSULT_STATE_REJECT="2";
	public static Map<String,String> CONSULT_STATE_MAP;

	static{
		CONSULT_STATE_MAP = new HashMap<String,String>();
		CONSULT_STATE_MAP.put(CONSULT_STATE_NEW, "未处理");
		CONSULT_STATE_MAP.put(CONSULT_STATE_DONE, "已处理");
		CONSULT_STATE_MAP.put(CONSULT_STATE_REJECT, "不处理");
	}
	// Constructors

	/** default constructor */
	public Consult() {
	}

	/** full constructor */
	public Consult(ConsultReply consultReply, String consTitle,
			String consContent, Timestamp consTime, String state,
			String consDept, String consName, String consMobile) {
		this.consultReply = consultReply;
		this.consTitle = consTitle;
		this.consContent = consContent;
		this.consTime = consTime;
		this.state = state;
		this.consDept = consDept;
		this.consName = consName;
		this.consMobile = consMobile;
	}

	// Property accessors

	public String getConsId() {
		return this.consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public ConsultReply getConsultReply() {
		return this.consultReply;
	}

	public void setConsultReply(ConsultReply consultReply) {
		this.consultReply = consultReply;
	}

	public String getConsTitle() {
		return this.consTitle;
	}

	public void setConsTitle(String consTitle) {
		this.consTitle = consTitle;
	}

	public String getConsContent() {
		return this.consContent;
	}

	public void setConsContent(String consContent) {
		this.consContent = consContent;
	}

	public Timestamp getConsTime() {
		return this.consTime;
	}

	public void setConsTime(Timestamp consTime) {
		this.consTime = consTime;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getConsDept() {
		return this.consDept;
	}

	public void setConsDept(String consDept) {
		this.consDept = consDept;
	}

	public String getConsName() {
		return this.consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getConsMobile() {
		return this.consMobile;
	}

	public void setConsMobile(String consMobile) {
		this.consMobile = consMobile;
	}
}