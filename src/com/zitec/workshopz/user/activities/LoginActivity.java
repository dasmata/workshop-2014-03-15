package com.zitec.workshopz.user.activities;

import android.os.Bundle;

import com.zitec.workshopz.base.BaseActivity;
import com.zitec.workshopz.user.views.LoginView;

public class LoginActivity extends BaseActivity {
	LoginView view;
	
	@Override
	public void onCreate(Bundle args0){
		super.onCreate(args0);
		this.view = new LoginView(this);
		this.view.initView();
		this.view.setActions();
	}
	
}
