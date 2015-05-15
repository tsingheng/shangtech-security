package net.shangtech.security.service.bo;

import java.util.List;

import net.shangtech.security.entity.Resource;

public class ResourceBo extends Resource {

    private static final long serialVersionUID = 2020664358383891136L;
	
    private List<ResourceBo> children;

	public List<ResourceBo> getChildren() {
		return children;
	}

	public void setChildren(List<ResourceBo> children) {
		this.children = children;
	}
    
}
