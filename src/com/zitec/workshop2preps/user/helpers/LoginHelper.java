package com.zitec.workshop2preps.user.helpers;

import java.util.ArrayList;

import com.zitec.workshop2preps.user.entities.User;
import com.zitec.workshop2preps.user.storage.adapters.UserDbAdapter;
import com.zitec.workshop2preps.user.storage.adapters.UserWSAdapter;
import com.zitec.workshop2preps.user.storage.mappers.UserMapper;
import com.zitec.workshop2preps.base.BaseEntity;
import com.zitec.workshop2preps.base.EntityResponseListener;
import com.zitec.workshop2preps.base.storage.Error;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

public class LoginHelper {
	
	static public void login(Context ctx, String username, String password, LoginListener lst){
		final LoginListener listener = lst;
		final UserMapper mapper = new UserMapper();
		final Context context = ctx;
		mapper.setAdapter(new UserWSAdapter(ctx));
		mapper.setListener(new EntityResponseListener() {
			
			@Override
			public void onError(Error err) {
				listener.onError(err);		
			}

			@Override
			public void onSuccess(ArrayList<BaseEntity> obj) {
				User usr = (User) obj.get(0);
				if(usr.getId() == null || "".equals(usr.getId())){
					try {
						mapper.setAdapter(new UserDbAdapter(context));
					} catch (NameNotFoundException e) {
						this.onError(new Error("Cound not initiate db adapter."));
						return;
					}
					LoginHelper.CheckUserListener checkUserListener = new LoginHelper().new CheckUserListener();
					checkUserListener.setCurrentUser(usr);
					checkUserListener.setMapper(mapper);
					checkUserListener.setLoginListener(listener);
					mapper.setListener(checkUserListener);
					mapper.checkUser(usr);
				}
				listener.onLogin(usr);
			}
		});
		mapper.getEntity(username, password);
		
	}
	
	public interface LoginListener {
		public void onLogin(User usr);
		public void onError(Error err);
	}
	
	protected class CheckUserListener implements EntityResponseListener{

		User usrToCheck;
		UserMapper mapper;
		LoginListener listener;
		
		public void setCurrentUser(User usr){
			this.usrToCheck = usr;
		}
		
		public void setMapper(UserMapper mapper){
			this.mapper = mapper;
		}
		
		
		public void setLoginListener(LoginListener listener){
			this.listener = listener;
		}
		
		@Override
		public void onSuccess(ArrayList<BaseEntity> obj) {
			if(obj.size() == 0){
				usrToCheck.setCurrentIdentity("true");
				mapper.save(usrToCheck);	
			} else {
				User usr = (User)obj.get(0);
				if("true".equals(usr.getCurrentIdentity())){
					this.listener.onLogin(usr);
				} else {
					usr.setCurrentIdentity("true");
					mapper.save(usr);
				}
			}
			
		}

		@Override
		public void onError(Error err) {
			// TODO Auto-generated method stub
			
		}
		
	}
}