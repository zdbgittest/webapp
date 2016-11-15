package com.hx.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hx.base.BaseAction;

@Controller
@Scope("prototype")
public class AopAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1985038406169795790L;

	
	public String toAopList(){
		return SUCCESS;
	}
	
	
	
}
