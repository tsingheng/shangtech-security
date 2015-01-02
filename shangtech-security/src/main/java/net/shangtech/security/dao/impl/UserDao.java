package net.shangtech.security.dao.impl;

import org.springframework.stereotype.Repository;

import net.shangtech.framework.dao.hibernate.BaseDao;
import net.shangtech.security.dao.IUserDao;
import net.shangtech.security.entity.User;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao {

}
