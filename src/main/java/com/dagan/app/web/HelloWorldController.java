package com.dagan.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/helloWorld")
public class HelloWorldController {
	
	private static final Logger logger =
	LoggerFactory.getLogger(HelloWorldController.class);
	
		
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String hello(ModelMap model) {
		
		logger.debug("welcome() is executed, value {}", "kiuk");
		//logger.error("This is Error message", new Exception("Testing"));		
		model.addAttribute("msg", "Hello World!");		
		return "helloWorld";
	}	
	
	@RequestMapping(value = "/displayMessage/{msg}", method = RequestMethod.GET)
	public String displayMessage(@PathVariable String msg, ModelMap model){		
		model.addAttribute("msg", msg);		
		return "helloWorld";
	}

}
