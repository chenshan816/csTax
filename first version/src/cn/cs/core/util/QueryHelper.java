package cn.cs.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class QueryHelper {
	//hql语句
	private String fromClause ="";
	private String whereClause ="";
	private String orderByClause ="";
	//hql语句中对应的参数
	private List<Object> parameters;
	
	/**
	 * 构造from子句
	 * @param clazz 实体类
	 * @param alias 实体类的别名
	 */
	public QueryHelper(Class clazz ,String alias){
		if(StringUtils.isNotBlank(alias))
			fromClause ="from " + clazz.getSimpleName() + " " + alias;
		else
			fromClause ="from " + clazz.getSimpleName() + " ";
	}
	/**
	 * 构造where子句
	 * @param condition 查询条件语句;例如： i.title like ?
	 * @param params 查询条件语句中的参数；例如 ：%标题%
	 */
	public void addCondition(String condition,Object... params){
		//添加条件
		if(whereClause.length() > 1){
			whereClause += " and " + condition;
		}else{
			whereClause = " where " + condition;
		}
		if(parameters == null){
			parameters = new ArrayList<Object>();
		}
		if(params != null){
			for(Object param : params){
				parameters.add(param);
			}
		}
	}
	/**
	 * 构造order子句
	 * @param property  排序属性 例如： i.createTime
	 * @param order		排序顺序
	 */
	public void addOrderByProperty(String property,String order){
		if(orderByClause.length() >1){
			orderByClause += "," + property + " " + order; 
		}else{
			orderByClause = " order by " + property + " " + order; 
		}
		
	}
	
	public String getQueryListHql() {
		
		return fromClause + whereClause + orderByClause;
	}
	
	public String getQueryCountHql() {
		
		return "select count(*) "+fromClause + whereClause;
	}
	
	public List<Object> getParameters() {
		return parameters;
	}
}
