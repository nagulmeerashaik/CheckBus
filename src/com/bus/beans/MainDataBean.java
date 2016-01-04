package com.bus.beans;

public class MainDataBean {
	public String Url = null;
	public String From = null;
	public String To = null;
	public String RedBusUrl = null;
	public String AbhiUrl = null;
	public String RTCUrl = null;
	
	public String getAbhiUrl() {
		return AbhiUrl;
	}
	public void setAbhiUrl(String abhiUrl) {
		AbhiUrl = abhiUrl;
	}
	public String getRTCUrl() {
		return RTCUrl;
	}
	public void setRTCUrl(String rTCUrl) {
		RTCUrl = rTCUrl;
	}
	public String getRedBusUrl() {
		return RedBusUrl;
	}
	public void setRedBusUrl(String redBusUrl) {
		RedBusUrl = redBusUrl;
	}
	public String getUrl() {
		
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getFrom() {
		return From;
	}
	public void setFrom(String from) {
		From = from;
	}
	public String getTo() {
		return To;
	}
	public void setTo(String to) {
		To = to;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String date = null;
}
