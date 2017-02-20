package cn.cs.nsfw.role.action;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionContext;

import cn.cs.core.action.BaseAction;
import cn.cs.core.constant.Constant;
import cn.cs.core.exception.SysException;
import cn.cs.core.util.PageResult;
import cn.cs.core.util.QueryHelper;
import cn.cs.nsfw.info.entity.Info;
import cn.cs.nsfw.role.entity.Role;
import cn.cs.nsfw.role.entity.RolePrivilege;
import cn.cs.nsfw.role.entity.RolePrivilegeId;
import cn.cs.nsfw.role.service.RoleService;
/**
 * 角色管理界面功能
 * 列表界面
 * 跳转到新增页面
 * 保存新增
 * 跳转到编辑页面
 * 保存编辑
 * 删除操作
 * 批量删除
 *
 */
public class RoleAction extends BaseAction{
	@Resource
	private RoleService roleService;
	private List<Role> roleList;
	private Role role;
	//权限接收
	private String[] privilegeIds;
	//搜索功能
	private String strName;
	
	
	//列表界面
	public String listUI() throws Exception{
		//从业务层取出所有用户
		ActionContext.getContext().getContextMap().put("privilegeMap",Constant.PRIVILEGE_MAP);
		QueryHelper queryHelper = new QueryHelper(Role.class, "r");
		if(role !=null){
			if(StringUtils.isNotBlank(role.getName())){
				role.setName(URLDecoder.decode(role.getName(),"utf-8"));
				queryHelper.addCondition("r.name like ?", "%"+role.getName()+"%");
			}
		}
		setPageResult(roleService.getPageResult(getPageNo(),getPageSize(),queryHelper));
		return "listUI";
	}
	//跳转到新增页面 
	public String addUI(){
		//加载权限集合              
		ActionContext.getContext().getContextMap().put("privilegeMap",Constant.PRIVILEGE_MAP);
		//解决信息被覆盖问题
		strName = role.getName();
		role = null;
		return "addUI";
	}
	//保存新增                                                                                                                                                                                                                                                                                                                                                          
	public String add(){
		if(role != null){
			if(privilegeIds != null){
				HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
				for(int i =0;i<privilegeIds.length;i++){
					set.add(new RolePrivilege(new RolePrivilegeId(role, privilegeIds[i])));
				}
				role.setRolePrivilege(set);
			}
			roleService.save(role);
		}
		role.setName(strName);
		return "list";
	}
	//跳转到编辑页面  
	public String editUI(){
		//显示信息
		if(role != null && role.getRoleId() != null){
			ActionContext.getContext().getContextMap().put("privilegeMap",Constant.PRIVILEGE_MAP);
			//解决信息被覆盖问题
			strName = role.getName();
			role = roleService.findById(role.getRoleId());
			if(role.getRolePrivilege() != null){
				privilegeIds = new String[role.getRolePrivilege().size()];
				int i =0;
				for(RolePrivilege rp :role.getRolePrivilege()){
					privilegeIds[i++] = rp.getId().getCode();
				}
			}
		}
		return "editUI";
	}
	//保存编辑     
	public String edit(){
		if(role !=null){
			if(privilegeIds != null){
				HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
				for(int i =0;i<privilegeIds.length;i++){
					set.add(new RolePrivilege(new RolePrivilegeId(role, privilegeIds[i])));
				}
				role.setRolePrivilege(set);
			}
			roleService.update(role);
		}
		role.setName(strName);
		return "list";
	}
	//删除操作     
	public String delete(){
		if(role !=null && role.getRoleId() != null)
			roleService.delete(role.getRoleId());
		return "list";
	}
	//批量删除    
	public String deleteSelected(){
		if(selectedRow != null){
			for(String id : selectedRow){
				roleService.delete(id);
			}
		}
		return "list";
	}
	
	
	//列表
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	//权限
	public String[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}

}
