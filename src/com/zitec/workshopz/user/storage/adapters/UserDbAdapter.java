package com.zitec.workshopz.user.storage.adapters;

import java.util.HashMap;

import com.zitec.workshopz.base.storage.adapters.BaseStorageAdapter;
import com.zitec.workshopz.utils.DbHelper;
import com.zitec.workshopz.utils.VersionUtils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class UserDbAdapter extends BaseStorageAdapter{

	DbHelper database;
	
	public UserDbAdapter(Context ctx) {
		super(ctx);
		try {
			this.database = new DbHelper(ctx, null, VersionUtils.getVersionCode(ctx));
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void find(HashMap<String, String> data){
		
	}
}