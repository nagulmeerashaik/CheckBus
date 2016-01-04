package com.bus.impl;

import hbmpojos.BusXDetails;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Time;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.bus.beans.BusDataBean;
import com.bus.beans.MainDataBean;
import com.bus.handlers.ClientFactory;
import com.bus.handlers.UserBeanFactory;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class AbhiBusDataImpl implements DataExtractorFactory{
	public HtmlPage startPage;
	public MainDataBean MDB;
	public WebClient client;
	public void loadPage(){
		MDB = (MainDataBean) UserBeanFactory.getBeanFactoryObject().getBean("mainDataBean");
		ClientFactory cf = (ClientFactory) UserBeanFactory.getBeanFactoryObject().getBean("clientFactory");
		 client = cf.getWebClient();
		try {
			startPage = client.getPage(MDB.getAbhiUrl());
			client.waitForBackgroundJavaScriptStartingBefore(200);
			client.waitForBackgroundJavaScript(2000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public HtmlPage getData() {
		// TODO Auto-generated method stub
		HtmlPage resultPage = null;
			try{
			HtmlForm form = startPage.getFormByName("frmFinal");
			HtmlTextInput source = form.getInputByName("source");
			HtmlTextInput destination = form.getInputByName("destination");
			HtmlHiddenInput source_id = form.getInputByName("source_id");
			HtmlHiddenInput destination_id = form.getInputByName("destination_id");
			HtmlTextInput journey_date = form.getInputByName("journey_date");

			HtmlElement searchbutton = form.getFirstByXPath("//a[@class='btnab icosearch']");
			source.setValueAttribute(MDB.getFrom());
			destination.setValueAttribute(MDB.getTo());
			source_id.setValueAttribute("58");
			destination_id.setValueAttribute("5");
			journey_date.setValueAttribute(MDB.getDate());
			resultPage = searchbutton.click();
			client.waitForBackgroundJavaScript(2000);
			//System.out.println(resultPage.asXml());
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if( resultPage == null){
			//throw new Exception("Invalid Html Page ");
		}
		return resultPage;
	}

	@Override
	public Boolean setData(HashMap data) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public HashMap extractData(HtmlPage page) {
		// TODO Auto-generated method stub
		HashMap<Integer, BusXDetails> data = new HashMap<Integer, BusXDetails>();
		List<DomElement> mainDivs = (List<DomElement>) page.getByXPath("//div[@class='travele clearfix rSet']");
		for (DomElement mainDiv : mainDivs) {
			if(mainDiv.hasChildNodes()){
				BusXDetails busDataBean = (BusXDetails) UserBeanFactory.getBeanFactoryObject().getBean("busDataBean");
				int ServiceNo = Integer.parseInt(mainDiv.getAttribute("id").replace("result_", ""));
				//System.out.println("Servcice No :"+ServiceNo);
				busDataBean.setServiceNo(ServiceNo);
				Iterable<DomElement> innerDivs =  mainDiv.getChildElements();
				for (DomElement innerDiv : innerDivs) {
					if(innerDiv.hasChildNodes()){
					
						switch(innerDiv.getAttribute("class")){
						case "col1":
							Iterable<DomElement> COL1Childs =  innerDiv.getChildElements();
							for (DomElement COL1Child : COL1Childs) {
								if(COL1Child.getNodeName().equals("h2") && COL1Child.hasChildNodes()){
									Iterable<DomElement> spanElements =  COL1Child.getChildElements();
									for (DomElement spanElement : spanElements) {
										if(spanElement.getAttribute("class").contains("StrtTm")){
											//System.out.println("Dep time :"+spanElement.getTextContent());
											busDataBean.setDep_time(spanElement.getTextContent().trim());
											
										}else if(spanElement.getAttribute("class").contains("ArvTm")){
											busDataBean.setArr_time(spanElement.getTextContent().trim());
											//System.out.println("Arv time :"+spanElement.getTextContent());
										}
									}
								}else if(COL1Child.getNodeName().equals("p")){
									busDataBean.setDuration_hrs(COL1Child.getTextContent());
									//System.out.println("Duration :"+COL1Child.getTextContent());
								}
							}
							break;
						case "col2":
							Iterable<DomElement> COL2Childs =  innerDiv.getChildElements();
							for (DomElement COL2Child : COL2Childs) {
								if(COL2Child.getAttribute("class").equals("TravelAgntNm")){
									busDataBean.setName(COL2Child.getTextContent());
									//System.out.println("Bus Name :"+COL2Child.getTextContent());
								}else if(COL2Child.getAttribute("class").equals("BsTyp")){
									busDataBean.setType(COL2Child.getTextContent());
									//System.out.println("Bus Type :"+COL2Child.getTextContent());
								}
							}
							break;
						case "col3 booksts clearfix":
							Iterable<DomElement> COL3Childs = innerDiv.getChildElements();
							for (DomElement COL3Child : COL3Childs) {
								if(COL3Child.hasChildNodes() && (COL3Child.getAttribute("class").equals("col1") || COL3Child.getAttribute("class").equals("col2"))){
									Iterable<DomElement> innerChilds =  COL3Child.getChildElements();
									for (DomElement innerChild : innerChilds) {
										if(innerChild.getNodeName().equals("h2")){
											for (DomElement rateEle : innerChild.getChildElements()) {
												if(rateEle.hasAttribute("tickrate")){
													busDataBean.setFare(Integer.parseInt(rateEle.getTextContent().trim().replace(",","")));
													System.out.println(ServiceNo+" : Fare  :"+rateEle.getTextContent().trim());
												}
											}
											
										}else if(innerChild.getAttribute("class").equals("noseats AvailSts")){
											busDataBean.setAvbl_seats(Integer.parseInt(innerChild.getTextContent().trim().replace("seats available", "").replaceAll("\\s", "")));
											//System.out.println("Available Seats :"+innerChild.getTextContent());
										}
									}
								}
							}
							break;
						}
					}
				}
				//System.out.println(busDataBean.toString());
				if(!data.containsKey(ServiceNo))
					data.put(ServiceNo, busDataBean);
			}
		}
		return data;
	}

}
