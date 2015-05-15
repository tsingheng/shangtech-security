package net.shangtech.security.dao.impl;

import java.util.List;

import net.shangtech.framework.orm.dao.hibernate.BaseDao;
import net.shangtech.framework.orm.dao.support.MapHolder;
import net.shangtech.security.dao.IRoleToResourceDao;
import net.shangtech.security.entity.RoleToResource;

import org.springframework.stereotype.Repository;

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

	@Override
    public RoleToResource findByRoleIdAndResourceId(Long roleId, Long resourceId) {
	    return findOneByProperties(MapHolder.instance("roleId", roleId).put("resourceId", resourceId));
    }

}
