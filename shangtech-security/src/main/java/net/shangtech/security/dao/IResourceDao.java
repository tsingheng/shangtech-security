package net.shangtech.security.dao;

import java.util.List;

import net.shangtech.framework.dao.IBaseDao;
import net.shangtech.security.entity.Resource;

public interface IResourceDao extends IBaseDao<Resource> {

	List<Resource> findByParentId(Long parentId);
	
}
