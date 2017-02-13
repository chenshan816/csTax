package cn.cs.nsfw.consult.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cs.core.service.impl.BaseServiceImpl;
import cn.cs.nsfw.consult.dao.ConsultDao;
import cn.cs.nsfw.consult.entity.Consult;
import cn.cs.nsfw.consult.service.ConsultService;


@Service("consultService")
public class ConsultServiceImpl extends BaseServiceImpl<Consult> implements
		ConsultService {
	private ConsultDao consultDao;
	@Resource
	public void setConsultDao(ConsultDao consultDao) {
		setBaseDao(consultDao);
		this.consultDao = consultDao;
	}
}
