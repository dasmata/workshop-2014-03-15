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
					this.loadWorkshops();
				} else {
					System.out.println("not auth");
					// not auth
				}
			}
			
			@Override
			public void onError(Error err) {
				// not auth
				System.out.println("error");
			}
		});
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("current_identity", "1");
		mapper.find(params);
	}
	
	public void loadWorkshops(){
		
	}
}
