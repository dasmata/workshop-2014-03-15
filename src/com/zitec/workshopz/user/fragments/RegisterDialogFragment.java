package com.zitec.workshopz.user.fragments;


import java.util.ArrayList;
import java.util.HashMap;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zitec.workshopz.base.BaseActivity;
import com.zitec.workshopz.base.BaseEntity;
import com.zitec.workshopz.base.EntityResponseListener;
import com.zitec.workshopz.base.storage.Error;
import com.zitec.workshopz.user.RegisterValidator;
import com.zitec.workshopz.user.entities.User;
import com.zitec.workshopz.user.storage.adapters.UserDbAdapter;
import com.zitec.workshopz.user.storage.adapters.UserWSAdapter;
import com.zitec.workshopz.user.storage.mappers.UserMapper;
import com.zitec.workshopz.user.views.RegisterFragmentView;

public class RegisterDialogFragment extends DialogFragment {

	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        // Pick a style based on the num.
	        int style = DialogFragment.STYLE_NORMAL, theme = 0;
	        this.setStyle(style, theme);
	    }

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    	RegisterFragmentView view = new RegisterFragmentView(this);
	    	view.setInflater(inflater);
	    	view.initView();
	    	view.setActions();
	    	
	    	return view.getLayout();
	    }
	    
	    public SparseArray<String> checkForm(SparseArray<String> values){
	    	RegisterValidator validator = new RegisterValidator();
	    	validator.validate(this.getActivity(), values);
	    	return validator.getErrors();
	    }
	    
	    public void registerUser(HashMap<String, String> values){
	    	final UserMapper mapper = new UserMapper();
	    	mapper.setAdapter(new UserWSAdapter(this.getActivity()));
	    	mapper.setListener(new EntityResponseListener() {
				
				@Override
				public void onSuccess(ArrayList<BaseEntity> obj) {
					User usr = (User)obj.get(0);
					BaseActivity.identity = usr;
					BaseActivity act = ((BaseActivity)RegisterDialogFragment.this.getActivity());
					usr.setCurrentIdentity("true");
					try {
						mapper.setAdapter(new UserDbAdapter(act));
						mapper.save(usr);
					} catch (NameNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					act.loadWorkshops();
				}
				
				@Override
				public void onError(Error err) {
					((BaseActivity)RegisterDialogFragment.this.getActivity()).showGenericError(err);
				}
			});
	    	mapper.save(values);
	    }
}
