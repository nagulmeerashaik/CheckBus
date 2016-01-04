package com.bus.handlers;



import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class UserBeanFactory {
	private static  BeanFactory beanFactory= null;
	
	public static BeanFactory getBeanFactoryObject(){
		if(beanFactory == null){
			Resource resource=(Resource) new ClassPathResource("applicationContext.xml");  
		    BeanFactory factory=new XmlBeanFactory(resource);
		    beanFactory = factory;
		    return beanFactory;
		}else{
			return beanFactory;
		}
	}
	
}
