package com.jee4a.rocketmq.consumer;

import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * 
 * <p>消息处理接口 </p> 
 * @author tpeng 2017年12月25日
 * @email 398222836@qq.com
 */
public interface MessageProcessor {
	 public boolean handleMessage(MessageExt messageExt);
}
