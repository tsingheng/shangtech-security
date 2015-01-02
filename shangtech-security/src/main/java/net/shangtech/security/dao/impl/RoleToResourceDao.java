package net.shangtech.security.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.shangtech.framework.dao.hibernate.BaseDao;
import net.shangtech.framework.dao.support.MapHolder;
import net.shangtech.security.dao.IRoleToResourceDao;
import net.shangtech.security.entity.RoleToResource;

@Repository
public class RoleToResourceDao extends BaseDao<RoleToResource> implements IRoleToResourceDao {

	@Override
    public List<RoleToResource> findByRoleId(Long roleId) {
	    return findByProperties(MapHolder.instance("roleId", roleId));
    }

	@Override
    public List<RoleToResource> findByResourceId(Long resourceId) {
	    return findByProperties(MapHolder.instance("resourceId", resourceId));
    }

}
