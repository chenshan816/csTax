package cn.cs.nsfw.reserveItem.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cs.core.exception.ServiceException;
import cn.cs.core.service.impl.BaseServiceImpl;
import cn.cs.core.util.PageResult;
import cn.cs.core.util.QueryHelper;
import cn.cs.nsfw.reserveItem.dao.ReserveItemDao;
import cn.cs.nsfw.reserveItem.entity.ReserveItem;
import cn.cs.nsfw.reserveItem.service.ReserveItemService;

@Service("reserveItemService")
public class ReserveItemServiceImpl extends BaseServiceImpl<ReserveItem>
		implements ReserveItemService {
	private ReserveItemDao reserveItemDao;
	@Resource
	public void setReserveItemDao(ReserveItemDao reserveItemDao) {
		setBaseDao(reserveItemDao);
		this.reserveItemDao = reserveItemDao;
	}

	
}
