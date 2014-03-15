package com.zitec.workshopz.base;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.zitec.workshopz.R;
import com.zitec.workshopz.base.storage.Error;
import com.zitec.workshopz.user.activities.LoginActivity;
import com.zitec.workshopz.user.entities.User;
import com.zitec.workshopz.utils.NetworkManagerHelper;
import com.zitec.workshopz.workshopz.activities.WorkshopzActivity;
import com.zitec.workshopz.workshopz.entities.Workshop;
import com.zitec.workshopz.workshopz.storage.adapters.WorkshopzCacheAdapter;
import com.zitec.workshopz.workshopz.storage.adapters.WorkshopzDBAdapter;
import com.zitec.workshopz.workshopz.storage.adapters.WorkshopzWSAdapter;
import com.zitec.workshopz.workshopz.storage.mappers.WorkshopzMapper;

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
	
	public void startWorkshopsActivity(){
		Intent i = new Intent(this, WorkshopzActivity.class);
		this.startActivity(i);
	}
	
	public void loadWorkshops(){
		final WorkshopzMapper mapper = new WorkshopzMapper();
		if(this.networkAvail = NetworkManagerHelper.isNetworkAvailable(this)){
			this.workshopsFromDb = false;
			mapper.setAdapter(new WorkshopzWSAdapter(this));
		} else {
			this.workshopsFromDb = true;
			try {
				mapper.setAdapter(new WorkshopzDBAdapter(BaseActivity.this));
			} catch (NameNotFoundException e) {
				e.printStackTrace();
				this.handleLoadError(mapper);
			}
		}
		mapper.setListener(new EntityResponseListener() {
			@Override
			public void onSuccess(ArrayList<BaseEntity> items) {
				if(items.size() < 1){
					BaseActivity.this.showGenericError(BaseActivity.this, new Error(BaseActivity.this.getResources().getString(R.string.network_error)));
				}
				if(!BaseActivity.this.workshopsFromDb){
					try {
						WorkshopzDBAdapter dbAdapter = new WorkshopzDBAdapter(BaseActivity.this);
						WorkshopzCacheAdapter cacheAdapter = new WorkshopzCacheAdapter(BaseActivity.this);
						cacheAdapter.clear();
						for(BaseEntity i : items){
							mapper.setAdapter(dbAdapter);
							mapper.save(i);
							cacheAdapter.save((Workshop)i);
						}
					} catch (NameNotFoundException e) {
						e.printStackTrace();
					}
				}
				BaseActivity.this.startWorkshopsActivity();
				BaseActivity.this.finish();
			}

			@Override
			public void onError(Error err) {
				BaseActivity.this.handleLoadError(mapper);
			}
		});
		mapper.getAll();
	}
	
	public void handleLoadError(WorkshopzMapper mapper){
		if(!BaseActivity.this.workshopsFromDb){
			BaseActivity.this.showGenericError(BaseActivity.this, new Error(BaseActivity.this.getResources().getString(R.string.network_error)));
			this.workshopsFromDb = true;
			try {
				mapper.setAdapter(new WorkshopzDBAdapter(BaseActivity.this));
			} catch (NameNotFoundException e) {
				e.printStackTrace();
				this.handleLoadError(mapper);
			}
			mapper.getAll();
		} else {
			BaseActivity.this.setContentView(R.layout.activity_splash_screen);
			BaseActivity.this.showGenericError(BaseActivity.this, new Error(BaseActivity.this.getResources().getString(R.string.network_error)));
		}
	}
}
