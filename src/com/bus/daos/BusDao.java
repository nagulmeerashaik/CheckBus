package com.bus.daos;

import hbmpojos.BusXDetails;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.bus.beans.BusDataBean;
import com.gargoylesoftware.htmlunit.javascript.host.Map;

public class BusDao {
	HibernateTemplate template;

	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	public void saveData(HashMap<Integer , BusXDetails> data){
		for (Entry<Integer, BusXDetails> entry : data.entrySet()) {
			
		}
	}
}
