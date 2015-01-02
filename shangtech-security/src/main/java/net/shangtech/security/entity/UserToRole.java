package net.shangtech.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import net.shangtech.framework.dao.support.BaseEntity;

/**
 * 用户-角色中间表
 * @author tsingheng
 *
 */
@Entity
@Table(name = "t_sys_security_user_to_role")
public class UserToRole extends BaseEntity<Long> {

    private static final long serialVersionUID = 3783809841707444040L;

    @Column(name = "user_id")
    @Index(name = "sys_security_user_to_role_user_id")
    private Long userId;
    
    @Column(name = "role_id")
    @Index(name = "sys_security_user_to_role_role_id")
    private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
    
}
