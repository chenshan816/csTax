package cn.cs.core.service;

import java.io.Serializable;
import java.util.List;

import cn.cs.core.exception.ServiceException;
import cn.cs.core.util.PageResult;
import cn.cs.core.util.QueryHelper;

public interface BaseService<T> {
	//新增
	public void save(T entity);
	//更新
	public void update(T entity);
	//根据id删除
	public void delete(Serializable id);
	//根据id查找
	public T findById(Serializable id);
	//查找列表
	public List<T> findObjects() throws ServiceException;
	//根据条件查询
	public List<T> findObjects(QueryHelper queryHelper);
	//分页查询
	public PageResult getPageResult(int pageNo,int pageSize,QueryHelper queryHelper);
}
