package com.zitec.workshopz.user.storage.adapters;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.zitec.workshopz.R;
import com.zitec.workshopz.base.storage.adapters.BaseWSStorageAdapter;

public class UserWSAdapter extends BaseWSStorageAdapter{

	String methodName = "users";
	
	public UserWSAdapter(Context ctx) {
		super(ctx);
	}

	public void getEntity(String username, String password){
		String url = this.getBaseUrl() + "/" + this.methodName + "?username="+username+"&password="+password;
		Log.d("Volley", url);
		final String usr = username;
		final String pass = password;
		JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, this, this){
			@Override
		    public HashMap<String, String> getParams() {
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("username", usr);
				params.put("password", pass);
				return params;
			}
			
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

//	@Override
//	public String getBaseUrl(){
//		String url = super.getBaseUrl();
//		return url.replace("http://", "https://");
//		
//	}
	
	@Override
	public void onResponse(JSONObject response) {
		HashMap<String, String> data = new HashMap<String, String>();
		try {
			data.put("email", response.getString("email"));
			data.put("position", response.getString("position"));
			data.put("remote_id", response.getString("id"));
			data.put("name", response.getString("name"));
			data.put("phone_number", response.getString("phone_number"));
			
			this.mapper.onSuccess(data);
		} catch (JSONException e) {
			this.mapper.onError(this.context.getResources().getString(R.string.network_error));
		}
	}
}
