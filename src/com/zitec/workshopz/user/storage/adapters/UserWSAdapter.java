package com.zitec.workshopz.user.storage.adapters;

import java.util.HashMap;





import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.zitec.workshopz.R;
import com.zitec.workshopz.base.storage.adapters.BaseWSStorageAdapter;
import com.zitec.workshopz.user.entities.User;

public class UserWSAdapter extends BaseWSStorageAdapter{

	String methodName = "users";
	
	public UserWSAdapter(Context ctx) {
		super(ctx);
	}

	public void getEntity(String username, String password){
		String url = this.getUrl() + "?username="+username+"&password="+password;
		Log.d("Volley", url);
		final String usr = username;
		final String pass = password;
		JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, this, this){
			@Override
		    public HashMap<String, String> getParams() {
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("username", usr);
				params.put("password", pass);
				Log.d("Volley", params.toString());
				return params;
			}
			
			@Override
			public HashMap<String, String> getHeaders(){
				return UserWSAdapter.this.getHeaders();
			}
		};
		this.queue.add(req);
	}
	

	protected String getUrl() {
		return this.getBaseUrl() + "/" + this.methodName;
	}
	
	
	public void save(HashMap<String, String> data) {
		String url = this.getUrl();
		final HashMap<String, String> request_data = data;
		Log.d("Volley", url);
		
		StringRequest postRequest = new StringRequest(Request.Method.POST, url, 
			    new Response.Listener<String>() 
			    {
			        @Override
			        public void onResponse(String response) {
			        	JSONObject json;
						try {
							json = new JSONObject(response);
				        	request_data.put("remote_id", json.getString("id"));
				        	Log.d("Response", request_data.toString());
				            UserWSAdapter.this.mapper.onSuccess(request_data);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							UserWSAdapter.this.mapper.onError(UserWSAdapter.this.context.getResources().getString(R.string.network_error));
						}
			        }
			    }, 
			    new Response.ErrorListener() 
			    {
			         @Override
			       public void onErrorResponse(VolleyError error) {
			             // error
			        	 UserWSAdapter.this.mapper.onError(UserWSAdapter.this.context.getResources().getString(R.string.network_error));
			       }
			    }
			) {     
			    @Override
			    protected HashMap<String, String> getParams() 
			    {  
			      return request_data;  
			    }
			};
		this.queue.add(postRequest);
	}

//	@Override
//	public String getBaseUrl(){
//		String url = super.getBaseUrl();
//		return url.replace("http://", "https://");
//		
//	}
	
	@Override
	public void onResponse(JSONObject response) {
		Log.d("REG", response.toString());
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
