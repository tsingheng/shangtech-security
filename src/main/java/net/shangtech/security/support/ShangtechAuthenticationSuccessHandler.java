package net.shangtech.security.support;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shangtech.security.entity.Role;
import net.shangtech.security.service.IRoleService;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class ShangtechAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(ShangtechAuthenticationSuccessHandler.class);
	
	@Autowired private IRoleService roleService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		ShangtechUserDetails userDetails = (ShangtechUserDetails) authentication.getPrincipal();
		
		logger.info("loading authorities for {}", userDetails.getUsername());
		
		Collection<GrantedAuthority> gas = userDetails.getAuthorities();
	    List<Role> roles = roleService.findRolesByUserId(userDetails.getUser().getId());
	    if(!CollectionUtils.isEmpty(roles)){
	    	for(Role role : roles){
	    		GrantedAuthority ga = new SimpleGrantedAuthority(role.getCode());
	    		gas.add(ga);
	    	}
	    }
	}

}
