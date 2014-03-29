package com.zitec.workshopz.user.fragments;

import com.zitec.workshopz.R;
import com.zitec.workshopz.user.activities.LoginActivity;
import com.zitec.workshopz.user.entities.User;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccountFragment extends DialogFragment {
	protected LoginActivity act;
	protected EditText email;
	protected EditText name;
	protected EditText phoneNumber;
	protected EditText position;
	protected EditText password;
	protected EditText password_confirm;
	protected Button submit;
	
	public CreateAccountFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_account, container);
        this.setElements(view);
        this.setActions(view);
        getDialog().setTitle("Create a new account");
        return view;
    }

	/**
	 * Set the screen elements
	 * @param view
	 */
	protected void setElements(View view) {
		this.submit = (Button) view.findViewById(R.id.create_account);	
		this.email = (EditText) view.findViewById(R.id.email);
		this.name = (EditText) view.findViewById(R.id.name);
		this.phoneNumber = (EditText) view.findViewById(R.id.phoneNumber);
		this.position = (EditText) view.findViewById(R.id.position);
		this.password = (EditText) view.findViewById(R.id.password);
		this.password_confirm = (EditText) view.findViewById(R.id.password_confirm);
	}
	
	/**
	 * Set the activity 
	 * @param act
	 */
	public void setActivity(LoginActivity act) {
		this.act = act;
	}
	
	/**
	 * Set the actions on specific scree (fragment) elements
	 */
	public void setActions(View view) {
		
		this.submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CreateAccountFragment.this.submitCreateAccount();
				
			}
		});
	}
	
	/**
	 * Submit create account 
	 */
	protected void submitCreateAccount() {
		User user = this.getUserFromSubmit();
		//TODO validate
		this.act.createAccount(user);
	}
	
	/**
	 * Get the User from the submited values
	 * @return User
	 */
	protected User getUserFromSubmit() {
		User user = new User();
		user.setEmail(this.email.getText().toString());
		user.setPassword(this.password.getText().toString());
		user.setPhoneNumber(this.phoneNumber.getText().toString());
		user.setPosition(this.position.getText().toString());
		user.setName(this.name.getText().toString());
		return user;
	}
}
