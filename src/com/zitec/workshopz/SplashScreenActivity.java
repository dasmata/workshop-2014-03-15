package com.zitec.workshopz;
import java.util.ArrayList;

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
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onError(Error err) {
				// TODO Auto-generated method stub
			}
		});
		
	}
	
	public void loadWorkshops(){
		
	}
}
