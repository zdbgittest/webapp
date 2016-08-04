package com.hx.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void init(){ 
		System.out.println("负责初始化的servlet，我刚启动");
		ServletContext sctx = getServletContext();//创建ServletContext对象
		String basePath = sctx.getInitParameter("basePath");
		System.out.println("basePath:"+basePath);
	}
}
