package cn.cs.core.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.cs.core.dao.BaseDao;
import cn.cs.core.exception.ServiceException;
import cn.cs.core.service.BaseService;
import cn.cs.core.util.PageResult;
import cn.cs.core.util.QueryHelper;

public class BaseServiceImpl<T> implements BaseService<T> {
	
	private BaseDao<T> baseDao;
	
	
	public BaseDao<T> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	@Override
	public T findById(Serializable id) {
		
		return baseDao.findById(id);
	}

	@Override
	public List<T> findObjects() throws ServiceException {
		
		return baseDao.findObjects();
	}

	@Override
	public List<T> findObjects(QueryHelper queryHelper) {
		
		return baseDao.findObjects(queryHelper);
	}

	@Override
	public PageResult getPageResult(int pageNo, int pageSize,QueryHelper queryHelper) {
		return baseDao.getPageResult(pageNo,pageSize,queryHelper);
	}

}
