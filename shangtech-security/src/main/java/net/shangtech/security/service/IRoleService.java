package net.shangtech.security.service;

import java.util.List;

import net.shangtech.framework.service.IBaseService;
import net.shangtech.security.entity.Role;

public interface IRoleService extends IBaseService<Role> {
	
	List<Role> findRolesByUserId(Long userId);
	
	List<Role> findRolesByResourceId(Long resourceId);
	
	/**
	 * 给角色授权资源
	 * @param roleId
	 * @param resourceIds
	 */
	void auth(Long roleId, List<Long> resourceIds);
	
	void auth(Long roleId, Long resourceId);
	
	void removeAuth(Long roleId, Long resourceId);
	
	List<Long> findIdsByResourceId(Long resourceId);
}
