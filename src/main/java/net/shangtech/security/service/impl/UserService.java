package net.shangtech.security.service.impl;

import java.util.Collection;

import net.shangtech.framework.orm.dao.support.MapHolder;
import net.shangtech.framework.orm.service.BaseService;
import net.shangtech.security.dao.IRoleDao;
import net.shangtech.security.dao.IUserDao;
import net.shangtech.security.dao.IUserToRoleDao;
import net.shangtech.security.entity.Role;
import net.shangtech.security.entity.User;
import net.shangtech.security.entity.UserToRole;
import net.shangtech.security.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
public class UserService extends BaseService<User> implements IUserService {

	@Autowired private IUserDao dao;
	
	@Autowired private IRoleDao roleDao;
	
	@Autowired private IUserToRoleDao userToRoleDao;

	@Override
    public void removeRole(Long userId, Collection<Long> roleIds) {
	    Assert.notNull(userId);
	    if(CollectionUtils.isEmpty(roleIds)){
	    	return;
	    }
	    for(Long roleId : roleIds){
	    	UserToRole utr = userToRoleDao.findByUserIdAndRoleId(userId, roleId);
		    if(utr != null){
		    	userToRoleDao.delete(utr.getId());
		    }
	    }
    }

	@Override
    public void addRoles(Long userId, Collection<Long> roleIds) {
		Assert.notNull(userId);
		if(CollectionUtils.isEmpty(roleIds)){
			return;
		}
	    User user = dao.find(userId);
	    if(user == null){
	    	return;
	    }
	    for(Long roleId : roleIds){
	    	Role role = roleDao.find(roleId);
		    if(role == null){
		    	return;
		    }
		    UserToRole utr = userToRoleDao.findByUserIdAndRoleId(userId, roleId);
		    if(utr == null){
		    	utr = new UserToRole();
			    utr.setUserId(userId);
			    utr.setRoleId(roleId);
			    userToRoleDao.save(utr);
		    }
	    }
	    
    }
	
	@Override
	public void update(User entity) {
	    User old = dao.find(entity.getId());
	    if(old != null){
	    	old.setUsername(entity.getUsername());
	    	dao.update(old);
	    }
	}

	@Override
    public User findByUsername(String username) {
	    return dao.findOneByProperties(MapHolder.instance("username", username));
    }
	
}
