package com.bus.handlers;

import hbmpojos.BusXDetails;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.HashMap;

import com.bus.impl.AbhiBusDataImpl;
import com.bus.impl.RedBusDataImpl;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class PrivateDataHandler {
	public void getAbhibusData() throws IOException{
		AbhiBusDataImpl ABDI = (AbhiBusDataImpl) UserBeanFactory.getBeanFactoryObject().getBean("abhiBusDataImpl");
		HtmlPage page = ABDI.getData();
		HashMap< Integer, BusXDetails> data = ABDI.extractData(page);
		File f= new File("/home/nagulmeera/data.txt");
		FileWriter fw= new FileWriter(f,true);
		for (java.util.Map.Entry<Integer, BusXDetails> entry: data.entrySet()) {
			fw.write(entry.getValue().toString());
			System.out.println(entry.getValue().toString());
		}
	}
	public void getRedBusData() {
		RedBusDataImpl RBDI = (RedBusDataImpl) UserBeanFactory.getBeanFactoryObject().getBean("redBusDataImpl");
		HtmlPage page = RBDI.getData();
		RBDI.extractData(page);
	}
}
