package net.shangtech.security.support;

import java.util.Collection;

import net.shangtech.security.entity.User;

import org.springframework.security.core.GrantedAuthority;

public class UserDetails extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = -7694894659502722966L;
    
    private User user;

	public UserDetails(String username, 
			String password, 
			boolean enabled, 
			boolean accountNonExpired,
            boolean credentialsNonExpired, 
            boolean accountNonLocked, 
            Collection<? extends GrantedAuthority> authorities) {
	    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
