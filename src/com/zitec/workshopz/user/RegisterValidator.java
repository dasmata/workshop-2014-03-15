package com.zitec.workshopz.user;

import com.zitec.workshopz.R;
import com.zitec.workshopz.base.validators.BaseValidator;
import com.zitec.workshopz.base.validators.EmailAddress;
import com.zitec.workshopz.base.validators.Equals;
import com.zitec.workshopz.base.validators.NotEmpty;

import android.content.Context;
import android.util.SparseArray;

public class RegisterValidator {

	SparseArray<String> errors;
	SparseArray<String> values;

	public RegisterValidator(){
		this.errors = new SparseArray<String>();
	}
	
	
	public void validate(Context ctx, SparseArray<String> values){
		this.values = values;
		int nr = values.size();
		this.errors.clear();
		BaseValidator validator = new NotEmpty(ctx);
		for(int i = 0; i < nr; i++){
			try {
				if(!validator.validate(values.valueAt(i))){
					this.errors.append(values.keyAt(i), validator.getMessage());
				}
			} catch (Exception e) {
				this.errors.append(values.keyAt(i), validator.getMessage());
			}
		}
		this.validateEmail(ctx);
		this.validateConfirmPassword(ctx);
	}
	
	protected void validateEmail(Context ctx){
		if(this.errors.get(R.id.emailAddress) != null){
			return;
		}
		BaseValidator validator = new EmailAddress(ctx);
		String value = this.values.get(R.id.emailAddress);
		try {
			if(!validator.validate(value)){
				this.errors.append(R.id.emailAddress, validator.getMessage());
			}
		} catch (Exception e) {
			this.errors.append(R.id.emailAddress, validator.getMessage());
		}
	}

	protected void validateConfirmPassword(Context ctx){
		if(this.errors.get(R.id.confirmPassword) != null || this.errors.get(R.id.password) != null){
			return;
		}
		
		String value = values.get(R.id.confirmPassword);
		Equals validator = new Equals(ctx);
		validator.setBaseValue(this.values.get(R.id.password));
		try {
			if(!validator.validate(value)){
				this.errors.append(R.id.confirmPassword, ctx.getResources().getString(R.string.paswords_dont_match));
			}
		} catch (Exception e) {
			this.errors.append(R.id.confirmPassword, ctx.getResources().getString(R.string.paswords_dont_match));
		}
	}
	
	public SparseArray<String> getErrors(){
		return this.errors;
	}
	
}
