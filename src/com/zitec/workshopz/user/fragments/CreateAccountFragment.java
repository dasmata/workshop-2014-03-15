package com.zitec.workshopz.user.fragments;

import com.zitec.workshopz.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class CreateAccountFragment extends DialogFragment {
//	private EditText username;
//	private EditText password;
//	private EditText password_confirm;
	public CreateAccountFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_account, container);
//        mEditText = (EditText) view.findViewById(R.id.txt_your_name);
        getDialog().setTitle("Hello");

        return view;
    }

}
