package com.zitec.workshopz.workshopz.services;


import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.zitec.workshopz.base.BaseEntity;
import com.zitec.workshopz.base.EntityResponseListener;
import com.zitec.workshopz.workshopz.entities.Workshop;
import com.zitec.workshopz.workshopz.storage.adapters.WorkshopzCacheAdapter;
import com.zitec.workshopz.workshopz.storage.adapters.WorkshopzWSAdapter;
import com.zitec.workshopz.workshopz.storage.mappers.WorkshopzMapper;

public class SyncDbService extends IntentService {

	public SyncDbService() {
		super("WorkshopzSync");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		final Context appContext = this.getApplicationContext(); 
		WorkshopzWSAdapter adapter = new WorkshopzWSAdapter(appContext);
		WorkshopzMapper mapper = new WorkshopzMapper();
		mapper.setAdapter(adapter);
		mapper.setListener(new EntityResponseListener() {
			
			@Override
			public void onSuccess(ArrayList<BaseEntity> obj) {
				WorkshopzCacheAdapter cache = new WorkshopzCacheAdapter(appContext);
				cache.clear();
				for(BaseEntity item : obj){
					Workshop workshop = (Workshop)item;
					cache.save(workshop);
				}
				
			}

			@Override
			public void onError(com.zitec.workshopz.base.storage.Error err) {
				System.out.println(err.getMessage());
			}
		});
		mapper.getAll();
		
	}

	public static void scheduleToRun(Context ctx){
		Calendar cal = Calendar.getInstance();

		Intent intent = new Intent(ctx, SyncDbService.class);
		PendingIntent pintent = PendingIntent.getService(ctx, 0, intent, 0);

		AlarmManager alarm = (AlarmManager)ctx.getSystemService(Context.ALARM_SERVICE);
		// Start every 30 seconds
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 10*1000, pintent);
	}
}
