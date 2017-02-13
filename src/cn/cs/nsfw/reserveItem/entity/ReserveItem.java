package cn.cs.nsfw.reserveItem.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Reserveitem entity. @author MyEclipse Persistence Tools
 */

public class ReserveItem implements java.io.Serializable {

	// Fields

	private String resItemId;
	private String itemId;
	private String resItemName;
	private String resItemDept;
	private String resItemToName;
	private String state;

	//状态常量
	public static String RESERVE_ITEM_STATE_VALID = "1";
	public static String RESERVE_ITEM_STATE_INVALID = "0";
	public static Map<String,String> RESERVE_ITEM_STATE_MAP;
	static{
		RESERVE_ITEM_STATE_MAP = new HashMap<String,String>();
		RESERVE_ITEM_STATE_MAP.put(RESERVE_ITEM_STATE_VALID, "有效");
		RESERVE_ITEM_STATE_MAP.put(RESERVE_ITEM_STATE_INVALID, "无效");
	}
	// Constructors

	/** default constructor */
	public ReserveItem() {
	}

	/** full constructor */
	public ReserveItem(String itemId, String resItemName, String resItemDept,
			String resItemToName) {
		this.itemId = itemId;
		this.resItemName = resItemName;
		this.resItemDept = resItemDept;
		this.resItemToName = resItemToName;
	}

	// Property accessors

	public String getResItemId() {
		return this.resItemId;
	}

	public void setResItemId(String resItemId) {
		this.resItemId = resItemId;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getResItemName() {
		return this.resItemName;
	}

	public void setResItemName(String resItemName) {
		this.resItemName = resItemName;
	}

	public String getResItemDept() {
		return this.resItemDept;
	}

	public void setResItemDept(String resItemDept) {
		this.resItemDept = resItemDept;
	}

	public String getResItemToName() {
		return this.resItemToName;
	}

	public void setResItemToName(String resItemToName) {
		this.resItemToName = resItemToName;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}