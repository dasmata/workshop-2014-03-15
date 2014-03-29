package com.zitec.workshopz.user.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zitec.workshopz.R;
import com.zitec.workshopz.user.activities.LoginActivity;
import com.zitec.workshopz.user.entities.User;
import com.zitec.workshopz.user.views.RegisterView;


public class RegisterDialog extends DialogFragment
{

	RegisterView registerView;
	SparseArray<String> errors;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		errors = new SparseArray<String>();
		registerView = new RegisterView(this);
		View view = registerView.getView(inflater, container);
		
		registerView.initView();
		registerView.setActions();
		
		return view;
	}
	
	protected boolean validate(SparseArray<String> data)
	{
		errors.clear();
		for (int index = 0; index < data.size(); ++index) {
			int key = data.keyAt(index);
			String value = data.valueAt(index);
			
			if (value.isEmpty()) {
				errors.put(key, "This field is required");
			}
		}
		
		if (!data.get(R.id.registerPassword).equals(data.get(R.id.registerConfirmPassword))) {
			errors.put(R.id.registerConfirmPassword, "Passwords does not match");
		}
		return errors.size() == 0;
	}
	
	protected User getUserFromArray(SparseArray<String> data)
	{
		User user = new User();
		user.setName(data.get(R.id.registerName));
		user.setEmail(data.get(R.id.registerEmail));
		user.setPhoneNumber(data.get(R.id.registerPhone));
		user.setPosition(data.get(R.id.registerPosition));
		user.setPassword(data.get(R.id.registerPassword));
		return user;
	}
	
	public void processRegister(SparseArray<String> data)
	{	
		if (!validate(data)) {
			registerView.setErrors(errors);
			return;
		}
		
		User user = getUserFromArray(data);
	
		Activity activity = getActivity();
		if (activity instanceof LoginActivity) {
			((LoginActivity) activity).register(user);
			dismiss();
		}
	}
	
	/*public static final String WORKSHOP_ID = "id";
	protected WorkshopDetailView fragmentView;
	protected boolean viewInitialized;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.fragmentView = new WorkshopDetailView(this);
		View view = this.fragmentView.getView(inflater, container);
		this.fragmentView.initView();
        
		return view;
	}
	
	@Override
	public void onStart(){
		super.onStart();
		Bundle args = this.getArguments();
        if (args != null) {
        	setWorkshopId(args.getInt(WORKSHOP_ID));
        }
	}
	
	public void setWorkshopId(int id){
		WorkshopzCacheAdapter cache = new WorkshopzCacheAdapter(this.getActivity());
		this.fragmentView.setWorkshop(cache.get(id));
		  
	}*/
}
