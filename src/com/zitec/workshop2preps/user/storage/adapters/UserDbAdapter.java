package com.zitec.workshop2preps.user.storage.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;

import com.zitec.workshop2preps.base.storage.adapters.BaseDbStorageAdapter;

public class UserDbAdapter extends BaseDbStorageAdapter {

	public static final String tableName = "users";
	
	public UserDbAdapter(Context ctx) throws NameNotFoundException {
		super(ctx);
		this.table = UserDbAdapter.tableName;
	}
	
	public void find(HashMap<String, String> params){
		String sql = "select * from users";
		ArrayList<String> args = new ArrayList<String>(); 
		if(params != null && params.size() > 0){
			sql += " where ";
			Set<String> keys = params.keySet();
			int x = 0;
			for(String i : keys){
				if(x > 0){
					sql += " and ";
				}
				args.add(params.get(i));
				sql += i + "= ?";
				x++;
			}
 		}
		Cursor c = this.database.rawQuery(sql, (String[])args.toArray(new String[args.size()]));
		this.mapper.onSuccess(this.processCursor(c));
	}
}
