package com.zitec.workshopz;

import com.zitec.workshopz.base.BaseView;

public class SplashScreenView extends BaseView {

	protected SplashScreenActivity act;
	
	public SplashScreenView(SplashScreenActivity act){
		this.act = act;
	}
	
	@Override
	public void initView() {
		this.act.setContentView(R.layout.splashscreen_activity);

	}

	@Override
	public void setActions() {
		// TODO Auto-generated method stub

	}

}
