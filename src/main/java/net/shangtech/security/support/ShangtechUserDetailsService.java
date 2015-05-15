package net.shangtech.security.support;

import net.shangtech.security.entity.User;
import net.shangtech.security.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ShangtechUserDetailsService implements UserDetailsService {

	@Autowired private IUserService userService;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
	    if(user == null){
	    	throw new UsernameNotFoundException("用户名或密码错误");
	    }
	    UserDetails ud = new ShangtechUserDetails(username, user.getPassword());
	    return ud;
    }

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
