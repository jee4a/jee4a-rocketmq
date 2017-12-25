package com.jee4a.rocketmq.common;

/**
 * 
 * <p>消息标签</p> 
 * @author tpeng 2017年12月25日
 * @email 398222836@qq.com
 */
public enum TagsEnum {
	
	Login("login"),
	Register("register"),
	Forgetpwd("forgetpwd"),
	Pay("pay") ;
	
	private String type ;
	
	private TagsEnum(String type) {
		this.setType(type) ;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	/*public static void main(String[] args) {
		System.out.println("TopicEnum.LOGIN   = " + TopicEnum.LOGIN.getType());
	}*/
}
