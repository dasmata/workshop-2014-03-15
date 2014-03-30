package com.zitec.workshopz.user.dialogs;

import java.util.HashMap;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zitec.workshopz.R;
import com.zitec.workshopz.base.validators.NotEmpty;
import com.zitec.workshopz.user.activities.LoginActivity;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterDialog extends DialogFragment implements View.OnClickListener{
	protected EditText name;
	protected EditText email;
	protected EditText phone;
	protected EditText position;
	protected EditText password;
	protected Button create;
	protected Button cancel;
	protected View view;
	protected LoginActivity act;
	
	public void setActivity(LoginActivity act) {
		this.act = act;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.register_dialog, container);
        setCancelable(false);
        this.name = (EditText) view.findViewById(R.id.name);
        this.email = (EditText) view.findViewById(R.id.email);
        this.phone = (EditText) view.findViewById(R.id.phone);
        this.position = (EditText) view.findViewById(R.id.position);
        this.password = (EditText) view.findViewById(R.id.password);
        
        this.create = (Button) view.findViewById(R.id.create);
        this.cancel = (Button) view.findViewById(R.id.cancel);
        
        this.create.setOnClickListener(this);
        this.cancel.setOnClickListener(this);
        
        getDialog().setTitle("Create new user");

        return this.view;
    }


	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.cancel:
				dismiss();
			case R.id.create:
				createAccount();
		}
	}


	protected void createAccount(){
		SparseArray<String> values = new SparseArray<String>();
		values.append(this.name.getId(), this.name.getText().toString());
		values.append(this.email.getId(), this.email.getText().toString());
		values.append(this.phone.getId(), this.phone.getText().toString());
		values.append(this.position.getId(), this.position.getText().toString());
		values.append(this.password.getId(), this.password.getText().toString());

		if (!isFormValid(values)) {
			return;
		}
		makeWsRequest();		
	}
	
	protected void makeWsRequest() {
		String url = this.view.getResources().getString(R.string.ws_base_url) + "/users";
		Log.d("Volley", url);

		RequestQueue reqQueue = Volley.newRequestQueue(this.act);
		StringRequest req = new StringRequest(
			Request.Method.POST,
			url,
			new Response.Listener<String>(){
				@Override
				public void onResponse(String response) {
					Toast.makeText(RegisterDialog.this.act, RegisterDialog.this.getResources().getString(R.string.account_creted), Toast.LENGTH_SHORT).show();
					dismiss();
				}

			},
			new Response.ErrorListener(){
				@Override
				public void onErrorResponse(VolleyError error) {
					Toast.makeText(RegisterDialog.this.act, RegisterDialog.this.getResources().getString(R.string.account_failed), Toast.LENGTH_SHORT).show();
				}
			}
		) {
		    public HashMap<String, String> getParams() {
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("name", RegisterDialog.this.name.getText().toString());
				params.put("email", RegisterDialog.this.email.getText().toString());
				params.put("phone_number", RegisterDialog.this.phone.getText().toString());
				params.put("position", RegisterDialog.this.position.getText().toString());
				params.put("password", RegisterDialog.this.password.getText().toString());
				
				return params;
			}
		};
		reqQueue.add(req);
	}
	
	protected boolean isFormValid(SparseArray<String> values){
		boolean result = true;
		NotEmpty validator = new NotEmpty();
		int nr = values.size();
		for(int i = 0; i < nr; i++){
			if(!validator.validate(values.valueAt(i))){
				result = false;
				((EditText)this.view.findViewById(values.keyAt(i))).setError(this.getResources().getString(R.string.empty_field_label));
			}
		}
		return result;
	}
}
