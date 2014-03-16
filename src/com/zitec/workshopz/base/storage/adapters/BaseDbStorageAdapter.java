package com.zitec.workshopz.base.storage.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zitec.workshopz.utils.DbHelper;
import com.zitec.workshopz.utils.VersionUtils;

public class BaseDbStorageAdapter extends BaseStorageAdapter {
	protected static DbHelper dbHelper;
	protected SQLiteDatabase database;
	protected String table;
	
	public BaseDbStorageAdapter(Context ctx) throws NameNotFoundException {
		super(ctx);
		if(BaseDbStorageAdapter.dbHelper == null){
			BaseDbStorageAdapter.dbHelper = new DbHelper(ctx.getApplicationContext(), null, VersionUtils.getVersionCode(ctx));
		}
		this.database = dbHelper.getWritableDatabase();
	}

	public ContentValues getValues(HashMap<String, String> data){
		ContentValues values = new ContentValues();
		Set<String> keys = data.keySet();
		for(String i : keys){
			values.put(i, data.get(i));
		}
		
		return values;
	}
	
	@Override
	public void save(HashMap<String, String> data) {
		
		if(data.get("id") != null && !"".equals(data.get("id"))){
			String id = data.get("id");
			data.remove("id");
			this.database.update(this.table, this.getValues(data), "id = ?", new String[]{id});
		} else {
			long lastId;
			this.database.insert(this.table, null, this.getValues(data));
			String query = "SELECT ROWID from " + this.table + " order by ROWID DESC limit 1";
			Cursor c = this.database.rawQuery(query, null);
			if (c != null && c.moveToFirst()) {
			    lastId = c.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
			    data.put("id", String.valueOf(lastId));
			}
		}
		this.mapper.onSuccess(data);
	}
	
	public ArrayList<HashMap<String, String>> processCursor(Cursor c){
		ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			HashMap<String, String> row = new HashMap<String, String>();
			int columnsNr = c.getColumnCount();
			for(int i = 0; i < columnsNr; i++){
				row.put(c.getColumnName(i), c.getString(i));
			}
			 results.add(row);
		}
		
		return results;
	}

}
