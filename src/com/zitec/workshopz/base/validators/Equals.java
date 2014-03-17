package com.zitec.workshopz.base.validators;

import android.content.Context;

public class Equals extends BaseValidator {

	String baseValue;
	
	public Equals(Context ctx) {
		super(ctx);
		this.message = "";
	}

	public void setBaseValue(String baseValue){
		this.baseValue = baseValue;
	}
	
	public boolean validate(String value){
		if(this.baseValue == null){
			if(value == null){
				return true;
			}
			return false;
		}
		return this.baseValue.equals(value);
	}
}
