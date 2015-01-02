package net.shangtech.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.shangtech.framework.dao.support.BaseEntity;

/**
 * 系统角色表
 * @author tsingheng
 *
 */
@Entity
@Table(name = "t_sys_security_role")
public class Role extends BaseEntity<Long> {

    private static final long serialVersionUID = 201217730857516016L;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String name;
    
    /**
     * 角色编码
     */
    @Column(name = "role_code")
    private String code;
    
    /**
     * 备注
     */
    private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}
