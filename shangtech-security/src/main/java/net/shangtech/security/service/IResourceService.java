package net.shangtech.security.service;

import java.util.List;

import net.shangtech.framework.service.IBaseService;
import net.shangtech.security.entity.Resource;

public interface IResourceService extends IBaseService<Resource> {

	List<Resource> findByParentId(Long parentId);
	
	List<Long> findIdsByRoleId(Long roleId);
	
}
