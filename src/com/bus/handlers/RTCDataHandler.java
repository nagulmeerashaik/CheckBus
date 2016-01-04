package com.bus.handlers;

import hbmpojos.BusXDetails;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.HashMap;

import com.bus.impl.APSRTCDataImpl;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class RTCDataHandler {
	public void getRTCData() throws IOException {
		APSRTCDataImpl ADI = (APSRTCDataImpl) UserBeanFactory.getBeanFactoryObject().getBean("apsrtcImpl");
		HtmlPage page = ADI.getData();
		HashMap<Integer, BusXDetails> data = ADI.extractData(page);
		File file = new File("/home/nagulmeera/data.txt");
		FileWriter fw = new FileWriter(file , true);
		for (java.util.Map.Entry<Integer, BusXDetails> entry : data.entrySet()) {
			fw.write(entry.getValue().toString());
		}
	}
}
