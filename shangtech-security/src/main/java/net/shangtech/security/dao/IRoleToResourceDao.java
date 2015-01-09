package net.shangtech.security.dao;

import java.util.List;

import net.shangtech.framework.dao.IBaseDao;
import net.shangtech.security.entity.RoleToResource;

public interface IRoleToResourceDao extends IBaseDao<RoleToResource> {

	List<RoleToResource> findByRoleId(Long roleId);
	
	List<RoleToResource> findByResourceId(Long resourceId);
	
	RoleToResource findByRoleIdAndResourceId(Long roleId, Long resourceId);
	
}
