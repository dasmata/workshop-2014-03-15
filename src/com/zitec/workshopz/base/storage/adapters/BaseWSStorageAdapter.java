package com.zitec.workshopz.base.storage.adapters;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.zitec.workshopz.R;

import android.content.Context;

public class BaseWSStorageAdapter extends BaseStorageAdapter implements Listener<JSONObject>, ErrorListener{

	protected RequestQueue queue;
	
	public BaseWSStorageAdapter(Context ctx) {
		super(ctx);
		this.queue = Volley.newRequestQueue(ctx);
	}
	
	public String getBaseUrl(){
		return this.context.getResources().getString(R.string.ws_base_url);
	}
	
	protected String getErrorMessage(String jsonResponse){
		try {
			JSONObject obj = new JSONObject(jsonResponse);
			return obj.getString("error_message");
		} catch (JSONException e) {
			return null;
		}
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		String errorMessage = "";
		if(error.networkResponse != null){
			String serverResponse = new String(error.networkResponse.data);
			errorMessage = this.getErrorMessage(serverResponse);
		}
		
		if(errorMessage == null || "".equals(errorMessage)){
			errorMessage = this.context.getResources().getString(R.string.network_error);
		}
		this.mapper.onError(errorMessage);
	}

	@Override
	public void onResponse(JSONObject serverResponse) {

	}
}
