package net.shangtech.security.support;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import net.shangtech.security.entity.Role;
import net.shangtech.security.entity.User;
import net.shangtech.security.service.IRoleService;
import net.shangtech.security.service.IUserService;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ShangtechUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(ShangtechUserDetailsService.class);
	
	private String rolePrefix = "ROLE_";
	
	@Autowired private IUserService userService;
	@Autowired private IRoleService roleService;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
	    if(user == null){
	    	throw new UsernameNotFoundException("用户名或密码错误");
	    }
	    
	    //我曾经尝试过登录成功以后再去加载用户角色,但是发现user里面的authorities是不可变的,UserDetails一旦创建就不能变了
	    //当然自己再拓展一个UserDetails就可以解决这个问题了
	    logger.info("loading authorities for {}", username);
		
		Collection<GrantedAuthority> gas = new HashSet<GrantedAuthority>();
	    List<Role> roles = roleService.findRolesByUserId(user.getId());
	    if(!CollectionUtils.isEmpty(roles)){
	    	for(Role role : roles){
	    		GrantedAuthority ga = new SimpleGrantedAuthority(rolePrefix + role.getCode());
	    		gas.add(ga);
	    	}
	    }
	    
	    ShangtechUserDetails ud = new ShangtechUserDetails(user, gas);
	    
	    return ud;
    }

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
