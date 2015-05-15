package net.shangtech.security.dao.impl;

import net.shangtech.framework.orm.dao.hibernate.BaseDao;
import net.shangtech.security.dao.IUserDao;
import net.shangtech.security.entity.User;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao {

}
