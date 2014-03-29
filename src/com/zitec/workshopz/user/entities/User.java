package com.zitec.workshopz.user.entities;

import com.zitec.workshopz.base.BaseEntity;

public class User extends BaseEntity{

	protected String email;
	protected String id;
	protected String position;
	protected String name;
	protected String phoneNumber;
	protected String remoteId;
	protected String currentIdentity;
	protected String password;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRemoteId() {
		return remoteId;
	}
	public void setRemoteId(String localId) {
		this.remoteId = localId;
	}
	public String getCurrentIdentity() {
		return currentIdentity;
	}
	public void setCurrentIdentity(String currentIdentity) {
		this.currentIdentity = currentIdentity;
	}
	public void setPassword(String string) {
		this.password = string;
	}
	public String getPassword()
	{
		return password;
	}
}
