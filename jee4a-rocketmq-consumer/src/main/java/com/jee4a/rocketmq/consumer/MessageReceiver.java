package com.jee4a.rocketmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.rocketmq.common.message.MessageExt;
import com.jee4a.rocketmq.common.TagsEnum;
import com.jee4a.rocketmq.common.TopicEnum;

/**
 * 
 * <p>消息处理实现</p> 
 * @author tpeng 2017年12月25日
 * @email 398222836@qq.com
 */
@Service("messageReceiver")
public class MessageReceiver implements MessageProcessor {

	private static Logger logger = LoggerFactory.getLogger(MessageListener.class);

	@Override
	public boolean handleMessage(MessageExt msg) {
		logger.error(Thread.currentThread().getName() + " receive  : " + byteArrayToStr(msg.getBody())
				+ "   ,   detail : " + msg.toString() + "\n");
		
		if(msg.getTopic() != null && msg.getTopic().equals(TopicEnum.Sms.getType())) {
			if(msg.getTags() != null && msg.getTags().equals(TagsEnum.Login.getType())) {
				System.out.println(String.format("这是一条主题为  %s，标签为 %s 的消息，内容为：%s " ,msg.getTopic(),msg.getTags(),byteArrayToStr(msg.getBody()))) ;
				return true ;
			}else if(msg.getTags() != null && msg.getTags().equals(TagsEnum.Register.getType())) {
				System.out.println(String.format("这是一条主题为  %s，标签为 %s 的消息，内容为：%s " ,msg.getTopic(),msg.getTags(),byteArrayToStr(msg.getBody()))) ;
				return true ;
			}if(msg.getTags() != null && msg.getTags().equals(TagsEnum.Forgetpwd.getType())) {
				System.out.println(String.format("这是一条主题为  %s，标签为 %s 的消息，内容为：%s " ,msg.getTopic(),msg.getTags(),byteArrayToStr(msg.getBody()))) ;
				return true ;
			}if(msg.getTags() != null && msg.getTags().equals(TagsEnum.Pay.getType())) {
				System.out.println(String.format("这是一条主题为  %s，标签为 %s 的消息，内容为：%s " ,msg.getTopic(),msg.getTags(),byteArrayToStr(msg.getBody()))) ;
				return true ;
			}
		}
		return false;
	}

	public static String byteArrayToStr(byte[] byteArray) {
		if (byteArray == null) {
			return null;
		}
		String str = new String(byteArray);
		return str;
	}
}
