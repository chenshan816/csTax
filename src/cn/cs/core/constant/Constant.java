package cn.cs.core.constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	
	/*---------------信息分类集合------------------*/
	public static String INFO_TYPE_TZGG="tzgg";
	public static String INFO_TYPE_ZCSD="zcsd";
	public static String INFO_TYPE_NSZD="nszd";
	public static Map<String,String> INFO_TYPE_MAP;
	/*----------登录用户在session中的标识符----------*/
	public static String User = "SYS_USER";
	public static String UserRole = "SYS_USERROLE";
	
	/*---------------系统权限集合------------------*/
	public static String PRIVILEGE_XZGL="xzgl";
	public static String PRIVILEGE_HQFW="hqfw";
	public static String PRIVILEGE_ZXXX="zxxx";
	public static String PRIVILEGE_NSFW="nsfw";
	public static String PRIVILEGE_SPACE="space";
	/*----------------排序常量------------------------*/
	public static String ORDERBY_DESC = "desc"; 
	public static String ORDERBY_ASC = "asc"; 
	
	public static Map<String,String> PRIVILEGE_MAP;
	static{
		//权限
		PRIVILEGE_MAP = new HashMap<String, String>();
		PRIVILEGE_MAP.put(PRIVILEGE_XZGL, "行政管理");
		PRIVILEGE_MAP.put(PRIVILEGE_HQFW, "后勤服务");
		PRIVILEGE_MAP.put(PRIVILEGE_ZXXX, "在线学习");
		PRIVILEGE_MAP.put(PRIVILEGE_NSFW, "纳税服务");
		PRIVILEGE_MAP.put(PRIVILEGE_SPACE, "我的空间");
		//信息分类
		INFO_TYPE_MAP = new HashMap<String,String>();
		INFO_TYPE_MAP.put(INFO_TYPE_TZGG, "通知公告");
		INFO_TYPE_MAP.put(INFO_TYPE_ZCSD, "政策速递");
		INFO_TYPE_MAP.put(INFO_TYPE_NSZD, "纳税指导");
	}
}
