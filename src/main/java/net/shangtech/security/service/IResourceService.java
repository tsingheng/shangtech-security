package net.shangtech.security.service;

import java.util.List;

import net.shangtech.framework.orm.service.IBaseService;
import net.shangtech.security.entity.Resource;
import net.shangtech.security.service.bo.ResourceBo;

public interface IResourceService extends IBaseService<Resource> {

	List<Resource> findByParentId(Long parentId);
	
	List<Long> findIdsByRoleId(Long roleId);
	
	ResourceBo findAllResources();
}
