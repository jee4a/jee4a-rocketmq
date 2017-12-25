package com.jee4a.rocketmq.common;

public class Result {

	
	protected Integer code;
	/**
	 * 返回值状态，false：失败 true：成功 默认悲观
	 */
	protected boolean success = false;
	protected String error;
	
	protected Object result = null;
	
	
}
