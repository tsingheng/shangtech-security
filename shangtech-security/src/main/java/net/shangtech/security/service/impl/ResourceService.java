package net.shangtech.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.shangtech.framework.service.BaseService;
import net.shangtech.security.dao.IResourceDao;
import net.shangtech.security.dao.IRoleToResourceDao;
import net.shangtech.security.entity.Resource;
import net.shangtech.security.entity.RoleToResource;
import net.shangtech.security.service.IResourceService;

@Service
@Transactional
public class ResourceService extends BaseService<Resource> implements IResourceService {
	
	@Autowired private IResourceDao dao;
	
	@Autowired private IRoleToResourceDao roleToResourceDao;
	
	@Override
	public void save(Resource entity) {
		Assert.notNull(entity);
		if(entity.getParentId() == null){
			entity.setParentId(Resource.DEFAULT_ROOT_ID);
		}
		dao.save(entity);
	}
	
	@Override
	public void update(Resource entity) {
		Assert.notNull(entity);
		Assert.notNull(entity.getId());
		Resource old = dao.find(entity.getId());
		if(old == null){
			return;
		}
		old.setCode(entity.getCode());
		old.setName(entity.getName());
		old.setPath(entity.getPath());
		old.setRemark(entity.getRemark());
		dao.update(old);
	}

	@Override
    public List<Resource> findByParentId(Long parentId) {
	    if(parentId == null){
	    	parentId = Resource.DEFAULT_ROOT_ID;
	    }
	    return dao.findByParentId(parentId);
    }
	
	@Override
    public List<Long> findIdsByRoleId(Long roleId) {
		Assert.notNull(roleId);
		List<RoleToResource> roleToResources = roleToResourceDao.findByRoleId(roleId);
		List<Long> list = new ArrayList<>();
		if(!CollectionUtils.isEmpty(roleToResources)){
			roleToResources.forEach(rtr -> {
				list.add(rtr.getResourceId());
			});
		}
	    return list;
    }
}
