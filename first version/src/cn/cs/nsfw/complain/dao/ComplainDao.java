package cn.cs.nsfw.complain.dao;

import java.util.List;
import java.util.Map;

import cn.cs.core.dao.BaseDao;
import cn.cs.nsfw.complain.entity.Complain;

public interface ComplainDao extends BaseDao<Complain> {
	//根据年份获取每个月的投诉数
	public List<Object[]> getAnnualStatisticData(int year);                                                                                                                                   
}
