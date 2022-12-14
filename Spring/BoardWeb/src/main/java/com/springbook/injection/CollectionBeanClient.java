package com.springbook.injection;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {
	
	public static void main(String[] args) {
		
		
		// 1. Spring 컨테이너를 구동한다
		AbstractApplicationContext factory = 
				new GenericXmlApplicationContext("applicationContext.xml");

		
		
		CollectionBean bean = (CollectionBean) factory.getBean("collectionBean");
		List<String> addressList = bean.getAddressList();
		for (String address : addressList) {
			System.out.println(address.toString());
		}
		
		factory.close();
	}

}
