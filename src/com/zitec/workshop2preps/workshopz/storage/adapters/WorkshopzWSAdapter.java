package com.zitec.workshop2preps.workshopz.storage.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.zitec.workshop2preps.base.storage.adapters.BaseWSStorageAdapter;

public class WorkshopzWSAdapter extends BaseWSStorageAdapter{

	String methodName = "workshopz";
	
	public WorkshopzWSAdapter(Context ctx) {
		super(ctx);
	}

	public void getAll(){
		String url = this.getBaseUrl() + "/" + this.methodName;
		
		JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, this, this){
			@Override
			public HashMap<String, String> getHeaders(){
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("Content-Type", "application/json");
				params.put("Accept", "application/json");
				return params;
			}
		};
		this.queue.add(req);
	}

	@Override
	public void onResponse(JSONObject serverResponse) {
		ArrayList<HashMap<String, String>> response = new ArrayList<HashMap<String, String>>();
		try {
			JSONArray workshops = serverResponse.getJSONArray("workshopz");
			int nr = workshops.length();
			for(int i = 0; i < nr; i++){
				JSONObject item = workshops.getJSONObject(i);
				HashMap<String, String> parsedItem = new HashMap<String, String>();
				parsedItem.put("remote_id", item.getString("id"));
				parsedItem.put("name", item.getString("name"));
				parsedItem.put("start_date", item.getString("start_date"));
				parsedItem.put("end_date", item.getString("end_date"));
				parsedItem.put("theme", item.getString("theme"));
				parsedItem.put("rating", item.getString("rating"));
				parsedItem.put("cost", item.getString("cost"));
				parsedItem.put("currency", item.getString("currency"));
				parsedItem.put("speakers", item.getString("speakers"));
				response.add(parsedItem);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.mapper.onSuccess(response);
	}
}
