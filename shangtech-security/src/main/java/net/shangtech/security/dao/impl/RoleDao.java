package net.shangtech.security.dao.impl;

import net.shangtech.framework.dao.hibernate.BaseDao;
import net.shangtech.security.dao.IRoleDao;
import net.shangtech.security.entity.Role;

import org.springframework.stereotype.Repository;

@Repository
public class RoleDao extends BaseDao<Role> implements IRoleDao {

}
