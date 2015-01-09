package net.shangtech.security.service;

import java.util.Collection;

import net.shangtech.framework.service.IBaseService;
import net.shangtech.security.entity.User;

public interface IUserService extends IBaseService<User> {
	
	void removeRole(Long userId, Collection<Long> roleId);
	
	void addRoles(Long userId, Collection<Long> roleIds);
	
	User findByUsername(String username);
	
}
