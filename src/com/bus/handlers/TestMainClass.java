package com.bus.handlers;


import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bus.beans.MainDataBean;

public class TestMainClass {
	
	public static void  main(String args[]) {
		final PrivateDataHandler PDH = (PrivateDataHandler) UserBeanFactory.getBeanFactoryObject().getBean("privateDataHandler");
		MainDataBean MDB = (MainDataBean) UserBeanFactory.getBeanFactoryObject().getBean("mainDataBean");
		final RTCDataHandler RDH = (RTCDataHandler) UserBeanFactory.getBeanFactoryObject().getBean("rtcDataHandler");
		MDB.setDate("2015-12-17");
		MDB.setFrom("Visakhapatnam");
		MDB.setTo("Vijayawada");
		MDB.setUrl("http://www.abhibus.com/");
		
		ExecutorService service = Executors.newFixedThreadPool(3);
		service.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					Date date1 = new Date();
					System.out.println("1 start  Time : "+ date1.toString());
					PDH.getAbhibusData();
					Date date2 = new Date();
					System.out.println("1  end Time : "+ date2.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		service.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					Date date1 = new Date();
					System.out.println("2 start  Time : "+ date1.toString());
					RDH.getRTCData();
					Date date2 = new Date();
					System.out.println("1  End Time : "+ date2.toString());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		service.shutdown();
		//PDH.getAbhibusData();
		//PDH.getRedBusData();
		//RDH.getRTCData();
	}

}
