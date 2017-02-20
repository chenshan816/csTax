package cn.cs.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.cs.core.exception.ServiceException;
import cn.cs.core.service.BaseService;
import cn.cs.nsfw.user.entity.User;
import cn.cs.nsfw.user.entity.UserRole;

public interface UserService extends BaseService<User>{
	
	//导出功能
	public void exportExcel(List<User> userList,ServletOutputStream outputStream);
	//导入功能
	public void importExcel(File userExcel, String userExcelFileName);
	//根据账户查找
	public User findByAccount(User user);
	//保存用户和角色信息
	public void saveUserAndRole(User user, String... userRoleIds);
	//更新用户和角色信息
	public void updateUserAndRole(User user,String... userRoleIds);
	//获得用户对应的角色
	public List<UserRole> getUserRoleByUserId(String id);
	//登录校验，根据用户帐号和密码获取用户
	public List<User> findByAccountAndPassword(User user);
}
