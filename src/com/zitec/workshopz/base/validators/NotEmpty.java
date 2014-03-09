package com.zitec.workshopz.base.validators;

public class NotEmpty extends BaseValidator{

	@Override
	public boolean validate(String value){
		if(value == null || "".equals(value)){
			return false;
		}
		return true;
	}
	
}
