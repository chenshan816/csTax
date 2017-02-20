package cn.cs.nsfw.complain.service;

import java.util.List;
import java.util.Map;

import cn.cs.core.service.BaseService;
import cn.cs.nsfw.complain.entity.Complain;

public interface ComplainService extends BaseService<Complain> {
	public void autoDeal();
	//获得year年份每个月的投诉数
	public List<Map> getAnnualStatisticData(int year);
}
