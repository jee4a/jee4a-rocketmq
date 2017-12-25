package com.jee4a.rocketmq.consumer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.jee4a.rocketmq.common.RocketMQException;

/**
 * <p>消费者配置及初始化</p> 
 * @author tpeng 2017年12月25日
 * @email 398222836@qq.com
 */
@SpringBootConfiguration
public class Consumer extends DefaultMQPushConsumer {

	private static Logger logger = LoggerFactory.getLogger(Consumer.class);

	private static DefaultMQPushConsumer consumer = null;

	@Value("${rocketmq.consumer.namesrvAddr}")
	private String namesrvAddr;
	@Value("${rocketmq.consumer.groupName}")
	private String groupName;
	@Value("${rocketmq.consumer.topic}")
	private String topic;
	@Value("${rocketmq.consumer.tag}")
	private String tag;
	@Value("${rocketmq.consumer.consumeThreadMin}")
	private int consumeThreadMin;
	@Value("${rocketmq.consumer.consumeThreadMax}")
	private int consumeThreadMax;

	@Autowired
	@Qualifier("messageReceiver")
	private MessageProcessor messageProcessor;

	static {
		logger.error("消息队列消費者初始化开始。。。");
		Consumer.consumer = new DefaultMQPushConsumer();
	}

	public static DefaultMQPushConsumer getConsumer() {
		return Consumer.consumer;
	}

	/**
	 * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例 注意：groupName需要由应用来保证唯一
	 */
	@PostConstruct
	public void init() throws RocketMQException {
		if (StringUtils.isBlank(groupName)) {
			throw new RocketMQException("groupName is null !!!");
		}
		if (StringUtils.isBlank(namesrvAddr)) {
			throw new RocketMQException("namesrvAddr is null !!!");
		}
		if (StringUtils.isBlank(topic)) {
			throw new RocketMQException("topic is null !!!");
		}
		if (StringUtils.isBlank(tag)) {
			throw new RocketMQException("tag is null !!!");
		}
		// DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
		consumer.setConsumerGroup(groupName);
		consumer.setNamesrvAddr(namesrvAddr);
		consumer.setConsumeThreadMin(consumeThreadMin);
		consumer.setConsumeThreadMax(consumeThreadMax);
		MessageListener messageListener = new MessageListener();
		messageListener.setMessageProcessor(messageProcessor);
		consumer.registerMessageListener(messageListener);
		try {
			consumer.subscribe(topic, tag);
			consumer.start();
			logger.error("consumer is start !!! groupName:{},topic:{},namesrvAddr:{}", groupName, topic, namesrvAddr);
		} catch (MQClientException e) {
			logger.error("consumer is start !!! groupName:{},topic:{},namesrvAddr:{}", groupName, topic, namesrvAddr,
					e);
			throw new RocketMQException(e);
		}
	}

	@PreDestroy
	public void shutdown() {
		getConsumer().shutdown();
		logger.error("consumer shutdown ok status: [{}]",
				getConsumer().getDefaultMQPushConsumerImpl().getServiceState());
	}

}
