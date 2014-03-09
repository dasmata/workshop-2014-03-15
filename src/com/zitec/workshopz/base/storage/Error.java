package com.zitec.workshopz.base.storage;

public class Error {

	protected String message;
	
	public Error(String message){
		this.message = message;
	}
	
	
	public String getMessage()
	{
		return this.message;
	}
}
