package com.zitec.workshopz.base.validators;

import com.zitec.workshopz.R;

import android.content.Context;

public class NotEmpty extends BaseValidator{
	
	public NotEmpty(Context ctx) {
		super(ctx);
		this.context = ctx;
		this.message = ctx.getResources().getString(R.string.not_empty_message);
	}
	
	@Override
	public boolean validate(String value){
		if(value == null || "".equals(value)){
			return false;
		}
		return true;
	}
	
}
