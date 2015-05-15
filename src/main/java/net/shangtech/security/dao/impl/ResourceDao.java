package net.shangtech.security.dao.impl;

import java.util.List;

import net.shangtech.framework.orm.dao.hibernate.BaseDao;
import net.shangtech.framework.orm.dao.support.MapHolder;
import net.shangtech.security.dao.IResourceDao;
import net.shangtech.security.entity.Resource;

import org.springframework.stereotype.Repository;

@Repository
public class ResourceDao extends BaseDao<Resource> implements IResourceDao {

	@Override
    public List<Resource> findByParentId(Long parentId) {
	    return findByProperties(MapHolder.instance("parentId", parentId));
    }
	
}
