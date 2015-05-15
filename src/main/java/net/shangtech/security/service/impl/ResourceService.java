package net.shangtech.security.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.shangtech.framework.orm.service.BaseService;
import net.shangtech.security.dao.IResourceDao;
import net.shangtech.security.dao.IRoleToResourceDao;
import net.shangtech.security.entity.Resource;
import net.shangtech.security.entity.RoleToResource;
import net.shangtech.security.service.IResourceService;
import net.shangtech.security.service.bo.ResourceBo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

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
			for(RoleToResource rtr : roleToResources){
				list.add(rtr.getResourceId());
			}
		}
	    return list;
    }

	@Override
    public ResourceBo findAllResources() {
	    ResourceBo root = new ResourceBo();
	    root.setCode("ROOT");
	    root.setId(Resource.DEFAULT_ROOT_ID);
	    root.setName("ROOT");
	    LinkedList<ResourceBo> list = new LinkedList<>();
	    list.add(root);
	    while(!list.isEmpty()){
	    	ResourceBo bo = list.remove();
	    	List<Resource> children = dao.findByParentId(bo.getId());
	    	if(!CollectionUtils.isEmpty(children)){
	    		bo.setChildren(new ArrayList<ResourceBo>());
	    		for(Resource resource : children){
	    			ResourceBo child = new ResourceBo();
	    			BeanUtils.copyProperties(resource, child);
	    			bo.getChildren().add(child);
	    			list.add(child);
	    		}
	    	}
	    }
	    return root;
    }
}
