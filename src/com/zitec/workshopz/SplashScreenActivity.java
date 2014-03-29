package com.zitec.workshopz;
import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;

import com.zitec.workshopz.base.BaseActivity;
import com.zitec.workshopz.base.BaseEntity;
import com.zitec.workshopz.base.BaseView;
import com.zitec.workshopz.base.EntityResponseListener;
import com.zitec.workshopz.base.storage.Error;
import com.zitec.workshopz.user.storage.adapters.UserDbAdapter;
import com.zitec.workshopz.user.storage.mappers.UserMapper;
import com.zitec.workshopz.utils.NetworkManagerHelper;


public class SplashScreenActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle aa){
		super.onCreate(aa);
		BaseView view = new SplashScreenView(this);
		view.initView();
		this.checkIdentity();
	}
	
	public void checkIdentity(){
		if(BaseActivity.identity != null){
			this.loadWorkshops();
		}
		UserMapper mapper = new UserMapper();
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
	}
	
}
