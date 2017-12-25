package com.jee4a.rocketmq.producer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * 
 * <p>消息发送工具类</p> 
 * @author tpeng 2017年12月25日
 * @email 398222836@qq.com
 */
@Component
public class RocketMqUtils {

	private static Logger logger = LoggerFactory.getLogger(RocketMqUtils.class);
	
	public static void asyncSendMessage(Message msg) throws MQClientException, RemotingException, InterruptedException {
		logger.error("producerGroupName:[{}],message:[{}],body[{}]", new Object[] { Producer.getProducer().getProducerGroup(), msg, new String(msg.getBody()) });
		Producer.getProducer().send(msg, new SendCallback() {
			
			public void onSuccess(SendResult sendResult) {
				logger.info("异步消息放入队列成功[{}]", sendResult);
			}
			
			public void onException(Throwable e) {
				logger.debug("producer[{}]", Producer.getProducer());
			    logger.info("异步消息放入队列失败,reason:[{}]", e);
			}
		});
	}
}
