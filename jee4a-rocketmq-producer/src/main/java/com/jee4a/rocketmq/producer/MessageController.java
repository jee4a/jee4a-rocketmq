package com.jee4a.rocketmq.producer;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * <p>消息发送接口</p> 
 * @author tpeng 2017年12月25日
 * @email 398222836@qq.com
 */
@RestController
@RequestMapping("v1/send/sms")
public class MessageController {

	@Resource
	private MessageService messageService ;
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	public Map<String, Object> login(String msg) {
		Map<String,Object> result = messageService.login(msg) ;
		return result ;
	}
	
	@RequestMapping(value = "register",method = RequestMethod.POST)
	public Map<String, Object> register(String msg) {
		Map<String,Object> result = messageService.register(msg) ;
		return result ;
	}
	
	
	@RequestMapping(value = "forgetpwd",method = RequestMethod.POST)
	public Map<String, Object> forgetpwd(String msg) {
		Map<String,Object> result = messageService.forgetpwd(msg) ;
		return result ;
	}
	
	@RequestMapping(value = "pay",method = RequestMethod.POST)
	public Map<String, Object> pay(String msg) {
		Map<String,Object> result = messageService.pay(msg) ;
		return result ;
	}
	
}
