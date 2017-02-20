package cn.cs.nsfw.complain.dao.impl;


import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import cn.cs.core.dao.impl.BaseDaoImpl;
import cn.cs.nsfw.complain.dao.ComplainDao;
import cn.cs.nsfw.complain.entity.Complain;

public class ComplainDaoImpl extends BaseDaoImpl<Complain> implements ComplainDao {

	//根据年份获得每个月的投诉数
	@Override
	public List<Object[]> getAnnualStatisticData(int year) {
		String sql = "SELECT imonth,c2 FROM t_month LEFT JOIN "
				+ " (SELECT MONTH(comp_time) c1,COUNT(comp_id) c2 FROM t_complain WHERE YEAR(comp_time)= ? GROUP BY MONTH(comp_time)) t "
				+ " ON imonth = c1 ORDER BY imonth";
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		sqlQuery.setParameter(0, year);
		return sqlQuery.list();
	}
	
	
}
