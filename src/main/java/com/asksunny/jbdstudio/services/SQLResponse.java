package com.asksunny.jbdstudio.services;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;


@XmlRootElement(name="sqlresponse")
public class SQLResponse {

	String result;
	long id;
	
	@XmlElement
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@XmlElement
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
