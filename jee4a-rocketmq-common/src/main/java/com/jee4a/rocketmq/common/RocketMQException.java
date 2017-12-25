package com.jee4a.rocketmq.common;

/**
 * 
 * <p>消息异常处理类</p> 
 * @author tpeng 2017年12月25日
 * @email 398222836@qq.com
 */
public class RocketMQException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5233980968371237544L;

	public RocketMQException() {
		super();
	}

	public RocketMQException(String message) {
		super(message);
	}

	public RocketMQException(String message, Throwable cause) {
		super(message, cause);
	}

	public RocketMQException(Throwable cause) {
		super(cause);
	}

	protected RocketMQException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
