package uk.ac.cam.cl.dtg.univdate;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.common.collect.ImmutableMap;


public class SetGlobalsFilter implements Filter {

	static Logger log = Logger.getLogger(SetGlobalsFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			String contextPath = httpRequest.getContextPath();
	        Map<String, ?> globals = ImmutableMap.of("siteRoot", contextPath);
	        request.setAttribute("globals", globals);					
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
