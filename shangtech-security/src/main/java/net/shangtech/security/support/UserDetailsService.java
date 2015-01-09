package net.shangtech.security.support;

import java.util.ArrayList;
import java.util.List;

import net.shangtech.security.entity.Role;
import net.shangtech.security.entity.User;
import net.shangtech.security.service.IRoleService;
import net.shangtech.security.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired private IUserService userService;
	@Autowired private IRoleService roleService;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
	    if(user == null){
	    	return null;
	    }
	    List<GrantedAuthority> gas = new ArrayList<>();
	    List<Role> roles = roleService.findRolesByUserId(user.getId());
	    if(!CollectionUtils.isEmpty(roles)){
	    	roles.forEach(role -> {
	    		GrantedAuthority ga = new SimpleGrantedAuthority(role.getCode());
	    		gas.add(ga);
	    	});
	    }
	    UserDetails ud = new net.shangtech.security.support.UserDetails(username, user.getPassword(), true, true, true, true, gas);
	    return ud;
    }

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

}
