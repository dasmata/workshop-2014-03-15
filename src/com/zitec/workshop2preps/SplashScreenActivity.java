package com.zitec.workshop2preps;

import java.util.ArrayList;
import java.util.HashMap;

import com.zitec.workshop2preps.base.BaseActivity;
import com.zitec.workshop2preps.base.BaseEntity;
import com.zitec.workshop2preps.base.EntityResponseListener;
import com.zitec.workshop2preps.base.storage.Error;
import com.zitec.workshop2preps.user.storage.adapters.UserDbAdapter;
import com.zitec.workshop2preps.user.storage.mappers.UserMapper;
import com.zitec.workshop2preps.utils.NetworkManagerHelper;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public class SplashScreenActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	protected void onResume(){
		super.onResume();
		setContentView(R.layout.activity_splash_screen);
		this.init();
	}
	
	public void init(){
		if(BaseActivity.identity != null){
			this.loadWorkshops();
		}
		UserMapper mapper = new UserMapper();
		try {
			mapper.setAdapter(new UserDbAdapter(this));
			mapper.setListener(new EntityResponseListener() {
				
				@Override
				public void onSuccess(ArrayList<BaseEntity> obj) {
					if(obj == null || obj.size() == 0){
						if(!NetworkManagerHelper.isNetworkAvailable(SplashScreenActivity.this)){
							SplashScreenActivity.this.showGenericError(SplashScreenActivity.this, new Error(SplashScreenActivity.this.getResources().getString(R.string.network_error)));
							return;
						}
						SplashScreenActivity.this.startLoginActivity();
					} else {
						SplashScreenActivity.this.loadWorkshops();
					}
				}
				
				@Override
				public void onError(Error err) {
					if(!NetworkManagerHelper.isNetworkAvailable(SplashScreenActivity.this)){
						SplashScreenActivity.this.showGenericError(SplashScreenActivity.this, new Error(SplashScreenActivity.this.getResources().getString(R.string.network_error)));
						return;
					}
					SplashScreenActivity.this.startLoginActivity();
					SplashScreenActivity.this.finish();
				}
			});
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("current_identity", "true");
			mapper.find(params);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}
}