package com.zitec.workshop2preps.user.views;

import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.zitec.workshop2preps.R;
import com.zitec.workshop2preps.base.BaseView;
import com.zitec.workshop2preps.user.activities.LoginActivity;

public class LoginView extends BaseView{

	protected LoginActivity activity;
	
	protected EditText username;
	protected EditText password;
	protected Button loginBtn;
	
	public LoginView(LoginActivity act){
		this.activity = act;
	}
	
	public void initView(){
		this.activity.setContentView(R.layout.activity_main);
		this.username = (EditText) this.activity.findViewById(R.id.usernameInput);
		this.password = (EditText) this.activity.findViewById(R.id.passwordInput);
		this.loginBtn = (Button) this.activity.findViewById(R.id.loginSubmit);
		this.setActions();
	}
	
	public void submitLogin(){
		SparseArray<String> values = new SparseArray<String>();
		values.put(R.id.usernameInput, this.username.getText().toString());
		values.put(R.id.passwordInput, this.password.getText().toString());

		this.activity.onLoginSubmit(values);
	}
	
	public void setActions(){
		this.password.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(event == null){
					return false;
				}
				LoginView.this.submitLogin();
				return false;
			}
			
		});
		
		this.loginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LoginView.this.submitLogin();
			}
		});
	}
	
	public void showErrors(SparseArray<Integer> errors){
		int errLength = errors.size();
		for(int i = 0; i < errLength; i++){
			((EditText)this.activity.findViewById(errors.keyAt(i))).setError(this.activity.getResources().getString(errors.valueAt(i)));
		}
	}
}
