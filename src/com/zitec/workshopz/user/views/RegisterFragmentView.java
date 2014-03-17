package com.zitec.workshopz.user.views;

import java.util.HashMap;

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
import com.zitec.workshopz.user.fragments.RegisterDialogFragment;

public class RegisterFragmentView extends BaseView {

	RegisterDialogFragment fragment;
	LinearLayout layout;
	LayoutInflater inflater;
	
	EditText email;
	EditText password;
	EditText confirmPassword;
	EditText name;
	EditText phoneNumber;
	EditText position;
	Button submit;
	
	public RegisterFragmentView(RegisterDialogFragment dialog){
		this.fragment = dialog;
	}
	
	public void setInflater(LayoutInflater infl){
		this.inflater = infl;
	}
	
	public View getLayout(){
		return this.layout;
	}
	
	@Override
	public void initView() {
		this.fragment.getDialog().setTitle(this.fragment.getResources().getString(R.string.register_dialog_title));
        this.layout = (LinearLayout)inflater.inflate(R.layout.register_fragment, null, false);
        this.email = (EditText) this.layout.findViewById(R.id.emailAddress);
        this.password = (EditText) this.layout.findViewById(R.id.password);
        this.confirmPassword = (EditText) this.layout.findViewById(R.id.confirmPassword);
        this.name = (EditText) this.layout.findViewById(R.id.name);
        this.position = (EditText) this.layout.findViewById(R.id.position);
        this.phoneNumber = (EditText) this.layout.findViewById(R.id.phoneNumber);
        this.submit = (Button) this.layout.findViewById(R.id.registerBtn);
	}

	@Override
	public void setActions() {
		this.submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RegisterFragmentView.this.submit();
			}
		});
		this.position.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				RegisterFragmentView.this.submit();
				return false;
			}
		});
	}
	
	protected HashMap<String, String> createUserValues(){
		HashMap<String, String> values = new HashMap<String, String>();
		
		values.put("email", this.email.getText().toString());
		values.put("password", this.password.getText().toString());
		values.put("phone_number", this.phoneNumber.getText().toString());
		values.put("position", this.position.getText().toString());
		values.put("name", this.name.getText().toString());
		
		return values;
	}
	
	public void submit(){
		SparseArray<String> values = new SparseArray<String>();
		values.append(R.id.emailAddress, this.email.getText().toString());
		values.append(R.id.password, this.password.getText().toString());
		values.append(R.id.confirmPassword, this.confirmPassword.getText().toString());
		values.append(R.id.phoneNumber, this.phoneNumber.getText().toString());
		values.append(R.id.position, this.position.getText().toString());
		values.append(R.id.name, this.name.getText().toString());
		
		SparseArray<String> errors = this.fragment.checkForm(values);
		if(errors.size() == 0){
			this.fragment.registerUser(this.createUserValues());
		} else {
			this.displayErrors(errors);
		}
	}
	
	public void displayErrors(SparseArray<String> errors){
		int nr = errors.size();
		EditText el;
		for(int i = 0; i < nr; i++){
			el = ((EditText)this.layout.findViewById(errors.keyAt(i)));
			el.setError(errors.valueAt(i));
			el.requestFocus();
		}
	}
}
