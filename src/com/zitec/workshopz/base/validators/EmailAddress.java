package com.zitec.workshopz.base.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zitec.workshopz.R;

import android.content.Context;

public class EmailAddress extends BaseValidator {

	public static final String regExpn =
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                      +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                      +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                      +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                      +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
	
	public EmailAddress(Context ctx) {
		super(ctx);
		this.message = ctx.getResources().getString(R.string.invalid_email);
	}

	@Override
	public boolean validate(String value){
		CharSequence inputStr = value;
		Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		
		return matcher.matches();
	}
}
