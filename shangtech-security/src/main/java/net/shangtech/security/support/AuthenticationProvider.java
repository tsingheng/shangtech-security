package net.shangtech.security.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationProvider.class);

	private UserDetailsService userDetailsService;
	
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	    UserDetails ud = userDetailsService.loadUserByUsername(authentication.getName());
	    if(ud == null){
	    	throw new UsernameNotFoundException("用户名或密码错误");
	    }
	    if(authentication.getCredentials() == null || !authentication.getCredentials().equals(ud.getPassword())){
	    	throw new BadCredentialsException("用户名或密码错误");
	    }
	    Authentication result = new UsernamePasswordAuthenticationToken(ud, authentication.getCredentials(), ud.getAuthorities());
	    return result;
    }

	@Override
    public boolean supports(Class<?> clazz) {
	    logger.info(clazz.getName());
	    return UsernamePasswordAuthenticationToken.class.equals(clazz);
    }

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}
