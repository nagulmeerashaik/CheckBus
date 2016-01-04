package com.bus.impl;

import java.util.HashMap;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public interface DataExtractorFactory {
	
	public HtmlPage getData();
	public HashMap extractData(HtmlPage page);
	public Boolean setData(HashMap data);
	
}