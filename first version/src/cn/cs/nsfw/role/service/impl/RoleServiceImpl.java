package cn.cs.nsfw.role.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cs.core.exception.ServiceException;
import cn.cs.core.service.impl.BaseServiceImpl;
import cn.cs.nsfw.role.dao.RoleDao;
import cn.cs.nsfw.role.entity.Role;
import cn.cs.nsfw.role.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{
		//获取dao层
		private RoleDao roleDao;
		@Resource
		public void setRoleDao(RoleDao roleDao) {
			super.setBaseDao(roleDao);
			this.roleDao = roleDao;
		}

}
