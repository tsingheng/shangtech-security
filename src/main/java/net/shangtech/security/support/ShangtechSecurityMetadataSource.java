package net.shangtech.security.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shangtech.security.entity.Resource;
import net.shangtech.security.entity.Role;
import net.shangtech.security.service.IResourceService;
import net.shangtech.security.service.IRoleService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.CollectionUtils;

public class ShangtechSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	private static final Logger logger = LoggerFactory.getLogger(SecurityMetadataSource.class);

	private static Map<String, Collection<ConfigAttribute>> RESOURCE_ROLE_MAP = null;
	
	private static Collection<ConfigAttribute> ALL_CONFIG_ATTRIBUTES = null;
	
	private static Map<Long, ConfigAttribute> ALL_CONFIG_ATTRIBUTES_MAP = null;
	
	private String rolePrefix = "ROLE_";
	
	@Autowired private IRoleService roleService;
	@Autowired private IResourceService resourceService;

	//计算哪些角色具有该资源的权限
	@Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
	    logger.info(object.getClass().getName());
	    FilterInvocation fi = (FilterInvocation) object;
	    String url = fi.getRequestUrl();
		return getResourceRoleMap().get(url);
    }

	@Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
	    logger.info("getAllConfigAttributes");
	    if(ALL_CONFIG_ATTRIBUTES == null){
	    	ALL_CONFIG_ATTRIBUTES = getAllConfigAttributesMap().values();
	    }
	    return ALL_CONFIG_ATTRIBUTES;
    }
	
	private Map<Long, ConfigAttribute> getAllConfigAttributesMap(){
		if(ALL_CONFIG_ATTRIBUTES_MAP == null){
			ALL_CONFIG_ATTRIBUTES_MAP = new HashMap<>();
			List<Role> roles = roleService.findAll();
	    	if(!CollectionUtils.isEmpty(roles)){
	    		for(Role role : roles){
	    			ConfigAttribute ca = new SecurityConfig(rolePrefix + role.getCode());
	    			ALL_CONFIG_ATTRIBUTES_MAP.put(role.getId(), ca);
	    		}
	    	}
		}
		return ALL_CONFIG_ATTRIBUTES_MAP;
	}
	
	private Map<String, Collection<ConfigAttribute>> getResourceRoleMap(){
		if(RESOURCE_ROLE_MAP == null){
			RESOURCE_ROLE_MAP = new HashMap<>();
			List<Resource> resources = resourceService.findAll();
			if(!CollectionUtils.isEmpty(resources)){
				for(Resource resource : resources){
					if(StringUtils.isBlank(resource.getPath())){
						continue;
					}
					Collection<ConfigAttribute> cas = new ArrayList<>();
					List<Long> roles = roleService.findIdsByResourceId(resource.getId());
					if(!CollectionUtils.isEmpty(roles)){
						for(Long role : roles){
							ConfigAttribute ca = getAllConfigAttributesMap().get(role);
							if(ca != null){
								cas.add(ca);
							}
						}
					}
					RESOURCE_ROLE_MAP.put(resource.getPath(), cas);
				}
			}
		}
		return RESOURCE_ROLE_MAP;
	}
	
	public void clear(){
		RESOURCE_ROLE_MAP = null;
		ALL_CONFIG_ATTRIBUTES = null;
		ALL_CONFIG_ATTRIBUTES_MAP = null;
	}

	@Override
    public boolean supports(Class<?> clazz) {
		logger.info(clazz.getName());
	    return FilterInvocation.class.equals(clazz);
    }
}
