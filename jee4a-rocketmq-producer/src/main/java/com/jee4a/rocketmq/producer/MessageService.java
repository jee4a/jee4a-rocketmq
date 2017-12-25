package com.jee4a.rocketmq.producer;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Service;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * 
 * <p>消息发送业务处理</p> 
 * @author tpeng 2017年12月25日
 * @email 398222836@qq.com
 */
@Service("messageService")
@SpringBootConfiguration
public class MessageService {
	
	private static Logger logger = LoggerFactory.getLogger(MessageService.class);
	
	
	@Value("${rocketmq.producer.topic.sms}")
    private String topicSms;
 
	//tags
	@Value("${rocketmq.producer.tag.login}")
    private String tagLogin ;
	
	@Value("${rocketmq.producer.tag.register}")
    private String tagRegister;
	
	@Value("${rocketmq.producer.tag.forgetpwd}")
    private String tagForgetpwd;
	
	@Value("${rocketmq.producer.tag.pay}")
    private String tagPay;
	
	/**
	 * 登陆消息
	 * @param msg
	 * @return
	 */
	public  Map<String ,Object>  login(String msg) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Message message = new Message(topicSms, // topic
				tagLogin, // tag
				"KKK", // key用于标识业务的唯一性
				(msg).getBytes()// body 二进制字节数组
		);
		try {
			RocketMqUtils.asyncSendMessage(message);
			result.put("status", "true");
		} catch (MQClientException | RemotingException | InterruptedException e) {
			logger.error("异步消息放入队列失败，错误信息： {} ", e);
			result.put("status", "false");
			result.put("message", "发送失败");
		}
		return result;
	}
	
	
	/**
	 * 注册消息 
	 * @param msg
	 * @return
	 */
	public Map<String ,Object> register(String msg) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Message message = new Message(topicSms, // topic
				tagRegister, // tag
				"KKK", // key用于标识业务的唯一性
				(msg).getBytes()// body 二进制字节数组
		);
		try {
			RocketMqUtils.asyncSendMessage(message);
			result.put("status", "true");
		} catch (MQClientException | RemotingException | InterruptedException e) {
			logger.error("异步消息放入队列失败，错误信息： {} ", e);
			result.put("status", "false");
			result.put("message", "发送失败");
		}
		return result;
	}
	
	
	/**
	 * 找回密码消息
	 * @param msg
	 * @return
	 */
	public Map<String ,Object> forgetpwd(String msg) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Message message = new Message(topicSms, // topic
				tagForgetpwd, // tag
				"KKK", // key用于标识业务的唯一性
				(msg).getBytes()// body 二进制字节数组
		);
		try {
			RocketMqUtils.asyncSendMessage(message);
			result.put("status", "true");
		} catch (MQClientException | RemotingException | InterruptedException e) {
			logger.error("异步消息放入队列失败，错误信息： {} ", e);
			result.put("status", "false");
			result.put("message", "发送失败");
		}
		return result;
	}
	
	/**
	 * 支付消息
	 * @param msg
	 * @return
	 */
	public Map<String ,Object> pay(String msg) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Message message = new Message(topicSms, // topic
				tagPay, // tag
				"KKK", // key用于标识业务的唯一性
				(msg).getBytes()// body 二进制字节数组
		);
		try {
			RocketMqUtils.asyncSendMessage(message);
			result.put("status", "true");
		} catch (MQClientException | RemotingException | InterruptedException e) {
			logger.error("异步消息放入队列失败，错误信息： {} ", e);
			result.put("status", "false");
			result.put("message", "发送失败");
		}
		return result;
	}
}
