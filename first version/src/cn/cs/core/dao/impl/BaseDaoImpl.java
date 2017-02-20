package cn.cs.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.cs.core.dao.BaseDao;
import cn.cs.core.util.PageResult;
import cn.cs.core.util.QueryHelper;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{

	Class<T> clazz;
	
	//反射获取真实运行的时候的类型
	public BaseDaoImpl(){
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();//BaseDaoImpl<T>
		clazz = (Class<T>) pt.getActualTypeArguments()[0];//<T>中的内容
	}
	
	@Override
	public void save(Object entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void update(Object entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public void delete(Serializable id) {
		this.getHibernateTemplate().delete(findById(id));
	}

	@Override
	public T findById(Serializable id) {
		return this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findObjects() {
		Query query = getSession().createQuery("from "+clazz.getSimpleName());
		return query.list();
	}

	@Override
	public List<T> findObjects(QueryHelper queryHelper) {
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		List<Object> parameters = queryHelper.getParameters();
		if(parameters != null){
			for(int i =0;i<parameters.size();i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}

	@Override
	public PageResult getPageResult(int pageNo, int pageSize,QueryHelper queryHelper) {
		long totalCount = getTotalCount(queryHelper);
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		List<Object> parameters = queryHelper.getParameters();
		if(parameters != null){
			for(int i =0;i<parameters.size();i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		if(pageNo < 1) pageNo = 1;
		query.setFirstResult((pageNo-1)*pageSize);//设置数据起始索引号
		query.setMaxResults(pageSize);
		List<T> items = query.list();
		
		return new PageResult(totalCount,pageNo,pageSize,items);
	}

	@Override
	public long getTotalCount(QueryHelper queryHelper) {
		Query query = getSession().createQuery(queryHelper.getQueryCountHql());
		List<Object> parameters = queryHelper.getParameters();
		if(parameters != null){
			for(int i =0;i<parameters.size();i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		return ((Long)query.uniqueResult());
	}
}
