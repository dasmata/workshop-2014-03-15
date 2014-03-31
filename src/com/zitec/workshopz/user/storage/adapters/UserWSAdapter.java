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
	JSONObject userRegisterParams;
	
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

	public void save(HashMap<String, String> data){
		String url = this.getBaseUrl() + "/" + this.methodName;
		final HashMap<String, String> userParams = data;
		this.userRegisterParams = new JSONObject(userParams);
		JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, this.userRegisterParams, this, this){
			
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
			if(this.userRegisterParams == null){
				this.userRegisterParams = response;
			} else {
				this.userRegisterParams.put("id", response.getString("id"));
			}
			
			data.put("email", this.userRegisterParams.getString("email"));
			data.put("position", this.userRegisterParams.getString("position"));
			data.put("remote_id", this.userRegisterParams.getString("id"));
			data.put("name", this.userRegisterParams.getString("name"));
			data.put("phone_number", this.userRegisterParams.getString("phone_number"));
			
			this.mapper.onSuccess(data);
		} catch (JSONException e) {
			this.mapper.onError(this.context.getResources().getString(R.string.network_error));
		}
	}
}
