package net.shangtech.security.support;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

public class SecurityFilter extends AbstractSecurityInterceptor implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);
	
	private SecurityMetadataSource securityMetadataSource;

	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {  
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());  
        } 
		finally {  
            super.afterInvocation(token, null);  
        }
		logger.info("securityFilter.doFilter");
    }

	@Override
    public Class<?> getSecureObjectClass() {
	    return FilterInvocation.class;
    }

	@Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
	    return getSecurityMetadataSource();
    }

	public SecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(SecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}
	
	@Override
    public void init(FilterConfig arg0) throws ServletException {}
	
	@Override
    public void destroy() {}

}
