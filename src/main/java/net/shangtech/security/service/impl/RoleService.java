package net.shangtech.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.shangtech.framework.orm.service.BaseService;
import net.shangtech.security.dao.IRoleDao;
import net.shangtech.security.dao.IRoleToResourceDao;
import net.shangtech.security.dao.IUserToRoleDao;
import net.shangtech.security.entity.Role;
import net.shangtech.security.entity.RoleToResource;
import net.shangtech.security.entity.UserToRole;
import net.shangtech.security.service.IRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
public class RoleService extends BaseService<Role> implements IRoleService {

	@Autowired private IRoleDao dao;
	
	@Autowired private IRoleToResourceDao roleToResourceDao;
	
	@Autowired private IUserToRoleDao userToRoleDao;
	
	@Override
	public void update(Role entity) {
		Assert.notNull(entity);
		Assert.notNull(entity.getId());
		Role old = dao.find(entity.getId());
		if(old == null){
			return;
		}
		old.setCode(entity.getCode());
		old.setName(entity.getName());
		old.setRemark(entity.getRemark());
		dao.save(old);
	}

	@Override
    public List<Role> findRolesByUserId(Long userId) {
		Assert.notNull(userId);
	    List<UserToRole> userToRoles = userToRoleDao.findByUserId(userId);
	    List<Role> list = new ArrayList<>();
	    if(!CollectionUtils.isEmpty(userToRoles)){
	    	for(UserToRole utr : userToRoles){
	    		list.add(dao.find(utr.getRoleId()));
	    	}
	    }
	    return list;
    }

	@Override
    public List<Role> findRolesByResourceId(Long resourceId) {
		Assert.notNull(resourceId);
		List<RoleToResource> roleToResources = roleToResourceDao.findByResourceId(resourceId);
		List<Role> list = new ArrayList<>();
		if(!CollectionUtils.isEmpty(roleToResources)){
			for(RoleToResource rtr : roleToResources){
				list.add(dao.find(rtr.getRoleId()));
			}
		}
		return list;
    }

	@Override
    public void auth(Long roleId, List<Long> resourceIds) {
	    Assert.notNull(roleId);
	    Role role = dao.find(roleId);
	    if(role == null){
	    	return;
	    }
	    //查询角色授权过的资源
	    List<RoleToResource> roleToResources = roleToResourceDao.findByRoleId(roleId);
	    
	    //删除不在本次授权范围的资源
	    if(!CollectionUtils.isEmpty(roleToResources)){
	    	for(RoleToResource rtr : roleToResources){
	    		if(!CollectionUtils.containsInstance(resourceIds, rtr.getResourceId())){
	    			roleToResourceDao.delete(rtr.getId());
	    		}
	    		else{
	    			resourceIds.remove(rtr.getResourceId());
	    		}
	    	}
	    }
	    
	    //授权新加的资源
	    if(!CollectionUtils.isEmpty(resourceIds)){
	    	for(Long resourceId : resourceIds){
	    		RoleToResource rtr = new RoleToResource();
	    		rtr.setResourceId(resourceId);
	    		rtr.setRoleId(roleId);
	    		roleToResourceDao.save(rtr);
	    	}
	    }
    }

	@Override
    public void auth(Long roleId, Long resourceId) {
		Assert.notNull(roleId);
		Assert.notNull(resourceId);
	    RoleToResource rtr = roleToResourceDao.findByRoleIdAndResourceId(roleId, resourceId);
	    if(rtr == null){
	    	rtr = new RoleToResource();
	    	rtr.setRoleId(roleId);
	    	rtr.setResourceId(resourceId);
	    	roleToResourceDao.save(rtr);
	    }
    }

	@Override
    public void removeAuth(Long roleId, Long resourceId) {
		Assert.notNull(roleId);
		Assert.notNull(resourceId);
		RoleToResource rtr = roleToResourceDao.findByRoleIdAndResourceId(roleId, resourceId);
		if(rtr != null){
			roleToResourceDao.delete(rtr.getId());
		}
    }

	@Override
    public List<Long> findIdsByResourceId(Long resourceId) {
		Assert.notNull(resourceId);
		List<RoleToResource> roleToResources = roleToResourceDao.findByResourceId(resourceId);
		List<Long> list = new ArrayList<>();
		if(!CollectionUtils.isEmpty(roleToResources)){
			for(RoleToResource rtr : roleToResources){
				list.add(rtr.getRoleId());
			}
		}
		return list;
    }
	
}
