package net.shangtech.security.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.shangtech.framework.orm.dao.support.BaseEntity;

/**
 * 系统登录用户表,只存用户名密码
 * @author tsingheng
 *
 */
@Entity
@Table(name = "t_sys_security_user")
public class User extends BaseEntity<Long> {

    private static final long serialVersionUID = -197223617884749297L;

    private String username;
    
    private String salt;
    
    private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
