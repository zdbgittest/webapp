package com.hx.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SessionFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		
		
		chain.doFilter(request, response);
	}

	public void destroy() {
		
	}

}
