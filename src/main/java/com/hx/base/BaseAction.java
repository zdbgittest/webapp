package com.hx.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	/**
	 * 得到Request
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	/**
	 * 得到response
	 */
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	/**
	 * 得到session
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}
	
	/**
	 * 返回数据给ajax，指定头ContentType
	 * 
	 * @param data
	 */
	public void writeAjaxString(String data) {
		PrintWriter out = null;
		try {
			getResponse().setCharacterEncoding("utf-8");
			getResponse().setContentType("text/plain");
			out = getResponse().getWriter();			
			out.write(data);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null) out.close();
		}
	}
}
