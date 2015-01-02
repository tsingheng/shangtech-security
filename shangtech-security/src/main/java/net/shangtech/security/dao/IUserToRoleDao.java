package net.shangtech.security.dao;

import java.util.List;

import net.shangtech.framework.dao.IBaseDao;
import net.shangtech.security.entity.UserToRole;

public interface IUserToRoleDao extends IBaseDao<UserToRole> {

	List<UserToRole> findByUserId(Long userId);
	
	UserToRole findByUserIdAndRoleId(Long userId, Long roleId);
	
}
