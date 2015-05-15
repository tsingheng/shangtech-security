package net.shangtech.security.dao.impl;

import java.util.List;

import net.shangtech.framework.orm.dao.hibernate.BaseDao;
import net.shangtech.framework.orm.dao.support.MapHolder;
import net.shangtech.security.dao.IUserToRoleDao;
import net.shangtech.security.entity.UserToRole;

import org.springframework.stereotype.Repository;

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
