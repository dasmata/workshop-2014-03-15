package com.zitec.workshopz.validators;

import com.zitec.workshopz.R;
import com.zitec.workshopz.base.validators.NotEmpty;

import android.util.SparseArray;

public class LoginValidator {

	protected SparseArray<Integer> errors;
	
	public boolean validate(SparseArray<String> values){
		String username = values.get(R.id.usernameInput);
		String password = values.get(R.id.passwordInput);
		NotEmpty validator = new NotEmpty();
		this.errors = new SparseArray<Integer>();
		if(!validator.validate(username)){
			this.errors.put(R.id.usernameInput, R.string.empty_username_error);
		}
		if(!validator.validate(password)){
			this.errors.put(R.id.passwordInput, R.string.empty_password_error);
		}
		
		return this.errors.size() < 1;
	}
	
	public SparseArray<Integer> getErrors(){
		return this.errors;
	}
}
