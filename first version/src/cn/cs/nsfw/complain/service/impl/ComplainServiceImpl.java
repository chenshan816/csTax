package cn.cs.nsfw.complain.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cs.core.exception.ServiceException;
import cn.cs.core.service.impl.BaseServiceImpl;
import cn.cs.core.util.PageResult;
import cn.cs.core.util.QueryHelper;
import cn.cs.nsfw.complain.dao.ComplainDao;
import cn.cs.nsfw.complain.entity.Complain;
import cn.cs.nsfw.complain.service.ComplainService;

@Service("complainService")
public class ComplainServiceImpl extends BaseServiceImpl<Complain> implements
		ComplainService {

	private ComplainDao complainDao;
	@Resource
	public void setComplainDao(ComplainDao complainDao) {
		setBaseDao(complainDao);
		this.complainDao = complainDao;
	}
	
	//自动受理投诉
	public void autoDeal(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		//1.获取本月之前的待受理的投诉信息
		QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
		queryHelper.addCondition("c.state=?", Complain.COMPLAIN_STATE_UNDONE);
		queryHelper.addCondition("c.compTime < ?", cal.getTime());//本月1号0时0分0秒
		
		List<Complain> list = findObjects(queryHelper);
		//2.改变状态为已失效
		if(list != null && list.size() >0){
			for(Complain comp : list){
				comp.setState(Complain.COMPLAIN_STATE_INVALID);
				update(comp);
			}
		}
	}

	@Override
	public List<Map> getAnnualStatisticData(int year) {
		List<Map> resList = new ArrayList<Map>();
		List<Object[]> list = complainDao.getAnnualStatisticData(year);
		//格式化统计结果
		int curYear = Calendar.getInstance().get(Calendar.YEAR);
		int curMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
		int temMonth = 0;
		Map<String,Object> map = null;
		boolean isCurYear = (curYear == year);
		if(list != null && list.size() >0){
			for(Object[] obj :list){
				temMonth = Integer.valueOf(obj[0]+"");
				map = new HashMap<String, Object>();
				map.put("label", temMonth + "月");
				if(isCurYear){
					//判断是否为当月之后
					if(temMonth > curMonth){
						map.put("value", "");
					}else{
							map.put("value",obj[1] == null?"0":obj[1]);
					}
				}else{
					map.put("value",obj[1] == null?"0":obj[1]);
				}
				resList.add(map);
			}
		}
		return resList;
	}

}
