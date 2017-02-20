package cn.cs.nsfw.reserveItem.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;

import cn.cs.core.action.BaseAction;
import cn.cs.core.constant.Constant;
import cn.cs.core.util.PageResult;
import cn.cs.core.util.QueryHelper;
import cn.cs.nsfw.reserveItem.entity.ReserveItem;
import cn.cs.nsfw.reserveItem.service.ReserveItemService;
import cn.cs.nsfw.user.entity.User;
import cn.cs.nsfw.user.service.UserService;

/**
 * 预约模块--预约事项管理
 * 1.列表展示
 * 2.跳转新增预约事项
 * 3.保存新增预约事项
 * 4.跳转编辑页面
 * 5.更新预约事项
 * 6.获取处理人数据
 * 7.删除操作
 * 8.全部删除操作
 * @author dell
 *
 */
public class ReserveItemAction extends BaseAction {
	@Resource
	private ReserveItemService reserveItemService;
	@Resource
	private UserService userService;
	
	private ReserveItem item;
	//搜索信息保存
	private String strNo;
	private String strName;
	private String strDept;
	//列表展示
	public String listUI() throws Exception{
		ActionContext.getContext().getContextMap().put("itemStateMap", ReserveItem.RESERVE_ITEM_STATE_MAP);
		QueryHelper queryHelper = new QueryHelper(ReserveItem.class, "r");
		if(item != null){
			if(StringUtils.isNotBlank(item.getItemId())){
				queryHelper.addCondition("r.itemId = ?", item.getItemId());
			}
			if(StringUtils.isNotBlank(item.getResItemName())){
				item.setResItemName(URLDecoder.decode(item.getResItemName(),"utf-8"));
				queryHelper.addCondition("r.resItemName like ?", "%"+item.getResItemName()+"%");
			}
			if(StringUtils.isNotBlank(item.getResItemDept())){
				item.setResItemDept(URLDecoder.decode(item.getResItemDept(),"utf-8"));
				queryHelper.addCondition("r.resItemDept like ?", "%"+item.getResItemDept()+"%");
			}
		}
//		queryHelper.addOrderByProperty("r.state", Constant.ORDERBY_DESC);
		PageResult pageResult1 = reserveItemService.getPageResult(getPageNo(), getPageSize(), queryHelper);
		setPageResult(pageResult1);
		return "listUI";
	}
	//跳转新增预约事项
	public String addUI(){
		strNo = item.getItemId();
		strName = item.getResItemName();
		strDept = item.getResItemDept();
		item = null;
		return "addUI";
	}
	public void getUserJson() throws Exception{
		//获取部门
		String dept = ServletActionContext.getRequest().getParameter("dept");
		QueryHelper queryHelper = new QueryHelper(User.class, "u");
		if(StringUtils.isNotBlank(dept)){
			queryHelper.addCondition("u.dept = ?", dept);
		}
		List<User> userList = userService.findObjects(queryHelper);
		if(userList != null && userList.size() > 0){
			JSONObject json = new JSONObject();
			json.put("msg", "success");
			json.accumulate("userList", userList);
			//输出json
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(json.toString().getBytes("utf-8"));
			outputStream.close();
		}
	}
	//保存新增预约事项
	public String add(){
		if(item != null){
			item.setState(ReserveItem.RESERVE_ITEM_STATE_VALID);
			reserveItemService.save(item);
		}
		return "list";
	}
	//跳转编辑页面
	public String editUI(){
		if(item != null){
			strNo = item.getItemId();
			strName = item.getResItemName();
			strDept = item.getResItemDept();
			String id = item.getResItemId();
			item = reserveItemService.findById(id);
			ActionContext.getContext().getContextMap().put("itemStateMap", ReserveItem.RESERVE_ITEM_STATE_MAP);
		}
		return "editUI";
	}
	//更新预约事项
	public String edit(){
		if(item != null){
			reserveItemService.update(item);
		}
		return "list";
	}
	//删除
	public String delete(){
		if(item != null)
		{
			String id = item.getResItemId();
			reserveItemService.delete(id);
		}
		return "list";
	}
	//删除所有
	public String deleteAll(){
		if(selectedRow != null)
		{
			for(String id:selectedRow){
				reserveItemService.delete(id);
			}
		}
		return "list";
	}
	//get、set方法
	public ReserveItem getItem() {
		return item;
	}

	public void setItem(ReserveItem item) {
		this.item = item;
	}

	public String getStrNo() {
		return strNo;
	}

	public void setStrNo(String strNo) {
		this.strNo = strNo;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public String getStrDept() {
		return strDept;
	}

	public void setStrDept(String strDept) {
		this.strDept = strDept;
	}
	
}
