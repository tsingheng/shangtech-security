package net.shangtech.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.shangtech.framework.orm.dao.support.BaseEntity;

import org.hibernate.annotations.Index;

/**
 * 角色-资源中间表
 * @author tsingheng
 *
 */
@Entity
@Table(name = "t_sys_security_role_to_resource")
public class RoleToResource extends BaseEntity<Long> {

    private static final long serialVersionUID = -7569364502242896715L;
    
    @Column(name = "role_id")
    @Index(name = "idx_sys_security_role_to_resource_role_id")
    private Long roleId;
    
    @Column(name = "resource_id")
    @Index(name = "idx_sys_security_role_to_resource_resource_id")
    private Long resourceId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

}
