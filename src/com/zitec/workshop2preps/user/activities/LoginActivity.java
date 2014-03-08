package com.zitec.workshop2preps.user.activities;


import com.zitec.workshop2preps.R;

import com.zitec.workshop2preps.user.entities.User;
import com.zitec.workshop2preps.user.helpers.LoginHelper;
import com.zitec.workshop2preps.user.views.LoginView;
import com.zitec.workshop2preps.validators.LoginValidator;
import com.zitec.workshop2preps.base.BaseActivity;
import com.zitec.workshop2preps.base.storage.Error;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;

public class LoginActivity extends BaseActivity {

	LoginView view;
	LoginValidator validator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.validator = new LoginValidator();
		this.view = new LoginView(this);
		this.view.initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onLoginSubmit(SparseArray<String> values){
		if(this.validator.validate(values)){
			this.login(values);
		} else {
			this.view.showErrors(this.validator.getErrors());
		}
	}
	
	public void login(SparseArray<String> values){
		String username = values.valueAt(0);
		String password = values.valueAt(1);
		LoginHelper.login(this, username, password, new LoginHelper.LoginListener(){
			@Override
			public void onLogin(User usr) {
				LoginActivity.this.setIdentity(usr);
				LoginActivity.this.loadWorkshops();
			}

			@Override
			public void onError(Error err) {
				LoginActivity.this.showGenericError(LoginActivity.this, err);
			}
			
		});
	}
}
