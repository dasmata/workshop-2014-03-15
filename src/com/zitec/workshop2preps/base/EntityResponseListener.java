package com.zitec.workshop2preps.base;
import java.util.ArrayList;

import com.zitec.workshop2preps.base.storage.Error;

public interface EntityResponseListener {

	public void onSuccess(ArrayList<BaseEntity> obj);
	public void onError(Error err);
}
