package cn.cs.nsfw.user.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.ServerException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import cn.cs.core.service.impl.BaseServiceImpl;
import cn.cs.core.util.ExcelUtil;
import cn.cs.nsfw.role.entity.Role;
import cn.cs.nsfw.user.dao.UserDao;
import cn.cs.nsfw.user.entity.User;
import cn.cs.nsfw.user.entity.UserRole;
import cn.cs.nsfw.user.entity.UserRoleId;
import cn.cs.nsfw.user.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	//获取dao层
	private UserDao userDao;
	@Resource
	public void setUserDao(UserDao userDao) {
		super.setBaseDao(userDao);
		this.userDao = userDao;
	}
	
	
	//根据帐号获取用户信息
	public User findByAccount(User user){
		return userDao.findByAccount(user);
	}


	@Override
	public void exportExcel(List<User> userList,ServletOutputStream outputStream) {
		ExcelUtil.exportExcel(userList, outputStream);
	}

	@Override
	public void importExcel(File userExcel, String userExcelFileName) {
		ExcelUtil.importExcel(userExcel, userExcelFileName,this);
	}
	
	@Override
	public void saveUserAndRole(User user, String... roleIds) {
		//保存用户
		save(user);
		//保存用户规定的角色
		if(roleIds != null){
			for(String roleId:roleIds){
				Role role = new Role();
				role.setRoleId(roleId);
				userDao.saveUserRole(new UserRole(new UserRoleId(role,user.getId())));
			}
		}
	}
	@Override
	public void updateUserAndRole(User user, String... roleIds) {
		//1.更新用户信息
		update(user);
		//2.清除用户历史角色信息
		userDao.deleteUserRoleByUserId(user.getId());
		//3.保存角色信息
		if(roleIds != null){
			for(String roleId:roleIds){
				Role role = new Role();
				role.setRoleId(roleId);
				userDao.saveUserRole(new UserRole(new UserRoleId(role,user.getId())));
			}
		}
	}
	@Override
	public List<UserRole> getUserRoleByUserId(String id) {
		return userDao.getUserRoleByUserId(id);
	}
	@Override
	public List<User> findByAccountAndPassword(User user) {
		return userDao.findByAccountAndPassword(user);
	}
	
	

}
