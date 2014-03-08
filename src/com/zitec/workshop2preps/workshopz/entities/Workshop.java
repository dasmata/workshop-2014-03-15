package com.zitec.workshop2preps.workshopz.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import com.zitec.workshop2preps.base.BaseEntity;
import com.zitec.workshop2preps.user.entities.User;

public class Workshop extends BaseEntity {
	
	protected String id;
	protected String name;
	protected Date startDate;
	protected Date endDate;
	protected String theme;
	protected String rating;
	protected String cost;
	protected String currency;
	protected String remoteId;
	protected ArrayList<User> speakers;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemoteId() {
		return remoteId;
	}
	public void setRemoteId(String id) {
		this.remoteId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setStartDate(String startDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");  
		try {  
		    Date date = format.parse(startDate);  
		    this.startDate = date;  
		} catch (ParseException e) {    
		    e.printStackTrace(); 
		}
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setEndDate(String startDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");  
		try {  
		    Date date = format.parse(startDate);  
		    this.endDate = date;  
		} catch (ParseException e) {    
		    e.printStackTrace(); 
		}
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public ArrayList<User> getSpeakers() {
		return speakers;
	}
	public void setSpeakers(ArrayList<BaseEntity> speakers) {
		this.speakers = new ArrayList<User>();
		for(BaseEntity usr : speakers){
			this.speakers.add((User)usr);
		}
	}
	
	
}
