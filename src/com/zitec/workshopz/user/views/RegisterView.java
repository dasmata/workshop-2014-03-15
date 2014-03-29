package com.zitec.workshopz.user.views;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.zitec.workshopz.R;
import com.zitec.workshopz.base.BaseView;
import com.zitec.workshopz.user.fragments.RegisterDialog;

public class RegisterView extends BaseView
{

	RegisterDialog fragment;
	private LinearLayout view;
	private EditText name;
	private EditText email;
	private EditText phone;
	private EditText position;
	private EditText password;
	private EditText confirmPassword;
	private Button register;
	
	public RegisterView(RegisterDialog fragment)
	{
		this.fragment = fragment;
	}
	
	@Override
	public void initView()
	{
		name 			= (EditText)this.view.findViewById(R.id.registerName);
		email 			= (EditText)this.view.findViewById(R.id.registerEmail);
		phone 			= (EditText)this.view.findViewById(R.id.registerPhone);
		position 		= (EditText)this.view.findViewById(R.id.registerPosition);
		password 		= (EditText)this.view.findViewById(R.id.registerPassword);
		confirmPassword = (EditText)this.view.findViewById(R.id.registerConfirmPassword);
		register		= (Button)this.view.findViewById(R.id.register);
	}

	@Override
	public void setActions()
	{
		register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v)
			{
				RegisterView.this.processRegister();
			}
		});
		
		confirmPassword.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
			{
				if(event == null){
					return false;
				}
				RegisterView.this.processRegister();
				return false;
			}
		});
	}

	@SuppressLint("UseSparseArrays")
	protected void processRegister()
	{
		SparseArray<String> data = new SparseArray<String>();
		data.put(R.id.registerName, name.getText().toString());
		data.put(R.id.registerEmail, email.getText().toString());
		data.put(R.id.registerPhone, phone.getText().toString());
		data.put(R.id.registerPosition, position.getText().toString());
		data.put(R.id.registerPassword, password.getText().toString());
		data.put(R.id.registerConfirmPassword, confirmPassword.getText().toString());
		
		fragment.processRegister(data);
	}

	public View getView(LayoutInflater inflater, ViewGroup container)
	{
		View view = inflater.inflate(R.layout.fragment_register, null, false);
		this.view = (LinearLayout) view;
		return view;
	}

	public void setErrors(SparseArray<String> errors)
	{
		for (int index = 0; index < errors.size(); ++index) {
			int key = errors.keyAt(index);
			String error = errors.valueAt(index);
			EditText field = (EditText)view.findViewById(key);
			if (field != null) {
				field.setError(error);
			}
		}
	}

}
