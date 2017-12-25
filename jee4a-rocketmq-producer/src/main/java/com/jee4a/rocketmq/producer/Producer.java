package com.jee4a.rocketmq.producer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.jee4a.rocketmq.common.RocketMQException;

/**
 * 
 * <p>生产者配置及初始化</p> 
 * @author tpeng 2017年12月25日
 * @email 398222836@qq.com
 */
@SpringBootConfiguration
public class Producer extends DefaultMQProducer {

	private static Logger logger = LoggerFactory.getLogger(Producer.class);
	
	private static DefaultMQProducer producer = null ;
	
	
	@Value("${rocketmq.producer.groupName}")
    private String groupName;
    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.producer.instanceName}")
    private String instanceName;
    @Value("${rocketmq.producer.maxMessageSize}")
    private int maxMessageSize ; //4M
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private int sendMsgTimeout ;

    static {
		logger.error("生产者初始化开始。。。");
		Producer.producer = new DefaultMQProducer()  ;
	}
    
	public static   DefaultMQProducer getProducer() {
		return Producer.producer ;
	}
    
    @PostConstruct
    public void init() throws RocketMQException {
        if (StringUtils.isBlank(groupName)) {
            throw new RocketMQException("groupName is blank");
        }
        if (StringUtils.isBlank(namesrvAddr)) {
            throw new RocketMQException("nameServerAddr is blank");
        }
        if (StringUtils.isBlank(instanceName)){
            throw new RocketMQException("instanceName is blank");
        }
        //DefaultMQProducer producer = new DefaultMQProducer(this.groupName);
        producer.setProducerGroup(groupName);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setInstanceName(instanceName);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeout);
        try {
            producer.start();
            logger.info(String.format("producer is start ! groupName:[%s],namesrvAddr:[%s]"
                    , groupName, namesrvAddr));
        } catch (MQClientException e) {
        	logger.error(String.format("producer is error {}"
                    , e.getMessage(),e));
            throw new RocketMQException(e);
        }
    }
    
    @PreDestroy
    public void shutdown() {
		getProducer().shutdown();
		logger.error("producer shutdown ok status: [{}]", getProducer().getDefaultMQProducerImpl().getServiceState());
	}
}
