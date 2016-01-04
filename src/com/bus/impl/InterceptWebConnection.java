package com.bus.impl;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.FalsifyingWebConnection;

class InterceptWebConnection extends FalsifyingWebConnection{
    public InterceptWebConnection(WebClient webClient) throws IllegalArgumentException{
        super(webClient);
    }
    @Override
    public WebResponse getResponse(WebRequest request) throws IOException {
        WebResponse response=super.getResponse(request);
        if(response.getWebRequest().getUrl().toString().contains("facebook") || response.getWebRequest().getUrl().toString().contains("googletagmanager") 
        		/*|| response.getWebRequest().getUrl().toString().contains("libs.min.acf53a50.js")
        		|| response.getWebRequest().getUrl().toString().contains("master.min.d08aac49.js")
        		|| response.getWebRequest().getUrl().toString().contains("cities.js")
        		|| response.getWebRequest().getUrl().toString().contains("SelectBus.js")
        		|| response.getWebRequest().getUrl().toString().contains("newselectbus.min.96451ee7.js")
        		|| response.getWebRequest().getUrl().toString().contains("rdbuz.com")*/ ){
            return createWebResponse(response.getWebRequest(), "", "application/javascript", 200, "Ok");
        }
        return super.getResponse(request);
    }
}
