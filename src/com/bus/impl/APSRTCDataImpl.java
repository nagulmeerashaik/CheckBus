package com.bus.impl;

import hbmpojos.BusXDetails;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;

import com.bus.beans.BusDataBean;
import com.bus.beans.MainDataBean;
import com.bus.handlers.ClientFactory;
import com.bus.handlers.UserBeanFactory;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class APSRTCDataImpl implements DataExtractorFactory{
	public HtmlPage startPage;
	public MainDataBean MDB;
	public WebClient client;
	public void loadPage(){
		MDB = (MainDataBean) UserBeanFactory.getBeanFactoryObject().getBean("mainDataBean");
		ClientFactory cf = (ClientFactory) UserBeanFactory.getBeanFactoryObject().getBean("clientFactory");
		client = cf.getWebClient();
		try{
			this.startPage = client.getPage(MDB.RTCUrl);
			client.waitForBackgroundJavaScriptStartingBefore(200);
			client.waitForBackgroundJavaScript(2000);
		}catch(Exception e){
			
		}
	}
	@Override
	public HtmlPage getData() {
		HtmlPage resultPage = null;
		
		try{
			HtmlForm form = startPage.getFormByName("bookingsForm");
			HtmlTextInput source = form.getInputByName("fromPlaceName");
			HtmlTextInput destination = form.getInputByName("toPlaceName");
			HtmlTextInput date = form.getInputByName("txtJourneyDate");
			HtmlHiddenInput  Page_hidden_from = form.getInputByName("startPlaceId");
			HtmlHiddenInput  Page_hidden_to =  form.getInputByName("endPlaceId");
			HtmlHiddenInput  page_ajaxAction 			= form.getInputByName("ajaxAction");
			
			Page_hidden_from.setValueAttribute("15941");
			Page_hidden_to.setValueAttribute("15881");
			source.setValueAttribute(MDB.getFrom());
			destination.setValueAttribute(MDB.getTo());
			date.setValueAttribute("16/12/2015");
			System.out.println("Datatta"+MDB.getDate().replace("-", "/"));
			page_ajaxAction.setValueAttribute("fw");
			HtmlButtonInput button = form.getInputByName("searchBtn");
			resultPage = button.click();
			client.waitForBackgroundJavaScript(3000);
			//System.out.println(page.asXml());
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultPage;
	}

	@Override
	public HashMap extractData(HtmlPage page) {
		HashMap<Integer, BusXDetails> data = new HashMap<Integer , BusXDetails>();
		
		String NoOfServices = page.getElementById("fwTotalServicesId").getTextContent();
		System.out.println("NoOfServices : "+NoOfServices);
//		File f= new File("/home/nagulmeera/rtc.txt");
//		try {
//			FileWriter fw = new FileWriter(f);
//			fw.write(page.asXml());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		List<DomElement> mainDivs =(List<DomElement>) page.getByXPath("//div[@class='rSetForward']/div[@class='row']");
		
		for (DomElement domElement : mainDivs) {
			//System.out.println(domElement.getNodeName());
			BusXDetails BDB = (BusXDetails) UserBeanFactory.getBeanFactoryObject().getBean("busDataBean");
			for (DomElement columns : domElement.getChildElements()) {
				for(DomElement innerElements : columns.getChildElements()) {
					
					if(innerElements.hasAttribute("class") && innerElements.getAttribute("class").equalsIgnoreCase("srvceNO")){
						int ServiceNUmber = Integer.parseInt(innerElements.getTextContent().replaceAll("\\s", ""));
						BDB.setServiceNo(ServiceNUmber);
					}else if(innerElements.hasAttribute("class") && innerElements.getAttribute("class").equalsIgnoreCase("StrtTm curHand tooltipstered")){
						//System.out.println("Departure Time : "+innerElements.getTextContent().trim());
						BDB.setDep_time(innerElements.getTextContent().trim());
					}else if(innerElements.hasAttribute("class") && innerElements.getAttribute("class").equalsIgnoreCase("ArvTm curHand tooltipstered")){
						//System.out.println("Arrival Time : "+innerElements.getTextContent().trim());
						BDB.setArr_time(innerElements.getTextContent().trim());
					}else if (innerElements.hasAttribute("class") && innerElements.getAttribute("class").equalsIgnoreCase("availCs")) {
						//System.out.println("Available seats : "+innerElements.getTextContent().trim());
						BDB.setAvbl_seats(Integer.parseInt(innerElements.getTextContent().trim().replace("Seats","").replaceAll("\\s", "")));
					}else if(innerElements.getNodeName().equals("p") && columns.getAttribute("class").equals("col2")){
						//System.out.println("Duration : "+innerElements.getTextContent().trim());
						BDB.setDuration_hrs(innerElements.getTextContent().trim());
					}else if(innerElements.getNodeName().equals("p") && columns.getAttribute("class").equals("col4")){
						//System.out.println("Window Seats : "+innerElements.getTextContent().trim());
					}else if(innerElements.getAttribute("class").equals("col3-left")){
						for (DomElement span : innerElements.getChildElements()) {
							//System.out.println("name :"+span.getNodeName());
							if(span.getNodeName().equalsIgnoreCase("span")){
								//System.out.println("name :"+span.getNodeName());
								BDB.setName(span.getTextContent().trim());
							}
						}
					}else if(innerElements.hasAttribute("tickrate")){
						BDB.setFare(Integer.parseInt(innerElements.getTextContent().trim()));
					}
					
				}
			}
			//System.out.println(BDB.toString());
			data.put(BDB.getServiceNo(), BDB);
		}
		return data;
	}

	@Override
	public Boolean setData(HashMap data) {
		// TODO Auto-generated method stub
		return null;
	}

}
