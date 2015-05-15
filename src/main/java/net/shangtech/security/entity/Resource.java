package net.shangtech.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.shangtech.framework.orm.dao.support.BaseEntity;

import org.hibernate.annotations.Index;

/**
 * 系统资源表,树形结构
 * @author tsingheng
 *
 */
@Entity
@Table(name = "t_sys_security_resouce")
public class Resource extends BaseEntity<Long> {

    private static final long serialVersionUID = -5521747344745316255L;
    
    public static final Long DEFAULT_ROOT_ID = 0L;
    
    /**
     * 上级ID
     */
    @Column(name = "parent_id")
    private Long parentId;
    
    /**
     * 资源名称
     */
    @Column(name = "resource_name")
    private String name;
    
    /**
     * 资源编码
     */
    @Column(name = "resource_code")
    private String code;
    
    /**
     * 资源访问路径
     */
    @Column(name = "resource_path")
    @Index(name = "idx_sys_security_resouce_resource_path")
    private String path;
    
    /**
     * 备注
     */
    private String remark;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
