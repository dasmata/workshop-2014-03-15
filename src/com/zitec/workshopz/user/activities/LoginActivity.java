package com.zitec.workshopz.user.activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.SparseArray;

import com.zitec.workshopz.R;
import com.zitec.workshopz.base.BaseActivity;
import com.zitec.workshopz.base.BaseEntity;
import com.zitec.workshopz.base.EntityResponseListener;
import com.zitec.workshopz.base.storage.Error;
import com.zitec.workshopz.base.validators.NotEmpty;
import com.zitec.workshopz.user.entities.User;
import com.zitec.workshopz.user.fragments.RegisterDialog;
import com.zitec.workshopz.user.storage.adapters.UserDbAdapter;
import com.zitec.workshopz.user.storage.adapters.UserWSAdapter;
import com.zitec.workshopz.user.storage.mappers.UserMapper;
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
		this.errors = new SparseArray<String>();
	}
	
	public boolean validateLogin(SparseArray<String> values){
		boolean result = true;
		NotEmpty validator = new NotEmpty();
		int nr = values.size();
		this.errors.clear();
		for(int i = 0; i < nr; i++){
			int key = values.keyAt(i);
			if(!validator.validate(values.valueAt(i))){
				result = false;
				this.errors.put(key, this.view.getEmptyError(key));
			}
		}
		return result;
	}
	
	public SparseArray<String> getErrors(){
		return this.errors;
	}
	
	public void register()
	{
		RegisterDialog register = new RegisterDialog();
		register.show(getSupportFragmentManager(), "register");
	}
	
	public void register(User newUser)
	{
		final UserMapper mapper = new UserMapper();
		final User user = newUser;
		mapper.setAdapter(new UserWSAdapter(this));
		mapper.setListener(new EntityResponseListener() {
			
			@Override
			public void onSuccess(ArrayList<BaseEntity> obj)
			{
				LoginActivity.this.login(user.getEmail(), user.getPassword());
			}
			
			@Override
			public void onError(Error err)
			{
				LoginActivity.this.showGenericError(
						LoginActivity.this,
						err);
			}
		});
		mapper.save(newUser);
	}
	
	public void login(String username, String password){
		final UserMapper mapper = new UserMapper();
		mapper.setAdapter(new UserWSAdapter(this));
		mapper.setListener(new EntityResponseListener() {
			
			@Override
			public void onSuccess(ArrayList<BaseEntity> obj) {
				if(obj.size() < 1){
					LoginActivity.this.showGenericError(
							LoginActivity.this,
							new Error(LoginActivity.this.getResources().getString(R.string.network_error)));
					return;
				}
				User usr = (User) obj.get(0);
				BaseActivity.identity = usr;
				usr.setCurrentIdentity("true");

				mapper.setAdapter(new UserDbAdapter(LoginActivity.this));
				mapper.setListener(new EntityResponseListener() {
					
					@Override
					public void onSuccess(ArrayList<BaseEntity> obj)
					{
						LoginActivity.this.loadWorkshops();
					}
					
					@Override
					public void onError(Error err)
					{
						LoginActivity.this.showGenericError(
								LoginActivity.this,
								err);
					}
				});
				
				mapper.save(usr);
			}
			
			@Override
			public void onError(Error err)
			{
				LoginActivity.this.showGenericError(
						LoginActivity.this,
						err);
			}
		});
		mapper.getEntity(username, password);
		
		
		
		
		
		
		
		
		
		
	}
}
