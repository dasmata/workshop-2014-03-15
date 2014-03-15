package com.zitec.workshopz.user.storage.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;

import com.zitec.workshopz.base.storage.adapters.BaseStorageAdapter;
import com.zitec.workshopz.utils.DbHelper;
import com.zitec.workshopz.utils.VersionUtils;

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
		ArrayList<String> params = new ArrayList<String>();
		String sql = "select * from users where 1 = 1";
		for(String key: data.keySet()){
			sql += " and " + key + " = ?";
			params.add(data.get(key));
		}
		Cursor crs = this.database.getWritableDatabase().rawQuery(sql, (String[])params.toArray());
		this.mapper.onSuccess(this.getRows(crs));
	}
	
	protected ArrayList<HashMap<String, String>> getRows(Cursor crs){
		ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
		crs.moveToFirst();
		for (crs.moveToFirst(); !crs.isAfterLast(); crs.moveToNext()) {
			HashMap<String, String> row = new HashMap<String, String>();
			int columnsNr = crs.getColumnCount();
			for(int i = 0; i < columnsNr; i++){
				row.put(crs.getColumnName(i), crs.getString(i));
			}
			results.add(row);
		}		
		return results;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}