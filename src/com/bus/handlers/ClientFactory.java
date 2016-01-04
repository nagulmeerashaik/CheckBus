package com.bus.handlers;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

public class ClientFactory {
	private  static WebClient webclient = null;
	
	private ClientFactory(){}
	public static WebClient getWebClient(){
		if(webclient == null){
			WebClient webclient  = new WebClient(BrowserVersion.FIREFOX_38);
			ClientFactory.webclient = webclient;
			return ClientFactory.webclient;
		}else{
			return ClientFactory.webclient;
		}
	}

}
