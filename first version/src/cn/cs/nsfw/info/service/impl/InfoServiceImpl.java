package cn.cs.nsfw.info.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.cs.core.service.impl.BaseServiceImpl;
import cn.cs.nsfw.info.entity.Info;
import cn.cs.nsfw.info.service.InfoService;
import cn.cs.nsfw.info.dao.InfoDao;

@Service("infoService")
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService {

		//获取dao层
		private InfoDao infoDao;
		@Resource
		public void setInfoDao(InfoDao infoDao) {
			super.setBaseDao(infoDao);
			this.infoDao = infoDao;
		}
}
