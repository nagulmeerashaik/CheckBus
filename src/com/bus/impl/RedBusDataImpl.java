package com.bus.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.bus.beans.MainDataBean;
import com.bus.handlers.ClientFactory;
import com.bus.handlers.UserBeanFactory;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.bus.impl.InterceptWebConnection;



public class RedBusDataImpl implements DataExtractorFactory{

	@Override
	public HtmlPage getData() {
		// TODO Auto-generated method stub
		MainDataBean mainDataBean = (MainDataBean) UserBeanFactory.getBeanFactoryObject().getBean("mainDataBean");
		String URL = mainDataBean.getRedBusUrl();
		URL = "https://www.redbus.in/";
		ClientFactory cf = (ClientFactory) UserBeanFactory.getBeanFactoryObject().getBean("clientFactory");
		WebClient client = cf.getWebClient();
//		try {
//			client.getOptions().setJavaScriptEnabled(true);
//			client.getOptions().setThrowExceptionOnScriptError(false);
//			new InterceptWebConnection(client);
//			HtmlPage page = client.getPage(URL);
//			client.waitForBackgroundJavaScript(10000);
//			System.out.print(page.asXml());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String html;
		try{
//			client.getOptions().setUseInsecureSSL(true);
//			client.se
			//client.getOptions().setJavaScriptEnabled(true);
			//client.getOptions().setThrowExceptionOnScriptError(false);
			new InterceptWebConnection(client);
			//client.addRequestHeader("Host", "www.redbus.in");
			//client.addRequestHeader("Referer", "https://www.redbus.in/");
			//Booking/SelectBus.aspx?fromCityId=248&toCityId=134&doj=12-Dec-2015&busType=Any&opId=0
			HtmlPage page = client.getPage("https://www.redbus.in/");
			client.waitForBackgroundJavaScript(1000);
			
			DomElement source = page.getElementById("txtSource");
			DomElement destination = page.getElementById("txtDestination");
			DomElement date = page.getElementById("txtOnwardCalendar");
			DomElement search = page.getElementById("searchBtn");
//			List<DomElement> inputs = (List<DomElement>) page.getByXPath("//input[@class='XXinput']");
//			HtmlTextInput source=null,destination=null,date1=null;
//			for (DomElement domElement : inputs) {
//				System.out.println("Coming  into sourrce");
//				if(domElement.getAttribute("id").equals("txtSource")){
//					System.out.println("Coming  into sourrce");
//					source = (HtmlTextInput) domElement;
//				}else if (domElement.getAttribute("id").equals("txtDestination")){
//					System.out.println("Coming  into destination");
//					destination =(HtmlTextInput) domElement;
//				}
//			}
//			List<DomElement> Cal_inputs = (List<DomElement>) page.getByXPath("//input[@class='XXinput calendar']");
//			HtmlElement searchButton = (HtmlElement) page.getFirstByXPath("//button[@class='RB Xbutton']");
//			for (DomElement domElement : Cal_inputs) {
//				if(domElement.getAttribute("id").equals("txtOnwardCalendar")){
//					System.out.println("Coming  into calandar");
//					date1 =(HtmlTextInput) domElement;
//				}
//			}
			source.setAttribute("value", "Visakapatnam");
			//source.setValueAttribute("Visakapatnam");
			destination.setAttribute("value", "Vijayawada");
			//destination.setValueAttribute("Vijayawada");
			date.setAttribute("value", "12-12-2015");
			
			//date.setValueAttribute("27-10-2015");
//			client.getOptions().setThrowExceptionOnScriptError(false);
//			client.getOptions().setThrowExceptionOnFailingStatusCode(false);
//			
			HtmlPage page1 = search.click();
			client.waitForBackgroundJavaScript(200);
			html = page1.asXml();
			System.out.println(html);
		}catch(Exception e ){
			e.printStackTrace();
		}
				
		return null;
	}

	@Override
	public HashMap extractData(HtmlPage page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean setData(HashMap data) {
		// TODO Auto-generated method stub
		return null;
	}

}
