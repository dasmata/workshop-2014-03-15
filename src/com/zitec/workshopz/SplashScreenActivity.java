package com.zitec.workshopz;
import android.os.Bundle;

import com.zitec.workshopz.R;
import com.zitec.workshopz.base.BaseActivity;
import com.zitec.workshopz.base.BaseView;


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
	}
	
	public void loadWorkshops(){
		
	}
}
