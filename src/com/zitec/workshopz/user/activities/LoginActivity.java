package com.zitec.workshopz.user.activities;

import android.os.Bundle;
import android.util.SparseArray;

import com.zitec.workshopz.R;
import com.zitec.workshopz.base.BaseActivity;
import com.zitec.workshopz.base.validators.NotEmpty;
import com.zitec.workshopz.user.views.LoginView;

public class LoginActivity extends BaseActivity {
	LoginView view;
	SparseArray<String> errors;
	
	@Override
	public void onCreate(Bundle args0){
		super.onCreate(args0);
		this.view = new LoginView(this);
		this.view.initView();
		this.view.setActions();
	}
	
	public boolean validateLogin(SparseArray<String> values){
		boolean result = true;
		NotEmpty validator = new NotEmpty();
		int nr = values.size();
		for(int i = 0; i < nr; i++){
			int key = values.keyAt(i);
			if(validator.validate(values.valueAt(i))){
				result = false;
				this.errors.put(key, this.view.getEmptyError(key));
			}
		}
		return result;
	}
	
	public SparseArray<String> getErrors(){
		return this.errors;
	}
}
