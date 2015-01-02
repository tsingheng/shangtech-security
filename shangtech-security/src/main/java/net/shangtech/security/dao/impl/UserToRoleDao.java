package net.shangtech.security.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.shangtech.framework.dao.hibernate.BaseDao;
import net.shangtech.framework.dao.support.MapHolder;
import net.shangtech.security.dao.IUserToRoleDao;
import net.shangtech.security.entity.UserToRole;

@Repository
public class UserToRoleDao extends BaseDao<UserToRole> implements IUserToRoleDao {

	@Override
    public List<UserToRole> findByUserId(Long userId) {
	    return findByProperties(MapHolder.instance("userId", userId));
    }

	@Override
    public UserToRole findByUserIdAndRoleId(Long userId, Long roleId) {
	    return findOneByProperties(MapHolder.instance("userId", userId).put("roleId", roleId));
    }

}
