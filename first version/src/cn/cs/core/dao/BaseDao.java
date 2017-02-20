package cn.cs.core.dao;

import java.io.Serializable;
import java.util.List;

import cn.cs.core.util.PageResult;
import cn.cs.core.util.QueryHelper;

/**
 * 所有DAO的父类接口
 * @author dell
 *
 * @param <T>
 */
public interface BaseDao <T>{
	//新增
	public void save(T entity);
	//更新
	public void update(T entity);
	//根据id删除
	public void delete(Serializable id);
	//根据id查找
	public T findById(Serializable id);
	//查找列表
	public List<T> findObjects();
	//根据条件查询
	public List<T> findObjects(QueryHelper queryHelper);
	//分页查询
	public PageResult getPageResult(int pageNo,int pageSize,QueryHelper queryHelper);
	//获得总记录数
	public long getTotalCount(QueryHelper queryHelper);
}
