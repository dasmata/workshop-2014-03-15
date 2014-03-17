package com.zitec.workshopz.base.validators;

import com.zitec.workshopz.R;

import android.content.Context;

abstract public class BaseValidator {

	protected Context context;
	protected String message;
	
	public BaseValidator(Context ctx){
		this.context = ctx;
	}
	
	public boolean validate(String value) throws Exception{
		throw new Exception("This method is not implemented");
	}
	
	public boolean validate(Integer value) throws Exception{
		throw new Exception("This method is not implemented");
	}
	
	public String getMessage(){
		return this.message;
	}
}
