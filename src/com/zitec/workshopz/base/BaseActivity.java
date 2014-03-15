package com.zitec.workshopz.base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.zitec.workshopz.base.storage.Error;
import com.zitec.workshopz.user.activities.LoginActivity;
import com.zitec.workshopz.user.entities.User;

public class BaseActivity extends FragmentActivity {

	protected static Boolean ACTIVITY_NETWORK_ERROR;
	protected static User identity;
	protected boolean networkAvail;
	protected boolean workshopsFromDb;
	
	public BaseActivity(){
		BaseActivity.ACTIVITY_NETWORK_ERROR = false;
		this.workshopsFromDb = false;
	}
	
	public void showGenericError(Context ctx, Error err){
		Toast tst = Toast.makeText(ctx, err.getMessage(), Toast.LENGTH_LONG);
		tst.show();
	}
	
	public void setIdentity(User usr){
		BaseActivity.identity = usr;
	}
	
	public User getIdentity(){
		return BaseActivity.identity;
	}
	
	public void initLoginActivity(){
		Intent i = new Intent(this, LoginActivity.class);
		this.startActivity(i);
	}
}
