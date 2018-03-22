package com.aurospaces.neighbourhood.bean;

import java.util.Date;

public class ReportsBean {
	protected int id   = 0;
	private String name;
	private String tomonth;
	private String frommonth ;
	private String day ;
	
	private String schemeTitle ;
	
	public String getSchemeTitle() {
		return schemeTitle;
	}
	public void setSchemeTitle(String schemeTitle) {
		this.schemeTitle = schemeTitle;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTomonth() {
		return tomonth;
	}
	public void setTomonth(String tomonth) {
		this.tomonth = tomonth;
	}
	public String getFrommonth() {
		return frommonth;
	}
	public void setFrommonth(String frommonth) {
		this.frommonth = frommonth;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
}
	