package com.huiyou.msfw.controller.console;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class PageController{
	
	@RequestMapping(value="/console/{page}")
	public String list(@PathVariable(value="page") String page){
		
		return "/console/"+page;
	}
}
