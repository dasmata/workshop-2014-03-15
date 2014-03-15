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
			return;
		}
		UserMapper mapper = new UserMapper();
		UserDbAdapter adapter= new UserDbAdapter(this);
		mapper.setAdapter(adapter);
		mapper.setListener(new EntityResponseListener() {
			
			@Override
			public void onSuccess(ArrayList<BaseEntity> obj) {
				if(obj.size() > 0){
					SplashScreenActivity.this.loadWorkshops();
				} else {
					SplashScreenActivity.this.initLoginActivity();
					SplashScreenActivity.this.finish();
				}
			}
			
			@Override
			public void onError(Error err) {
				SplashScreenActivity.this.initLoginActivity();
			}
		});
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("current_identity", "true");
		mapper.find(params);
	}
	
	public void loadWorkshops(){
		
	}
}
