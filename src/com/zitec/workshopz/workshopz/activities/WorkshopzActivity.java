package com.zitec.workshopz.workshopz.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.zitec.workshopz.R;
import com.zitec.workshopz.base.BaseActivity;
import com.zitec.workshopz.workshopz.WorkshopClickListener;
import com.zitec.workshopz.workshopz.fragments.WorkshopDetail;
import com.zitec.workshopz.workshopz.fragments.WorkshopzList;
import com.zitec.workshopz.workshopz.services.SyncDbService;
import com.zitec.workshopz.workshopz.storage.adapters.WorkshopzCacheAdapter;

public class WorkshopzActivity extends BaseActivity implements WorkshopClickListener{

	WorkshopzList listFragment;
	WorkshopDetail detailFragment;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workshopz);
        
        this.listFragment = new WorkshopzList();
        if (savedInstanceState != null) {
        	return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentContainer, this.listFragment).commit();
        this.startUpdateService();
    }

	@Override
	public void workshopListClick(int id) {
		this.detailFragment = (WorkshopDetail)this.getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
		if(this.detailFragment != null){
			if(id == -1){
				id = Integer.valueOf(((new WorkshopzCacheAdapter(this)).get().get(0).getRemoteId()));
			}
			this.detailFragment.setWorkshopId(id);
		} else {
			if(id == -1){
				return;
			}
			this.detailFragment = new WorkshopDetail();
			Bundle args = new Bundle();
			args.putInt(WorkshopDetail.WORKSHOP_ID, id);
			this.detailFragment.setArguments(args);
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.fragmentContainer, this.detailFragment);
			transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();
		}
	}
	
	public void startUpdateService(){
		Intent i= new Intent(this.getApplicationContext(), SyncDbService.class);
		i.putExtra("test", "aaaa");
		this.getApplicationContext().startService(i); 
	}
}
