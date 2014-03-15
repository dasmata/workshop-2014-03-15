package com.zitec.workshopz.user.views;


import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.zitec.workshopz.R;
import com.zitec.workshopz.base.BaseView;
import com.zitec.workshopz.user.activities.LoginActivity;

public class LoginView extends BaseView {

	protected LoginActivity act;
	EditText username;
	EditText password;
	Button submit;
	
	public LoginView(LoginActivity act){
		this.act = act;
	}
	
	@Override
	public void initView() {
		this.act.setContentView(R.layout.login_activity);
		this.username = (EditText)this.act.findViewById(R.id.username);
		this.password = (EditText)this.act.findViewById(R.id.password);
		this.submit = (Button)this.act.findViewById(R.id.submit);
	}

	@Override
	public void setActions() {
		this.submit.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				LoginView.this.submitLogin();
			}
		});
		this.password.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	protected void submitLogin(){
		
	}
}
