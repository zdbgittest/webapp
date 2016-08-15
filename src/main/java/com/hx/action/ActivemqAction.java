package com.hx.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hx.base.BaseAction;

@Controller
@Scope("prototype")
public class ActivemqAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	//前往activemq的测试页面
	public String operList(){
		return SUCCESS;
	}
}
